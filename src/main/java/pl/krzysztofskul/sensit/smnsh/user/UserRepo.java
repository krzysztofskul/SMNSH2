package pl.krzysztofskul.sensit.smnsh.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    List<User> findAllByBusinessPosition(UserBusinessPosition userBusinessPosition);

    User findByEmail(String email);

    //@Query("SELECT u FROM User u WHERE u.nameFirst = ?1 AND u.nameLast = ?2")
	User findByNameFirstAndNameLast(String nameFirst, String nameLast);

}
