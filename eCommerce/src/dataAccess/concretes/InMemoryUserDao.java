package dataAccess.concretes;

import java.util.ArrayList;
import java.util.List;

import dataAccess.abstracts.UserDao;
import entities.concretes.User;

public class InMemoryUserDao implements UserDao{
	
	private List<User> users = new ArrayList<User>();
	
	public InMemoryUserDao() {
		
		User user1 = new User(1,"Engin Can","Kurubal","enginkuruball@gmail.com","243424",true);
		User user2 = new User(2,"Utku Can","Doðu","utkudoguu@gmail.com","373437",true);
		User user3 = new User(3,"Sinan","Boz","ssinanbozl@gmail.com","167816",true);
		
		users.add(user1);
		users.add(user2);
		users.add(user3);
	}
	
	@Override
	public void add(User user) {
		System.out.println("Veri tabanýna eklendi : " + user.getFirstName());
		users.add(user);
	}

	@Override
	public void update(User user) {
		User userToUpdate = users.stream()
				.filter(u -> u.getId() == user.getId())
				.findFirst()
				.orElse(null);
		
		userToUpdate.seteMail(user.geteMail());
		userToUpdate.setFirstName(user.getFirstName());
		userToUpdate.setLastName(user.getLastName());
		userToUpdate.setPassword(user.getPassword());
		userToUpdate.setVerify(user.isVerify());
	}

	@Override
	public void delete(int UserId) {
		User userToDelete = users.stream()
				.filter(u -> u.getId() == UserId)
				.findFirst()
				.orElse(null);
		users.remove(userToDelete);
	}

	@Override
	public User get(String email) {
		
		User user = users.stream()
				.filter(u -> u.geteMail() == email)
				.findFirst()
				.orElse(null);
		return user;
	}

	@Override
	public List<User> getAll() {
		
		return users;
	}

}
