package com.xylinz.poc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Flag 8 & 9 - Receive Result, harus pake startActivityForResult() biar nilainya ga null & ngembaliin nama class nya
        Button flag8 = findViewById(R.id.flag89_button);
        Intent flag89Intent = new Intent(ActivityResult.this, Hextree.class);

        flag8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(flag89Intent);
            }
        });

    }
}