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
            participant2.setEmail("elmahdi.chiheb@uit.ac.ma");
            participant2.setName("Kamal");
            participant2.setPassword("x123456");
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
            event.setTitle("Mawazin");
            event.setCategoryid(category.getCategoryId());
            event.setOrganizatorid(organizator.getId());
            event.setDescription("20 eduthion du mazain");
            event.setLocation("Rabat hotel sofitel 21 ");
            event.setEventStartTime(LocalDateTime.now().plusDays(3).plusHours(12));
            event.setEventEndTime(LocalDateTime.now().plusDays(3).plusHours(18));
            event.setMaxCapacity(2);
            event.setImageUrl("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/0b/ee/54/fb/mawazine-festival.jpg?w=1200&h=-1&s=1");
            event.setDate(LocalDate.now().plusDays(3));
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
            event2.setTitle("Education methodolgy with ai");
            event2.setOrganizatorid(organizator.getId());
            event2.setCategoryid(education.getCategoryId());
            event2.setOrganizatorid(organizator.getId());
            event2.setDescription("Using ai in education ");
            event2.setLocation("Marrakech theatre avenu ibn khold");
            event2.setEventStartTime(LocalDateTime.now().plusDays(5).plusHours(19));
            event2.setEventEndTime(LocalDateTime.now().plusDays(20).plusHours(20));
            event2.setMaxCapacity(2);
            event2.setImageUrl("https://images.ctfassets.net/x0wnv07j8mtt/ab8da628-fb41-5265-7b7a-3837d230ebb3/80878197369e64bbe06b97281b79f5f6/ab8da628-fb41-5265-7b7a-3837d230ebb3");
            event2.setDate(LocalDate.now().plusDays(11));
             eventService.createEventFront(event2);



            EventCreationFrontDto even = new EventCreationFrontDto();
            even.setTitle("Tomorowlamd");
            even.setOrganizatorid(organizator.getId());
            even.setCategoryid(category.getCategoryId());
            even.setOrganizatorid(organizator.getId());
            even.setDescription("Edition 20 of Tomowoland");
            even.setLocation("irlannd dublin");
            even.setEventStartTime(LocalDateTime.now().plusDays(20).plusHours(12));
            even.setEventEndTime(LocalDateTime.now().plusDays(20).plusHours(52));
            even.setMaxCapacity(2);
            even.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/e/ed/Tomorrowland2016mainstage.jpg/1200px-Tomorrowland2016mainstage.jpg");
            even.setDate(LocalDate.now().plusDays(20));
            eventService.createEventFront(even);

            EventCreationFrontDto eve = new EventCreationFrontDto();
            eve.setTitle("Angular Material Courde");
            eve.setOrganizatorid(organizator.getId());
            eve.setCategoryid(techcat.getCategoryId());
            eve.setOrganizatorid(organizator.getId());
            eve.setDescription("Angular worckshop in swiss");
            eve.setLocation("Abu dahbi riad 25 ");
            eve.setEventStartTime(LocalDateTime.now().plusDays(2).plusHours(12));
            eve.setEventEndTime(LocalDateTime.now().plusDays(2).plusHours(15));
            eve.setMaxCapacity(20);
            eve.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSFvBJUZLKtSOD7Obbec1I4x2JzTPpIYvpyppXJIKoQcxuGQKQO_GB9TOCjc6TOtxp11Z8&usqp=CAU");
            eve.setDate(LocalDate.now().plusDays(2));
            eventService.createEventFront(eve);

            EventCreationFrontDto ev = new EventCreationFrontDto();
            ev.setTitle("Medical Event ");
            ev.setOrganizatorid(organizator.getId());
            ev.setCategoryid(medecine.getCategoryId());
            ev.setOrganizatorid(organizator.getId());
            ev.setDescription("New method for curing diabities");
            ev.setLocation("Uit ibn tofel fs science ");
            ev.setEventStartTime(LocalDateTime.now().plusDays(10).plusHours(8));
            ev.setEventEndTime(LocalDateTime.now().plusDays(10).plusHours(11));
            ev.setMaxCapacity(20);
            ev.setImageUrl("https://within3.com/wp-content/uploads/2022/06/W3_02.04.22_PurposeofMedConferences.jpg");
            ev.setDate(LocalDate.now().plusDays(10));
            eventService.createEventFront(ev);

        };
    }
    public static void main(String[] args) {
        SpringApplication.run(EventManagmentApplication.class, args);
    }


}
