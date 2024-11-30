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

            Participant participant2 = new Participant();
            participant2.setEmail("elmahdichiheb19@gmail.com");
            participant2.setName("Kamal");
            participant2.setPassword("x");

            Participant participant3 = new Participant();
            participant3.setEmail("x3");
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
            System.out.println(organizator1);
            EventCreationFrontDto event = new EventCreationFrontDto();
            event.setTitle("mazazin");
            event.setCategoryid(category.getCategoryId());
            event.setOrganizatorid(organizator.getId());
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

            Event event1 = eventService.createEventFront(event);

        };
    }
    public static void main(String[] args) {
        SpringApplication.run(EventManagmentApplication.class, args);
    }


}
