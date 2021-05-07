package core;

import GoogleEmailManager.GoogleEmailManager;

public class GoogleMailManagerAdapter implements EmailService{

	private GoogleEmailManager googleMailManager;
	
	public GoogleMailManagerAdapter() {
		googleMailManager = new GoogleEmailManager();
	}
	
	@Override
	public void send(String email, String message) {
		googleMailManager.send(email, message);
	}

}
