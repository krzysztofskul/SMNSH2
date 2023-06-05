package pl.krzysztofskul.sensit.smnsh.project.remark;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemarkService {

	private RemarkRepo remarkRepo;

	/**
	 * @param remarkRepo
	 */
	@Autowired
	public RemarkService(RemarkRepo remarkRepo) {
		this.remarkRepo = remarkRepo;
	}
	
	public List<Remark> loadAllByProjectId(Long projectId) {
		return remarkRepo.findAllByProjectId(projectId);
	}
	
	public Remark saveAndReturn(Remark remark) {
		return remarkRepo.save(remark);
	}

	public void save(Remark remark) {
		remarkRepo.save(remark);
	}
	
}
