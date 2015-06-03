package com.example.test1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
 
public class QaFbAnswerActivity extends Activity {
	
	// Progress Dialog
    private ProgressDialog pDialog;
    private ListView listView;
    private ListAdapter adapter;
 
    // Creating JSON Parser object
    JSONparser JSONparser = new JSONparser();
 
    ArrayList<HashMap<String, String>> ansListFromJson;
 
    // products JSONArray
    JSONArray answers = null;
 
    // Outbox JSON url
    private static final String URL = "http://10.0.2.2/conn1.php";
     
    // ALL JSON node names
    private static final String TAG_MESSAGES = "posts";

    private static final String TAG_SUB =  "content";
    private static final String TAG_DID =  "did";
    private static final String TAG_DATE = "time";
    
    private static final String TAG_FRE = "frequency";
    private static final String TAG_HR = "targetHR";
    private static final String TAG_RPE = "RPE";
    private static final String TAG_MET = "MET";
    private static final String TAG_DUR = "duration";
    private static final String TAG_CAL = "Calorie";
     
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qa_fb_answer);
         
        listView = (ListView) findViewById(R.id.out_list);
        ansListFromJson = new ArrayList<HashMap<String, String>>();
  
        new LoadOutbox().execute();
    }
 
    /**
     * Background Async Task to Load all OUTBOX messages by making HTTP Request
     * */
    class LoadOutbox extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(QaFbAnswerActivity.this);
            pDialog.setMessage("Loading Answer ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
 
        /**
         * getting Outbox JSON
         * */
        protected String doInBackground(String... args) {
            // Building Parameters
            
        	List<NameValuePair> params = new ArrayList<NameValuePair>();
        	params.add(new BasicNameValuePair("s", "SELECT * FROM answers"));
        	
        	
            // getting JSON string from URL
            JSONObject json = JSONparser.makeHttpRequest(URL, "POST",
                    params);
 
            // Check your log cat for JSON reponse
            Log.d("Outbox JSON: ", json.toString());
 
            try {
                answers = json.getJSONArray(TAG_MESSAGES);
                // looping through All messages
                for (int i = 0; i < answers.length(); i++) {
                    JSONObject c = answers.getJSONObject(i);
 
                    // Storing each json item in variable
                    
                    /***
                     * Add new data here
                     */
                    
                    String did = c.getString(TAG_DID);
                    String subject = c.getString(TAG_SUB);
                    String date = c.getString(TAG_DATE);
                     
                    // creating new HashMap
                    HashMap<String, String> map = new HashMap<String, String>();
 
                    // adding each child node to HashMap key => value
                    
                    map.put(TAG_DID, did);
                    map.put(TAG_SUB, subject);
                    map.put(TAG_DATE, date);
 
                    // adding HashList to ArrayList
                    ansListFromJson.add(map);
                }
 
            } catch (JSONException e) {
                e.printStackTrace();
            }
 
            return null;
        }
 
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                	
                	 listView.setOnItemClickListener(new OnItemClickListener() {
             			@Override
             			public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
         				
         				final HashMap<String, String> selected = (HashMap<String, String>) adapter.getItem(position);
         				
         	            final String showTitle = selected.get(TAG_SUB);
         	            
         	           /***
                         * Change message here
                         */
         	            
         	            final String showMessage = "did " + selected.get(TAG_DID) +", date " + selected.get(TAG_DATE);
         				
         				
         					final AlertDialog dialog = new AlertDialog.Builder(QaFbAnswerActivity.this)
         	    			.setTitle(showTitle)
         	    			.setMessage(showMessage)
         	    			.setPositiveButton("OK", new DialogInterface.OnClickListener() {
         	    				@Override
         	    				public void onClick(DialogInterface dialog, int which) {
         	    					dialog.dismiss();
         	    					//  do something         		   					
         	    				}
         	    			}).create();
         	    			dialog.show();
         				}
             		});
                	
                    adapter = new SimpleAdapter(
                            QaFbAnswerActivity.this, ansListFromJson,
                            R.layout.qa_fb_ans_list_item, new String[] { TAG_SUB, TAG_DID, TAG_DATE },
                            new int[] { R.id.subject, R.id.ans_did, R.id.date });
                    // updating listview
                    listView.setAdapter(adapter);
                }
            });
 
        }
 
    }
}

