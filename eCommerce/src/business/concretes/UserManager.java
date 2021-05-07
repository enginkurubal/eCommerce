package business.concretes;

import java.util.List;

import business.abstracts.UserService;
import core.EmailService;
import dataAccess.abstracts.UserDao;
import entities.concretes.User;

public class UserManager implements UserService {
	private UserDao userDao;
	private EmailService emailService;
	
	public UserManager(UserDao userDao,EmailService emailService) {
		this.emailService = emailService;
		this.userDao = userDao;
	}
	
	
	@Override
	public void add(User user) {
		userDao.add(user);
		System.out.println("Kullanýcý Loglandý : " + user.getFirstName());
		emailService.send(user.geteMail(), " Kayýt Olundu.");
		
	}

	@Override
	public void _add(String email) {
		User user = new User();
		user.seteMail(email);
		userDao.add(user);
		System.out.println("Email Loglandý : "+user.geteMail());
		emailService.send(email, " Kayýt Olundu.");
	}

	@Override
	public void update(User user) {
		if(userValidate(user)) {
			userDao.add(user);
		}
		
	}

	@Override
	public void delete(int userId) {
		userDao.delete(userId);
		
	}

	@Override
	public User get(String email) {
		
		return userDao.get(email);
	}

	@Override
	public List<User> getAll() {
		for(User user : userDao.getAll()) {
			System.out.println(user.getId()+" "+user.geteMail()+" "+user.getFirstName()+
					" "+user.getLastName());
			}
		return userDao.getAll();
	}
	
	public boolean userValidate(User user) {
		if(user.getFirstName().length()>=2 && user.getLastName().length()>=2) {
			return true;				
		}
		System.out.println("Adýnýz ve soyadýnýz minimum 2 karakter olmalý.");
		return false;
	}

}
