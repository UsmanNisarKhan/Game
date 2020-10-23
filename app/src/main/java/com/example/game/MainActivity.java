package com.example.game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.gridlayout.widget.GridLayout;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class MainActivity extends AppCompatActivity {

    enum player {
        one, two, no
    }

    boolean gamefinshed = false;
    private Button btn_reset;

    player current = player.one;
    GridLayout gridLayout;
    TextView text1,text2;

    ImageView imageView1;
    KonfettiView konfettiView;
    Drawable drawable;
    MediaPlayer mp,mp1,mp2;

    int score1,score2;

    player[] playerchoices = new player[9];
    int[][] winnerloser = {{0, 1, 2}, {0, 3, 6}, {0, 4, 8}, {1, 4, 7}, {2, 5, 8}, {2, 4, 6}, {3, 4, 5}, {6, 7, 8}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = MediaPlayer.create(this,R.raw.applause);
        mp1 = MediaPlayer.create(this,R.raw.swoosh);
        mp2 = MediaPlayer.create(this,R.raw.swoosh);

        for (int index = 0; index < playerchoices.length; index++) {
            playerchoices[index] = player.no;
        }

        gridLayout = findViewById(R.id.grid);
        text1 = findViewById(R.id.player1_score);
        text1.setText("Polar: " + score1);

        text2 = findViewById(R.id.player2_score);
        text2.setText("Parrot: " + score1);

        konfettiView = findViewById(R.id.viewKonfetti);
        drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_hearts);

        btn_reset = findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

    }

    public void imageViewistapped(View imageView) {

        imageView1 = (ImageView) imageView;

        int tag = Integer.parseInt(imageView1.getTag().toString());

        if (playerchoices[tag] == player.no && gamefinshed == false) {

            playerchoices[tag] = current;

            if (current == player.one) {

                mp1.start();
                imageView1.setTranslationX(-2000);

                imageView1.setImageResource(R.drawable.polar);
                current = player.two;

                imageView1.animate().translationXBy(2000).rotation(3600).setDuration(1000);
            }

            else if (current == player.two) {

                mp2.start();
                imageView1.setTranslationY(-2000);

                imageView1.setImageResource(R.drawable.canary);
                current = player.one;

                imageView1.animate().translationYBy(2000).rotation(3600).setDuration(1000);
            }

            for (int[] winners : winnerloser) {

                if (playerchoices[winners[0]] == playerchoices[winners[1]]
                        && playerchoices[winners[1]] == playerchoices[winners[2]]
                        && playerchoices[winners[0]] != player.no
                        && playerchoices[winners[1]] != player.no
                        && playerchoices[winners[2]] != player.no) {

                    btn_reset.setVisibility(View.VISIBLE);
                    gamefinshed = true;

                    if (current == player.one) {

                        konfetti();
                        mp.start();

                        score2 = score2 + 1;
                        text2.setText("Parrot: " + score2);


                        Toast.makeText(MainActivity.this, "Parrot is winner", Toast.LENGTH_LONG).show();
                    }

                    else if (current == player.two) {

                        konfetti();
                        mp.start();

                        score1 =score1 + 1;
                        text1.setText("Polar: " + score1);

                        Toast.makeText(MainActivity.this, "Polar is winner", Toast.LENGTH_LONG).show();
                    }
                }
              }
                        if(playerchoices[0] != player.no && playerchoices[1] != player.no &&
                                playerchoices[2] != player.no &&
                                playerchoices[3] != player.no &&
                                playerchoices[4] != player.no &&
                                playerchoices[5] != player.no &&
                                playerchoices[6] != player.no &&
                                playerchoices[7] != player.no &&
                                playerchoices[8] != player.no && gamefinshed == false){

                            btn_reset.setVisibility(View.VISIBLE);
                            gamefinshed = true;
                            Toast.makeText(MainActivity.this, "Game Draw", Toast.LENGTH_LONG).show();
                        }
            }
        }

    private void reset() {

        // Reset function which will be called when user restart the game.

        for (int index = 0; index < gridLayout.getChildCount(); index++) {
            imageView1 = (ImageView) gridLayout.getChildAt(index);
            imageView1.setImageDrawable(null);
            imageView1.setRotation(0);
        }

        gamefinshed = false;

        current = player.one;

        for (int index = 0; index < playerchoices.length; index++) {
            playerchoices[index] = player.no;
        }
        btn_reset.setVisibility(View.INVISIBLE);

    }

    private void konfetti()
    {
        final Shape.DrawableShape drawableShape = new Shape.DrawableShape(drawable, true);

                konfettiView.build()                                                                                // For Top to bottom stream
                        .addColors(Color.YELLOW, Color.GREEN, Color.RED)
                        .setDirection(0.0, 359.0)
                        .setSpeed(1f,10f)
                        .setFadeOutEnabled(true)
                        .setTimeToLive(2000L)
                        .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE, drawableShape)
                        .addSizes(new Size(12,5f))
                        .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                        .streamFor(300, 5000L);
            }


            // For Burst Animation.

//            private void burst()
//            {
//
//                final Shape.DrawableShape drawableShape = new Shape.DrawableShape(drawable, true);
//
//                konfettiView.build()
//                        .addColors(Color.YELLOW, Color.GREEN, Color.RED)
//                        .setDirection(0.0, 359.0)
//                        .setSpeed(1f,2f)
//                        .setFadeOutEnabled(true)
//                        .setTimeToLive(2000L)
//                        .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE, drawableShape)
//                        .addSizes(new Size(12,5f))
//                        .setPosition(konfettiView.getX() + konfettiView.getWidth() / 2, konfettiView.getY() + konfettiView.getHeight() / 3 ).burst(100);
//            }
}