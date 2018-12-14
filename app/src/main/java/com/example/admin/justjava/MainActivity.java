package com.example.admin.justjava;



         import android.os.Bundle;
         import android.support.v7.app.AppCompatActivity;
         import android.view.View;
         import android.widget.TextView;
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
        int price=calculatePrice();
        String priceMessage= createOrderSummary(price) ;
        displayMessage(priceMessage);
    }

    /**
     * method to calculate price of number of coffees
     */
    public int calculatePrice()
    {
        int price=quantity*5;
        return price;
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

    public String createOrderSummary(int price){
        String priceMessage= "Name: Kaptain Kunal";
        priceMessage=priceMessage+"\nQuantity:"+quantity;
        priceMessage=priceMessage+"\nTotal: $" + price ;
        priceMessage=priceMessage+"\nThank you!";
        return priceMessage;
    }

    public void increment(View view)
    {
        quantity=quantity+1;
        display(quantity);
    }
    public void decrement(View view)
    {
        quantity=quantity-1;
        display(quantity);
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}
