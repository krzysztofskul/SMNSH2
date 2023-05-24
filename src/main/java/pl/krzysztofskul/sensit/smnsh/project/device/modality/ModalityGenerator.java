package pl.krzysztofskul.sensit.smnsh.project.device.modality;

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
		
		modalityRepo.save(new Modality("AT", "Advenced Therapy", "ADVANZO"));
		modalityRepo.save(new Modality("CT", "Computed Tomography", "COMPUTO"));
		modalityRepo.save(new Modality("MR", "Magnetic Resonance", "MAGNESO"));
		modalityRepo.save(new Modality("MI", "Molecular Imaging", "MOLECUO"));
		modalityRepo.save(new Modality("XPF", "Fluoroscopy", "FLUORO"));
		modalityRepo.save(new Modality("XPR", "Radiogrpahy", "ROENTGENO"));
		modalityRepo.save(new Modality("XPM", "Mammography", "MAMMO"));
		modalityRepo.save(new Modality("XPU", "Urology", "UROLO"));
		modalityRepo.save(new Modality("US", "Ultrasound", "SONO"));
		modalityRepo.save(new Modality("RO", "Radiation Oncology", "RADONCO"));
		modalityRepo.save(new Modality("LD", "Laboratory Diagnostic", "LABO"));
		modalityRepo.save(new Modality("SY", "Imaging Software", "SOFTEO IMA."));
		modalityRepo.save(new Modality("SU", "Surgical Solutions", "SURGEO"));
		
	}
	
}
