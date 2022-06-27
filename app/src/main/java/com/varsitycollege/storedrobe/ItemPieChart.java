package com.varsitycollege.storedrobe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;

public class ItemPieChart extends AppCompatActivity {

    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_pie_chart);

        pieChart = findViewById(R.id.pieChart);

        PieChartData();
    }

    public void PieChartData(){
        ArrayList<PieChart> data =  new ArrayList<>();

        //data.add(new PieChart(15, "hats"));
        pieChart.addPieSlice( new PieModel("Hats", 15, Color.parseColor("#FFA726")));
        pieChart.addPieSlice( new PieModel("Shirts", 30, Color.parseColor("#66BB6A")));
        pieChart.addPieSlice( new PieModel("Pants", 25, Color.parseColor("#EF5350")));
        pieChart.addPieSlice( new PieModel("Shoes", 30, Color.parseColor("#29B6F6")));

        pieChart.startAnimation();
    }

}