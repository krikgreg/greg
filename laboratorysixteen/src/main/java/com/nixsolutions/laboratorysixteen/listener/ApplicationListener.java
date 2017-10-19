//package com.nixsolutions.laboratorysixteen.listener;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import com.nixsolutions.laboratorysixteen.Constants;
//import com.nixsolutions.laboratorysixteen.config.AppConfig;
//import com.nixsolutions.laboratorysixteen.service.UserManagementService;
//import com.nixsolutions.laboratorysixteen.service.impl.UserManagementServiceImpl;
//
//public class ApplicationListener implements ServletContextListener {
//
//	@Override
//	public void contextInitialized(ServletContextEvent sce) {
//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//		UserManagementService service = context.getBean(UserManagementService.class);
//		sce.getServletContext().setAttribute(Constants.USER_MANAGEMENT_SERVICE, service);
//	}
//
//	@Override
//	public void contextDestroyed(ServletContextEvent sce) {
//	}
//}
