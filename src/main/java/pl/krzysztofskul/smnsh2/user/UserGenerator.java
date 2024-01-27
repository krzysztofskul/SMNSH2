package pl.krzysztofskul.smnsh2.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserGenerator {

	private List<User> userListEssential = new ArrayList<User>();

	/**
	 * Creates if not exist and return essential users
	 * 
	 * @return list of essential users
	 */
    public List<User> createAndGetEssentialUsers() {
    	
    	if (userListEssential.size() == 0) {
            userListEssential.add(new User(
            		"Piotr", "W.", UserBusinessPosition.ADMIN, 
            		"piotr.w@example.com", "test"
            		));
            userListEssential.add(new User(
            		"Magdalena", "S.", UserBusinessPosition.ADMIN, 
            		"magdalena.s@example.com", "test"
            		));
            userListEssential.add(new User(
            		"Sebastian", "K.", UserBusinessPosition.PROJECT_MANAGER, 
            		"sebastian.k@example.com", "test"
            		));
            userListEssential.add(new User(
            		"Ewa", "W.-M.", UserBusinessPosition.PROJECT_MANAGER, 
            		"ewa.w-m@example.com", "test"
            		));
            userListEssential.add(new User(
            		"Henryk", "S.", UserBusinessPosition.PROJECT_MANAGER, 
            		"henryk.s@example.com", "test"
            		));
            userListEssential.add(new User(
            		"Sebastian", "S.", UserBusinessPosition.PROJECT_MANAGER, 
            		"sebastian.s@example.com", "test"
            		));
            userListEssential.add(new User(
            		"Sebastian", "R.", UserBusinessPosition.PROJECT_MANAGER, 
            		"sebastian.r@example.com", "test"
            		));
            userListEssential.add(new User(
            		"Kamil", "S.", UserBusinessPosition.PROJECT_MANAGER, 
            		"kamil.s@example.com", "test"
            		));
            userListEssential.add(new User(
            		"Wojciech", "P.", UserBusinessPosition.PROJECT_MANAGER, 
            		"wojciech.p@example.com", "test"
            		));
            userListEssential.add(new User(
            		"Arkadiusz", "O.", UserBusinessPosition.PROJECT_MANAGER, 
            		"arkadiusz.o@example.com", "test"
            		));
            userListEssential.add(new User(
            		"Piotr", "S.", UserBusinessPosition.PROJECT_MANAGER, 
            		"piotr.s@example.com", "test"
            		));
            userListEssential.add(new User(
            		"Krzysztof", "S.", UserBusinessPosition.PROJECT_MANAGER, 
            		"krzysztof.s@example.com", "test"
            		));
            userListEssential.add(new User(
            		"Marika", "J.-B.", UserBusinessPosition.PROJECT_MANAGER, 
            		"marika.j-b@example.com", "test"
            		));
            userListEssential.add(new User(
            		"Mateusz", "C.", UserBusinessPosition.PROJECT_MANAGER, 
            		"mateusz.c@example.com", "test"
            		));
            userListEssential.add(new User(
            		"Jarosław", "N.", UserBusinessPosition.PROJECT_MANAGER, 
            		"jaroslaw.n@example.com", "test"
            		));
            userListEssential.add(new User(
            		"Emilia", "B.", UserBusinessPosition.PROJECT_MANAGER, 
            		"emialia.b@example.com", "test"
            		));
            userListEssential.add(new User(
            		"Maciej", "D.", UserBusinessPosition.DESIGNER, 
            		"maciej.d@example.com", "test"
            		));
            userListEssential.add(new User(
            		"Krzysztof", "K.", UserBusinessPosition.DESIGNER, 
            		"krzysztof.k@example.com", "test"
            		));
            userListEssential.add(new User(
            		"Karol", "D.", UserBusinessPosition.DESIGNER, 
            		"karol.d@example.com", "test"
            		));
            userListEssential.add(new User(
            		"Wojciech", "G.", UserBusinessPosition.SALES_REP, 
            		"wojciech.g@example.com", "test"
            		));
            userListEssential.add(new User(
            		"Ryszard", "G.", UserBusinessPosition.SALES_REP, 
            		"ryszard.g@example.com", "test"
            		));
    	}
    	
    	return userListEssential;

    }
	   
}
