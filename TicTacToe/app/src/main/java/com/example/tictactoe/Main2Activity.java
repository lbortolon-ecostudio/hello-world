package com.example.tictactoe;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn = true;

    private int roundCount;

    private int player1Points;
    private int pcPoints;

    private int playerStart = 0;

    private TextView textViewPlayer1;
    private TextView textViewPc;

    private String Name1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);

        String pc = "PC : ";

        textViewPlayer1 = (TextView) findViewById(R.id.text_view_p1);
        textViewPc = (TextView) pc;

        textViewPlayer1.setText(getIntent().getStringExtra("player1Nick"));

        Name1 = textViewPlayer1.getText().toString();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (player1Turn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }


        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
            playerStart++;
            if(playerStart%2==0)
                player1Turn=true;
            else
                player1Turn=false;
        } else if (roundCount == 9) {
            draw();
            playerStart++;
            if(playerStart%2==0)
                player1Turn=true;
            else
                player1Turn=false;
        } else {
            player1Turn = !player1Turn;
        }

    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                Animation anim = new AlphaAnimation(0.0f, 1.0f);
                anim.setDuration(300); //You can manage the blinking time with this parameter
                anim.setStartOffset(20);
                anim.setRepeatCount(6);
                buttons[i][0].startAnimation(anim);
                buttons[i][1].startAnimation(anim);
                buttons[i][2].startAnimation(anim);
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                Animation anim = new AlphaAnimation(0.0f, 1.0f);
                anim.setDuration(300); //You can manage the blinking time with this parameter
                anim.setStartOffset(20);
                anim.setRepeatCount(6);
                buttons[0][i].startAnimation(anim);
                buttons[1][i].startAnimation(anim);
                buttons[2][i].startAnimation(anim);
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            Animation anim = new AlphaAnimation(0.0f, 1.0f);
            anim.setDuration(300); //You can manage the blinking time with this parameter
            anim.setStartOffset(20);
            anim.setRepeatCount(6);
            buttons[0][0].startAnimation(anim);
            buttons[1][1].startAnimation(anim);
            buttons[2][2].startAnimation(anim);
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            Animation anim = new AlphaAnimation(0.0f, 1.0f);
            anim.setDuration(300); //You can manage the blinking time with this parameter
            anim.setStartOffset(20);
            anim.setRepeatCount(6);
            buttons[0][2].startAnimation(anim);
            buttons[1][1].startAnimation(anim);
            buttons[2][0].startAnimation(anim);
            return true;
        }

        return false;
    }

    private void player1Wins() {
        player1Points++;
        Toast.makeText(this, Name1 + " wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(buttons[i][j].getText().toString().equals("")) {
                    buttons[i][j].setEnabled(false);
                }
            }
        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                resetBoard();
                for(int i=0; i<3; i++){
                    for(int j=0; j<3; j++){
                        buttons[i][j].setEnabled(true);
                    }
                }
            }
        }, 3000);
    }


    private void player2Wins() {
        pcPoints++;
        Toast.makeText(this, "PC wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(buttons[i][j].getText().toString().equals("")) {
                    buttons[i][j].setEnabled(false);
                }
            }
        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                resetBoard();
                for(int i=0; i<3; i++){
                    for(int j=0; j<3; j++){
                        buttons[i][j].setEnabled(true);
                    }
                }
            }
        }, 3000);
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                resetBoard();
            }
        }, 3000);
    }

    private void updatePointsText() {
        textViewPlayer1.setText(Name1 + " : " + player1Points);
        textViewPc.setText("PC : " + pcPoints);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        //cambio turno
    }

    private void resetGame() {
        player1Points = 0;
        pcPoints = 0;
        player1Turn = true;
        playerStart = 0;
        updatePointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1Points);
        outState.putInt("pcPoints", pcPoints);
        outState.putBoolean("player1Turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        pcPoints = savedInstanceState.getInt("pcPoints");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
    }
}

