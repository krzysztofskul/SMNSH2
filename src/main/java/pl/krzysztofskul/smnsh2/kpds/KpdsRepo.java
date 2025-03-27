package pl.krzysztofskul.smnsh2.kpds;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KpdsRepo extends JpaRepository<Kpds, Long>{

}
