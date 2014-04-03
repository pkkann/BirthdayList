package dk.pkkann.birthdaylist.model;

import java.util.Comparator;

public class FriendBirthdayComparator implements Comparator<Friend> {

	@Override
	public int compare(Friend lhs, Friend rhs) {
		return lhs.getBirthday().compareTo(rhs.getBirthday());
	}

}
