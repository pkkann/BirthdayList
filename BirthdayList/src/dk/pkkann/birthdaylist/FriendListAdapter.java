package dk.pkkann.birthdaylist;

import java.util.Calendar;
import java.util.List;

import dk.pkkann.birthdaylist.model.Friend;
import dk.pkkann.birthdaylist.model.FriendRegister;
import dk.pkkann.birthdaylist.model.FriendRegisterSingle;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class FriendListAdapter extends ArrayAdapter<Friend> {
	
	private LayoutInflater inflater;
	private int textViewResourceId;
	private Context context;
	private List<Friend> objects;

	public FriendListAdapter(Context context,int textViewResourceId, List<Friend> objects) {
		super(context, textViewResourceId, objects);
		this.inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
		this.textViewResourceId = textViewResourceId;
		this.context = context;
		this.objects = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View itemView = convertView;
		if(itemView == null) {
			itemView = inflater.inflate(textViewResourceId, parent, false);
		}
		Friend currentFriend = objects.get(position);
		
		TextView nameTV = (TextView)itemView.findViewById(R.id.nameTV);
		TextView birthdayTV = (TextView)itemView.findViewById(R.id.birthdayTV);
		
		//Set name
		nameTV.setText(currentFriend.getName());
		
		//Set birthday
		Calendar b = currentFriend.getBirthday();
		String birthday = b.get(Calendar.DATE) + "/" + (b.get(Calendar.MONTH) + 1) + "/" + b.get(Calendar.YEAR);
		birthdayTV.setText(birthday);
		
		return itemView;
	}
	
	

}
