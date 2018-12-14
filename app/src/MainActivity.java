package com.example.admin.justjava;



         import android.content.Intent;
         import android.net.Uri;
         import android.os.Bundle;
         import android.support.v7.app.AppCompatActivity;
         import android.util.Log;
         import android.view.View;
         import android.widget.CheckBox;
         import android.widget.EditText;
         import android.widget.TextView;
         import android.widget.Toast;

         import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        EditText editText = (EditText) findViewById(R.id.name);
        String name = editText.getText().toString();
        int price=calculatePrice(hasWhippedCream,hasChocolate);
        String priceMessage= createOrderSummary(price,hasWhippedCream,hasChocolate,name) ;

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_SUBJECT, "Just java order for " + name); //extra_subject is to add subject specification for an email
            intent.putExtra(Intent.EXTRA_TEXT,priceMessage);

        if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
    }

    /**
     * method to calculate price of number of coffees
     */
    public int calculatePrice(boolean addWhippedCream,boolean addChocolate)
    {
        int basePrice=5;
        if(addWhippedCream)
            basePrice+=1;
        if(addChocolate)
            basePrice+=2;
        return basePrice*quantity;
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * method to display order summary
     */

    public String createOrderSummary(int price,boolean addWhippedCream,boolean addChocolate,String name){
        String priceMessage= "Name:"+ name;
        priceMessage+="\nAdd Whipped Cream? "+ addWhippedCream;
        priceMessage+="\nAdd Chocolate? "+ addChocolate;
        priceMessage+="\nQuantity:"+quantity;
        priceMessage+="\nTotal: $" + price ;
        priceMessage+="\nThank you!";
        return priceMessage;
    }

    public void increment(View view)
    {
        if(quantity==100) {
            Toast.makeText(this, "you cannot have more than 100 coffees!", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity+1;
        display(quantity);
    }
    public void decrement(View view)
    {
        if(quantity==1) {
            Toast.makeText(this, "you cannot have less than 1 coffees!", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity-1;
        display(quantity);
    }
    /**
     * This method displays the given text on the screen.
     */
}
