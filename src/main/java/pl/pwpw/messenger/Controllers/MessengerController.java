package pl.pwpw.messenger.Controllers;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.pwpw.messenger.DAO_Impl.MessageDaoImpl;
import pl.pwpw.messenger.DAO_Impl.UserDaoImpl;
import pl.pwpw.messenger.Models.Message;
import pl.pwpw.messenger.Models.Topic;
import pl.pwpw.messenger.Models.User;
import pl.pwpw.messenger.Tools.Sort;

@Controller
public class MessengerController {

	@Autowired
	UserDaoImpl userDaoImpl;

	@Autowired
	MessageDaoImpl messageDaoImpl;

	@RequestMapping("/")
	public String getMessages(Model model) {
		Iterable<Message> unSortedListMessages = messageDaoImpl.findAll();
		Iterator<Message> messagesIterator = unSortedListMessages.iterator();
		ArrayList<Message> unSortedList = new ArrayList<>();
		while(messagesIterator.hasNext()) {
			unSortedList.add(messagesIterator.next());
		}
		ArrayList<Message> sortedListMessages = Sort.toMessagesTree(unSortedList);
		ArrayList<User> usersList =  new ArrayList<>();
		Iterator<User> usersIterator = userDaoImpl.findAll().iterator();
		while(usersIterator.hasNext()) {
			usersList.add(usersIterator.next());
		}
		model.addAttribute("messages", sortedListMessages);
		model.addAttribute("users", usersList);
		return "index";
	}

	@RequestMapping(value = "/addMessage")
	public String addMessage(
			@RequestParam(value = "owner", required = true) String owner_id,
			@RequestParam(value = "topic", required = true) String topic,
			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "content", required = true) String content,
			@RequestParam(value = "parent", required = false) String parent_id) {
		User owner = userDaoImpl.getUserById(Long.parseLong(owner_id));
		if(parent_id.equals("null")) {
			messageDaoImpl.addMessage(new Message(title, owner, content, Topic.valueOf(topic)));
		} else {
			Message parentMessage = messageDaoImpl.getMessageById(Long.parseLong(parent_id));
			messageDaoImpl.addMessage(new Message(title, owner, content, Topic.valueOf(topic), parentMessage));
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/addUser")
	public String addUser(
			@RequestParam(value = "firstName", required = true) String firstName,
			@RequestParam(value = "lastName", required = true) String lastName,
			@RequestParam(value = "age", required = true) String age) {

		userDaoImpl.addUser(new User(firstName, lastName, Integer.parseInt(age)));
		return "redirect:/";
	}
}
