package pl.krzysztofskul.sensit.smnsh.project.attachment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentCategoryRepo extends JpaRepository<AttachmentCategory, Long> {

	AttachmentCategory findByAttCategoryCode(String code);

}
