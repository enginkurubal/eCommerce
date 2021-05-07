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
			System.out.println("Kayýt baþarýsýz.");
		}
		
		
	}

	@Override
	public void verify(User user, String token) {
		if(user != null && token.length()<15) {
			User existUser = userService.get(user.geteMail());
			existUser.setVerify(true);
			System.out.println("Email doðrulandý");
		}else {
			System.out.println("Email doðrulama hatalý");
		}
		
	}

	@Override
	public boolean userExists(String email) {
		User user = userService.get(email);
		if(user == null) {
			return false;
		}else {
			System.out.println("Bu email ile bir kayýt zaten var");
			return true;
		}
	}

	@Override
	public void login(Login login) {
		User user = userService.get(login.getEmail());
		if(user != null && user.getPassword().equals(login.getPassword())) {
			System.out.println("Baþarýyla giriþ yaptýnýz");
		}else {
			System.out.println("Kullanýcý adý yada þifre hatalý");
		}
	}
	
	public boolean userValidate(User user) {
		
		if(user != null && !user.getFirstName().isEmpty() && !user.getLastName().isEmpty()
				&& !user.geteMail().isEmpty()	&& !user.getPassword().isEmpty()) {
			return true;
		}
		System.out.println("Gerekli Alanlarý Boþ Býrakmayýnýz !");
		return false;
	}

	public boolean passwordValidate(String password) {
		if(password.length()>=6) {
			return true;
		}
		System.out.println("Þifre en az 6 karakter olmalýdýr.");
		return false;
	}
}
