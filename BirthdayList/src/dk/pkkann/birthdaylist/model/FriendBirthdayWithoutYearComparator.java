package dk.pkkann.birthdaylist.model;

import java.util.Calendar;
import java.util.Comparator;

public class FriendBirthdayWithoutYearComparator implements Comparator<Friend> {

	@Override
	public int compare(Friend lhs, Friend rhs) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		
		c1.set(1111, lhs.getBirthday().get(Calendar.MONTH), lhs.getBirthday().get(Calendar.DATE));
		c2.set(1111, rhs.getBirthday().get(Calendar.MONTH), rhs.getBirthday().get(Calendar.DATE));
		
		return c1.compareTo(c2);
	}

}
