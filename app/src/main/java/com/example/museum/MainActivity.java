package com.example.museum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Class that controls the MainActivity of the Museum app. Creates the first activity
 * MainActivity along with it's components such as the Museum buttons and checks to see what
 * museum button was clicked to open the second activity TicketActivity.
 *
 * @author Seth Santos, Tiffany Chen
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Method is called so that user does not lose prior information when activity is recreated.
     * @param savedInstanceState of main activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Checks what museum button was clicked and assigns a number to buttonPressed.
     * Starts second activity while taking in buttonPressed.
     *
     * @param v view of the activity
     */
    public void museumClicked(View v) {
        // what activity intends to do
        Button b = (Button)v;
        String buttonText = b.getText().toString();
        int buttonPressed = 0;

        if (buttonText.equalsIgnoreCase("THE MUSEUM OF MODERN ART")){
            buttonPressed = 1;
        }else if(buttonText.equalsIgnoreCase("METROPOLITAN MUSEUM OF ART")){
            buttonPressed = 2;
        }else if(buttonText.equalsIgnoreCase("WHITNEY MUSEUM OF AMERICAN ART")){
            buttonPressed = 3;
        }else if(buttonText.equalsIgnoreCase("AMERICAN MUSEUM OF NATURAL HISTORY")){
            buttonPressed = 4;
        }else{
            buttonPressed = 0;
        }

        Intent i = new Intent(this,TicketActivity.class);
        i.putExtra("PressedButton", Integer.toString(buttonPressed));
        startActivity(i);
    }
}