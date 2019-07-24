package com.example.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class Menu_1vPc_3b extends AppCompatActivity {

    EditText p1Nick;
    Button sendEditText;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_menu_1v_pc_3b);

        sendEditText = (Button)findViewById(R.id.start_button);
        p1Nick = (EditText)findViewById(R.id.player1_text_input);

        sendEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(getApplicationContext(),Main2Activity.class);
                intent.putExtra("player1Nick", p1Nick.getText().toString());
                startActivity(intent);
                /*
                intentp1 = new Intent(getApplicationContext(),MainActivity.class);
                intentp1.putExtra("player1Nick", p1Nick.getText().toString());
                intentp2 = new Intent(getApplicationContext(),MainActivity.class);
                intentp2.putExtra("player2Nick", p2Nick.getText().toString());
                startActivity(intentp1);
                startActivity(intentp2);
                */
            }
        });
        Button settings = (Button)findViewById(R.id.settings);

        settings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Menu_1vPc_3b.this,Pop.class));
            }
        });
    }
    public void startGame(View view){
        startActivity(new Intent(getApplicationContext(), Main2Activity.class));
    }
}
