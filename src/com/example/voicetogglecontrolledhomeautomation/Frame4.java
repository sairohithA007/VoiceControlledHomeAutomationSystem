package com.example.voicetogglecontrolledhomeautomation;
import com.example.voicetogglecontrolledhomeautomation.R;


import android.annotation.SuppressLint;
import android.app.Activity;
//import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
//import android.view.View;

public class Frame4 extends Activity {

   @SuppressLint("NewApi")
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
  	setContentView(R.layout.activityframe4);
      getActionBar().setDisplayHomeAsUpEnabled(true);
   }
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.bluetooth_chat, menu);
      return true;
   }  
   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId()) {
         // Respond to the action bar's Up/Home button
         case android.R.id.home:
         NavUtils.navigateUpFromSameTask(this);
         return true;
      }
      return super.onOptionsItemSelected(item);
   }
   @Override
   public void onBackPressed() {
      moveTaskToBack(true); 
      Frame4.this.finish();
   }

}


