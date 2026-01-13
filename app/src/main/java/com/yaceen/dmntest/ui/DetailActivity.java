package com.yaceen.dmntest.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.yaceen.dmntest.helpers.DatabaseHelper;
import com.yaceen.dmntest.model.Contact;
import com.yaceen.dmntest.data.DataBase;
import com.yaceen.dmntest.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent detail = getIntent();
        int id = detail.getIntExtra("id", -1);

        Contact contact = DatabaseHelper.getInstance(this).getContactById(id);

        TextView name = findViewById(R.id.tvname);
        TextView phone = findViewById(R.id.tvphone);

        name.setText(contact.getName());
        phone.setText(contact.getPhone());

        Button deleteItem = findViewById(R.id.btndelete);
        deleteItem.setOnClickListener( v -> {
            new AlertDialog.Builder(this).setTitle("Title")
                    .setMessage("azertyui")
                    .setPositiveButton("Delete", (d,w) -> {
                        SQLiteDatabase db = DatabaseHelper.getInstance(this).getWritableDatabase();
                        db.execSQL("DELETE FROM contacts where id = ?", new Integer[]{contact.getId()});
                        //DataBase.contactList.remove(position);
                        Intent back = new Intent(this, ContactListActivity.class);
                        startActivity(back);
                        finish();
                    })
                    .setNegativeButton("Cancel",null)
                    .show();

        });

        Button btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(v -> {
            Intent in = new Intent(this, UpdateActivity.class);
            in.putExtra("id", contact.getId());
            startActivity(in);
            finish();
        });
    }
}