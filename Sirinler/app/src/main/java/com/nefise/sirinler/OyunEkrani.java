package com.nefise.sirinler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class OyunEkrani extends AppCompatActivity {

    Button buttonBasla;

    TextView textViewSkor;
    int skor;

    TextView textViewSure;


    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageVieWArray;

    Handler handler;
    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyun_ekrani);


        buttonBasla=findViewById(R.id.buttonBasla);

        textViewSkor=findViewById(R.id.textViewSkor);
        skor=0;

        textViewSure=findViewById(R.id.textViewSure);


        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);

        imageVieWArray=new ImageView[]{imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};

        ImageGizle();

    }

    public void start(View view){
        ImageHareket();
        buttonBasla.setVisibility(View.INVISIBLE);

        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                textViewSure.setText("Kalan Süre : " + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                textViewSure.setText("Süre Bitti!");
                handler.removeCallbacks(runnable);

                Intent intent=new Intent(OyunEkrani.this,SkorEkrani.class);
                intent.putExtra("skor",skor);
                startActivity(intent);

            }
        }.start();
    }

    public void ImageGizle(){
        for(ImageView image:imageVieWArray){
            image.setVisibility(View.INVISIBLE);
        }
    }

    public void ImageHareket(){

        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {

                ImageGizle();

                Random random=new Random();
                int i= random.nextInt(9);

                imageVieWArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(runnable,500);


            }
        };
        handler.post(runnable);
    }

    public void skorArttir(View view){
        skor++;
        textViewSkor.setText("Yakalanan \nŞirin Sayısı: "+skor);
    }

}