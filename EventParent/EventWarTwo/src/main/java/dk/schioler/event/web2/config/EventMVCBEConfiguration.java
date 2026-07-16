package dk.schioler.event.web2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc
@PropertySource("classpath:/event-dev.properties")
@ComponentScan(" org.springframework.jdbc , org.springframework.transaction")
@ComponentScan("dk.schioler.shared.bits, dk.schioler.shared.security, dk.schioler.shared.timeline")
@ComponentScan( "dk.schioler.event.base" )
public class EventMVCBEConfiguration implements WebMvcConfigurer {
   
//   @Bean
//   public InternalResourceViewResolver resolver() {
//      InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setViewClass(JstlView.class);
//
//      resolver.setPrefix("/WEB-INF/views/");
//      resolver.setSuffix(".jsp");
//      return resolver;
//   }

   
   
//   @Override
//   public void addResourceHandlers(ResourceHandlerRegistry registry) {
//      registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//   }
}