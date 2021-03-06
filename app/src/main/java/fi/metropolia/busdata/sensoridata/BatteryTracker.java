package fi.metropolia.busdata.sensoridata;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by jasu on 25/11/2016.
 */

public class BatteryTracker extends Activity {

    // https://dzone.com/articles/getting-battery-level-android
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data);
        batteryLevel();
    }

    private void batteryLevel() {
        BroadcastReceiver batteryLevelReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                context.unregisterReceiver(this);
                int rawlevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                int batLevel = -1;
                if (rawlevel >= 0 && scale > 0) {
                    batLevel = (rawlevel * 100) / scale;
                }
                Log.e("Battery Level", "Remaining" + batLevel);
                DataContainer.setBatLevel(batLevel);
            }
        };
        IntentFilter batteryLevelFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryLevelReceiver, batteryLevelFilter);
    }
}