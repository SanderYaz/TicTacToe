package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final  String PLAYER_1_SYMBOL = "X";
    static final  String PLAYER_2_SYMBOL = "O";
    boolean player1Turn = true;

    byte[][] board = new byte[3][3];
    static final byte EMPTY_VALUE = 0;
    static final byte PLAYER_1_VALUE = 1;
    static final byte PLAYER_2_VALUE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout table = findViewById(R.id.table);
        for (int i = 0; i<3; i++){
            TableRow row = (TableRow) table.getChildAt(i);
            for (int j = 0; j<3; j++){
                Button btn = (Button) row.getChildAt(j);
                btn.setOnClickListener(new CellListener(i,j));
            }
        }
    }

    class CellListener implements View.OnClickListener{
        int row, col;

        public CellListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void onClick(View v) {
            if (board[row][col] != EMPTY_VALUE){
                Toast.makeText(MainActivity.this, "Cell is already Occupied.", Toast.LENGTH_SHORT).show();
                return;
            }
            byte playerValue = EMPTY_VALUE;
            if (player1Turn) {
                ((Button)v).setText(PLAYER_1_SYMBOL);
                board[row][col]=PLAYER_1_VALUE;
                playerValue = PLAYER_1_VALUE;
            } else {
                ((Button)v).setText(PLAYER_2_SYMBOL);
                board[row][col]=PLAYER_2_VALUE;
                playerValue = PLAYER_2_VALUE;

            }
            player1Turn = !player1Turn;
            int gameState = gameEnded(row, col, playerValue);
            if (gameState > 0) {
                Toast.makeText(MainActivity.this, "Player " + gameState + " has WON !", Toast.LENGTH_SHORT).show();
                setBoardEnabled(false);
            }
        }
    }



    public int gameEnded(int row, int col, byte playerValue) {
        //Col
        boolean win = false;
        for (int r = 0; r < 3; r++) {
            if (board[r][col] != playerValue) {
                win = false;
                break;
            }
        }
        if (win) {
            return playerValue;
        }

        //Row


        //Diag
        return -1;
    }

    public boolean onCreateOptionMenu (Menu menu) {
        return super.onCreateOptionsMenu (menu);
    }


    void setBoardEnabled(boolean enable) {
        TableLayout table = findViewById(R.id.table);
        for (int i = 0; i<3; i++){
            TableRow row = (TableRow) table.getChildAt(i);
            for (int j = 0; j<3; j++){
                Button btn = (Button) row.getChildAt(j);
                btn.setEnabled(enable);
            }
        }
    }

    public boolean newGame(MenuItem item) {
        setBoardEnabled(true);
        for (int i = 0; i<3; i++){
            for (int j = 0 ; j<3; j++) {
                board[i][j] = EMPTY_VALUE;
            }
        }
        TableLayout table = findViewById(R.id.table);
        for (int i = 0; i<3; i++){
            TableRow row = (TableRow) table.getChildAt(i);
            for (int j = 0; j<3; j++){
                Button btn = (Button) row.getChildAt(j);
                btn.setText("");
            }
        }
        return true;
    }
}
