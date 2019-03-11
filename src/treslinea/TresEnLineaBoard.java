package treslinea;

import damas.misc.Coordinate;
import proto.Board;
import proto.SimplePlayer;

import java.util.ArrayList;
import java.util.Arrays;

public class TresEnLineaBoard extends Board {


    public TresEnLineaBoard() {
        rows = 3;
        cols = 3;
        table = new char[rows][cols];
        clearBoard();
    }

    public void clearBoard() {
        for (char[] chars : table) {
            Arrays.fill(chars, ' ');
        }
    }

    public boolean isFull() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }

    public void printBoard() {
        String line = "-------------";
        System.out.println(line);
        for (char[] chars : table) {
            System.out.printf("| %c | %c | %c |%n", chars[0], chars[1], chars[2]);
            System.out.println(line);
        }

    }

    public String toString(){
        String line = "-------------\n";
        String str = line;
        for (char[] chars : table) {
            str += String.format("| %c | %c | %c |%n", chars[0], chars[1], chars[2]);
            str += line;
        }
        return str;
    }

    public ArrayList<int[]> moves(){
        ArrayList<int[]> moves = new ArrayList<>();
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                if(table[row][col] == ' '){
                    moves.add( new int[] {row,col});
                }
            }
        }
        return moves;
    }

    public Coordinate[] movesArray(){
        ArrayList<int[]> moves = moves();
        Coordinate[] array = new Coordinate[moves.size()];

        for (int i = 0; i < moves.size(); i++) {
            array[i] = new Coordinate(moves.get(i));

        }
        return array;
    }


    public Character isGameOver() {
        for (int i = 0; i < table.length; i++) {
            if (table[i][0] != ' ' && (table[i][0] == table[i][1] && table[i][1] == table[i][2])) {
                return table[i][0];//row winner
            }
        }
        for (int i = 0; i < table.length; i++) {
            if (table[0][i] != ' ' && (table[0][i] == table[1][i] && table[1][i] == table[2][i])) {
                return table[0][i];//col winner
            }
        }
        if (table[0][0] != ' ' && (table[0][0] == table[1][1] && table[1][1] == table[2][2])) {
            return table[0][0];//main diag winner
        } else if (table[0][2] != ' ' && (table[0][2] == table[1][1] && table[1][1] == table[2][0])) {
            return table[0][2];//reverse diag winner
        } else if (isFull())
            return ' ';//game tied
        else return null;//game not over
    }

    public boolean validTurn(int[] coords, SimplePlayer simplePlayer) {
        return (table[coords[0] - 1][coords[1] - 1] == ' ');
    }

    public void doTurn(int[] coords, SimplePlayer simplePlayer) {
        table[coords[0] - 1][coords[1] - 1] = simplePlayer.getId();
    }

}
