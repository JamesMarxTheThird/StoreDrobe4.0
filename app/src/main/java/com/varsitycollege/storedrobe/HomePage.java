package com.varsitycollege.storedrobe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    private Dialog addCategoryDialog;
    private Button addCatBTN;
    //private Button catDialogBTN;
    //private EditText catName;
    //private ItemClass itemClass;
    //private String itemCatTODB;
    //private CategoryClass catClass;
    private ImageButton goToViewItems;
    private ImageButton hatsBTN;
    private ImageButton shirtsBTN;
    private ImageButton pantsBTN;
    private ImageButton shoesBTN;
    private Spinner otherSpinner;
    private Button goalBTN;
    //private ArrayAdapter<String> otherAdapter;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference storeDrobeRef = database.getReference("Categories");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hatsBTN = findViewById(R.id.hatsButton);
        shirtsBTN = findViewById(R.id.shirtsButton);
        pantsBTN = findViewById(R.id.pantsButton);
        shoesBTN = findViewById(R.id.shoesButton);
        otherSpinner = findViewById(R.id.spinner2);
        //goalBTN = findViewById(R.id.itemGoalButton);

        //addCatBTN = findViewById(R.id.newCategoryButton);


        //addCategoryDialog = new Dialog(HomePage.this);
        //addCategoryDialog.setContentView(R.layout.activity_goal_pop_up_page);
        //addCategoryDialog.setCancelable(true);
        //addCategoryDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

/*
        addCatBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //addCategoryDialog.show();
                //Intent intent = new Intent (this, CategoryPopupPage.class);
                //startActivity(intent);
            }
        });



        goalBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addCategoryDialog.show();

            }
        });

        ArrayList<String> category_List = new ArrayList<>();
        categoryList.add("Categories");
        categoryList.add("Shirts");
        categoryList.add("Pants");
        categoryList.add("Hats");
        categoryList.add("Shoes");

        ArrayAdapter<String> category_Adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, category_List);

        otherSpinner.setAdapter(category_Adapter);

 */

    }

    public void goToPopUp (View v)
    {
        Intent intent = new Intent (this, CategoryPopupPage.class);
        startActivity(intent);
    }

    public void goToCategory (View v)
    {
        Intent intent = new Intent (this, HomePage.class);
        startActivity(intent);
    }

    public void setItemGoal()
    {

    }

    public void goToViewItemsPage(View v5){
        Intent addItem = new Intent (this, ViewAllItemsPage.class);
        startActivity(addItem);
    }

    public void addNewCategory(){

    }

    public void goToGoal(){

        Intent addItem = new Intent (this, ViewAllItemsPage.class);
        startActivity(addItem);

    }


}
/*


        hatsBTN = findViewById(R.id.hatsButton);
        shirtsBTN = findViewById(R.id.shirtsButton);
        pantsBTN = findViewById(R.id.pantsButton);
        shoesBTN = findViewById(R.id.shoesButton);

        hatsBTN.setOnClickListener(this);
        shirtsBTN.setOnClickListener(this);
        pantsBTN.setOnClickListener(this);
        shoesBTN.setOnClickListener(this);

    @Override
    public void onClick(View view){

        switch (view.getId()) {
            case R.id.hatsButton:

                break;
            case R.id.shirtsButton:

                break;
            case R.id.pantsButton:

                break;
            case R.id.shoesButton:

                break;
        }

        Intent intent = new Intent (HomePage.this, ViewAllItemsPage.class);
        startActivity(intent);
    }

 */