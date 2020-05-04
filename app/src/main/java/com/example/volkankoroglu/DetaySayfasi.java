package com.example.volkankoroglu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetaySayfasi extends AppCompatActivity {

    TextView txtBas, txtAciklama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay_sayfasi);

        txtBas = findViewById(R.id.baslikTxt);
        txtAciklama = findViewById(R.id.acikTxt);

        Intent getData = getIntent();

        String a = getData.getStringExtra("HEADER");
        String b = getData.getStringExtra("DESCRIPTION");

        txtBas.setText(a);
        txtAciklama.setText(b);
    }
}
