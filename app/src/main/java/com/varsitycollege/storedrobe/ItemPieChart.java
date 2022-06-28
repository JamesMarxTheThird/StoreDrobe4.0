package com.varsitycollege.storedrobe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;

public class ItemPieChart extends AppCompatActivity {

    private PieChart pieChart;
    private DatabaseReference countRef;
    private int catCount;
    private TextView tvHats;
    private TextView tvShirts;
    private TextView tvPants;
    private TextView tvShoes;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    //DatabaseReference storeDrobeRef = database.getReference("Categories");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_pie_chart);

        countRef = FirebaseDatabase.getInstance().getReference().child("ItemCategories");

        pieChart = findViewById(R.id.pieChart);
        tvHats = findViewById(R.id.tvHats);
        tvShirts = findViewById(R.id.tvShirts);
        tvPants = findViewById(R.id.tvPants);
        tvShoes = findViewById(R.id.tvShoes);

        PieChartData();
    }

    public void PieChartData(){

        countRef.child("Hats").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {

                    catCount = (int) snapshot.getChildrenCount();
                    tvHats.setText(String.valueOf(catCount));

                    pieChart.addPieSlice( new PieModel("Hats", catCount, Color.parseColor("#FFA726")));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ItemPieChart.this, "Could not find required information", Toast.LENGTH_SHORT).show();

            }
        });

        countRef.child("Shirts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {

                    catCount = (int) snapshot.getChildrenCount();
                    tvShirts.setText(String.valueOf(catCount));

                    pieChart.addPieSlice( new PieModel("Shirts", catCount, Color.parseColor("#66BB6A")));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ItemPieChart.this, "Could not find required information", Toast.LENGTH_SHORT).show();

            }
        });

        countRef.child("Pants").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {

                    catCount = (int) snapshot.getChildrenCount();
                    tvPants.setText(String.valueOf(catCount));

                    pieChart.addPieSlice( new PieModel("Pants", catCount, Color.parseColor("#EF5350")));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ItemPieChart.this, "Could not find required information", Toast.LENGTH_SHORT).show();

            }
        });

        countRef.child("Shoes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {

                    catCount = (int) snapshot.getChildrenCount();
                    tvShoes.setText(String.valueOf(catCount));

                    pieChart.addPieSlice( new PieModel("Shoes", catCount, Color.parseColor("#29B6F6")));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ItemPieChart.this, "Could not find required information", Toast.LENGTH_SHORT).show();

            }
        });

        pieChart.startAnimation();
    }

}