package com.example.voicetogglecontrolledhomeautomation;
import java.util.ArrayList;

import java.util.List;

import com.example.voicetogglecontrolledhomeautomation.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.RecognizerIntent;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.ToggleButton;

public class Frame2 extends Activity {

   @SuppressLint("NewApi")
  
  
   // Debugging
   private static final String TAG = "BluetoothChat";
   private static final boolean D = true;

   // Message types sent from the BluetoothChatService Handler
   public static final int MESSAGE_STATE_CHANGE = 1;
   public static final int MESSAGE_READ = 2;
   public static final int MESSAGE_WRITE = 3;
   public static final int MESSAGE_DEVICE_NAME = 4;
   public static final int MESSAGE_TOAST = 5;

   // Key names received from the BluetoothChatService Handler
   public static final String DEVICE_NAME = "device_name";
   public static final String TOAST = "toast";

   // Intent request codes
   private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
   private static final int REQUEST_CONNECT_DEVICE_INSECURE = 2;
   private static final int REQUEST_ENABLE_BT = 3;

   Button speak;
	TextView display;
	private static final int REQUEST_CODE = 1234;

   // Name of the connected device
   private String mConnectedDeviceName = null;
   // Array adapter for the conversation thread
   //private ArrayAdapter<String> mConversationArrayAdapter;
   // String buffer for outgoing messages
   private StringBuffer mOutStringBuffer;
   // Local Bluetooth adapter
   private BluetoothAdapter mBluetoothAdapter = null;
   // Member object for the chat services
   private BluetoothChatService mChatService = null;


   @Override
 
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       if(D) Log.e(TAG, "+++ ON CREATE +++");

       // Set up the window layout
       setContentView(R.layout.activityframe2);
       speak = (Button) findViewById(R.id.button4);
		display = (TextView) findViewById(R.id.textView1);
		
		PackageManager pm = getPackageManager();
		
		  List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(
		
		    RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
		
		  if (activities.size() == 0) {
		
		   speak.setEnabled(false);
		
		   Toast.makeText(getApplicationContext(), "Recognizer Not Found",Toast.LENGTH_SHORT).show();
		
		  }
		

       // Get local Bluetooth adapter
       mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

       // If the adapter is null, then Bluetooth is not supported
       if (mBluetoothAdapter == null) {
           Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
           finish();
           return;
       }
       getActionBar().setDisplayHomeAsUpEnabled(true);
       
      
       
   }

   @Override
   public void onStart() {
       super.onStart();
       if(D) Log.e(TAG, "++ ON START ++");

       // If BT is not on, request that it be enabled.
       // setupChat() will then be called during onActivityResult
       if (!mBluetoothAdapter.isEnabled()) {
           Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
           startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
       // Otherwise, setup the chat session
       } else {
           if (mChatService == null) setupChat();
       }
   }

   @Override
   public synchronized void onResume() {
       super.onResume();
       if(D) Log.e(TAG, "+ ON RESUME +");

       // Performing this check in onResume() covers the case in which BT was
       // not enabled during onStart(), so we were paused to enable it...
       // onResume() will be called when ACTION_REQUEST_ENABLE activity returns.
       if (mChatService != null) {
           // Only if the state is STATE_NONE, do we know that we haven't started already
           if (mChatService.getState() == BluetoothChatService.STATE_NONE) {
             // Start the Bluetooth chat services
             mChatService.start();
           }
       }
   }

