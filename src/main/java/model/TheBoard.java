/*
 * This object represents a complete board, it's function allow manipulation.
 */
package model; //Part of the data model

public class TheBoard {

    private final int board[][];
    private int numberOfMoves; //Number of moves left for current puzzle

    //Constructor
    public TheBoard() {
        board = new int[6][12];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 12; j++) {
                board[i][j] = -1;
            }
        }
        numberOfMoves = 0;
    } //Constructor

    public TheBoard(TheBoard tb) {
        board = new int[6][12];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 12; j++) {
                board[i][j] = tb.getBrick(i, j);
            }
        }
        numberOfMoves = tb.getNumberOfMoves();

    }

    //sets a brick, returns true if succeded
    public boolean setBrick(int x, int y, int color) {
        if ((x < 0) || (x >= 6) || (y < 0) || (y >= 12)) {
            return false;
        }
        board[x][y] = color;
        return true;
    } //setBrick

    //returns brick at given coordinates, return -999 if failed
    public int getBrick(int x, int y) {
        if ((x < 0) || (x >= 6) || (y < 0) || (y >= 12)) {
            return -999;
        }
        return board[x][y];
    } //getBrick

    public void setNumberOfMoves(int moves) {
        numberOfMoves = moves;
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    //moves all brick one field up, from current position, return true if succeded
    public boolean moveUp(int x, int y) {
        if ((x < 0) || (x >= 6) || (y < 0) || (y >= 12)) {
            return false;
        }
        for (int i = 1; i <= y; i++) {
            board[x][i - 1] = board[x][i];
        }
        board[x][y] = -1;
        return true;
    }  //moveUp

    //	moves all brick one field down, from current position, return true if succeded
    public boolean moveDown(int x, int y) {
        if ((x < 0) || (x >= 6) || (y < 0) || (y >= 12)) {
            return false;
        }
        for (int i = y - 1; i >= 0; i--) {
            board[x][i + 1] = board[x][i];
        }
        board[x][0] = -1;
        return true;
    }

    //moves all pieces one to the right
    public void moveRight() {
        for (int i = 0; i < 12; i++) {
            for (int j = 4; j >= 0; j--) {
                board[j + 1][i] = board[j][i];
            }
            board[0][i] = -1;
        }
    } //moveRight

    //moves all pieces one to the right
    public void moveLeft() {
        for (int i = 0; i < 12; i++) {
            for (int j = 1; j <= 5; j++) {
                board[j - 1][i] = board[j][i];
            }
            board[5][i] = -1;
        }
    } //moveRight

}
