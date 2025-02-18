package dk.schioler.event.main.test.data;


import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dk.schioler.configuration.EventBaseConfiguration;
import dk.schioler.event.base.dao.EventDAO;
import dk.schioler.event.base.dao.EventTemplateDAO;
import dk.schioler.event.base.dao.criteria.EventTemplateCriteria;
import dk.schioler.event.base.entity.Event;
import dk.schioler.event.base.entity.EventTemplate;

public class GenerateTestEventsMain {

   static {
      System.getProperties().setProperty("event.env", "dev1");
   }

   private static Logger logger = LoggerFactory.getLogger(GenerateTestEventsMain.class);


   static EventTemplateDAO eventTemplateDAO;

   static EventDAO eventDAO;

   static ApplicationContext ctx;

   public GenerateTestEventsMain() {

   }

   public static void main(String[] args) {
      try {
         int count = 1000;
         int loginId = 2;
         ctx = new AnnotationConfigApplicationContext(EventBaseConfiguration.class);

         eventTemplateDAO = ctx.getBean(EventTemplateDAO.class);
         eventDAO = ctx.getBean(EventDAO.class);

         GenerateTestEventsMain self = new GenerateTestEventsMain(); 

         self.generate(count, loginId);
      } catch (Exception e) {
         logger.error(e.getMessage(), e);
      }
   }

   public void generate(int count, int loginId) {
      EventTemplateCriteria crit = new EventTemplateCriteria();
      crit.addLoginId(loginId);

      List<EventTemplate> eventTemplates = eventTemplateDAO.retrieve(crit, 0);

      UniformRandomProvider rng = RandomSource.XO_RO_SHI_RO_128_PP.create();

      for (int i = 0; i < count; i++) {
         int nextInt = rng.nextInt(0, eventTemplates.size());

         EventTemplate tmpl = eventTemplates.get(nextInt);
         Event randomEvent = generateRandomEvent(tmpl);

         eventDAO.insert(randomEvent);
      }
   }

   public Event generateRandomEvent(EventTemplate template) {
      Event e = new Event();
      e.setLoginId(template.getLoginId());
      e.setParentId(template.getId());
      e.setName(template.getName());
      e.setDescription(template.getDescription());
      e.setDose(template.getDose());
      e.setUnit(template.getUnit());

      UniformRandomProvider rng = RandomSource.XO_RO_SHI_RO_128_PP.create();

      int year = rng.nextInt(2021, 2024);
      int month = rng.nextInt(1, 13);
      int day = rng.nextInt(1, 28);
      int hour = rng.nextInt(0, 12);
      int minute = rng.nextInt(1, 60);
      int sec = rng.nextInt(1, 60);

      LocalDateTime eventTS = LocalDateTime.of(year, month, day, hour, minute, sec);

      e.setEventTS(eventTS);
      return e;

   }
}
