package pl.krzysztofskul.smnsh2.importdata;

import java.util.List;

import org.springframework.stereotype.Service;

import pl.krzysztofskul.smnsh2.project.Project;
import pl.krzysztofskul.smnsh2.project.training.Training;

@Service
public class ImportDataTraining {

	public Project importDataTraining(Project project, String trainingFilePath) {
		
		XlsCellReader xlsCellReader = XlsCellReader.getXlsCellReader(trainingFilePath);
		List<String> trainingList = xlsCellReader.getCellsValuesInRow(trainingFilePath, "Szkolenia", 9, 2, true);
		for (String training : trainingList) {
			project.getInstallation().getDeviceInstance().addTraining(new Training(training));
		}
		
		return project;
	}
}
