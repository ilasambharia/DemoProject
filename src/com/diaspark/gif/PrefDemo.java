package com.diaspark.gif;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PrefDemo extends Activity implements OnClickListener{
	Button btnSimplePref;
	Button btnFancyPref;	
	TextView txtCaption1;
	Boolean fancyPrefChosen = false;
	View  myLayout1Vertical;
	
	final int mode = Activity.MODE_PRIVATE;
	final String MYPREFS = "MyPreferences_001";
	// create a reference to the shared preferences object
	SharedPreferences mySharedPreferences;
	SharedPreferences.Editor myEditor;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.prefdemo);
	myLayout1Vertical = (View)findViewById(R.id.linLayout1Vertical);
	txtCaption1 = (TextView) findViewById(R.id.txtCaption1);
	txtCaption1.setText("This is a sample line \n" 
	+ "suggesting the way the UI looks \n"
	+ "after you choose your preference");
	// create a reference & editor for the shared preferences object
	mySharedPreferences = getSharedPreferences(MYPREFS, 0);
	myEditor = mySharedPreferences.edit();
	// has a Preferences file been already created?
	if (mySharedPreferences != null
	&& mySharedPreferences.contains("backColor")) {	
	applySavedPreferences();
	} else {
	Toast.makeText(getApplicationContext(), 
	"No Preferences found", 1).show();
	}
	btnSimplePref = (Button) findViewById(R.id.btnPrefSimple);
	btnSimplePref.setOnClickListener(this);
	btnFancyPref = (Button) findViewById(R.id.btnPrefFancy);
	btnFancyPref.setOnClickListener(this);
	}// onCreat



	public void onClick(View v) {
	// clear all previous selections
	myEditor.clear();
	// what button has been clicked?
	if (v.getId() == btnSimplePref.getId()) {
	myEditor.putInt("backColor", Color.BLACK);// black background
	myEditor.putInt("layoutColor", Color.DKGRAY);// black background

	} else { // case btnFancyPref
		
	myEditor.putInt("backColor", Color.BLUE); // fancy blue
	myEditor.putInt("layoutColor", Color.GREEN);//fancy green 
	}
	
	myEditor.commit();
	applySavedPreferences();
	}
	
	public void applySavedPreferences() {
		// extract the <key/value> pairs, use default param for missing data
		int backColor = mySharedPreferences.getInt("backColor",Color.BLACK);
		int layoutColor = mySharedPreferences.getInt("layoutColor", Color.DKGRAY);
		
		txtCaption1.setBackgroundColor(backColor);
		
		myLayout1Vertical.setBackgroundColor(layoutColor);
		}// applySavedPreferences
	
	
	public  void setUserName(Context context, String pUserName) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				MYPREFS, Context.MODE_PRIVATE);

		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString("UserName", pUserName);
		editor.commit();
	}

	

	public String getUserName(Context context) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				MYPREFS, Context.MODE_PRIVATE);

		return sharedPreferences.getString("UserName", null);
	}
	
	
	
}//class
	



