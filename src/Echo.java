package tw.com.ace.myPlugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
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
                
				//使用Calendar指定時間
				Calendar calendar = Calendar.getInstance();
				calendar.set(2014, 7, 8, 16, 30);

				//建立意圖
				Intent intent = new Intent();

				//這裡的 this 是指當前的 Activity
				//AlarmReceiver.class 則是負責接收的 BroadcastReceiver
				intent.setClass(this, AlarmReceiver.class);

				//建立待處理意圖
				PendingIntent pending = PendingIntent.getBroadcast(context, 0, intent, 0);

				//取得AlarmManager
				AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

				//設定一個警報
				//參數1,我們選擇一個會在指定時間喚醒裝置的警報類型
				//參數2,將指定的時間以millisecond傳入
				//參數3,傳入待處理意圖
				alarm.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending);
				

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
