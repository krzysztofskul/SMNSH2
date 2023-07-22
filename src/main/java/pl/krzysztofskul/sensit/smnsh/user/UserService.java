package pl.krzysztofskul.sensit.smnsh.user;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
	
    private UserRepo userRepo;

    /**
     * CONSTRUCTOR
     * 
	 * @param userRepo
	 */
	public UserService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	public void save(User user) {
        userRepo.save(user);
    }
    
    public User saveAndReturn(User user) {
    	return userRepo.save(user);
    }

    public List<User> loadAll() {
        return userRepo.findAll();
    }

    public List<User> loadAllDesigners() {
        return userRepo.findAllByBusinessPosition(UserBusinessPosition.DESIGNER);
    }

    public List<User> loadAllProjectManagers() {
        return userRepo.findAllByBusinessPosition(UserBusinessPosition.PROJECT_MANAGER);
    }

    public List<User> loadAllSalesReps() {
        return userRepo.findAllByBusinessPosition(UserBusinessPosition.SALES_REP);
    }

    public User loadById(Long id) {
        return userRepo.findById(id).get();
    }

    public User loadByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public boolean isExist(String value) {
        List<User> userList = userRepo.findAll();

        if (userList.size() != 0) {
            for (User user : userList) {
                if (user.getEmail().equals(value)){
                    return true;
                }
            }
        }
        return false;
    }

	public User loadByUserNames(String userNameFirst, String userNameLast) {
		User user = userRepo.findByNameFirstAndNameLast(userNameFirst, userNameLast);
		return userRepo.findByNameFirstAndNameLast(userNameFirst, userNameLast);
	}
    
}
