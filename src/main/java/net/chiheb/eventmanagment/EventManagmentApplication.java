package net.chiheb.eventmanagment;

import net.chiheb.eventmanagment.Dto.EventCreationDto;
import net.chiheb.eventmanagment.Entity.Category;
import net.chiheb.eventmanagment.Entity.Event;
import net.chiheb.eventmanagment.Entity.Organizator;
import net.chiheb.eventmanagment.Entity.Participant;
import net.chiheb.eventmanagment.Repository.EventRepository;
import net.chiheb.eventmanagment.Service.CategoryService;
import net.chiheb.eventmanagment.Service.EventService;
import net.chiheb.eventmanagment.Service.OrganizatorService;
import net.chiheb.eventmanagment.Service.PartcipantService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class EventManagmentApplication {


    @Bean
    CommandLineRunner start(CategoryService categoryService,
                            OrganizatorService organizatorService,
                            PartcipantService partcipantService,
                            EventRepository eventRepository,
                            EventService eventService){
        return args -> {
            Participant participant = new Participant();
            participant.setEmail("x");
            participant.setName("x");
            participant.setPassword("x");

            Participant participant2 = new Participant();
            participant2.setEmail("x");
            participant2.setName("x");
            participant2.setPassword("x");

            Participant participant3 = new Participant();
            participant3.setEmail("x");
            participant3.setName("x");
            participant3.setPassword("x");

            partcipantService.addParticipant(participant);
            partcipantService.addParticipant(participant2);
            partcipantService.addParticipant(participant3);


            Category category =new Category();
            category.setCategoryName("music");
            Category category1 = categoryService.createCategoty(category);
            Category category2 = categoryService.getCategoryById(1L);
            Organizator organizator = new Organizator();
            organizator.setPhoneNumber("0625145862");
            organizator.setWebsite("hhtp://mazaing.com");
            organizator.setName("mehdi");
            organizator.setEmail("mehdi@gmail.com");
            organizator.setPassword("kaiahhbs");
            Organizator organizator1 = organizatorService.createOrganizator(organizator);
            EventCreationDto event = new EventCreationDto();
            event.setTitle("mazazin");
            event.setCategory(category);
            event.setOrganizator(organizator);
            event.setDescription("20 eduthion du mazain");
            event.setMaxCapacity(2);
            event.setDate(LocalDate.now());
            Event newevent = Event.builder()
                    .title("mazazin")
                    .date(LocalDate.now())
                    .MaxCapacity(500)
                    .description("20 eduthion du mazain")
                    .category(category1)
                    .organizator(organizator1)
                    .build();
            //organizator1.getEventSet().add(newevent);
            //organizator1.getEventSet().add(newevent);
            //category1.getEvents().add(newevent);
            //System.out.println(organizator1.getEventSet().size());

            Event event1 = eventService.createEvent(event);
            //organizator1.getEventSet().add(newevent);
            //System.out.println(organizatorService.getOrganizatorEvents(organizator1).size());
            //System.out.println(categoryService.getalleventsbycategory(category1).size());
            //System.out.println(participant2.getParticipantId());
           // eventService.addParticipantToEvent(event1,participant);
            //Participant p2 = new Participant();
            //partcipantService.addParticipant(p2);
           // eventService.addParticipantToEvent(event1,participant3);
            /*event1.getParticipants().add(participant3);
            participant3.getEventList().add(event1);
            eventRepository.saveAndFlush(event1);
            event1.getParticipants().add(participant2);
            participant2.getEventList().add(event1);
            System.out.println(event1.getParticipants());
            eventRepository.saveAndFlush(event1);*/
           // eventRepository.save(event1);
            //eventService.addParticipantToEvent(event1,p2);
            //System.out.println(category1.getEvents().size());
            //System.out.println(event1.getParticipants().size());
            /*System.out.println("gggg");
            System.out.println(organizatorService.getOrganizatorEvents(organizator).size());*/
            /*
            //category1.getEvents().add(event1);

            event1.setLocation("new location");
            eventService.updateEvent(event1);
            //Event e2 =  eventService.getOneEvent(event1.getEventid());
            System.out.println(categoryService.getalleventsbycategory(category1.getCategoryId()).size());
            eventService.getAllEventsByCategory(category1).forEach(event2 -> {
                System.out.println(event2.getDescription());
            });
            eventService.getAllEventByOrganizator(organizator1).forEach(event3 -> {
                System.out.println(event3.getMaxCapacity());
            });
            System.out.println("organizator event size = " + organizator1.getEventSet().size());
           // eventService.deleteEvent(event1);
            System.out.println(eventService.getAllEventByOrganizator(organizator1).size());
            //System.out.println(e2.getParticipants().size());
            eventService.addParticipantToEvent(event1, participant);
            System.out.println(event1.getParticipants().size());
            System.out.println(category1.getEvents().size());*/
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(EventManagmentApplication.class, args);
    }


}
