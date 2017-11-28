package com.nixsolutions.laboratoryeighteen.config;

import javax.servlet.Filter;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Configuration
public class WebAppInitializer extends
AbstractAnnotationConfigDispatcherServletInitializer  {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebConfig.class, CustomWebSecurityConfigurerAdapter.class, RestTemplateConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	@Override
	protected Filter[] getServletFilters() {
		return new Filter[]{new RequestContextFilter(), new DelegatingFilterProxy("springSecurityFilterChain"), buildConfigurableSiteMeshFilter() };
	}

	private ConfigurableSiteMeshFilter buildConfigurableSiteMeshFilter() {
		return new ConfigurableSiteMeshFilter() {
			@Override
			protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
				builder.addDecoratorPath("/*", "/WEB-INF/view/page-template.jsp");
			}
		};
	}
}