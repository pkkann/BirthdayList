package dk.pkkann.birthdaylist;

import java.util.Calendar;

import dk.pkkann.birthdaylist.model.Friend;
import dk.pkkann.birthdaylist.model.FriendRegister;
import dk.pkkann.birthdaylist.model.FriendRegisterSingle;
import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
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

public class EditFriendActivity extends Activity {
	
	private Intent intent;
	private FriendRegister fRegister;
	private EditText nameET;
	private DatePicker birthdayDP;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_friend);
		fRegister = FriendRegisterSingle.getInstance();
		nameET = (EditText)findViewById(R.id.nameET);
		birthdayDP = (DatePicker)findViewById(R.id.birthdayDP);
		
		intent = getIntent();
		String name = intent.getStringExtra("name");
		Calendar birth = (Calendar) intent.getSerializableExtra("birth");
		
		nameET.setText(name);
		
		birthdayDP.updateDate(birth.get(Calendar.YEAR), birth.get(Calendar.MONTH), birth.get(Calendar.DATE));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.edit_friend, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch(id) {
		case R.id.action_saveFriend:
			int pos = intent.getIntExtra("pos", -1);
			if(pos == -1) {
				Toast.makeText(this, getString(R.string.toast_friendNotFound), Toast.LENGTH_SHORT).show();
			} else {
				Friend f = fRegister.get(pos);
				String name = nameET.getText().toString();
				int date = birthdayDP.getDayOfMonth();
				int month = birthdayDP.getMonth();
				int year = birthdayDP.getYear();
				Calendar birth = Calendar.getInstance();
				birth.set(year, month, date);
				name = name.substring(0, 1).toUpperCase() + name.substring(1);
				
				if(fRegister.canBeSaved(pos, name, birth)) {
					if(!name.isEmpty()) {
						MainActivity.notifyDataSetChanged();
						FriendRegisterSingle.getInstance().writeToFile(this);
						finish();
						Toast.makeText(this, getString(R.string.toast_friendSaved), Toast.LENGTH_SHORT).show();
						f.setName(name);
						f.setBirthday(birth);
					} else {
						Toast.makeText(this, getString(R.string.toast_fieldsNotFilled), Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(this, getString(R.string.toast_friendAlreadyExists), Toast.LENGTH_SHORT).show();
				}
			}
			break;
		case R.id.action_deleteFriend:
			DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			    @Override
			    public void onClick(DialogInterface dialog, int which) {
			        switch (which){
			        case DialogInterface.BUTTON_POSITIVE:
			        	int pos = intent.getIntExtra("pos", -1);
			        	if(pos == -1) {
			        		Toast.makeText(EditFriendActivity.this, getString(R.string.toast_friendNotFound), Toast.LENGTH_SHORT).show();
						} else {
							Friend f = fRegister.get(pos);
							fRegister.remove(f);
							MainActivity.notifyDataSetChanged();
							FriendRegisterSingle.getInstance().writeToFile(EditFriendActivity.this);
							finish();
							Toast.makeText(EditFriendActivity.this, getString(R.string.toast_friendDeleted), Toast.LENGTH_SHORT).show();
						}
			            break;

			        case DialogInterface.BUTTON_NEGATIVE:
			            //No button clicked
			            break;
			        }
			    }
			};

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(getString(R.string.yn_q_delete)).setPositiveButton(getString(R.string.yes), dialogClickListener)
			    .setNegativeButton(getString(R.string.no), dialogClickListener).show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
