package com.example.museum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void museumClicked(View v) {
        // what activity intends to do
        Button b = (Button)v;
        String buttonText = b.getText().toString();
        int buttonPressed =0;

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

        //System.out.println(buttonText);
        Intent i = new Intent(this,TicketActivity.class);
        i.putExtra("PressedButton", Integer.toString(buttonPressed));
        startActivity(i);
    }
}