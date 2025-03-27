package pl.krzysztofskul.smnsh2.project.device.modality;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModalityGenerator {
	
	private ModalityRepo modalityRepo;

	@Autowired
	private ModalityGenerator(ModalityRepo modalityRepo) {
		super();
		this.modalityRepo = modalityRepo;
	}

	public void createAndSaveToDbEssentialModalities() {
		
		List<Modality> modalityList = modalityRepo.findAll();
		for (Modality modality : modalityList) {
			modalityRepo.delete(modality);
		}
		
		modalityRepo.save(new Modality("AT", "Advenced Therapy", "ARTEEZO"));
		modalityRepo.save(new Modality("CT", "Computed Tomography", "SCOMPUTO"));
		modalityRepo.save(new Modality("MR", "Magnetic Resonance", "MAGNESO"));
		modalityRepo.save(new Modality("MI", "Molecular Imaging", "MOLECUO"));
		modalityRepo.save(new Modality("XPF", "Fluoroscopy", "FLUORO"));
		modalityRepo.save(new Modality("XPR", "Radiogrpahy", "ROENTGENO"));
		modalityRepo.save(new Modality("XPM", "Mammography", "MAMMO"));
		modalityRepo.save(new Modality("XPU", "Urology", "UROLO"));
		modalityRepo.save(new Modality("US", "Ultrasound", "SONO"));
		modalityRepo.save(new Modality("RO", "Radiation Oncology", "RADONCO"));
		modalityRepo.save(new Modality("LD", "Laboratory Diagnostic", "LABO"));
		modalityRepo.save(new Modality("SY", "Imaging Software", "SOIGO"));
		modalityRepo.save(new Modality("SU", "Surgical Solutions", "SURGEO"));
		
	}
	
}
