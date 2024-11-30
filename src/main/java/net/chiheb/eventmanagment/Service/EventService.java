package net.chiheb.eventmanagment.Service;

import net.chiheb.eventmanagment.Dto.EventCreationDto;
import net.chiheb.eventmanagment.Dto.EventCreationFrontDto;
import net.chiheb.eventmanagment.Dto.ParticipantEventEnrolDto;
import net.chiheb.eventmanagment.Dto.mapper.EventPartcipantMapper;
import net.chiheb.eventmanagment.Entity.*;
import net.chiheb.eventmanagment.Exeption.AleardyEnrolled;
import net.chiheb.eventmanagment.Exeption.CapacityNotEnoughExeption;
import net.chiheb.eventmanagment.Repository.EventParticipantRepository;
import net.chiheb.eventmanagment.Repository.EventRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class EventService {

    private EventRepository eventRepository;
    private LisAttenteService lisAttenteService;
    private OrganizatorService organizatorService;
    private CategoryService categoryService;
    private PartcipantService partcipantService;
    private EventParticipantRepository eventParticipantRepository;
    private EmailService  emailService;

    public EventService(EventRepository eventRepository,
                        LisAttenteService lisAttenteService,
                        @Lazy OrganizatorService organizatorService,
                        @Lazy CategoryService categoryService,
                        @Lazy PartcipantService partcipantService,
                        EventParticipantRepository eventParticipantRepository ,
                        EmailService emailService1){
        this.eventRepository = eventRepository;
        this.lisAttenteService = lisAttenteService;
        this.organizatorService = organizatorService;
        this.categoryService = categoryService;
        this.partcipantService = partcipantService;
        this.eventParticipantRepository = eventParticipantRepository;
        this.emailService = emailService1;
    }

    @Transactional
    public Event createEvent(EventCreationDto eventCreationDto){
        Event e = new Event();
        e.setTitle(eventCreationDto.getTitle());
        e.setDescription(eventCreationDto.getDescription());
        e.setCategory(eventCreationDto.getCategory());
        e.setMaxCapacity(eventCreationDto.getMaxCapacity());
        e.setParticipants(new ArrayList<>());
        e.setOrganizator(eventCreationDto.getOrganizator());
        e.setDate(eventCreationDto.getDate());
        e.getCategory().getEvents().add(e);
        /*e.getCategory().addEvent(e);*/
        e.getOrganizator().getEventSet().add(e);

        return eventRepository.saveAndFlush(e);
    }
    /*
        *  Function for creating events by an Organizator
     * */
    @Transactional
    public Event createEventFront(EventCreationFrontDto eventCreationDto){
        Event e = new Event();
        e.setTitle(eventCreationDto.getTitle());
        e.setDescription(eventCreationDto.getDescription());
        Category category = categoryService.getCategoryById(eventCreationDto.getCategoryid());
        e.setCategory(category);
        e.setImageUrl(eventCreationDto.getImageUrl());
        e.setMaxCapacity(eventCreationDto.getMaxCapacity());
        e.setLocation(eventCreationDto.getLocation());
        e.setParticipants(new ArrayList<>());
        Organizator organizator = organizatorService.getOrganizatorById(eventCreationDto.getOrganizatorid());
        e.setOrganizator(organizator);
        e.setDate(eventCreationDto.getDate());
        e.getCategory().getEvents().add(e);
        /*e.getCategory().addEvent(e);*/
        e.getOrganizator().getEventSet().add(e);

        return eventRepository.saveAndFlush(e);
    }


    public Optional<Event> getEventById(Long id){
        return eventRepository.findById(id);
    }

    /*
    * Update an event
    * */
     public Event updateEvent(Event event){
        return eventRepository.save(event);
    }


    //@Transactional
   /* public void addParticipantToEvent(Long eventid, Participant participant){
        Event event = getEventById(eventid).get();
        System.out.println(event.getParticipants().size());
        if(checkifcapacityenough(event)){
            //System.out.println(event.getParticipants());
            if(checkifparticipantexistsonevent(event,participant)){
                System.out.println("Participant already exists");
                throw new AleardyEnrolled("aleady enrollred");
            }else {
                //System.out.println(event);
                event.getParticipants().add(participant);
                participant.getEventList().add(event);
                eventRepository.saveAndFlush(event);
            }

        }else {
            lisAttenteService.addParticipantToWaitingListofEvent(participant,event);
            throw new CapacityNotEnoughExeption("Capacity not enough");
        }
    }*/
    public EventParticipant addParticipantToEventFront(Long eventid, ParticipantEventEnrolDto participant){
        Event event = getEventById(eventid).get();
        Participant participant1 = partcipantService.getParticipantById(participant.getParticipantId());
        //System.out.println(event.getParticipants().size());
        if(checkifcapacityenough(event)){
            //System.out.println(event.getParticipants());
            if(checkifparticipantexistsonevent(event,participant1)){
                System.out.println("Participant already exists");
                throw new AleardyEnrolled("aleady enrollred");
            }else {
                //System.out.println(event);

                /*event.getParticipants().add(participant1);
                participant1.getEventList().add(event);*/
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                String formatDateTime = now.format(formatter);
                EventParticipant eventParticipant = new EventParticipant();
                eventParticipant.setParticipant(participant1);
                eventParticipant.setEvent(event);
                eventParticipant.setPurchaseat(
                        LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),formatter)
                );
                eventParticipant.setConfirmad(false);
                EventPartcipantPk eventPartcipantPk = new EventPartcipantPk();
                eventPartcipantPk.setEventId(event.getEventid());
                eventPartcipantPk.setParticipantId(participant1.getId());
                eventParticipant.setEventPartcipantPk(eventPartcipantPk);
               /* event.getParticipants().add(eventParticipant);
                participant1.getEventList().add(eventParticipant);*/

                EventParticipant eventParticipant1 = eventParticipantRepository.saveAndFlush(eventParticipant);

                event.getParticipants().add(eventParticipant1);
                participant1.getEventList().add(eventParticipant1);

                String token = emailService.saveConfirmationToken(eventParticipant1);
                String link = "http://localhost:8081/api/v1/event/confirm?token=" + token;
                //emailService.sendEmail(participant1.getEmail(), buildEmail(participant1.getName(), link));
                return eventParticipant1;
                //eventRepository.saveAndFlush(event);
            }

        }else {
            lisAttenteService.addParticipantToWaitingListofEvent(participant1,event);
            throw new CapacityNotEnoughExeption("Capacity not enough");
        }
    }

    public void confirmparticipation(EventParticipant eventParticipant){

    }
    public boolean checkifparticipantexistsonevent(Event event , Participant participant){
        //List<Participant> participants = event.getParticipants();
        EventParticipant eventParticipant = eventParticipantRepository.
                findEventParticipantByEventAndParticipant(event,participant);
        /*List<EventParticipant> eventParticipants = eventParticipantRepository.findAll();
        AtomicBoolean exist = new AtomicBoolean(false);
        eventParticipants.forEach(eventParticipant -> {
            if(Objects.equals(eventParticipant.getEvent().getEventid(), event.getEventid())&&
            Objects.equals(eventParticipant.getParticipant().getId() , participant.getId())){
                exist.set(true);
            }
        });*/
        if (eventParticipant == null){
            return false;
        }else return true;
        //return exist.get();
    }
    public boolean checkifcapacityenough(Event event){
        return event.getParticipants().size() < event.getMaxCapacity();
    }
    public List<Event> getAllEventsByCategory(String category) {
        Category category1 = categoryService.getCategoryByName(category);
        return eventRepository.findAllByCategory(category1);
    }
    public List<Event> getAllEventByOrganizator(Organizator organizator){
        return eventRepository.findAllByOrganizator(organizator);
    }
    public void deleteEvent(Event event ){
        try {
            eventRepository.delete(event);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public List<Event> getallevents(){
        return eventRepository.findAll();
    }

    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your Participation</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering for the event. Please click on the below link to confirm your participation </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 24 hours. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}


