package com.nixsolutions.laboratorysixteen.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import com.nixsolutions.laboratorysixteen.entity.converter.FromStringIdToRoleConverter;
import com.nixsolutions.laboratorysixteen.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.nixsolutions.laboratorysixteen")
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

	@Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		 source.setBasenames("classpath:messages");
		 source.setUseCodeAsDefaultMessage(true);
		 source.setDefaultEncoding("UTF-8");
		return source;
	}
	
	@Bean
	public UserDetailsService getUserDetailsService(){
	    return new UserDetailsServiceImpl();
	}
	
	@Override
    public void addFormatters(FormatterRegistry formatterRegistry)
    {
        formatterRegistry.addConverter(getMyConverter());
    }
	
	@Bean
    public FromStringIdToRoleConverter getMyConverter()
    {
        return new FromStringIdToRoleConverter();
    }
	
	
}
