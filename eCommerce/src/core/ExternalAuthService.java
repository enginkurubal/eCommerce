package core;

import entities.concretes.Login;

public interface ExternalAuthService {
	void register(String email);
	boolean userExists(String email);
	void login(Login login);
}
