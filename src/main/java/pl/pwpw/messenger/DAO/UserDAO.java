package pl.pwpw.messenger.DAO;

import pl.pwpw.messenger.Models.Message;
import pl.pwpw.messenger.Models.User;

public interface UserDAO {
	void addUser(User newUser);
	User getUserById(long Id);
	Iterable<User> findAll();
}
