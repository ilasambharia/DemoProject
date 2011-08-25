package com.diaspark.gif;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class FileDemo extends Activity{
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	
	super.onCreate(savedInstanceState);
	//String str=PlayWithRawFiles();
	//writeInternalfile(str);
	writeFiletoSdCard();
	readSdCardFile();
	
}
public void writeInternalfile(String str)
{
	if(str!=null)
	{

		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(openFileOutput("notes.txt", 0));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			out.write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
public String PlayWithRawFiles() throws IOException {      
	String str="";
	StringBuffer buf = new StringBuffer();
	
	InputStream is = this.getResources().openRawResource(R.raw.filedemores);
	
	BufferedReader reader = new BufferedReader(
			new InputStreamReader(is));
	
	if (is!=null) {
		while ((str = reader.readLine()) != null) {
		buf.append(str + "\n" );
		}
		}
		is.close();
		Toast.makeText(getBaseContext(), 
		buf.toString(), Toast.LENGTH_LONG).show();
	return buf.toString();
}


public void writeFiletoSdCard()
{
	File myFile = new File("/sdcard/mysdfile.txt"); 
	try {
		myFile.createNewFile();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	FileOutputStream fOut = null;
	try {
		fOut = new FileOutputStream(myFile);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
	try {
		myOutWriter.append("dummy data in file.");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		myOutWriter.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		fOut.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void readSdCardFile()
{
	File myFile = new File("/sdcard/mysdfile.txt");
	FileInputStream fIn = null;
	try {
		fIn = new FileInputStream(myFile);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
	String aDataRow = "";
	String aBuffer = "";
	try {
		while ((aDataRow = myReader.readLine()) != null) {
			aBuffer += aDataRow + "\n";
		}
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		try {
			myReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Toast.makeText(getBaseContext(), aBuffer.toString(),Toast.LENGTH_LONG).show();
}
}
