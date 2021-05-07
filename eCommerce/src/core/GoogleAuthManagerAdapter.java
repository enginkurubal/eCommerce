package core;

import business.abstracts.UserService;
import entities.concretes.Login;
import entities.concretes.User;

public class GoogleAuthManagerAdapter implements ExternalAuthService{

	private UserService userService;
	
	public GoogleAuthManagerAdapter(UserService userService) {
		this.userService = userService;
	}
	
	
	@Override
	public void register(String email) {
		if(userExists(email)==false) {
			userService._add(email);
		}else {
			User user = userService.get(email);
			Login login = new Login();
			login.setEmail(user.geteMail());
			login.setPassword(user.getPassword());
			
			login(login);
		}
		
	}

	@Override
	public boolean userExists(String email) {
		if(userService.get(email) != null) {
			return true;
		}
		return false;
	}

	@Override
	public void login(Login login) {
		if(login != null && login.getPassword().equals(login.getPassword())) {
			System.out.println("Ba�ar�yla giri� yapt�n�z.");
		}else {
			System.out.println("Kullan�c� ad� veya �ifre hatal� !");
		}
		
	}

}
