package tw.com.ace.myPlugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Contacts.People;
import android.view.View;
import android.widget.Button;

    /**
     * This class echoes a string called from JavaScript.
     */
    public class Echo extends CordovaPlugin {

        @Override
        public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
            if (action.equals("echo")) {
                String message = args.getString(0);
                
				/*
				//操作：发送一个广播，广播接收后Toast提示定时操作完成     
				Intent intent = new Intent(Main.this, alarmreceiver.class);
    			intent.setAction("short");
			    PendingIntent sender = PendingIntent.getBroadcast(Main.this, 0, intent, 0);
    
			    //设定一个五秒后的时间
    			//Calendar calendar=Calendar.getInstance();
			    //calendar.setTimeInMillis(System.currentTimeMillis());
    			//calendar.add(Calendar.SECOND, 5);
    
			    AlarmManager alarm=(AlarmManager)getSystemService(ALARM_SERVICE);
			    //alarm.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
			    
				//或者以下面方式简化
			    alarm.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+5*1000, sender);
				
				*/
				Toast.makeText(this,"訊息",Toast.LENGTH_SHORT).show(); 

                this.echo(message, callbackContext);
                return true;
            }
            return false;
        }

        private void echo(String message, CallbackContext callbackContext) {
            if (message != null && message.length() > 0) {
                callbackContext.success(message);
            } else {
                callbackContext.error("Expected one non-empty string argument.");
            }
        }
    }
