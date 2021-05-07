package business.abstracts;

import java.util.List;

import entities.concretes.User;

public interface UserService {
	void add(User user);
	void _add(String email);
	void update(User user);
	void delete(int UserId);
	User get(String email);
	List<User> getAll();
	
}
