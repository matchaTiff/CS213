package com.example.museum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TicketActivity extends AppCompatActivity {

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        Intent i = getIntent();
        String buttonNum = i.getStringExtra("PressedButton");
        ImageView museumImage = (ImageView) findViewById(R.id.museumImage);
        TextView museumName = (TextView) findViewById(R.id.museumName);

        if( buttonNum.equals("1") ){
            museumImage.setImageResource(R.drawable.museumofmodernart);
            url = "https://www.moma.org/";
            museumName.setText(R.string.museum1);

        }else if( buttonNum.equals("2") ){
            museumImage.setImageResource(R.drawable.metropolitan);
            url = "https://www.metmuseum.org/";
            museumName.setText(R.string.museum2);
        }else if( buttonNum.equals("3") ){
            museumImage.setImageResource(R.drawable.whitney);
            url = "https://whitney.org/";
            museumName.setText(R.string.museum3);
        }else if( buttonNum.equals("4") ){
            museumImage.setImageResource(R.drawable.americanmuseum);
            url = "https://www.amnh.org/";
            museumName.setText(R.string.museum4);
        }else{}

        // create a back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // toast
        Toast.makeText(getApplicationContext(), R.string.toast, Toast.LENGTH_LONG).show();
    }

    public void onClickMuseumImage(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);

    }


}