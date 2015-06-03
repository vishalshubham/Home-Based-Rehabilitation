package com.example.test1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends Activity {

	private ImageView image1;
	private ImageView image2;
	private ImageView imageQA;
	private ImageView image4;
	private ImageView image5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        image1 = (ImageView) findViewById(R.id.create_image_1);
        image2 = (ImageView) findViewById(R.id.create_image_2);
        imageQA = (ImageView) findViewById(R.id.create_image_3);
        image4 = (ImageView) findViewById(R.id.create_image_4);
        image5 = (ImageView) findViewById(R.id.create_image_5);
        image1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				// Toast.makeText(getApplicationContext(), "Click On Image 1", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(MainActivity.this, FirstActivity.class);
				startActivity(intent);
		
			}
		});
        
        image2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				// Toast.makeText(getApplicationContext(), "Click On Image 1", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(MainActivity.this, SelfReportAct.class);
				startActivity(intent);
		
			}
		});
        
        image4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				// Toast.makeText(getApplicationContext(), "Click On Image 1", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(MainActivity.this, RegularReportAct.class);
				startActivity(intent);
		
			}
		});

        imageQA.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(MainActivity.this, QAActivity.class);
				startActivity(intent);
		
			}
		});
        image5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//Intent intent = new Intent(MainActivity.this, AlarmTest.class);
				//startActivity(intent);
		
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    
    
}

// Intent with Bundle
