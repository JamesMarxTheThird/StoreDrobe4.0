package com.varsitycollege.storedrobe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CategoryPopupPage extends AppCompatActivity {

    private Button addCatBTN;
    private EditText catName;
    private CategoryClass catClass;
    private Button catDialogBTN;
    private String awe;
    private Button backBTN2;

    //jamozayy check

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference storeDrobeRef = database.getReference("Data");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_popup_page);

        catDialogBTN = findViewById(R.id.categoryBTN);
        catName = findViewById(R.id.categoryTB);
        backBTN2 = findViewById(R.id.backButton2);

        //awe = "hats";
        catClass = new CategoryClass();

        catDialogBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String itemCatTODB = catName.getText().toString();
                catClass.setCategoryName(itemCatTODB);

                if(!TextUtils.isEmpty(itemCatTODB)){

                    //storeDrobeRef.push().setValue(catClass);
                    //storeDrobeRef.child(catClass.toString()).push().child("Pants").setValue(catClass);
                    storeDrobeRef.child("Categories").push().setValue(catClass);
                    Toast.makeText(CategoryPopupPage.this, "Category has been added!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(CategoryPopupPage.this, "Please fill in the category name field", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backBTN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (CategoryPopupPage.this, HomePage.class);
                startActivity(intent);

            }
        });

        /*
        catDialogBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CategoryPopupPage.this, "Button works", Toast.LENGTH_SHORT).show();

                String itemCatTODB = catName.getText().toString();
                catClass.setCategoryName(itemCatTODB);

                if(!TextUtils.isEmpty(itemCatTODB)){

                    storeDrobeRef.push().setValue(catClass);
                    Toast.makeText(CategoryPopupPage.this, "Category added", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(CategoryPopupPage.this, "Please fill in the category name field", Toast.LENGTH_SHORT).show();
                }



            }

        });

         */
    }
}