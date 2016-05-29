package com.thatboytitz.tictactaco;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TicTacToe game;
    private boolean playerTurn;
    private Random random;
    private ArrayList<Button> grid;
    private final int gridSize = 9;
    private Toast toast;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        random = new Random();
        playerTurn = random.nextBoolean();
        grid = new ArrayList<Button>();
        game = new TicTacToe();
        for(int i = 0; i < gridSize; i++){
            String buttonID = "Button" + i;
            int resID = getResources().getIdentifier(buttonID,"id",getPackageName());
            grid.add((Button) findViewById(resID));
        }
        if(!playerTurn) CPUTurn();

    }

    public void ResetClick(View view){
        for(int i = 0; i < gridSize; i++){
            game.setTile(i, 0);
            grid.get(i).setClickable(true);
            grid.get(i).setEnabled(true);
            grid.get(i).setText("");
            grid.get(i).setBackgroundResource(android.R.drawable.btn_default);

        }
        playerTurn = random.nextBoolean();
        if(!playerTurn) CPUTurn();
    }

    public void CPUTurn() {
        playerTurn = false;
        ArrayList<Integer> winnable = game.WinnableTiles();
        if(!winnable.isEmpty()){
            Turn(winnable.get(random.nextInt(winnable.size())));
        }
        else{
            ArrayList<Integer> empty = game.getEmpty();
            Turn(empty.get(random.nextInt(empty.size())));
        }
        playerTurn = true;
    }

    public void OnClick(View view) {
        switch(view.getId()){
            case R.id.Button0:
                Turn(0);
                break;
            case R.id.Button1:
                Turn(1);
                break;
            case R.id.Button2:
                Turn(2);
                break;
            case R.id.Button3:
                Turn(3);
                break;
            case R.id.Button4:
                Turn(4);
                break;
            case R.id.Button5:
                Turn(5);
                break;
            case R.id.Button6:
                Turn(6);
                break;
            case R.id.Button7:
                Turn(7);
                break;
            case R.id.Button8:
                Turn(8);
                break;
            default:
                break;
        }
    }

    private void Turn(int id){
        if(playerTurn) {
            game.setTile(id, 1);
            //grid.get(id).setImageResource(R.drawable.o);
            grid.get(id).setText("O");
            //grid.get(id).setBackgroundColor(Color.RED);
            grid.get(id).setClickable(false);
        }
        else {
            game.setTile(id, -1);
//            grid.get(id).setImageResource(R.drawable.x);
            grid.get(id).setText("X");
            //grid.get(id).setBackgroundColor(Color.BLUE);
            grid.get(id).setClickable(false);
        }
        Log.d("debug","" + game.gameStatus());
        ArrayList<Integer> winningTiles;
        switch (game.gameStatus()){
            case -1:
                toast = Toast.makeText(getApplicationContext(),"Cpu Wins", Toast.LENGTH_SHORT);
                toast.show();
                winningTiles = game.WinningTiles();
                for(int i = 0; i < 3; i++){
                    grid.get(winningTiles.get(i)).setBackgroundColor(Color.RED);
                }
                for(int i = 0; i < 9; i++) grid.get(i).setEnabled(false);
                break;
            case 0:
                if(playerTurn) CPUTurn();
                break;
            case 1:
                toast = Toast.makeText(getApplicationContext(),"You Win!", Toast.LENGTH_SHORT);
                toast.show();
                winningTiles = game.WinningTiles();
                for(int i = 0; i < winningTiles.size(); i++){
                    grid.get(winningTiles.get(i)).setBackgroundColor(Color.BLUE);
                }
                for(int i = 0; i < 9; i++) grid.get(i).setEnabled(false);
                break;
            case 2:
                toast = Toast.makeText(getApplicationContext(),"DRAW!", Toast.LENGTH_SHORT);
                toast.show();
                break;
        }
    }

}