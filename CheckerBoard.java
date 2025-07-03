import java.util.*;
public class CheckerBoard {
    static pieces[][] Board = new pieces[8][8];

    public static void buildBoard() {
        //build empty Board
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (x % 2 == 0 && y % 2 == 0) {
                    Board[x][y] = new pieces("#", x, y, false);
                }
                if (x % 2 == 0 & y % 2 != 0) {
                    Board[x][y] = new pieces(" ", x, y,false);
                }
                if (x % 2 != 0 && y % 2 != 0) {
                    Board[x][y] = new pieces("#", x, y,false);
                }
                if (x % 2 != 0 & y % 2 == 0) {
                    Board[x][y] = new pieces(" ", x, y,false);
                }
            }
        }
        //Specify which places are pieces and their color
        for (int z = 0; z < 8; z++) {
            for (int i = 0; i < 8; i++) {
                if (z < 3 && z % 2 == 0 && i % 2 != 0) {
                    Board[z][i] = new pieces("r", z, i,false);
                }
                if (z == 1 && i % 2 == 0) {
                    Board[z][i] = new pieces("r", z, i, false);
                }
                if (z > 4 && z % 2 == 0 && i % 2 != 0) {
                    Board[z][i] = new pieces("b", z, i,false);
                }
                if (z > 4 && z % 2 != 0 && i % 2 == 0) {
                    Board[z][i] = new pieces("b", z, i, false);
                }
            }
        }
    }

    public static pieces[][] getBoard(){
        return Board;
    }

    //prints the Board
    public static void printBoard() {
        System.out.println("    | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | (X) ");
        System.out.println("    ---------------------------------");
        for (int x = 0; x < 8; x++) {
            System.out.print(" "+ x + "  | ");
            for (int y = 0; y < 8; y++) {
                System.out.print(Board[x][y] + " | ");
            }
            System.out.println();
        }
        System.out.println("(Y)");
    }

    //move the black piece
    public static void moveB(int x, int y, int posx, int posy) {
        if (Board[y][x].kingStatus() == false) {
            //FORWARDS
            //move diagnol one space left
            if (x - 1 == posx && y - 1 == posy) {
                Board[y][x] = new pieces(" ", x, y, false);
                Board[posy][posx] = new pieces("b", posy, posx, false);
            }
            // move diagonal one space right
            if (x + 1 == posx && y - 1 == posy) {
                Board[y][x] = new pieces(" ", x, y, false);
                Board[posy][posx] = new pieces("b", posy, posx, false);
            }
            //move diagonal two space right
            if (x + 2 == posx && y - 2 == posy) {
                Board[y][x] = new pieces(" ", x, y, false);
                Board[y - 1][x + 1] = new pieces(" ", x, y, false);
                Board[posy][posx] = new pieces("b", posy, posx, false);
                Checkers.multiB(posx,posy);
            }
            //move diagonal two space left
            if (x - 2 == posx && y - 2 == posy) {
                Board[y][x] = new pieces(" ", x, y, false);
                Board[y - 1][x - 1] = new pieces(" ", x, y, false);
                Board[posy][posx] = new pieces("b", posy, posx, false);
                Checkers.multiB(posx,posy);
            }
        }

        if (Board[y][x].kingStatus() == true) {
            //FORWARDS
            //move diagnol one space left
            if (x - 1 == posx && y - 1 == posy) {
                Board[y][x] = new pieces(" ", x, y, false);
                Board[posy][posx] = new pieces("B", posy, posx, true);
            }
            // move diagonal one space right
            if (x + 1 == posx && y - 1 == posy) {
                Board[y][x] = new pieces(" ", x, y, false);
                Board[posy][posx] = new pieces("B", posy, posx, true);
            }
            //move diagonal two space right
            if (x + 2 == posx && y - 2 == posy) {
                Board[y][x] = new pieces(" ", x, y, false);
                Board[y - 1][x + 1] = new pieces(" ", x, y, false);
                Board[posy][posx] = new pieces("B", posy, posx, true);
                Checkers.multiB(posx,posy);
            }
            //move diagonal two space left
            if (x - 2 == posx && y - 2 == posy) {
                Board[y][x] = new pieces(" ", x, y, false);
                Board[y - 1][x - 1] = new pieces(" ", x, y, false);
                Board[posy][posx] = new pieces("B", posy, posx, true);
                Checkers.multiB(posx,posy);
            }
            //BACKWARDS
            if (x + 1 == posx && y + 1 == posy) {
                Board[y][x] = new pieces(" ", x, y,false);
                Board[posy][posx] = new pieces("B", posy, posx,true);
            }
            // move diagonal one space right
            if (x - 1 == posx && y + 1 == posy) {
                Board[y][x] = new pieces(" ", x, y,false);
                Board[posy][posx] = new pieces("B", posy, posx,true);
            }
            //move diagonal two space right
            if (x + 2 == posx && y + 2 == posy) {
                Board[y][x] = new pieces(" ", x, y,false);
                Board[y + 1][x + 1] = new pieces(" ", x, y,false);
                Board[posy][posx] = new pieces("B", posy, posx,true);
                Checkers.multiB(posx,posy);
            }
            //move diagonal two space left
            if (x - 2 == posx && y + 2 == posy) {
                Board[y][x] = new pieces(" ", x, y,false);
                Board[y + 1][x - 1] = new pieces(" ", x, y,false);
                Board[posy][posx] = new pieces("B", posy, posx,true);
                Checkers.multiB(posx,posy);
            }
        }
    }
    //move the red piece
    public static void moveR(int x, int y, int posx, int posy) {
        if(Board[y][x].kingStatus()==false) {
            //move one space left
            if (x + 1 == posx && y + 1 == posy) {
                Board[y][x] = new pieces(" ", x, y,false);
                Board[posy][posx] = new pieces("r", posy, posx,false);
            }
            // move diagonal one space left
            if (x - 1 == posx && y + 1 == posy) {
                Board[y][x] = new pieces(" ", x, y,false);
                Board[posy][posx] = new pieces("r", posy, posx,false);
            }
            //move diagonal two space right
            if (x + 2 == posx && y + 2 == posy) {
                Board[y][x] = new pieces(" ", x, y,false);
                Board[y + 1][x + 1] = new pieces(" ", x, y,false);
                Board[posy][posx] = new pieces("r", posy, posx,false);
                Checkers.multiR(posx,posy);
            }
            //move diagonal two space left
            if (x - 2 == posx && y + 2 == posy) {
                Board[y][x] = new pieces(" ", x, y,false);
                Board[y + 1][x - 1] = new pieces(" ", x, y,false);
                Board[posy][posx] = new pieces("r", posy, posx,false);
                Checkers.multiR(posx,posy);
            }
            }
        if(Board[y][x].kingStatus()==true) {
            //FORWARDS
            if (x + 1 == posx && y + 1 == posy) {
                Board[y][x] = new pieces(" ", x, y,false);
                Board[posy][posx] = new pieces("R", posy, posx,true);
            }
            // move diagonal one space right
            if (x - 1 == posx && y + 1 == posy) {
                Board[y][x] = new pieces(" ", x, y,false);
                Board[posy][posx] = new pieces("R", posy, posx,true);
            }
            //move diagonal two space right
            if (x + 2 == posx && y + 2 == posy) {
                Board[y][x] = new pieces(" ", x, y,false);
                Board[y + 1][x + 1] = new pieces(" ", x, y,false);
                Board[posy][posx] = new pieces("R", posy, posx,true);
                Checkers.multiR(posx,posy);
            }
            //move diagonal two space left
            if (x - 2 == posx && y + 2 == posy) {
                Board[y][x] = new pieces(" ", x, y,false);
                Board[y + 1][x - 1] = new pieces(" ", x, y,false);
                Board[posy][posx] = new pieces("R", posy, posx,true);
                Checkers.multiR(posx,posy);
            }
            //BACKWARDS
            //move diagonal one space left
            if (x - 1 == posx && y - 1 == posy) {
                Board[y][x] = new pieces(" ", x, y, false);
                Board[posy][posx] = new pieces("R", posy, posx, true);
            }
            // move diagonal one space right
            if (x + 1 == posx && y - 1 == posy) {
                Board[y][x] = new pieces(" ", x, y, false);
                Board[posy][posx] = new pieces("R", posy, posx, true);
            }
            //move diagonal two space right
            if (x + 2 == posx && y - 2 == posy) {
                Board[y][x] = new pieces(" ", x, y, false);
                Board[y - 1][x + 1] = new pieces(" ", x, y, false);
                Board[posy][posx] = new pieces("R", posy, posx, true);
                Checkers.multiR(posx,posy);
            }
            //move diagonal two space left
            if (x - 2 == posx && y - 2 == posy) {
                Board[y][x] = new pieces(" ", x, y, false);
                Board[y - 1][x - 1] = new pieces(" ", x, y, false);
                Board[posy][posx] = new pieces("R", posy, posx, true);
                Checkers.multiR(posx,posy);
            }
        }

        }


}