package pl.krzysztofskul.smnsh2.filestorage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FileStorageRepo extends JpaRepository<File, Long>{

}
