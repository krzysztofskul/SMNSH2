package pl.krzysztofskul.smnsh2.project.attachment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttachmentService {

	private AttachmentRepo attachmentRepo;

	/**
	 * CONSTRUCTOR
	 */
	@Autowired
	public AttachmentService(AttachmentRepo attachmentRepo) {
		this.attachmentRepo = attachmentRepo;
	}
	
	public void save(Attachment attachment) {
		attachmentRepo.save(attachment);
	}
	
}
