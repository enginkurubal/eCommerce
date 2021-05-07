import business.abstracts.UserService;
import business.concretes.AuthManager;
import business.concretes.UserManager;
import core.GoogleMailManagerAdapter;
import dataAccess.abstracts.UserDao;
import dataAccess.concretes.InMemoryUserDao;
import entities.concretes.User;

public class Main {


	public static void main(String[] args) {
		
		User berke=new User(4, "Berke", "Demir", "brkdmr@gmail.com", "783778", true);
		
		
		User ferhat = new User(2, "Ferhat", "Eryýlmaz", "eryýlmazferhat@gmail.com", "177817", false);
		
		
		InMemoryUserDao inMemoryUserDao = new InMemoryUserDao();
		GoogleMailManagerAdapter googleMailManagerAdapter = new GoogleMailManagerAdapter();
		

		
		AuthManager authManager =new AuthManager(new UserManager(inMemoryUserDao, googleMailManagerAdapter));
		
		authManager.register(berke);
		authManager.register(ferhat);
		
		UserManager userManager = new UserManager(inMemoryUserDao, googleMailManagerAdapter);
		userManager.getAll();
	}	
}
