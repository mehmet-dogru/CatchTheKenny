package com.mehmetdogru.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int score;
    TextView textViewScore;
    TextView textViewTime;
    ImageView imageViewKenny;
    ImageView imageViewKenny1;
    ImageView imageViewKenny2;
    ImageView imageViewKenny3;
    ImageView imageViewKenny4;
    ImageView imageViewKenny5;
    ImageView imageViewKenny6;
    ImageView imageViewKenny7;
    ImageView imageViewKenny8;
    ImageView[] imageArray;

    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewScore = findViewById(R.id.textViewScore);
        textViewTime = findViewById(R.id.textViewTime);

        imageViewKenny = findViewById(R.id.imageViewKenny);
        imageViewKenny1 = findViewById(R.id.imageViewKenny1);
        imageViewKenny2 = findViewById(R.id.imageViewKenny2);
        imageViewKenny3 = findViewById(R.id.imageViewKenny3);
        imageViewKenny4 = findViewById(R.id.imageViewKenny4);
        imageViewKenny5 = findViewById(R.id.imageViewKenny5);
        imageViewKenny6 = findViewById(R.id.imageViewKenny6);
        imageViewKenny7 = findViewById(R.id.imageViewKenny7);
        imageViewKenny8 = findViewById(R.id.imageViewKenny8);

        score = 0;

        imageArray = new ImageView[]
                {imageViewKenny,imageViewKenny1,imageViewKenny2,imageViewKenny3,imageViewKenny4,imageViewKenny5,imageViewKenny6,imageViewKenny7,imageViewKenny8};

        hideImages();

        new CountDownTimer(10000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                textViewTime.setText("Time : " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(),"Oyun Bitti!",Toast.LENGTH_SHORT).show();
                textViewTime.setText("Süre Bitti!");
                imageViewKenny.setEnabled(false);
                handler.removeCallbacks(runnable);
                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                alertDialog();
            }
        }.start();

    }

    public void catchKenny(View view){
        textViewScore.setText("Score : " + score);
        score++;
        textViewScore.setText("Score : " + score);
    }

    public void alertDialog(){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        
        alert.setTitle("Skor : " + score);
        alert.setMessage("Tekrar oynamak ister misiniz?");
        alert.setPositiveButton("Tekrar Oyna", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Oyun Başladı!",Toast.LENGTH_SHORT).show();
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        alert.setNegativeButton("Çık", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Teşekkürler!",Toast.LENGTH_SHORT).show();
            }
        });

        alert.show();
    }

    public void hideImages(){

        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {

                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,300);
            }
        };

        handler.post(runnable);
    }

}