package pl.pwpw.messenger.Tools;

import static org.assertj.core.api.Assertions.in;

import java.util.ArrayList;
import java.util.Iterator;

import pl.pwpw.messenger.Models.Message;

public class Sort {

	public static ArrayList<Message> toMessagesTree(ArrayList<Message> unSortedList) {
		ArrayList<Message> parentList = findRootList(unSortedList);
		ArrayList<Message> sortedList = new ArrayList<>(parentList);
		for(Message parent : parentList) {
			findChildren(parent, sortedList, unSortedList);
		}
		return sortedList;
	}
	
	private static ArrayList<Message> findRootList(ArrayList<Message> unSortedList) {
		ArrayList<Message> inputList = new ArrayList<>(unSortedList);
		ArrayList<Message> rootList = new ArrayList<>();
		for(Message child : inputList) {
			if(child.getParent() == null) {
				rootList.add(child);
				unSortedList.remove(child);
			}
		}
		
		return rootList;
	}
	
	private static void findChildren(Message parent, ArrayList<Message> sortedList, ArrayList<Message> unSortedList) {
		ArrayList<Message> parentList = new ArrayList<>();
		ArrayList<Message> inputList = new ArrayList<>(unSortedList);
		for(Message child : inputList) {
			if(child.getParent().hashCode() == parent.hashCode()) {
				int indexOfParent = sortedList.indexOf(parent);
				sortedList.add((indexOfParent+1), child);
				parentList.add(child);
				unSortedList.remove(child);
			}
		}
		
		if(!unSortedList.isEmpty()) {
			for(Message nextParent : parentList) {
				findChildren(nextParent, sortedList, unSortedList);
			}
		}
	}
}