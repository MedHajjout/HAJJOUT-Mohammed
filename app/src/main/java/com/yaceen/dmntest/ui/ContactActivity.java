package com.yaceen.dmntest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.yaceen.dmntest.R;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String username = intent.getStringExtra("USER");

        TextView welcomeTxtView = findViewById(R.id.welcome);
        welcomeTxtView.setText("Welcome " + username);

        Button logout = findViewById(R.id.logout);

        logout.setOnClickListener(v -> {

            Intent goback = new Intent(ContactActivity.this, MainActivity.class);
            startActivity(goback);
            finish();
        });

        Button display = findViewById(R.id.btndisplay);

        display.setOnClickListener(v -> {

            Intent intent2 = new Intent(ContactActivity.this, ContactListActivity.class);
            startActivity(intent2);
        });

        Button add = findViewById(R.id.btnAdd);
        add.setOnClickListener(v -> {
            Intent intent1 = new Intent(ContactActivity.this, ContactFormActivity.class);
            startActivity(intent1);
        });




    }
}