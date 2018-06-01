package com.example.phonebook;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Welcome extends Activity {
	private ProgressDialog pDialog;
	int flag=0;
	JSONParser jsonParser = new JSONParser();
	private static String url = "http://10.0.2.2/rdias/about.php";
	private static final String about = "about"; 
	private static final String TAG_SUCCESS="success";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		.detectDiskReads().detectDiskWrites().detectNetwork()
		.penaltyLog().build());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
    	
    }
	public void action(View v)
	{
		if(v.getId()==R.id.w_bt1)
			new loginAccess().execute();
		else
			Toast.makeText(this, "This is demo for RDIAS", Toast.LENGTH_LONG).show();
	}


class loginAccess extends AsyncTask<String, String, String> {

	protected void onPreExecute() {
		super.onPreExecute();
		pDialog = new ProgressDialog(Welcome.this);
		pDialog.setMessage("Wait...");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(true);
		pDialog.show();
	}
	@Override
	protected String doInBackground(String... arg0) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair(" "," "));
		JSONObject json = jsonParser.makeHttpRequest(url,"POST", params);
		Log.d("Create Response", json.toString());
		
		try {
			int success = json.getInt(TAG_SUCCESS);
			if (success == 1) 
			 {
			  flag=0;	
			  String abt=json.getString(about);
			  Intent i = new Intent(getApplicationContext(),About.class);
			  i.putExtra("about", abt);
			  startActivity(i);
			 }
			 else
			 {
				flag=1;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	protected void onPostExecute(String file_url) {
		pDialog.dismiss();
		if(flag==1)
			Toast.makeText(Welcome.this,"Data Not Found", Toast.LENGTH_LONG).show();
		
	}
	
  }
	
}