   private void setupChat() {
       Log.d(TAG, "setupChat()");

       // Initialize the array adapter for the conversation thread
     //  mConversationArrayAdapter = new ArrayAdapter<String>(this, R.layout.message);
      // mConversationView = (ListView) findViewById(R.id.in);
      // mConversationView.setAdapter(mConversationArrayAdapter);

       // Initialize the compose field with a listener for the return key
       //mOutEditText = (EditText) findViewById(R.id.edit_text_out);
       //mOutEditText.setOnEditorActionListener(mWriteListener);

       // Initialize the send button with a listener that for click events
       
      /* speak.setOnClickListener(new OnClickListener() {
           public void onClick(View v) {
               // Send a message using content of the edit text widget
         //      TextView view = (TextView) findViewById(R.id.edit_text_out);
          //     String message = view.getText().toString();
            //   String message="a";
              // sendMessage(message);
           	startVoiceRecognitionActivity();
           	}
           });*/
       Button button1 = (Button) findViewById(R.id.Button1);

       button1.setOnClickListener(new View.OnClickListener() {
       public void onClick(View view) {
       startOperation1();
       }
       });



       Button button2 = (Button) findViewById(R.id.Button2);

       button2.setOnClickListener(new View.OnClickListener() {
       public void onClick(View view) {
       startOperation2();
       }
       });




       Button button3 = (Button) findViewById(R.id.Button3);

       button3.setOnClickListener(new View.OnClickListener() {
       public void onClick(View view) {
       startOperation3();
       }
       });




       Button button4 = (Button) findViewById(R.id.Button4);

       button4.setOnClickListener(new View.OnClickListener() {
       public void onClick(View view) {
       startOperation4();
       }
       });




       Button button5 = (Button) findViewById(R.id.Button5);

       button5.setOnClickListener(new View.OnClickListener() {
       public void onClick(View view) {
       startOperation5();
       }
       });





       Button button6 = (Button) findViewById(R.id.Button6);

       button6.setOnClickListener(new View.OnClickListener() {
       public void onClick(View view) {
       startOperation6();
       }
       });




       Button button7 = (Button) findViewById(R.id.Button7);

       button7.setOnClickListener(new View.OnClickListener() {
       public void onClick(View view) {
       startOperation7();
       }
       });






       Button button8 = (Button) findViewById(R.id.Button8);

       button8.setOnClickListener(new View.OnClickListener() {
       public void onClick(View view) {
       startOperation8();
       }
       });

               
               

               // Initialize the BluetoothChatService to perform bluetooth connections
               mChatService = new BluetoothChatService(this, mHandler);

               // Initialize the buffer for outgoing messages
               mOutStringBuffer = new StringBuffer("");
               
               
               
               
           }
           
           
           
           
         //startOperations() functions in onClicks of buttons were defined here


           private void startOperation1() {
           sendMessage("1");
           Toast.makeText(getApplicationContext(), "Toggle Load1",Toast.LENGTH_SHORT).show();
           }




           private void startOperation2() {
           sendMessage("2");
           Toast.makeText(getApplicationContext(), "Toggle Load2",Toast.LENGTH_SHORT).show();
           }






           private void startOperation3() {
           sendMessage("3");
           Toast.makeText(getApplicationContext(), "Toggle Load3",Toast.LENGTH_SHORT).show();
           }






           private void startOperation4() {
           sendMessage("4");
           Toast.makeText(getApplicationContext(), "Toggle Load4",Toast.LENGTH_SHORT).show();
           }






           private void startOperation5() {
           sendMessage("5");
           Toast.makeText(getApplicationContext(), "Toggle Load5",Toast.LENGTH_SHORT).show();
           }





           private void startOperation6() {
           sendMessage("6");
           Toast.makeText(getApplicationContext(), "Toggle Load6",Toast.LENGTH_SHORT).show();
           }





           private void startOperation7() {
           sendMessage("7");
           Toast.makeText(getApplicationContext(), "Toggle Load7",Toast.LENGTH_SHORT).show();
           }




           private void startOperation8() {
           sendMessage("8");
           Toast.makeText(getApplicationContext(), "Toggle Load8",Toast.LENGTH_SHORT).show();
           }





   
   
   
   
   
   
  /* private void startVoiceRecognitionActivity() {
		
		  Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		
		  intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
		
		    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		
		  intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
		
		    "ASSISTIVE TECHNOLOGICAL LAB...");
		
		  startActivityForResult(intent, REQUEST_CODE);
		
		 }
	*/

   @Override
   public synchronized void onPause() {
       super.onPause();
       if(D) Log.e(TAG, "- ON PAUSE -");
   }

   @Override
   public void onStop() {
       super.onStop();
       if(D) Log.e(TAG, "-- ON STOP --");
   }

