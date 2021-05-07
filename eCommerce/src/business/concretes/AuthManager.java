package business.concretes;

import business.abstracts.AuthService;
import business.abstracts.UserService;
import core.utils.Utils;
import entities.concretes.Login;
import entities.concretes.User;

public class AuthManager implements AuthService {

	UserService userService;
	
	public AuthManager(UserService userService){
		this.userService = userService;
	}
	
	
	@Override
	public void register(User user) {
		if(userValidate(user) && passwordValidate(user.getPassword())
				&& userExists(user.geteMail())==false
				&& Utils.emailValidate(user.geteMail())) 
		{
			userService.add(user);
		}else {
			System.out.println("Kay�t ba�ar�s�z.");
		}
		
		
	}

	@Override
	public void verify(User user, String token) {
		if(user != null && token.length()<15) {
			User existUser = userService.get(user.geteMail());
			existUser.setVerify(true);
			System.out.println("Email do�ruland�");
		}else {
			System.out.println("Email do�rulama hatal�");
		}
		
	}

	@Override
	public boolean userExists(String email) {
		User user = userService.get(email);
		if(user == null) {
			return false;
		}else {
			System.out.println("Bu email ile bir kay�t zaten var");
			return true;
		}
	}

	@Override
	public void login(Login login) {
		User user = userService.get(login.getEmail());
		if(user != null && user.getPassword().equals(login.getPassword())) {
			System.out.println("Ba�ar�yla giri� yapt�n�z");
		}else {
			System.out.println("Kullan�c� ad� yada �ifre hatal�");
		}
	}
	
	public boolean userValidate(User user) {
		
		if(user != null && !user.getFirstName().isEmpty() && !user.getLastName().isEmpty()
				&& !user.geteMail().isEmpty()	&& !user.getPassword().isEmpty()) {
			return true;
		}
		System.out.println("Gerekli Alanlar� Bo� B�rakmay�n�z !");
		return false;
	}

	public boolean passwordValidate(String password) {
		if(password.length()>=6) {
			return true;
		}
		System.out.println("�ifre en az 6 karakter olmal�d�r.");
		return false;
	}
}
