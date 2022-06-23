package com.varsitycollege.storedrobe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewItemsPage extends AppCompatActivity {

    private FirebaseAuth loginAuth;

    private ListView listViewItem2;
    private List<String> itemList2;
    private ItemClass itemClass;
    private ArrayAdapter<String> itemsAdapter2;
    private String cat;



    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference storeDrobeRef = database.getReference("ItemCategories");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items_page);

// Initialize Firebase Auth
        loginAuth = FirebaseAuth.getInstance();

        itemList2 = new ArrayList<>();
        listViewItem2 = findViewById(R.id.itemsLV2);
        itemClass = new ItemClass();
        //cat = "Pants";


        storeDrobeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot addLVSS) {
                for (DataSnapshot itemDetails : addLVSS.getChildren()){
                    //String email = itemHistory.child("Hats").getValue(String.class);

                        ItemClass item2 = itemDetails.getValue(ItemClass.class);
                        itemList2.add(item2.toString());

                }

                itemsAdapter2 = new ArrayAdapter<String>(ViewItemsPage.this, android.R.layout.simple_list_item_1, itemList2);
                listViewItem2.setAdapter(itemsAdapter2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ViewItemsPage.this, "Error! No data found.", Toast.LENGTH_SHORT).show();

            }
        });
        /*
        loginAuth.signInWithCustomToken(LoginToken)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCustomToken:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCustomToken:failure", task.getException());
                            Toast.makeText(CustomAuthActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });

         */


    }

    /*
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = loginAuth.getCurrentUser();
        updateUI(currentUser);
    }

     */



    public void goToAddItemPage(View v2){
        Intent intent = new Intent (this, AddNewItemPage.class);
        startActivity(intent);
    }


}