   @Override
   public void onDestroy() {
       super.onDestroy();
       // Stop the Bluetooth chat services
       if (mChatService != null) mChatService.stop();
       if(D) Log.e(TAG, "--- ON DESTROY ---");
   }

   private void ensureDiscoverable() {
       if(D) Log.d(TAG, "ensure discoverable");
       if (mBluetoothAdapter.getScanMode() !=
           BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
           Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
           discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
           startActivity(discoverableIntent);
       }
   }

   /**
    * Sends a message.
    * @param message  A string of text to send.
    */
   private void sendMessage(String message) {
       // Check that we're actually connected before trying anything
       if (mChatService.getState() != BluetoothChatService.STATE_CONNECTED) {
           Toast.makeText(this, R.string.not_connected, Toast.LENGTH_SHORT).show();
           return;
       }

       // Check that there's actually something to send
       if (message.length() > 0) {
           // Get the message bytes and tell the BluetoothChatService to write
           byte[] send = message.getBytes();
           mChatService.write(send);

           // Reset out string buffer to zero and clear the edit text field
           mOutStringBuffer.setLength(0);
           //mOutEditText.setText(mOutStringBuffer);
       }
   }

   // The action listener for the EditText widget, to listen for the return key
   private TextView.OnEditorActionListener mWriteListener = new TextView.OnEditorActionListener() {
       public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
           // If the action is a key-up event on the return key, send the message
           if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_UP) {
               String message = view.getText().toString();
               sendMessage(message);
           }
           if(D) Log.i(TAG, "END onEditorAction");
           return true;
       }
   };

   private final void setStatus(int resId) {
       getActionBar();
   }

   @SuppressWarnings("unused")
private final void setStatus(CharSequence subTitle) {
       getActionBar();
   }

   // The Handler that gets information back from the BluetoothChatService
   @SuppressLint("HandlerLeak")
