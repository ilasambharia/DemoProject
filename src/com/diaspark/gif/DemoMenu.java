package com.diaspark.gif;

import java.io.FileOutputStream;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.test.FlakyTest;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class DemoMenu extends Activity implements OnItemClickListener, TextWatcher{
	AlertDialog alert;
	ImageView pimgview;
	TextView selection;
	AutoCompleteTextView edit ;
	Gallery myGallery;
	
	TextView lblDateAndTime;
	Calendar myCalendar = Calendar.getInstance();
	private static final String[] DATA = { "Apple", "Banana", "Milk", "Egg",
		"Water", "Shake", "Bread", "Grapes" };
	
	String[] items = { "apple", "apple1", "apple3",
			"apple4", "apple5", "hello", "ello" };
	
	private Uri[] mUrls;  

	 String[] mFiles=null; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.tableview);
		//showCustomFont();
		//showContextMenu();
		//setContentView(R.layout.font);
		//showDialog(10);
		//aliasNamePopUp();
		//displayCustomToast();
		//showwebview();
		//displayGridView();
		//displayAutoCompleteView();
		//displayGalleryView();
		//displaydatetimepicker();
		//showDatePicker();
		//setContentView(R.layout.main);
		//showwebview();
		setContentView(R.layout.main);
		//showDialog(10);
		Button button = (Button)findViewById(R.id.btnStart);
		button.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v) {
				// TODO Auto-generated method stub
				//displayIntentDemo();
				displayGalleryView();
			}
			
		}
		
		);
		
		//aliasNamePopUp();
		
	}
	public void displayIntentDemo()
	{
		Intent intent = new Intent(this, IntentDemo.class);		
		intent.putExtra("Selected_Item", "10") ;

		startActivityForResult(intent, 1);
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		Log.v("request result","resultCode"+resultCode);
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	public void displayGalleryView()
	{
		setContentView(R.layout.gallerylayout);
		// Bind the gallery defined in the main.xml
		// Apply a new (customized) ImageAdapter to it.
		 myGallery = (Gallery) findViewById(R.id.myGallery);
		myGallery.setAdapter(new ImageAdapter(this));
		
		mFiles = new String[4];  
	
							
		mFiles[0]=	"/sdcard/1303885644172.jpg";
		mFiles[1]=	"/sdcard/1303885647708.jpg";
		mFiles[2]=	"/sdcard/1303885650801.jpg";
		mFiles[3]=	"/sdcard/1303885653964.jpg";
	
		   mUrls = new Uri[mFiles.length];  
			  
	        for(int i=0; i < mFiles.length; i++)  
	        {  
	            mUrls[i] = Uri.parse(mFiles[i]);     
	        }     
		
	}
	
public void showDatePicker()
{
	new DatePickerDialog(this, null, 
			myCalendar.get(Calendar.YEAR), 
			myCalendar.get(Calendar.MONTH),
			myCalendar.get(Calendar.DAY_OF_MONTH)).show();
			
}

public void showTimePicker()
{
	new TimePickerDialog(this, null, 
			myCalendar.get(Calendar.HOUR_OF_DAY), 
			myCalendar.get(Calendar.MINUTE), true).show();
			
}
public void displaydatetimepicker()
{
	setContentView(R.layout.datetime);
	TextView lblDateAndTime = (TextView) findViewById(R.id.lblDateAndTime);
	Button btnDate = (Button) findViewById(R.id.btnDate);
	
	btnDate.setOnClickListener(new View.OnClickListener() {
	public void onClick(View v) {
		showDatePicker();
	}
	});
	Button btnTime = (Button) findViewById(R.id.btnTime);
	btnTime.setOnClickListener(new View.OnClickListener() {
	public void onClick(View v) {
		showTimePicker();
	}
	});
	
	}


	public class ImageAdapter extends BaseAdapter {
		/** The parent context */
		private Context myContext;
		// Put some images to project-folder: /res/drawable/
		// format: jpg, gif, png, bmp, ...
		//private int[] myImageIds = { R.drawable.gallery1, R.drawable.gallery2,
		//R.drawable.gallery3, R.drawable.gallery4 };
		/** Simple Constructor saving the 'parent' context. */
		public ImageAdapter(Context c) {
		this.myContext = c;
		}
		// inherited abstract methods - must be implemented
		// Returns count of images, and individual IDs
		public int getCount() {
		//return this.myImageIds.length;
			return 5;
		}
		public Object getItem(int position) {
		return position;
		}
		public long getItemId(int position) {
		return position;
		}
		// Returns a new ImageView to be displayed,
		public View getView(int position, View convertView, 
		ViewGroup parent) {
		// Get a View to display image data 
		ImageView iv = new ImageView(this.myContext);
		//iv.setImageResource(this.myImageIds[position]);
		iv.setImageURI(mUrls[position]);
		iv.setPadding(0, 0, 30, 0);
		iv.setLayoutParams(new Gallery.LayoutParams(136, 136));
		iv.setScaleType(ImageView.ScaleType.FIT_XY);
		// Set the Width & Height of the individual images
		
		return iv;
		
		}// ImageAdapter
	}// class
	public void displayAutoCompleteView()
	{
	
		setContentView(R.layout.autocompleteview);
		selection = (TextView) findViewById(R.id.selection);
		 edit = (AutoCompleteTextView) findViewById(R.id.edit);
		
		edit.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, items));
		edit.addTextChangedListener(this);
		
	}
	public void displayGridView()
	{
		setContentView(R.layout.griddemo);
		selection = (TextView) findViewById(R.id.selection);
		GridView gv = (GridView)findViewById(R.id.grid);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, DATA);
		gv.setAdapter(adapter);
		gv.setOnItemClickListener(this);
	}
	public void displayListView()
	{
		
		setContentView(R.layout.selectionwidget);
		ListView pList=(ListView)findViewById(R.id.list);
		pList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,DATA));
		pList.setOnItemClickListener(this);
		selection=(TextView)findViewById(R.id.selection); 
	}
	public void displaySpinner()
	{
		setContentView(R.layout.spinner);
		Spinner spinner = (Spinner) findViewById(R.id.idSpinner);
		
		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,DATA);
		adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		spinner.setAdapter(adapter);
	}
	public void showCustomFont()
	{
		setContentView(R.layout.font);
		TextView tvCustom=(TextView)findViewById(R.id.customfont);
		Typeface myNewFace=Typeface.createFromAsset(
		getAssets(), "fonts/MTCORSVA.TTF" );
		tvCustom.setTypeface(myNewFace);
	}
	
	public void showContextMenu()
	{
		setContentView(R.layout.menucontext);
		pimgview=(ImageView)findViewById(R.id.idImageMenu);
		registerForContextMenu(pimgview);
	}
	
	public void showwebview()
	{
		setContentView(R.layout.webview);
		WebView browser=(WebView)findViewById(R.id.webkit);
		browser.loadUrl("http://www.diaspark.com");
		browser.getSettings().setJavaScriptEnabled(true);
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menudemo, menu);
	    return true;
	}
	

	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) 
	{
		
	super.onCreateContextMenu(menu, v, menuInfo);
	// decide what context menu needs to be made
	
	// create a menu for etMessage1 box
	MyFirstMenu(menu);
	
	} //onCreateContextMenu
	
	private void MyFirstMenu(Menu menu){
		int groupId = 0; int order= 0;
		//arguments: groupId, optionId, order, title
		menu.add(groupId, 1, 1, "first");
		menu.add(groupId, 2, 2, "second");
		menu.add(groupId, 3, 3, "third ");
		menu.add(groupId, 4, 4, "fourth ");
		
		} //populateMyMenu
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.demo:
	      Log.v("Hello","Demo clicked");
	        return true;
	    case R.id.help:
	    	 Log.v("Hello","Help clicked");
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch(id)
		{
		case 10:
		// TODO Auto-generated method stub
		android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("This will end the activity")
				.setCancelable(true)
				.setPositiveButton("I agree",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// Ends the activity
								finish();
							}
						})
				.setNegativeButton("No, no",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								Toast.makeText(getApplicationContext(),
										"Activity will continue",
										Toast.LENGTH_SHORT).show();
							}
						});
		alert = builder.create();
		alert.show();
		}
		return super.onCreateDialog(id);
	}
	
	public void createDialog()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Here i am");
		builder.setMessage("I am Dialog Error");
		
		builder.setPositiveButton("Yes", null);	
		builder.setNegativeButton("No", null);	
		
		alert = builder.create();		
		alert.show();
	}
	
	public void createToast()
	{
		Toast.makeText(this, "I am Toast", Toast.LENGTH_LONG).show();
	}
	
	private void aliasNamePopUp() {
		AlertDialog.Builder builder = null;
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.aliasview,
				(ViewGroup) findViewById(R.id.LinearLayoutAP01));
		builder = new AlertDialog.Builder(this);
	
		builder.setPositiveButton("Done",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {

					}
				});
		builder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						
						alert.dismiss();
					}
				});
		builder.setView(layout);
		alert = builder.create();
		alert.show();
	}
	
	  public void displayCustomToast()
	  {
		  LayoutInflater inflater = getLayoutInflater();
	      View layout = inflater.inflate(R.layout.aliasview,(ViewGroup) findViewById(R.id.LinearLayoutAP01));
	     // Button pSignUpBtn = (Button) layout.findViewById(R.id.idSignUpBtn);
	      
	     
	      Toast toast = new Toast(getApplicationContext());
	       toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
	       toast.setDuration(Toast.LENGTH_LONG);
	       toast.setView(layout);
	       toast.show();
	       //Ila:
	       //If in case We need toast to stay for longer time use this
	       //fireLongToast();
	    }

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		String text = "Selected" + DATA[arg2];
		selection.setText(text);
	}
	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub
		
	}
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		
	}
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		selection.setText(edit.getText());
	}
}
