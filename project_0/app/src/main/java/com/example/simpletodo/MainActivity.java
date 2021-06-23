package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.FileUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> items;

    Button addButton;
    EditText editTextItem;
    RecyclerView rvItems;
    ItemsAdpater itadap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.addButton);
        editTextItem = findViewById(R.id.editTextItem);
        rvItems = findViewById(R.id.rvItems);

        loadItems();

        ItemsAdpater.OnLongClickListener onLong = new ItemsAdpater.OnLongClickListener(){
            @Override
            public void onItemLongClicked(int position) {
                //delete from the model
                items.remove(position);
                //notify at which position we deleted an item
                itadap.notifyItemRemoved(position);
                Toast.makeText(getApplicationContext(), "item was removed!", Toast.LENGTH_SHORT).show();
                saveItems();
            }
        };
        itadap = new ItemsAdpater(items, onLong);
        rvItems.setAdapter(itadap);
        rvItems.setLayoutManager(new LinearLayoutManager(this));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoItem = editTextItem.getText().toString();
                //add item to the model
                items.add(todoItem);
                //notify that we inserted an item
                itadap.notifyItemInserted(items.size() - 1);
                //clear the text box after we add to model
                editTextItem.setText("");
                Toast.makeText(getApplicationContext(), "item was added!", Toast.LENGTH_SHORT).show();
                saveItems();
            }
        });
    }

    private File getDataFile(){
        return new File(getFilesDir(), "data.txt");
    }

    //this function will load items by reading every line of the data file
    private void loadItems(){
        try {
            items = new ArrayList<>(org.apache.commons.io.FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
        }catch(IOException e){
           Log.e("MainActivity", "error reading items", e);
           items = new ArrayList<>();
        }
    }

    //this function saves items by writing them into data file
    private void saveItems(){
        try {
            org.apache.commons.io.FileUtils.writeLines(getDataFile(),items);
        } catch (IOException e) {
            Log.e("MainActivity", "error writing items", e);
        }
    }

}