package dk.pkkann.birthdaylist.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import android.content.Context;

public class FriendRegister {
	
	private ArrayList<Friend> friends;
	private Comparator<Friend> defaultComparator;
	
	public FriendRegister() {
		friends = new ArrayList<Friend>();
		defaultComparator = new FriendNameComparator();
	}
	
	public void setFriends(ArrayList<Friend> friends) {
		this.friends = friends;
	}
	
	public ArrayList<Friend> getFriends() {
		return friends;
	}
	
	public void insert(Friend f) {
		friends.add(f);
	}
	
	public void remove(Friend f) {
		friends.remove(f);
	}
	
	public Friend get(int index) {
		return friends.get(index);
	}
	
	public boolean create(String name, Calendar birthday) {
		int day1 = birthday.get(Calendar.DATE);
		int month1 = birthday.get(Calendar.MONTH);
		int year1 = birthday.get(Calendar.YEAR);
				
		for(Friend ff : friends) {
			int day2 = ff.getBirthday().get(Calendar.DATE);
			int month2 = ff.getBirthday().get(Calendar.MONTH);
			int year2 = ff.getBirthday().get(Calendar.YEAR);
			
			if(ff.getName().equalsIgnoreCase(name) && day1 == day2 && month1 == month2 && year1 == year2) {
				return false;
			}
		}
		Friend f = new Friend(name, birthday);
		insert(f);
		return true;
	}
	
	public boolean canBeSaved(int position, String name, Calendar birthday) {
		Friend f = new Friend(name, birthday);
		for(Friend ff : friends) {
			if(ff.equals(f) && !(position == friends.indexOf(ff))) {
				return false;
			}
		}
		return true;
	}
	
	public void sort() {
		Collections.sort(friends, defaultComparator);
	}
	
	public void setDefaultSortMethod(Comparator<Friend> comp) {
		defaultComparator = comp;
	}
	
	public void writeToFile(Context context) {
		try {
			FileTool.writeSerializableToFile(friends, context);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void readFromFile(Context context) {
		try {
			friends = (ArrayList<Friend>) FileTool.readSerializableFromFile(context);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
