package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean is_game_Active = true;

    // Player representation that we will use in our code
    // 0 - X
    // 1 - O

    int active_player = 0;
    int[] game_state = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    //    We will consider State meanings as:
    //    0 - X
    //    1 - O
    //    2 - Null

    int[][] win_combinations = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if (!is_game_Active) {
            gameReset(view);
        }

        if (game_state[tappedImage] == 2) {
            game_state[tappedImage] = active_player;
            img.setTranslationY(-1000f);
            if (active_player == 0) {
                img.setImageResource(R.drawable.x);
                active_player = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn - Tap to play");
            } else {
                img.setImageResource(R.drawable.o);
                active_player = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }

        // Check if any player has won
        for (int[] win_position : win_combinations) {
            if (game_state[win_position[0]] == game_state[win_position[1]] &&
                    game_state[win_position[1]] == game_state[win_position[2]] &&
                    game_state[win_position[0]] != 2) {
                // Finding out who won!!!
                String winnerStr;
                is_game_Active = false;
                if (game_state[win_position[0]] == 0) {
                    winnerStr = "   X has won the game!";
                } else {
                    winnerStr = "   O has won the game!";
                }
                // Updating status for the winner
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
                Button restartButton = findViewById(R.id.restartButton);
                restartButton.setVisibility(View.VISIBLE);
            }

            boolean empty_square = false;
            for (int squareState : game_state) {
                if (squareState == 2) {
                    empty_square = true;
                    break;
                }
            }
            if (!empty_square && is_game_Active) {
                // Game is a draw
                is_game_Active = false;
                String winnerStr;
                winnerStr = "        Game Draw...!!!";
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
                Button restartButton = findViewById(R.id.restartButton);
                restartButton.setVisibility(View.VISIBLE);
            }
        }
    }


    public void gameReset(View view) {
        is_game_Active = true;
        active_player = 0;
        for (int i = 0; i < game_state.length; i++) {
            game_state[i] = 2;
        }

        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);


        TextView status = findViewById(R.id.status);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameReset(v);
                restartButton.setVisibility(View.INVISIBLE);
            }
        });
    }
}
