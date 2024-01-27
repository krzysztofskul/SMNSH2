package pl.krzysztofskul.smnsh2.logger;

import java.time.LocalDateTime;

import pl.krzysztofskul.smnsh2.project.Project;
import pl.krzysztofskul.smnsh2.user.User;

public interface LoggerServiceInterface {

	Log createLog(User subject, Project object, LogTypeEnum action, LocalDateTime time);
	
}
