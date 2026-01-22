package com.xylinz.poc;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Hextree extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hextree);

        // Kalo mo dapet flag 8 nyalain.
//        Intent resultFlag8 = new Intent();
//        resultFlag8.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag8Activity");
//        startActivityForResult(resultFlag8, 1337);

        Intent resultFlag9 = new Intent();
        resultFlag9.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag9Activity");
        startActivityForResult(resultFlag9, 1337);

    }


}