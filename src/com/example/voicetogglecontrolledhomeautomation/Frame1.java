package com.example.voicetogglecontrolledhomeautomation;

import android.support.v7.app.ActionBarActivity;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;
import android.view.View;


public class Frame1 extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_frame1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bluetooth_chat, menu);
		return true;
	}
	public void toggle(View view){
	      Intent intent = new Intent(this,com.example.voicetogglecontrolledhomeautomation.Frame2.class);
	      startActivity(intent);
	   }

	public void voice(View view){
	      Intent intent = new Intent(this,com.example.voicetogglecontrolledhomeautomation.Frame3.class);
	      startActivity(intent);
	   }
	public void about(View view){
	      Intent intent = new Intent(this,com.example.voicetogglecontrolledhomeautomation.Frame4.class);
	      startActivity(intent);
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