private final Handler mHandler = new Handler() {
       @Override
       public void handleMessage(Message msg) {
           switch (msg.what) {
           case MESSAGE_STATE_CHANGE:
               if(D) Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
               switch (msg.arg1) {
               case BluetoothChatService.STATE_CONNECTED:
                  // setStatus(getString(R.string.title_connected_to, mConnectedDeviceName));
     //              mConversationArrayAdapter.clear();
                   break;
               case BluetoothChatService.STATE_CONNECTING:
                   setStatus(R.string.title_connecting);
                   break;
               case BluetoothChatService.STATE_LISTEN:
               case BluetoothChatService.STATE_NONE:
                   setStatus(R.string.title_not_connected);
                   break;
               }
               break;
           case MESSAGE_WRITE:
               byte[] writeBuf = (byte[]) msg.obj;
               new String(writeBuf);
       //        mConversationArrayAdapter.add("Me:  " + writeMessage);
               break;
           case MESSAGE_READ:
               byte[] readBuf = (byte[]) msg.obj;
               new String(readBuf, 0, msg.arg1);
         //      mConversationArrayAdapter.add(mConnectedDeviceName+":  " + readMessage);
               break;
           case MESSAGE_DEVICE_NAME:
               // save the connected device's name
               mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
               Toast.makeText(getApplicationContext(), "Connected to "
                              + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
               break;
   	 
           case MESSAGE_TOAST:
               Toast.makeText(getApplicationContext(), msg.getData().getString(TOAST),
                              Toast.LENGTH_SHORT).show();
               break;
           }
       }
   };

   public void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(D) Log.d(TAG, "onActivityResult " + resultCode);
       switch (requestCode) {
       case REQUEST_CONNECT_DEVICE_SECURE:
           // When DeviceListActivity returns with a device to connect
       	
       	
           if (resultCode == Activity.RESULT_OK) {
           System.out.println(" result we  got");
           	connectDevice(data, true);
           }
           break;
       case REQUEST_CONNECT_DEVICE_INSECURE:
           // When DeviceListActivity returns with a device to connect
           if (resultCode == Activity.RESULT_OK) {
               connectDevice(data, false);
           }
           break;
       /*case REQUEST_CODE:
       	if(resultCode == RESULT_OK) {
				  ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
	 
	                display.setText(text.get(0));
	              String display1=display.getText().toString();
               String msg1="red";
               String msg2="black";
               String msg3="blue";
               String msg4="yellow";
               String msg5="orange";
               String msg6="green";
               String msg7="white";
               String msg8="purple";
               if(display1.equals(msg1))
               {	sendMessage("1");
               	Toast.makeText(getApplicationContext(), "Toggle Load1",Toast.LENGTH_SHORT).show();
               }
               if(display1.equals(msg2))
               {	sendMessage("2");
               	Toast.makeText(getApplicationContext(), "Toggle Load2",Toast.LENGTH_SHORT).show();
               }
               if(display1.equals(msg3))
               {	sendMessage("3");
               	Toast.makeText(getApplicationContext(), "Toggle Load3",Toast.LENGTH_SHORT).show();
               }
               if(display1.equals(msg4))
               {	sendMessage("4");
               	Toast.makeText(getApplicationContext(), "Toggle Load4",Toast.LENGTH_SHORT).show();
               }
               if(display1.equals(msg5))
               {	sendMessage("5");
               	Toast.makeText(getApplicationContext(), "Toggle Load5",Toast.LENGTH_SHORT).show();
               }
               if(display1.equals(msg6))
               {	sendMessage("6");
               	Toast.makeText(getApplicationContext(), "Toggle Load6",Toast.LENGTH_SHORT).show();
               }
               if(display1.equals(msg7))
               {	sendMessage("7");
               	Toast.makeText(getApplicationContext(), "Toggle Load7",Toast.LENGTH_SHORT).show();
               }
               if(display1.equals(msg8))
               {	sendMessage("8");
               	Toast.makeText(getApplicationContext(), "Toggle Load8",Toast.LENGTH_SHORT).show();
               }
    	   }
       	
     	  super.onActivityResult(requestCode, resultCode, data);
     	  startVoiceRecognitionActivity();
     	  
     	  break;
     	  */
       case REQUEST_ENABLE_BT:
           // When the request to enable Bluetooth returns
           if (resultCode == Activity.RESULT_OK) {
               // Bluetooth is now enabled, so set up a chat session
               setupChat();
           } else {
               // User did not enable Bluetooth or an error occurred
               Log.d(TAG, "BT not enabled");
               Toast.makeText(this, R.string.bt_not_enabled_leaving, Toast.LENGTH_SHORT).show();
               finish();
           }
       }
   }

   private void connectDevice(Intent data, boolean secure) {
       // Get the device MAC address
       String address = data.getExtras()
           .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
       // Get the BluetoothDevice object
       BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
       // Attempt to connect to the device
       mChatService.connect(device, secure);
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater inflater = getMenuInflater();
       inflater.inflate(R.menu.bluetooth_chat, menu);
       getMenuInflater().inflate(R.menu.bluetooth_chat, menu);
       return true;
   }
     
   @Override
  /* public boolean onOptionsItemSelected(MenuItem item) {
	      switch (item.getItemId()) {
	         // Respond to the action bar's Up/Home button
	        
	      }
	      return 
	   }*/
	  
   public boolean onOptionsItemSelected(MenuItem item) {
       Intent serverIntent = null;
       switch (item.getItemId()) {
       case R.id.secure_connect_scan:
           // Launch the DeviceListActivity to see devices and do scan
           serverIntent = new Intent(this, DeviceListActivity.class);
           startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_SECURE);
           return true;
       case R.id.insecure_connect_scan:
           // Launch the DeviceListActivity to see devices and do scan
           serverIntent = new Intent(this, DeviceListActivity.class);
           startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_INSECURE);
           return true;
       case R.id.discoverable:
           // Ensure this device is discoverable by others
           ensureDiscoverable();
           return true;
       case android.R.id.home:
	         NavUtils.navigateUpFromSameTask(this);
	         return true;
       }
       return super.onOptionsItemSelected(item);
   }
   @Override
   public void onBackPressed() {
      moveTaskToBack(true); 
      Frame2.this.finish();
   }

public TextView.OnEditorActionListener getmWriteListener() {
	return mWriteListener;
}

public void setmWriteListener(TextView.OnEditorActionListener mWriteListener) {
	this.mWriteListener = mWriteListener;
}

}


