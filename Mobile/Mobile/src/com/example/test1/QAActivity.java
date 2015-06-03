package com.example.test1;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class QAActivity extends Activity {

	private ImageView imageSA;
	private ImageView imageAsk;
	private ImageView imageFB;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qa);
		
        imageSA = (ImageView) findViewById(R.id.create_image_sa);
        imageAsk = (ImageView) findViewById(R.id.create_image_ask);
        imageFB = (ImageView) findViewById(R.id.create_image_fb);
        
        imageSA.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectType();
				
			}
		});
        
        imageAsk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				// Toast.makeText(getApplicationContext(), "Click On Image 1", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(QAActivity.this, QaAskActivity.class);
				startActivity(intent);
		
			}
		});
        
        imageFB.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				// Toast.makeText(getApplicationContext(), "Click On Image 1", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(QAActivity.this, QaFeedBackActivity.class);
				startActivity(intent);
		
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.qa, menu);
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
	
	private void selectType() {
		final String[] typeName = {
				"heart structure and function", "stress and your heart", "heart arteries normal/abnormal", "smoking and your heart" };

		final ArrayList<Integer> selList=new ArrayList<Integer>();
		final boolean[] finalSelected = new boolean[typeName.length];// finalSelected = 0;
		
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				new AlertDialog.Builder(QAActivity.this)
		        .setMultiChoiceItems(typeName, finalSelected, new OnMultiChoiceClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which, boolean isChecked) {
						// TODO Auto-generated method stub
						if(isChecked){
							selList.add(which);
						} else {
							if(selList.contains(which)){
								selList.remove(Integer.valueOf(which));
							}
						}
					}
				})
		        .setTitle("Choose 3 topics that you want to know next week")
		        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int whichButton) {
		                
		        		//parent.damageImageCaptureComplete(imageCaptured, selectedPosition+1);
		                
		                System.out.println("selList size " + selList.size());
		                
		                if(selList.size() != 3){
		                	
		                	showSelectTypeResult(false);
		                	
		                } else{
		                	dialog.dismiss();
		                	showSelectTypeResult(true);
			                
		                	// do post here
		                	// selList 是选择的结果，用index表示，从零开始。e.g 选择第一个和第二个选项，结果是 [0,1];
		                	// 用此index从typeName里面选择类型名称
		                	for(int i = 0; i< selList.size(); i++){
			                	System.out.println("selList index " + i + ": " + selList.get(i));
			                }
		                	
		                }
		            }
		        })
		        .setCancelable(true)		        
		        .show();
			}
		});
	}
	
	private void showSelectTypeResult(final boolean isThreeItem) {
		
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				final String resultTitle = "Choose Result";
				String resultMessage = "";
				
				if(isThreeItem){
					resultMessage = "Self Assessment sent.";
				} else {
					resultMessage = "Please choose three items.";
				}
				new AlertDialog.Builder(QAActivity.this)
		        .setTitle(resultTitle)
		        .setMessage(resultMessage)
		        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int whichButton) {
		                dialog.dismiss();
		        		if(!isThreeItem){
		        			selectType();
		        		}
		            }
		        })
		        .setCancelable(true)		        
		        .show();
			}
		});
	}
}
