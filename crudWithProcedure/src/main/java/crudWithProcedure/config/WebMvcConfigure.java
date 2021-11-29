package crudWithProcedure.config;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import crudWithProcedure.dao.SubjectDAO;
import crudWithProcedure.dao.SubjectDAOImpl;

@Configuration
@EnableWebMvc
//@PropertySource("classpath:application.properties")
@ComponentScan("crudWithProcedure.*")

public class WebMvcConfigure implements WebMvcConfigurer {

  @Autowired ServletContext servletContext;
  @Autowired Environment environment;
  
  @Bean
  ServletContextTemplateResolver templateResolver() {
    ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
    templateResolver.setCacheable(false);
    templateResolver.setTemplateMode("HTML");
    templateResolver.setPrefix("/WEB-INF/views/");
    templateResolver.setSuffix(".html");

    return templateResolver;
  }

  @Bean
  public SpringTemplateEngine templateEngine() {
    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.setTemplateResolver(templateResolver());
    return templateEngine;
  }

  @Bean
  public ThymeleafViewResolver viewResolver() {
    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
    viewResolver.setTemplateEngine(templateEngine());
    viewResolver.setOrder(1);
    viewResolver.setCharacterEncoding("UTF-8");

    return viewResolver;
  }
  
  @Bean
  public DataSource getDataSource() {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
      dataSource.setUrl("jdbc:mysql://localhost:3306/subjectdb?enabledTLSProtocols=TLSv1.2");
      dataSource.setUsername("");
      dataSource.setPassword("");
       
      return dataSource;
  }
   
  @Bean
  public SubjectDAO getSubjectDAO() {
      return new SubjectDAOImpl(getDataSource());
  }
  
}

