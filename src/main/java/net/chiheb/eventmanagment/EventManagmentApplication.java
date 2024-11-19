package net.chiheb.eventmanagment;

import net.chiheb.eventmanagment.Dto.EventCreationDto;
import net.chiheb.eventmanagment.Entity.Category;
import net.chiheb.eventmanagment.Entity.Event;
import net.chiheb.eventmanagment.Entity.Organizator;
import net.chiheb.eventmanagment.Entity.Participant;
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

@SpringBootApplication
public class EventManagmentApplication {


    @Bean
    CommandLineRunner start(CategoryService categoryService,
                            OrganizatorService organizatorService,
                            PartcipantService partcipantService,
                            EventService eventService){
        return args -> {
            Participant participant = new Participant();
            participant.setEmail("jiko@gjha.com");
            participant.setName("said");
            participant.setPassword("ssvv255");
            partcipantService.addParticipant(participant);
            Category category = Category.builder()
                    .events(new ArrayList<>())
                    .categoryName("music").build();
            Category category1 = categoryService.createCategoty(category);
            Category category2 = categoryService.getCategoryById(1L);
            Organizator organizator = new Organizator();
            organizator.setPhoneNumber("0625145862");
            organizator.setWebsite("hhtp://mazaing.com");
            organizator.setName("mehdi");
            organizator.setEmail("mehdi@gmail.com");
            organizator.setPassword("kaiahhbs");
            Organizator organizator1 = organizatorService.createOrganizator(organizator);
            EventCreationDto event = EventCreationDto.builder()
                    .title("mazazin")
                    .date(LocalDate.now())
                    .MaxCapacity(500)
                    .description("20 eduthion du mazain")
                    .category(category2)
                    .organizator(organizator1)
                    .build();
            Event newevent = Event.builder().title("mazazin")
                    .date(LocalDate.now())
                    .MaxCapacity(500)
                    .description("20 eduthion du mazain")
                    .category(category1)
                    .organizator(organizator1)
                    .build();
            Event event1 = eventService.createEvent(event);
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
            System.out.println(category1.getEvents().size());
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(EventManagmentApplication.class, args);
    }


}
