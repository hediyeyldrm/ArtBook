package com.hediyeyildirim.artbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> nameArray;
    ArrayList<Integer> idArray;
    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        nameArray = new ArrayList<String>();
        idArray = new ArrayList<Integer>();

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, nameArray);
        listView.setAdapter(arrayAdapter);


        getData();

    }

    public void getData() {

        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Arts", MODE_PRIVATE, null);

            Cursor cursor = database.rawQuery("SELECT * FROM arts", null);
            int nameIx = cursor.getColumnIndex("artname");
            int idIx = cursor.getColumnIndex("id");

            while (cursor.moveToNext()) {
                nameArray.add(cursor.getString(nameIx));
                idArray.add(cursor.getInt(idIx));

            }
            arrayAdapter.notifyDataSetChanged(); //yeni eklenen verileri listede gösterir.
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //Inflater
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_art, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.add_art_item) {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}