package com.example.test1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.test1.RegularReportAct.LoadOutbox1;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class AfterExerAct extends Activity {
	private ProgressDialog pDialog;
    private JSONparser jsonParser;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_after_exer);		 
	        Button button1=(Button)findViewById(R.id.button1);
	        jsonParser = new JSONparser();
	        button1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					String[] strs = new String[2];
					strs[0] = " ";
					strs[1] = ((RadioButton)findViewById(R.id.yesradioButton1)).isSelected()?"1":"0";			

					new LoadOutbox1().execute(strs);
			
				}
			});					
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.after_exer, menu);
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
            pDialog = new ProgressDialog(AfterExerAct.this);
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
	        
	    	//String bp = args[0].trim();
	    	String medicine = args[1].trim();
	    	
	    	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	
	    	List<NameValuePair> params = new ArrayList<NameValuePair>();
	    	params.add(new BasicNameValuePair("dcm", ((RadioButton)findViewById(R.id.yesradioButton1)).isChecked()?"1":"0"));
			params.add(new BasicNameValuePair("diz", ((RadioButton)findViewById(R.id.yesradioButton2)).isChecked()?"1":"0"));
	    	params.add(new BasicNameValuePair("rpe", String.valueOf(((RatingBar)findViewById(R.id.ratingBar1)).getProgress()+6)));
	    	params.add(new BasicNameValuePair("ou",  ((EditText)findViewById(R.id.editText1)).getText().toString()+" "+
	    	((EditText)findViewById(R.id.editText2)).getText().toString()+" "+
	    			((EditText)findViewById(R.id.editText3)).getText().toString()));
	    	params.add(new BasicNameValuePair("date", fmt.format(new Date())));
	    	    	
	        // getting JSON string from URL
	    	String URL = "http://10.0.2.2/post_exercise.php";
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
	    	AfterExerAct.this.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					new AlertDialog.Builder(AfterExerAct.this)
			        .setMessage("Message Sent Successfully.")
			        .setTitle("Send")
			        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int whichButton) {
			                dialog.dismiss();
			                Intent intent = new Intent(AfterExerAct.this, MainActivity.class);
							startActivity(intent);
			            }
			        })		        
			        .show();
				}
			});	            		               
	    }	
	}
}
