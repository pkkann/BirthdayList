package dk.pkkann.birthdaylist;

import java.util.ArrayList;
import java.util.Calendar;

import dk.pkkann.birthdaylist.model.Friend;
import dk.pkkann.birthdaylist.model.FriendRegister;
import dk.pkkann.birthdaylist.model.FriendRegisterSingle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private static FriendRegister fRegister;
	private static FriendListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fRegister = FriendRegisterSingle.getInstance();
        
        fRegister.readFromFile(this);
        
        populateList(fRegister.getFriends());
        registerListViewOnClick();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id) {
        case R.id.action_addFriend:
        	Intent intent = new Intent(this, AddFriendActivity.class);
        	startActivity(intent);
        	break;
        case R.id.action_changeSorting:
        	Intent intent2 = new Intent(this, ChangeSortingActivity.class);
        	startActivity(intent2);
        	break;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void populateList(ArrayList<Friend> friends) {
    	adapter = new FriendListAdapter(this, R.layout.list_item, friends);
    	ListView lv = (ListView)findViewById(R.id.listView1);
    	lv.setAdapter(adapter);
    }
    
    public void registerListViewOnClick() {
    	ListView lv = (ListView)findViewById(R.id.listView1);
    	lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Friend f = fRegister.get(position);
				Intent intent = new Intent(MainActivity.this, EditFriendActivity.class);
				intent.putExtra("name", f.getName());
				intent.putExtra("birth", f.getBirthday());
				intent.putExtra("pos", position);
				startActivity(intent);
			}
		});
    }
    
    public static void notifyDataSetChanged() {
    	fRegister.sort();
    	adapter.notifyDataSetChanged();
    }

}
