package com.example.test1;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.test1.AfterExerAct.LoadOutbox1;

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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SelfReportAct extends Activity {
	private ProgressDialog pDialog;
    private JSONparser jsonParser;
	private String bp="100";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_self_report);
        SeekBar seekBar1 = (SeekBar)findViewById(R.id.seekBar1);
        Button button1=(Button)findViewById(R.id.button1);
        final TextView textView8=(TextView)findViewById(R.id.textView8);
        jsonParser = new JSONparser();
		seekBar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{
			@Override
			public void onProgressChanged(SeekBar arg0
				, int progress, boolean fromUser)
			{
				textView8.setText(String.valueOf(progress)+" mmHg");
				bp=String.valueOf(progress);
			}
			@Override
			public void onStartTrackingTouch(SeekBar bar){}
			@Override
			public void onStopTrackingTouch(SeekBar bar){}
		});
        
        button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
							
		    				
					String[] strs = new String[2];
					strs[0] = " ";
					strs[1] = ((RadioButton)findViewById(R.id.yesradioButton1)).isChecked()?"1":"0";			

					new LoadOutbox1().execute(strs);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pre_exer, menu);
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
            pDialog = new ProgressDialog(SelfReportAct.this);
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
	    	// Toast.makeText(getApplicationContext(), "Click On Image 1", Toast.LENGTH_LONG).show();	    
	    		// 如果传递参数个数比较多的话可以对传递的参数进行封装
	    		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    		params.add(new BasicNameValuePair("dcm", ((RadioButton)findViewById(R.id.yesradioButton1)).isChecked()?"1":"0"));
	    		params.add(new BasicNameValuePair("diz", ((RadioButton)findViewById(R.id.yesradioButton2)).isChecked()?"1":"0"));
	    		params.add(new BasicNameValuePair("lm", ((RadioButton)findViewById(R.id.yesradioButton3)).isChecked()?"1":"0"));
	    		params.add(new BasicNameValuePair("up", ((RadioButton)findViewById(R.id.yesradioButton4)).isChecked()?"1":"0"));
	    		params.add(new BasicNameValuePair("co", ((RadioButton)findViewById(R.id.yesradioButton5)).isChecked()?"1":"0"));
	    		params.add(new BasicNameValuePair("bp",bp));
	    		params.add(new BasicNameValuePair("med", ((RadioButton)findViewById(R.id.yesradioButton7)).isChecked()?"1":"0"));
	    		params.add(new BasicNameValuePair("ou", ((EditText)findViewById(R.id.editText1)).getText().toString()));
	    		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    		params.add(new BasicNameValuePair("time", fmt.format(new Date())));
	    	    	
	        // getting JSON string from URL
	    	String URL = "http://10.0.2.2/pre_exercise.php";
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
	    	SelfReportAct.this.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					new AlertDialog.Builder(SelfReportAct.this)
			        .setMessage("Message Sent Successfully.")
			        .setTitle("Send")
			        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int whichButton) {
			                dialog.dismiss();
			                Intent intent = new Intent(SelfReportAct.this, AfterExerAct.class);
							startActivity(intent);
			            }
			        })		        
			        .show();
				}
			});	            		               
	    }	
	}
}
