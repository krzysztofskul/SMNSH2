package pl.krzysztofskul.smnsh2.logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

//import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import pl.krzysztofskul.smnsh2.project.Project;
import pl.krzysztofskul.smnsh2.project.ProjectService;
import pl.krzysztofskul.smnsh2.user.User;
import pl.krzysztofskul.smnsh2.user.UserService;

@SpringBootTest
class LoggerServiceTest {
	
	@Autowired
	private LoggerService loggerServiceProject;
	@Autowired
	private UserService userService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private LoggerRepo loggerRepo;
 	
	@Test
	void testCreateLog() {
		
		User userTest = new User();
		userTest.setNameFirst("Mr. Lorem");
		userService.save(userTest);
		
		Project projectTest = new Project();
		projectTest.setName("Ipsum project");
		projectService.save(projectTest);
		
		Log logTestLogIn = loggerServiceProject.createLog(userTest, null, LogTypeEnum.USER_LOGIN, LocalDateTime.now());
//		System.out.println("LOG: "+logTestLogIn.getTime().toLocalDate()+" "+logTestLogIn.getTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"))+" | "+logTestLogIn.getAction().getNamePl()+" "+logTestLogIn.getSubject().getNameFirst());
		Log logTestProjectCreate = loggerServiceProject.createLog(userTest, projectTest, LogTypeEnum.PROJECT_CREATE, LocalDateTime.now());		
//		System.out.println("LOG: "+logTestProjectCreate.getTime().toLocalDate()+" "+logTestProjectCreate.getTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"))+" | "+logTestProjectCreate.getAction().getNamePl()+" "+ logTestProjectCreate.getProject().getName() +" przez "+logTestProjectCreate.getSubject().getNameFirst());
		
		Log logTestLogInSaved = loggerRepo.save(logTestLogIn);
//		System.out.println("Log saved with id: "+logTestLogInSaved.getId());
//		System.out.println("Log saved with user: "+logTestLogInSaved.getSubject().getNameFirst());
		
		Log logTestProjectCreateSaved = loggerRepo.save(logTestProjectCreate);
//		System.out.println("Log saved with id: "+logTestProjectCreateSaved.getId());
//		System.out.println("Log saved with user: "+logTestProjectCreateSaved.getSubject().getNameFirst());
//		System.out.println("Log saved with project: "+logTestProjectCreateSaved.getProject().getName());
		
		assertTrue(logTestLogInSaved.getId() != null);
		assertTrue(logTestProjectCreateSaved.getId() != null);

	}

}
