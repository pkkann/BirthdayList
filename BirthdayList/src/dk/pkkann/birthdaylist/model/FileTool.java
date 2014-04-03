package dk.pkkann.birthdaylist.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import android.content.Context;

public class FileTool {
	
	private FileTool() {}
	
	public static void writeSerializableToFile(Serializable s, Context context) throws IOException {
		FileOutputStream fos = context.openFileOutput("friends.data", Context.MODE_PRIVATE);
		ObjectOutputStream os = new ObjectOutputStream(fos);
		
		os.writeObject(s);
		
		os.close();
	}
	
	public static Object readSerializableFromFile(Context context) throws IOException, ClassNotFoundException {
		FileInputStream fis = context.openFileInput("friends.data");
		ObjectInputStream is = new ObjectInputStream(fis);
		Object object = is.readObject();
		is.close();
		return object;
	}

}
