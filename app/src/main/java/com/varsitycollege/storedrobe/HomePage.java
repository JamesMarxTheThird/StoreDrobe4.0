package com.varsitycollege.storedrobe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    private Dialog addCategoryDialog;
    private Dialog addGoalDialog;
    private Button addCatBTN;
    //private Button catDialogBTN;
    //private EditText catName;
    private ItemClass itemClass;
    //private String itemCatTODB;
    //private CategoryClass catClass;
    private ImageButton goToViewItems;
    private ImageButton hatsBTN;
    private ImageButton shirtsBTN;
    private ImageButton pantsBTN;
    private ImageButton shoesBTN;
    private Spinner otherSpinner;
    private Button goalBTN;
    private ProgressBar goalPB;
    private Button getCurrentGoalAmount;
    private TextView goalTV;
    private DatabaseReference entriesRef;
    private int countNumInCat;
    private String currentChild;
    private ArrayAdapter<String> otherAdapter;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference storeDrobeRef = database.getReference("Categories");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entriesRef = FirebaseDatabase.getInstance().getReference().child("ItemCategories");

        hatsBTN = findViewById(R.id.hatsButton);
        shirtsBTN = findViewById(R.id.shirtsButton);
        pantsBTN = findViewById(R.id.pantsButton);
        shoesBTN = findViewById(R.id.shoesButton);
        otherSpinner = findViewById(R.id.categoryChoiceSpinner);
        goalBTN = findViewById(R.id.itemGoalButton);
        goalPB = findViewById(R.id.goalPB1);
        getCurrentGoalAmount = findViewById(R.id.viewCurrentGoalAmount);
        goalTV = findViewById(R.id.goalAmountTV);

        goalPB.isShown();
        goalPB.setMax(99);

        goalPB.setProgress(24);


        entriesRef.child("Pants").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists())
                {

                    countNumInCat = (int) snapshot.getChildrenCount();

                    goalTV.setText(Integer.toString(countNumInCat));

                }

                else
                {
                    Toast.makeText(HomePage.this, "Didnt count shit", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


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





 */
        addGoalDialog = new Dialog(HomePage.this);
        addGoalDialog.setContentView(R.layout.activity_goal_pop_up_page);
        addGoalDialog.setCancelable(true);
        addGoalDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

      //  ArrayList<String> category_List = new ArrayList<>();
       // category_List.add("Categories");
       // category_List.add("Shirts");
       // category_List.add("Pants");
       // category_List.add("Hats");
      //  category_List.add("Shoes");

       // ArrayAdapter<String> category_Adapter = new ArrayAdapter<String>(this,
              //  android.R.layout.simple_spinner_dropdown_item, category_List);

      //  otherSpinner.setAdapter(category_Adapter);

        goalBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addGoalDialog.show();
               // Intent intent = new Intent(this, )
                //addCategoryDialog.show();

            }
        });



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