package com.example.android.coffeeshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity
{
    int numberOfCoffees=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view)
    {
        int total = CalculatePrice(numberOfCoffees);
        CheckBox whippedCream = (CheckBox) findViewById(R.id.cream);
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        EditText name = (EditText) findViewById(R.id.Name);
        String customer = name.getText().toString();
        int toppings=0;
        if(whippedCream.isChecked() && chocolate.isChecked())
        {
            toppings=numberOfCoffees*3;
            displayBill(total+toppings,customer,"Whipped Cream and Chocolate");
        }
        else if(chocolate.isChecked())
        {
            toppings=numberOfCoffees*2;
            displayBill(total+toppings,customer,"Chocolate");
        }
        else if(whippedCream.isChecked())
        {
            toppings=numberOfCoffees;
            displayBill(total+toppings,customer,"Whipped Cream");
        }
        else
        {
            displayBill(total,customer,"None");
        }

    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number)
    {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     *
     * This method displays the price of the given quantity of coffee
     */
    private void displayBill(int total,String name, String topping)
    {
        TextView priceTextView = (TextView) findViewById(R.id.bill);
        createOrderSummary(total,name,topping);
    }

    public void increment(View view)
    {
        if(numberOfCoffees<100)
        {
            numberOfCoffees++;
            display(numberOfCoffees);
        }
    }

    public void decrement(View view)
    {
        if(numberOfCoffees>0)
        {
            numberOfCoffees--;
            display(numberOfCoffees);
        }
    }

    private int CalculatePrice(int num)
    {
        int price = num*5;
        return price;
    }

    private void createOrderSummary(int total,String name,String topping)
    {
        TextView billTextView = (TextView) findViewById(R.id.bill);
        String summary="Name : "+name +"\nQuantity : " +numberOfCoffees+"\nTopping : "+ topping+"\nTotal : $"+total +"\nThank You!";
        billTextView.setText(summary);
        String [] addresses = {"k164047@nu.edu.pk","k164064@nu.edu.pk","k164068@nu.edu.pk"};
        composeEmail(addresses,"Coffee Shop Bill Summary",summary);
    }

    public void composeEmail(String[] addresses, String subject,String summary)
    {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT,summary);
        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }
    }

}