package convertDateFomart.config;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
  
  public WebInitializer() {
    super();
  }

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[] { WebMvcConfigure.class};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[] { WebMvcConfigure.class };
  }

  @Override
  protected String[] getServletMappings() {    
    return new String[] {"/"};
  }
  
}

