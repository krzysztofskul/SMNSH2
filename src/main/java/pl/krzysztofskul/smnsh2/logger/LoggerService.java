package pl.krzysztofskul.smnsh2.logger;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.krzysztofskul.smnsh2.project.Project;
import pl.krzysztofskul.smnsh2.user.User;

@Service
public class LoggerService implements LoggerServiceInterface {

	private LoggerRepo loggerRepo;
	
	@Autowired
	public LoggerService(LoggerRepo loggerRepo) {
		this.loggerRepo = loggerRepo;
	}
	
	@Override
	public Log createLog(User subject, Project object, LogTypeEnum action, LocalDateTime time) {
		return new Log(subject, object, action, time);
	}
	
	public void save(Log log) {
		loggerRepo.save(log);
	}

	public List<Log> loadAll() {
		return loggerRepo.findAll();
	}
	
}
