package com.example.museum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class TicketActivity extends AppCompatActivity {

    NumberFormat formatter = NumberFormat.getCurrencyInstance();

    String url;

    Spinner adultTix;
    Spinner seniorTix;
    Spinner studentTix;

    TextView ticketPriceField;
    TextView salesTaxField;
    TextView ticketTotalField;

    TextView adultPrice;
    TextView seniorPrice;
    TextView studentPrice;

    double adultTicketPrice;
    double seniorTicketPrice;
    double studentTicketPrice;

    final double NY_SALES_TAX = 8.875;
    final double NJ_SALES_TAX = 6.625;
    double salesTax;

    // Initializes the ticket prices in the order of Adult, Senior, and Student
    final double[] MUSEUM_OF_MODERN_ART_TICKETS = new double[] {25.00, 18.00, 14.00};
    final double[] METROPOLITAN_MUSEUM_OF_ART_TICKETS = new double[] {25.00, 17.00, 12.00};
    final double[] WHITNEY_MUSEUM_OF_AMERICAN_ART_TICKETS = new double[] {25.00, 18.00, 18.00};
    final double[] AMERICAN_MUSEUM_OF_NATURAL_HISTORY_TICKETS = new double[] {23.00, 18.00, 18.00};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        Intent i = getIntent();
        String buttonNum = i.getStringExtra("PressedButton");
        ImageView museumImage = (ImageView) findViewById(R.id.museumImage);
        TextView museumName = (TextView) findViewById(R.id.museumName);

        adultTix = (Spinner) findViewById(R.id.spinner);
        seniorTix = (Spinner) findViewById(R.id.spinner2);
        studentTix = (Spinner) findViewById(R.id.spinner3);

        ticketPriceField = (TextView) findViewById(R.id.ticketPriceField);
        salesTaxField = (TextView) findViewById(R.id.salesTaxField);
        ticketTotalField = (TextView) findViewById(R.id.ticketTotalField);

        adultPrice = (TextView) findViewById(R.id.adultPrice);
        seniorPrice = (TextView) findViewById(R.id.seniorPrice);
        studentPrice = (TextView) findViewById(R.id.studentPrice);

        // Determines the url and image to be displayed
        // based on the museum choice that is pressed
        if( buttonNum.equals("1") ){
            museumImage.setImageResource(R.drawable.museumofmodernart);
            url = "https://www.moma.org/";
            museumName.setText(R.string.museum1);
            adultTicketPrice = MUSEUM_OF_MODERN_ART_TICKETS[0];
            seniorTicketPrice = MUSEUM_OF_MODERN_ART_TICKETS[1];
            studentTicketPrice = MUSEUM_OF_MODERN_ART_TICKETS[2];
            salesTax = NY_SALES_TAX;
        }else if( buttonNum.equals("2") ){
            museumImage.setImageResource(R.drawable.metropolitan);
            url = "https://www.metmuseum.org/";
            museumName.setText(R.string.museum2);
            adultTicketPrice = METROPOLITAN_MUSEUM_OF_ART_TICKETS[0];
            seniorTicketPrice = METROPOLITAN_MUSEUM_OF_ART_TICKETS[1];
            studentTicketPrice = METROPOLITAN_MUSEUM_OF_ART_TICKETS[2];
            salesTax = NY_SALES_TAX;
        }else if( buttonNum.equals("3") ){
            museumImage.setImageResource(R.drawable.whitney);
            url = "https://whitney.org/";
            museumName.setText(R.string.museum3);
            adultTicketPrice = WHITNEY_MUSEUM_OF_AMERICAN_ART_TICKETS[0];
            seniorTicketPrice = WHITNEY_MUSEUM_OF_AMERICAN_ART_TICKETS[1];
            studentTicketPrice = WHITNEY_MUSEUM_OF_AMERICAN_ART_TICKETS[2];
            salesTax = NY_SALES_TAX;
        }else if( buttonNum.equals("4") ){
            museumImage.setImageResource(R.drawable.americanmuseum);
            url = "https://www.amnh.org/";
            museumName.setText(R.string.museum4);
            adultTicketPrice = AMERICAN_MUSEUM_OF_NATURAL_HISTORY_TICKETS[0];
            seniorTicketPrice = AMERICAN_MUSEUM_OF_NATURAL_HISTORY_TICKETS[1];
            studentTicketPrice = AMERICAN_MUSEUM_OF_NATURAL_HISTORY_TICKETS[2];
            salesTax = NY_SALES_TAX;
        }else{}


        // Sets the values for the price fields
        adultPrice.setText( formatter.format(adultTicketPrice) );
        seniorPrice.setText( formatter.format(seniorTicketPrice) );
        studentPrice.setText( formatter.format(studentTicketPrice) );

        // create a back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // toast
        Toast.makeText(getApplicationContext(), R.string.toast, Toast.LENGTH_LONG).show();
    }

    public void onClickMuseumImage(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    public void onClickCalculationButton(View v){

        // Stores the amount of tickets selected for each type of ticket
        int totalAdultTickets = Integer.parseInt( adultTix.getSelectedItem().toString() );
        int totalSeniorTickets = Integer.parseInt( seniorTix.getSelectedItem().toString() );
        int totalStudentTickets = Integer.parseInt( studentTix.getSelectedItem().toString() );

        // Calculates the price of each set of tickets
        double adultPriceTotal = totalAdultTickets * adultTicketPrice;
        double seniorPriceTotal = totalSeniorTickets * seniorTicketPrice;
        double studentPriceTotal = totalStudentTickets * studentTicketPrice;

        // Calculates the final prices to be displayed
        double finalTicketPrice = adultPriceTotal + seniorPriceTotal + studentPriceTotal;
        double salesTaxPrice = finalTicketPrice * (salesTax/100);
        double finalTicketTotal = finalTicketPrice + salesTaxPrice;

        // Rounds the prices to the nearest cent



        //Sets the text
        ticketPriceField.setText( formatter.format(finalTicketPrice) );
        salesTaxField.setText( formatter.format(salesTaxPrice) );
        ticketTotalField.setText( formatter.format(finalTicketTotal) );

    }

}