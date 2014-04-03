package dk.pkkann.birthdaylist;

import java.util.Calendar;

import dk.pkkann.birthdaylist.model.FriendRegisterSingle;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class AddFriendActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_friend);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_friend, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch(id) {
		case R.id.action_addFriend:
			EditText nameET = (EditText)findViewById(R.id.nameET);
			DatePicker birthdayDP = (DatePicker)findViewById(R.id.birthdayDP);
			
			String name = nameET.getText().toString();
			int date = birthdayDP.getDayOfMonth();
			int month = birthdayDP.getMonth();
			int year = birthdayDP.getYear();
			Calendar birth = Calendar.getInstance();
			birth.set(year, month, date);
			name = name.substring(0, 1).toUpperCase() + name.substring(1);
			
			boolean done = FriendRegisterSingle.getInstance().create(name, birth);
			
			if(done) {
				if(!name.isEmpty()) {
					MainActivity.notifyDataSetChanged();
					FriendRegisterSingle.getInstance().writeToFile(this);
					finish();
					Toast.makeText(this, getString(R.string.toast_friendAdded), Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(this, getString(R.string.toast_fieldsNotFilled), Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(this, getString(R.string.toast_friendAlreadyExists), Toast.LENGTH_SHORT).show();
			}
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
