package com.xylinz.poc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class PendingIntentReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String flag = intent.getStringExtra("flag");
        Log.d("HACKER", "Dapet Flag 22: " + flag);
        Toast.makeText(context, "Flag: " + flag, Toast.LENGTH_LONG).show();
    }
}