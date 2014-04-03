package dk.pkkann.birthdaylist;

import dk.pkkann.birthdaylist.model.FriendBirthdayComparator;
import dk.pkkann.birthdaylist.model.FriendBirthdayWithoutYearComparator;
import dk.pkkann.birthdaylist.model.FriendNameComparator;
import dk.pkkann.birthdaylist.model.FriendRegister;
import dk.pkkann.birthdaylist.model.FriendRegisterSingle;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ChangeSortingActivity extends Activity {
	
	private FriendRegister fRegister;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_sorting);
		fRegister = FriendRegisterSingle.getInstance();
	}
	
	public void button_sortingName(View view) {
		fRegister.setDefaultSortMethod(new FriendNameComparator());
		fRegister.sort();
		MainActivity.notifyDataSetChanged();
		
		Toast.makeText(this, getString(R.string.toast_sortingChanged), Toast.LENGTH_SHORT).show();
		finish();
	}
	
	public void button_sortingBirth(View view) {
		fRegister.setDefaultSortMethod(new FriendBirthdayComparator());
		fRegister.sort();
		MainActivity.notifyDataSetChanged();
		
		Toast.makeText(this, getString(R.string.toast_sortingChanged), Toast.LENGTH_SHORT).show();
		finish();
	}

	public void button_sortingBirthNoYear(View view) {
		fRegister.setDefaultSortMethod(new FriendBirthdayWithoutYearComparator());
		fRegister.sort();
		MainActivity.notifyDataSetChanged();
		
		Toast.makeText(this, getString(R.string.toast_sortingChanged), Toast.LENGTH_SHORT).show();
		finish();
	}

}
