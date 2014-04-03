package dk.pkkann.birthdaylist.model;

import java.io.Serializable;
import java.util.Calendar;

public class Friend implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Calendar birthday;
	
	public Friend(String name, Calendar birthday) {
		this.name = name;
		this.birthday = birthday;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}
	
	public Calendar getBirthday() {
		return birthday;
	}
	
	@Override
	public boolean equals(Object o) {
		Friend f = (Friend) o;
		int day = f.getBirthday().get(Calendar.DATE);
		int month = f.getBirthday().get(Calendar.MONTH);
		int year = f.getBirthday().get(Calendar.YEAR);
		Calendar fbday = Calendar.getInstance();
		fbday.set(year, month, day);
		if(f.getName().equals(name) && day==birthday.get(Calendar.DATE) && month==birthday.get(Calendar.MONTH) && year==birthday.get(Calendar.YEAR)){
			return true;
		}
		return false;
	}
}
