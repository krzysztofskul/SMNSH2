package pl.krzysztofskul.sensit.smnsh.logger;

import java.time.LocalDateTime;

import pl.krzysztofskul.sensit.smnsh.project.Project;
import pl.krzysztofskul.sensit.smnsh.user.User;

public interface LoggerServiceInterface {

	Log createLog(User subject, Project object, LogTypeEnum action, LocalDateTime time);
	
}
