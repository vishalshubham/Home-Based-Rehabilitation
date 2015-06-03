package com.example.test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class QaAskActivity extends Activity {
	
	private EditText subjectTxt;
	private EditText noteTxt;
	private Button btnSend;
	private ImageView attachButton;
	
	private String subjectString;
	private String noteString;
	
	private ProgressDialog pDialog;
	
	JSONparser jsonParser = new JSONparser();	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qa_ask);
		
		subjectTxt = (EditText) findViewById(R.id.qa_ask_subject);
		noteTxt = (EditText) findViewById(R.id.qa_ask_note);
		attachButton = (ImageView) findViewById(R.id.qa_ask_attach_button);
		btnSend = (Button) findViewById(R.id.button_qa_ask_send);
		
		attachButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 添加附件的操作
			}
		});
		
		btnSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				// Post mysql statement here:
				// 参考QaFbAnswerActivity， create a classextends AsyncTask<String, String, String>
				// call:	new class().execute();
				
				// 把下面的代码放在 先的class -> onPostExecute > runOnUiThread(new Runnable() { -> public void run() {
				// begin
				
				// end
				
				// Strings get from UI
				subjectString = subjectTxt.getEditableText().toString();
				noteString = noteTxt.getEditableText().toString();
				
				System.out.println("**** received text" + subjectString + ":" + noteString);
				
				String[] strs = new String[2];
				strs[0] = subjectString;
				strs[1] = noteString;
				
				Date now = new Date();
				
				System.out.println("current time " + now.toString());
				
		    	System.out.println(strs[0] + ":" + strs[1]);

				new LoadOutbox1().execute(strs);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.qa_ask, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	class LoadOutbox1 extends AsyncTask<String, String, String> {
		 
	    /**
	     * Before starting background thread Show Progress Dialog
	     * */
	    @Override
	    protected void onPreExecute() {
	        super.onPreExecute();
            pDialog = new ProgressDialog(QaAskActivity.this);
            pDialog.setMessage("Loading ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
	    }
	
	    /**
	     * getting Outbox JSON
	     * */
	    protected String doInBackground(String... args) {
	        // Building Parameters
	        
	    	String sub = args[0].trim();
	    	String note = args[1].trim();
	    	Random ran = new Random(System.currentTimeMillis());
	    	
	    	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	
	    	List<NameValuePair> params = new ArrayList<NameValuePair>();
	    	params.add(new BasicNameValuePair("q", String.valueOf(ran.nextInt(100))));
	    	params.add(new BasicNameValuePair("sub", sub));
	    	params.add(new BasicNameValuePair("note", note));
	    	params.add(new BasicNameValuePair("date", fmt.format(new Date())));
	    	
	    	
	        // getting JSON string from URL
	    	String URL = "http://10.0.2.2/question.php";
	    	JSONObject json = jsonParser.makeHttpRequest(URL, "POST",
                    params);
            if(json == null) 
            		System.out.println("error1");
            else {
	        
			
	        // Check your log cat for JSON response
	       
				try {
					System.out.println(json.get("success"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Log.d("Outbox JSON: ", json.toString());
            }      
			
			return null;
	
	    }
	
	   
	    /**
	     * After completing background task Dismiss the progress dialog
	     * **/
	    protected void onPostExecute(String file_url) {
	        // dismiss the dialog after success
	      
	    	pDialog.dismiss();
	        
	        // updating UI from Background Thread
	    	QaAskActivity.this.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					new AlertDialog.Builder(QaAskActivity.this)
			        .setMessage("Message Sent Successfully.")
			        .setTitle("Send")
			        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int whichButton) {
			                dialog.dismiss();
			                Intent intent = new Intent(QaAskActivity.this, QAActivity.class);
							startActivity(intent);
			            }
			        })		        
			        .show();
				}
			});	            		               
	    }	
	}
}
