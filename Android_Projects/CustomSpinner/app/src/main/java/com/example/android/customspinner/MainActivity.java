package com.example.android.customspinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner customSpinner;
    private TextView displaySelected;
    private Button submit;
    private String selected="";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customSpinner = findViewById(R.id.customSpinner);
        submit = findViewById(R.id.submit);
        displaySelected = findViewById(R.id.displaySelected);
        // create spinneritemlist for spinner

        ArrayList<CustomItems> customList = new ArrayList<>();
        customList.add(new CustomItems("Android", R.drawable.ic_location_on_black_24dp));
        customList.add(new CustomItems("School", R.drawable.ic_location_on_black_24dp));
        customList.add(new CustomItems("satisfied", R.drawable.ic_location_on_black_24dp));
        customList.add(new CustomItems("shopping_cart", R.drawable.ic_location_on_black_24dp));
        customList.add(new CustomItems("whatshot", R.drawable.ic_location_on_black_24dp));

        // create Adapter for spinner
        CustomAdapter customAdapter = new CustomAdapter(this, customList);

        if (customSpinner != null)
        {
            customSpinner.setAdapter(customAdapter);
            customSpinner.setOnItemSelectedListener(this);
        }

        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                displaySelected.setText(selected);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        CustomItems items = (CustomItems) adapterView.getSelectedItem();
        Toast.makeText(this, items.getSpinnerText(), Toast.LENGTH_SHORT).show();
        selected = items.getSpinnerText();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {

    }
}