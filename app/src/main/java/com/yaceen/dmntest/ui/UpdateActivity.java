package com.yaceen.dmntest.ui;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.yaceen.dmntest.R;
import com.yaceen.dmntest.helpers.DatabaseHelper;
import com.yaceen.dmntest.model.Contact;

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);


        int idContact = getIntent().getIntExtra("id", -1);
        Contact contact = DatabaseHelper.getInstance(this).getContactById(idContact);

        EditText edId = findViewById(R.id.edIDUpdate);
        edId.setText(String.valueOf(contact.getId()));
        edId.setEnabled(false);

        EditText edName = findViewById(R.id.edNameUpdate);
        edName.setText(contact.getName());

        EditText edPhone = findViewById(R.id.edContactUpdate);
        edPhone.setText(contact.getPhone());


        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(v -> {
            String newName = edName.getText().toString();
            String newPhone = edPhone.getText().toString();

            SQLiteDatabase database = DatabaseHelper.getInstance(this).getWritableDatabase();
            database.execSQL(
                    "UPDATE contacts SET nom = ?, phone = ? WHERE id = ?",
                    new Object[]{newName, newPhone, contact.getId()}
            );


            Intent intent = new Intent(this, ContactListActivity.class);
            startActivity(intent);
            finish();
        });

    }
}