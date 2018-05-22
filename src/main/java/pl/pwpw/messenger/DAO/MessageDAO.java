package pl.pwpw.messenger.DAO;

import pl.pwpw.messenger.Models.Message;

public interface MessageDAO {
	void addMessage(Message newMessage);
	Message getMessageById(long Id);
	Iterable<Message> findAll();
}
