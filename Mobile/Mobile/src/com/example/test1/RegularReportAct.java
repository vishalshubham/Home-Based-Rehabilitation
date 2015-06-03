package com.example.test1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

import com.example.test1.QaAskActivity.LoadOutbox1;

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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class RegularReportAct extends Activity {

     private ProgressDialog pDialog;
     private JSONparser jsonParser;
	
    String bp="100",cig="0",weight="50";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_regular_report);
		SeekBar seekBar1 = (SeekBar)findViewById(R.id.seekBar1);
		SeekBar seekBar2 = (SeekBar)findViewById(R.id.seekBar2);
		SeekBar seekBar3 = (SeekBar)findViewById(R.id.seekBar3);
	    Button button1=(Button)findViewById(R.id.button1);
	    Button button2=(Button)findViewById(R.id.button2);
	    jsonParser = new JSONparser();	
	    final TextView textView2=(TextView)findViewById(R.id.textView2);
	    final TextView textView5=(TextView)findViewById(R.id.textView5);
	    final TextView textView7=(TextView)findViewById(R.id.textView7);
    //    HttpClient httpClient = new DefaultHttpClient();
        
		seekBar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{
			@Override
			public void onProgressChanged(SeekBar arg0
				, int progress, boolean fromUser)
			{
				textView2.setText(String.valueOf(progress)+" mmHg");	
				bp=String.valueOf(progress);
			}
			@Override
			public void onStartTrackingTouch(SeekBar bar){}
			@Override
			public void onStopTrackingTouch(SeekBar bar){}
		});
        
		seekBar2.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{
			@Override
			public void onProgressChanged(SeekBar arg0
				, int progress, boolean fromUser)
			{
				textView5.setText(String.valueOf(progress)+" packages");
				cig=String.valueOf(progress);
			}
			@Override
			public void onStartTrackingTouch(SeekBar bar){}
			@Override
			public void onStopTrackingTouch(SeekBar bar){}
		});
		seekBar3.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{
			@Override
			public void onProgressChanged(SeekBar arg0
				, int progress, boolean fromUser)
			{
				textView7.setText(String.valueOf(progress)+" kg");
				weight=String.valueOf(progress);
			}
			@Override
			public void onStartTrackingTouch(SeekBar bar){}
			@Override
			public void onStopTrackingTouch(SeekBar bar){}
		});
		
        button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				
//				// Toast.makeText(getApplicationContext(), "Click On Image 1", Toast.LENGTH_LONG).show();				
//					
//					HttpPost post = new HttpPost(
//						"http://192.168.1.88:8888/foo/login.jsp");
//					// 如果传递参数个数比较多的话可以对传递的参数进行封装
//					List<NameValuePair> params = new ArrayList<NameValuePair>();
//					params
//						.add(new BasicNameValuePair("name", name));
//					params
//						.add(new BasicNameValuePair("pass", pass));
//					try
//					{
//						// 设置请求参数
//						post.setEntity(new UrlEncodedFormEntity(
//							params, HTTP.UTF_8));
//						// 发送POST请求
//						HttpResponse response = httpClient.execute(post);
//						// 如果服务器成功地返回响应
//						if (response.getStatusLine()
//							.getStatusCode() == 200)
//						{
//							String msg = EntityUtils
//								.toString(response.getEntity());
//							// 提示登录成功
//							Toast.makeText(HttpClientTest.this,
//								msg, 5000).show();
//						}
//					}
//					catch (Exception e)
//					{
//						e.printStackTrace();
//					}
//				}							
				String[] strs = new String[2];
				strs[0] = bp;
				strs[1] = ((RadioButton)findViewById(R.id.yesradioButton1)).isChecked()?"1":"0";			

				new LoadOutbox1().execute(strs);
		
			}
		});
        
        	button2.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				String[] strs = new String[2];
				strs[0] = bp;
				strs[1] = ((RadioButton)findViewById(R.id.yesradioButton1)).isChecked()?"1":"0";			

				new LoadOutbox1().execute(strs);		
			}
		});			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.regular_report, menu);
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
            pDialog = new ProgressDialog(RegularReportAct.this);
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
	    	//params.add(new BasicNameValuePair("q", String.valueOf(ran.nextInt(100))));
	    	params.add(new BasicNameValuePair("bp", bp));
	    	params.add(new BasicNameValuePair("med", medicine));
	    	params.add(new BasicNameValuePair("cig", cig));
	    	params.add(new BasicNameValuePair("weight", weight));
	    	params.add(new BasicNameValuePair("date", fmt.format(new Date())));
	    	    	
	        // getting JSON string from URL
	    	String URL = "http://10.0.2.2/regular_report.php";
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
	    	RegularReportAct.this.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					new AlertDialog.Builder(RegularReportAct.this)
			        .setMessage("Message Sent Successfully.")
			        .setTitle("Send")
			        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int whichButton) {
			                dialog.dismiss();
			                Intent intent = new Intent(RegularReportAct.this, MainActivity.class);
							startActivity(intent);
			            }
			        })		        
			        .show();
				}
			});	            		               
	    }	
	}
}
