package dk.pkkann.birthdaylist.model;

import java.util.Comparator;

public class FriendNameComparator implements Comparator<Friend> {

	@Override
	public int compare(Friend lhs, Friend rhs) {
		return lhs.getName().compareToIgnoreCase(rhs.getName());
	}


}
