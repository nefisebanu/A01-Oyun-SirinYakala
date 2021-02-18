package com.nefise.sirinler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class SkorEkrani extends AppCompatActivity {

    TextView textViewPuan;
    TextView textViewEnYuksekSkor;
    TextView textViewMesaj;

    int skor;
    int enYuksekSkor;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skor_ekrani);

        textViewPuan=findViewById(R.id.textViewPuan);
        textViewEnYuksekSkor=findViewById(R.id.textViewEnYuksekPuan);
        textViewMesaj=findViewById(R.id.textViewMesaj);

        Intent intent = getIntent();
        skor=intent.getIntExtra("skor",0);

        sharedPreferences = this.getSharedPreferences(this.getPackageName(), MODE_PRIVATE);

        textViewPuan.setText("Puan: " +skor*100 );
        enYuksekSkor= sharedPreferences.getInt("enYuksekSkor",0);

        if(skor>enYuksekSkor){
            enYuksekSkor=skor;
            sharedPreferences.edit().putInt("enYuksekSkor",enYuksekSkor).apply();
            textViewEnYuksekSkor.setText("En yüksek puan: " + enYuksekSkor*100);
            textViewMesaj.setText("Muhteşem!");
        }else{
            textViewEnYuksekSkor.setText("En yüksek puan: " +enYuksekSkor*100 );
            textViewMesaj.setText("Tebrikler..");
        }


    }


    public void start(View view){
        Intent intent=new Intent(SkorEkrani.this,OyunEkrani.class);
        startActivity(intent);
    }
}