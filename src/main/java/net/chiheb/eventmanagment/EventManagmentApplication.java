package net.chiheb.eventmanagment;

import net.chiheb.eventmanagment.Dto.EventCreationDto;
import net.chiheb.eventmanagment.Dto.EventCreationFrontDto;
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
import java.time.LocalDate;
import java.time.LocalDateTime;

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
            participant.setEmail("x1");
            participant.setName("x");
            participant.setPassword("x");
            participant.setAddrese("Temara nassim 1 ");

            Participant participant2 = new Participant();
            participant2.setEmail("elmahdichiheb19@gmail.com");
            participant2.setName("Kamal");
            participant2.setPassword("x");
            participant2.setAddrese("Temara nassim 1 ");

            Participant participant3 = new Participant();
            participant3.setEmail("x3");
            participant3.setName("x");
            participant3.setPassword("x");
            participant3.setAddrese("Temara nassim 1 ");

            partcipantService.addParticipant(participant);
            partcipantService.addParticipant(participant2);
            partcipantService.addParticipant(participant3);


            Category category =new Category();
            category.setCategoryName("Music");
            Category techcat =new Category();
            techcat.setCategoryName("Tech");
            Category geming =new Category();
            geming.setCategoryName("Gaming");
            Category education =new Category();
            education.setCategoryName("Education");
            Category medecine =new Category();
            medecine.setCategoryName("Medecine");


            Category category1 = categoryService.createCategoty(category);
            Category category2 = categoryService.getCategoryById(1L);
            Organizator organizator = new Organizator();
            organizator.setPhoneNumber("0625145862");
            organizator.setWebsite("hhtp://mazaing.com");
            organizator.setName("mehdi");
            organizator.setEmail("mehdi@gmail.com");
            organizator.setPassword("kaiahhbs");
            Organizator organizator1 = organizatorService.createOrganizator(organizator);
            System.out.println(organizator1);

            EventCreationFrontDto event = new EventCreationFrontDto();
            event.setTitle("mawazin");
            event.setCategoryid(category.getCategoryId());
            event.setOrganizatorid(organizator.getId());
            event.setDescription("20 eduthion du mazain");
            event.setLocation("Rabat hotel sofitel 21 ");
            event.setEventStartTime(LocalDateTime.now().plusDays(5));
            event.setEventEndTime(LocalDateTime.now().plusDays(8));
            event.setMaxCapacity(2);
            event.setImageUrl("https://www.vfairs.com/wp-content/uploads/2024/09/powerful-speaker-session-in-a-event-Freepik.jpeg");
            event.setDate(LocalDate.now());
            System.out.println(event.getEventStartTime());
            System.out.println(event.getEventEndTime());
            //organizator1.getEventSet().add(newevent);
            //organizator1.getEventSet().add(newevent);
            //category1.getEvents().add(newevent);
            //System.out.println(organizator1.getEventSet().size());
            Event event1 = eventService.createEventFront(event);
            categoryService.createCategoty(techcat);
            categoryService.createCategoty(geming);
            categoryService.createCategoty(education);
            categoryService.createCategoty(medecine);


            EventCreationFrontDto event2 = new EventCreationFrontDto();
            event2.setTitle("mawazin");
            event2.setCategoryid(techcat.getCategoryId());
            event2.setOrganizatorid(organizator.getId());
            event2.setDescription("20 eduthion du mazain");
            event2.setLocation("Rabat hotel sofitel 21 ");
            event2.setEventStartTime(LocalDateTime.now().plusDays(5));
            event2.setEventEndTime(LocalDateTime.now().plusDays(8));
            event2.setMaxCapacity(2);
            event2.setImageUrl("https://www.vfairs.com/wp-content/uploads/2024/09/powerful-speaker-session-in-a-event-Freepik.jpeg");
            event2.setDate(LocalDate.now());
             eventService.createEventFront(event2);
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(EventManagmentApplication.class, args);
    }


}
