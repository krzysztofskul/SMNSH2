package pl.krzysztofskul.sensit.smnsh.project.device.modality;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModalityService {

	ModalityRepo modalityRepo;

	/**
	 * Constructor
	 */
	@Autowired
	public ModalityService(ModalityRepo modalityRepo) {
		this.modalityRepo = modalityRepo;
	}
	
	public List<Modality> loadAll() {
		return modalityRepo.findAll();
	}
	
	public Modality loadByCode(String code) {
		return modalityRepo.findByCode(code);
	}
}
