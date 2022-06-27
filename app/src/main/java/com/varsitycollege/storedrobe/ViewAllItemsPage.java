package com.varsitycollege.storedrobe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewAllItemsPage extends AppCompatActivity {

    private ListView listView_Item;
    private List<String> item_List;
    private ItemClass item_Class;
    private ArrayAdapter<String> itemsAdapter;
    private String cat;
    private Button addItemsBTN;
    private String childValue;
    private HomePage hp = new HomePage();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference storeDrobeRef = database.getReference("ItemCategories");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_items_page);

        item_List = new ArrayList<>();
        listView_Item = findViewById(R.id.itemsLV2);
        item_Class = new ItemClass();
        addItemsBTN = findViewById(R.id.addItemButton);
        cat = hp.btnVal;


        storeDrobeRef.child("pants").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot itemHistory : snapshot.getChildren()){
                    //String email = itemHistory.child("Hats").getValue(String.class);

                    ItemClass item = itemHistory.getValue(ItemClass.class);
                    item_List.add(item.toString());

                }

                itemsAdapter = new ArrayAdapter<String>(ViewAllItemsPage.this, android.R.layout.simple_list_item_1, item_List);
                listView_Item.setAdapter(itemsAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ViewAllItemsPage.this, "Error! No data found.", Toast.LENGTH_SHORT).show();

            }
        });




        addItemsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (ViewAllItemsPage.this, AddNewItemPage.class);
                startActivity(intent);

            }
        });


    }

}