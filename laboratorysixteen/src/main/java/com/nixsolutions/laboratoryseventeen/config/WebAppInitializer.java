package com.nixsolutions.laboratoryseventeen.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Filter;

@Configuration
public class WebAppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext container) {
		WebApplicationContext ctx = createWebApplicationContext(container);
		container.addListener(new ContextLoaderListener(ctx));
		registerFilters(container, ctx);
		registerSpringMVCDispatcherServlet(container, ctx);
	}
	
	private WebApplicationContext createWebApplicationContext(ServletContext container) {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.scan("com.nixsolutions.laboratoryseventeen.config");
		ctx.setServletContext(container);
		ctx.refresh();
		return ctx;
	}
	
	private void registerFilters(ServletContext container, WebApplicationContext ctx) {
		registerFilter(container, new RequestContextFilter());
		registerFilter(container, new DelegatingFilterProxy("springSecurityFilterChain"), "springSecurityFilterChain");
		registerFilter(container, buildConfigurableSiteMeshFilter(), "sitemesh");
	}

	private void registerFilter(ServletContext container, Filter filter, String... filterNames) {
		String filterName = filterNames.length > 0 ? filterNames[0] : filter.getClass().getSimpleName();
		container.addFilter(filterName, filter).addMappingForUrlPatterns(null, true, "/*");
	}
	
	private void registerSpringMVCDispatcherServlet(ServletContext container, WebApplicationContext ctx) {
		ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}
	
	private ConfigurableSiteMeshFilter buildConfigurableSiteMeshFilter() {
		return new ConfigurableSiteMeshFilter() {
			@Override
			protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
				builder
					.addDecoratorPath("/*", "/WEB-INF/view/page-template.jsp");
			}
		};
	}
}