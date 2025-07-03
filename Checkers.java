import java.util.*;
public class Checkers {
    public static void main(String[] args) {
        CheckerBoard.buildBoard();
        CheckerBoard.printBoard();

        int breaker=0;
        boolean winner=false;

        while(breaker==0) {
            if(winner==false) {
                Bturn();
                winner=CheckWin();
            }
            if(winner==true) {
                breaker++;
            }
            if(winner==false) {
                Rturn();
                winner=CheckWin();
            }
            if(winner==true) {
                breaker++;
            }
        }
    }
    public static boolean CheckWin() {
        pieces[][] Board = new pieces[8][8];
        Board = CheckerBoard.getBoard();
        int countR=0;
        int countB=0;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (Board[x][y].getColor().equals("b")||Board[x][y].getColor().equals("B")) {
                    countB++;
                }
                if (Board[x][y].getColor().equals("r")||Board[x][y].getColor().equals("R")) {
                    countR++;
                }
            }
        }
        if (countB == 0) {
            System.out.println("Red wins!!!");
            return true;
        }
        if (countR == 0) {
            System.out.println("Black wins!!!");
            return true;
        }
        else{
            return false;
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean InvalidB(int X, int Y,int posX, int posY) {
        pieces[][] Board = new pieces[8][8];
        Board = CheckerBoard.getBoard();
        boolean invalid=false;
        //out of bounds rule
        if (X > 7 || X < 0 || Y > 7 || Y < 0 || posX < 0 || posX > 7 || posY < 0 || posY > 7) {
            CheckerBoard.printBoard();
            System.out.println("Coordinates out of bounds try again!");
            return true;
        }
        //a piece can only go onto a vacant slot
        if(Board[posY][posX].getColor().equals("r")||Board[posY][posX].getColor().equals("b")||Board[posY][posX].getColor().equals("R")||Board[posY][posX].getColor().equals("B")||Board[posY][posX].getColor().equals("#")){
            CheckerBoard.printBoard();
            System.out.println("Invalid move!");
            return true;
        }
        //must select a black piece to move
        if(Board[Y][X].getColor().equals("#")||Board[Y][X].getColor().equals(" ")||Board[Y][X].getColor().equals("r")||Board[Y][X].getColor().equals("R")){
            CheckerBoard.printBoard();
            System.out.println("Invalid move!");
            return true;
        }
        //cannot move a piece on top of another piece
        if(Board[posY][posX].getColor().equals("r")||Board[posY][posX].getColor().equals("b")||Board[posY][posX].getColor().equals("R")||Board[posY][posX].getColor().equals("B")){
            CheckerBoard.printBoard();
            System.out.println("Invalid move!");
            return true;
        }
        //normal pieces can only move diagonally one or two spaces forward(down the board)
        if(Board[Y][X].kingStatus()==false) {
            boolean correct=false;
            //diag right one space
            if(X+1==posX&&(Y-1)==posY){
                correct=true;
            }
            //diag right two space
            if(X+2==posX&&(Y-2)==posY){
                correct=true;
            }
            //diag left one space
            if((X-1)==posX&&(Y-1)==posY ){
                correct=true;
            }
            //diag left two space
            if((X-2)==posX&&(Y-2)==posY){
                correct=true;
            }
            if(correct==false){
                CheckerBoard.printBoard();
                System.out.println("Invalid move!");
                return true;
            }
        }
        if(Board[Y][X].kingStatus()==true) {
            boolean correct=false;
            //diag right one space
            if(X+1==posX&&Y+1==posY){
                correct=true;
            }
            //diag right two space
            if(X+2==posX&&Y+2==posY){
                correct=true;
            }
            //diag left one space
            if((X-1)==posX&&Y+1==posY ){
                correct=true;
            }
            //diag left two space
            if((X-2)==posX&&Y+2==posY){
                correct=true;
            }

            //diag right one space
            if(X+1==posX&&(Y-1)==posY){
                correct=true;
            }
            //diag right two space
            if(X+2==posX&&(Y-2)==posY){
                correct=true;
            }
            //diag left one space
            if((X-1)==posX&&(Y-1)==posY ){
                correct=true;
            }
            //diag left two space
            if((X-2)==posX&&(Y-2)==posY){
                correct=true;
            }
            if(correct==false){
                CheckerBoard.printBoard();
                System.out.println("Invalid move!");
                return true;
            }
            // when moving two spaces backwards you must take a red piece
            if(X+2==posX&&Y+2==posY){
                if (Board[Y+1][X+1].getColor().equals(" ")||Board[Y+1][X+1].getColor().equals("b")||Board[Y+1][X+1].getColor().equals("B")||Board[Y+1][X+1].getColor().equals("#")) {
                    CheckerBoard.printBoard();
                    System.out.println("Invalid move!");
                    return true;
                }
            }
            if((X-2)==posX&&Y+2==posY){
                if (Board[Y+1][X-1].getColor().equals(" ")||Board[Y+1][X-1].getColor().equals("b")||Board[Y+1][X-1].getColor().equals("B")||Board[Y+1][X-1].getColor().equals("#")){
                    CheckerBoard.printBoard();
                    System.out.println("Invalid move!");
                    return true;
                }
            }
        }
        //when moving two spaces you have to take a red piece
        if (X + 2 == posX && (Y - 2) == posY) {
            if(Board[Y - 1][X + 1].getColor().equals(" ")||Board[Y - 1][X + 1].getColor().equals("b")||Board[Y - 1][X + 1].getColor().equals("B")||Board[Y - 1][X + 1].getColor().equals("#")){
                CheckerBoard.printBoard();
                System.out.println("Invalid move!");
                return true;
            }
        }
        if ((X - 2) == posX && (Y - 2) == posY) {
            if(Board[Y - 1][X - 1].getColor().equals(" ")||Board[Y - 1][X - 1].getColor().equals("b")||Board[Y - 1][X - 1].getColor().equals("B")||Board[Y - 1][X - 1].getColor().equals("#")){
                CheckerBoard.printBoard();
                System.out.println("Invalid move!");
                return true;
            }
        }
        return invalid;
    }
    public static boolean InvalidR(int X, int Y, int posX, int posY) {
        pieces[][] Board = new pieces[8][8];
        Board = CheckerBoard.getBoard();
        boolean invalid=false;
        //coordinates not in checkerboard range
        if (X > 7 || X < 0 || Y > 7 || Y < 0 || posX < 0 || posX > 7 || posY < 0 || posY > 7) {
            CheckerBoard.printBoard();
            System.out.println("Coordinates out of bounds try again!");
            return true;
        }
        //a piece can only go onto a vacant slot
        if(Board[posY][posX].getColor().equals("r")||Board[posY][posX].getColor().equals("b")||Board[posY][posX].getColor().equals("R")||Board[posY][posX].getColor().equals("B")||Board[posY][posX].getColor().equals("#")){
            CheckerBoard.printBoard();
            System.out.println("Invalid move!");
            return true;
        }
        //must select a red piece to move
        if(Board[Y][X].getColor().equals("#")||Board[Y][X].getColor().equals(" ")||Board[Y][X].getColor().equals("b")||Board[Y][X].getColor().equals("B")){
            CheckerBoard.printBoard();
            System.out.println("Invalid move!");
            return true;
        }
        //cannot move a piece on top of another piece
        if(Board[posY][posX].getColor().equals("r")||Board[posY][posX].getColor().equals("b")||Board[posY][posX].getColor().equals("R")||Board[posY][posX].getColor().equals("B")){
            CheckerBoard.printBoard();
            System.out.println("Invalid move!");
            return true;
        }
        //valid move for normal pieces
        if(Board[Y][X].kingStatus()==false) {
            //normal pieces can only move diagonally one or two spaces forward(down the board)
            boolean correct=false;
            //diag right one space
            if(X+1==posX&&Y+1==posY){
                correct=true;
            }
            //diag right two space
            if(X+2==posX&&Y+2==posY){
                correct=true;
            }
            //diag left one space
            if((X-1)==posX&&Y+1==posY ){
                correct=true;
            }
            //diag left two space
            if((X-2)==posX&&Y+2==posY){
                correct=true;
            }
            if(correct==false){
                CheckerBoard.printBoard();
                System.out.println("Invalid move!");
                return true;
            }
            }
        //valid moves for kings
        if(Board[Y][X].kingStatus()==true) {
            boolean correct=false;
            //one bottom right
            if(X+1==posX&&Y+1==posY){
                correct=true;
            }
            //two bottom right
            if(X+2==posX&&Y+2==posY){
                correct=true;
            }
            //one bottom left
            if((X-1)==posX&&Y+1==posY ){
                correct=true;
            }
            //two bottom left
            if((X-2)==posX&&Y+2==posY){
                correct=true;
            }

            //one top right
            if(X+1==posX&&(Y-1)==posY){
                correct=true;
            }
            //two top right
            if(X+2==posX&&(Y-2)==posY){
                correct=true;
            }
            //one bottom left
            if((X-1)==posX&&(Y-1)==posY ){
                correct=true;
            }
            //two bottom left
            if((X-2)==posX&&(Y-2)==posY){
                correct=true;
            }
            if(correct==false){
                CheckerBoard.printBoard();
                System.out.println("Invalid move!");
                return true;
            }
            //when moving two spaces up you have to take a black piece
            if (X + 2 == posX && (Y - 2) == posY) {
                if(Board[Y - 1][X + 1].getColor().equals(" ")||Board[Y - 1][X + 1].getColor().equals("r")||Board[Y - 1][X + 1].getColor().equals("R")||Board[Y - 1][X + 1].getColor().equals("#")){
                    CheckerBoard.printBoard();
                    System.out.println("Invalid move!");
                    return true;
                }
            }
            if ((X - 2) == posX && (Y - 2) == posY) {
                if(Board[Y - 1][X - 1] .getColor().equals(" ")||Board[Y - 1][X - 1] .getColor().equals("r")||Board[Y - 1][X - 1] .getColor().equals("R")||Board[Y - 1][X - 1] .getColor().equals("#")){
                    CheckerBoard.printBoard();
                    System.out.println("Invalid move!");
                    return true;
                }
            }
        }
        //when moving two spaces forwards, you have to take a black piece(down the board)
        if(X+2==posX&&Y+2==posY){
            if (Board[Y+1][X+1].getColor().equals(" ")||Board[Y+1][X+1].getColor().equals("r")||Board[Y+1][X+1].getColor().equals("R")||Board[Y+1][X+1].getColor().equals("#")) {
                CheckerBoard.printBoard();
                System.out.println("Invalid move!");
                return true;
            }
        }
        if(X-2==posX&&Y+2==posY){
            if (Board[Y+1][X-1].getColor().equals(" ")||Board[Y+1][X-1].getColor().equals("r")||Board[Y+1][X-1].getColor().equals("R")||Board[Y+1][X-1].getColor().equals("#")){
                CheckerBoard.printBoard();
                System.out.println("Invalid move!");
                return true;
            }
        }
        return invalid;
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void Bturn() {
        pieces[][] Board = new pieces[8][8];
        Board = CheckerBoard.getBoard();
        Scanner s = new Scanner(System.in);
        if (mandoB()==false) {
            System.out.println("Player Black choose a piece, then choose where to move that piece in the order (X,Y):");
            //coordinates for piece on board
            int BX = s.nextInt();
            s.nextLine();
            int BY = s.nextInt();
            s.nextLine();
            //coordinates for where person wants to move piece
            int BposX = s.nextInt();
            s.nextLine();
            int BposY = s.nextInt();
            s.nextLine();
            boolean invalid;
            invalid = InvalidB(BX, BY, BposX, BposY);
            if (invalid == false) {
                CheckerBoard.moveB(BX, BY, BposX, BposY);
                //check if any have reached king row, if so that piece becomes a king
                if (BposY == 0) {
                    Board[BposY][BposX].setColor("B");
                    Board[BposY][BposX].makeKing(true);
                }
                CheckerBoard.printBoard();
            }
            if (invalid == true) {
                Bturn();
            }
        }

    }

    public static void Rturn() {
        pieces[][] Board = new pieces[8][8];
        Board = CheckerBoard.getBoard();
        Scanner s = new Scanner(System.in);
        if (mandoR()==false) {
            System.out.println("Player Red, choose a piece then choose where to move that piece in the order (X,Y):");
            //coordinates for piece on board
            int RX = s.nextInt();
            s.nextLine();
            int RY = s.nextInt();
            s.nextLine();
            //coordinates for where person wants to move piece
            int RposX = s.nextInt();
            s.nextLine();
            int RposY = s.nextInt();
            s.nextLine();

            boolean invalid;
            invalid = InvalidR(RX, RY, RposX, RposY);
            if (invalid == false) {
                CheckerBoard.moveR(RX, RY, RposX, RposY);
                //check if any have reached king row, if so that piece becomes a king
                if (RposY == 7) {
                    Board[RposY][RposX].setColor("R");
                    Board[RposY][RposX].makeKing(true);
                }
                CheckerBoard.printBoard();
            }
            if (invalid == true) {
                Rturn();
            }
        }
    }

    public static boolean multiB(int xx, int yy){
        pieces[][] Board = new pieces[8][8];
        Board = CheckerBoard.getBoard();
        ArrayList<Integer> cords = new ArrayList<Integer>();
        boolean redo=false;
        if(Board[yy][xx].kingStatus()==false) {
            //top right
            if (yy>1 && xx<6) {
                if (Board[yy - 1][xx + 1].getColor().equals("r") || Board[yy - 1][xx + 1].getColor().equals("R")) {
                    if (Board[yy - 2][xx + 2].getColor().equals(" ")) {
                        cords.add(xx + 2);
                        cords.add(yy - 2);
                        redo = true;
                    }
                }
            }
            //top left
            if (yy>1 && xx>1) {
                if (Board[yy - 1][xx - 1].getColor().equals("r") || Board[yy - 1][xx - 1].getColor().equals("R")) {
                    if (Board[yy - 2][xx - 2].getColor().equals(" ")) {
                        cords.add(xx - 2);
                        cords.add(yy - 2);
                        redo = true;
                    }
                }
            }
        }
        if(Board[yy][xx].kingStatus()==true) {
            //bottom right
            if (yy<6 && xx<6) {
                if (Board[yy + 1][xx + 1].getColor().equals("r") || Board[yy + 1][xx + 1].getColor().equals("R")) {
                    if (Board[yy + 2][xx + 2].getColor().equals(" ")) {
                        cords.add(xx + 2);
                        cords.add(yy + 2);
                        redo = true;
                    }
                }
            }
            //bottom left
            if (yy<6 && xx>1) {
                if (Board[yy + 1][xx - 1].getColor().equals("r") || Board[yy + 1][xx - 1].getColor().equals("R")) {
                    if (Board[yy + 2][xx - 2].getColor().equals(" ")) {
                        cords.add(xx - 2);
                        cords.add(yy + 2);
                        redo = true;
                    }
                }
            }
            //top right
            if (yy>1 && xx<6) {
                if (Board[yy - 1][xx + 1].getColor().equals("r") || Board[yy - 1][xx + 1].getColor().equals("R")) {
                    if (Board[yy - 2][xx + 2].getColor().equals(" ")) {
                        cords.add(xx + 2);
                        cords.add(yy - 2);
                        redo = true;
                    }
                }
            }
            //top left
            if (yy>1&&xx>1) {
                if (Board[yy - 1][xx - 1].getColor().equals("r") || Board[yy - 1][xx - 1].getColor().equals("R")) {
                    if (Board[yy - 2][xx - 2].getColor().equals(" ")) {
                        cords.add(xx - 2);
                        cords.add(yy - 2);
                        redo = true;
                    }
                }
            }
        }
            if(redo==true) {
                CheckerBoard.printBoard();
                Scanner s = new Scanner(System.in);
                System.out.println("Player Black multikill available for coordinates ("+ xx+","+ yy +"). Choose where you want the piece to go!");
                //coordinates for where person wants to move piece
                int  x= s.nextInt();
                s.nextLine();
                int y= s.nextInt();
                s.nextLine();

                boolean wrong=true;
                for(int g=0;g<cords.size();g+=2){
                    if(x==cords.get(g)&&y==cords.get(g+1)){
                        CheckerBoard.moveB(xx, yy, x, y);
                        wrong=false;
                        //king converter
                        if (y == 0) {
                            Board[y][x].setColor("B");
                            Board[y][x].makeKing(true);
                        }
                        multiB(x,y);
                    }
                }
                if(wrong==true){
                    System.out.println("Invalid multikill coordinates, try again!");
                    multiB(xx,yy);
                }
            }
            return redo;
    }

    public static boolean multiR(int xx, int yy){
        pieces[][] Board = new pieces[8][8];
        Board = CheckerBoard.getBoard();
        ArrayList<Integer> cords = new ArrayList<Integer>();
        boolean redo=false;
        if(Board[yy][xx].kingStatus()==false) {
            //bottom right
            if (yy<6&& xx<6) {
                if (Board[yy + 1][xx + 1].getColor().equals("b") || Board[yy + 1][xx + 1].getColor().equals("B")) {
                    if (Board[yy + 2][xx + 2].getColor().equals(" ")) {
                        cords.add(xx + 2);
                        cords.add(yy + 2);
                        redo = true;
                    }
                }
            }
            //bottom left
            if (yy<6 && xx>1) {
                if (Board[yy + 1][xx - 1].getColor().equals("b") || Board[yy + 1][xx - 1].getColor().equals("B")) {
                    if (Board[yy + 2][xx - 2].getColor().equals(" ")) {
                        cords.add(xx - 2);
                        cords.add(yy + 2);
                        redo = true;
                    }
                }
            }
        }
        if(Board[yy][xx].kingStatus()==true) {
            //bottom right
            if (yy<6&& xx<6) {
                if (Board[yy + 1][xx + 1].getColor().equals("b") || Board[yy + 1][xx + 1].getColor().equals("B")) {
                    if (Board[yy + 2][xx + 2].getColor().equals(" ")) {
                        cords.add(xx + 2);
                        cords.add(yy + 2);
                        redo = true;
                    }
                }
            }
            //bottom left
            if (yy<6 && xx>1) {
                if (Board[yy + 1][xx - 1].getColor().equals("b") || Board[yy + 1][xx - 1].getColor().equals("B")) {
                    if (Board[yy + 2][xx - 2].getColor().equals(" ")) {
                        cords.add(xx - 2);
                        cords.add(yy + 2);
                        redo = true;
                    }
                }
            }
            //top right
            if (yy>1 && xx<6) {
                if (Board[yy - 1][xx + 1].getColor().equals("b") || Board[yy - 1][xx + 1].getColor().equals("B")) {
                    if (Board[yy - 2][xx + 2].getColor().equals(" ")) {
                        cords.add(xx + 2);
                        cords.add(yy - 2);
                        redo = true;
                    }
                }
            }
            //top left
            if (yy>1 && xx>1) {
                if (Board[yy - 1][xx - 1].getColor().equals("b") || Board[yy - 1][xx - 1].getColor().equals("B")) {
                    if (Board[yy - 2][xx - 2].getColor().equals(" ")) {
                        cords.add(xx - 2);
                        cords.add(yy - 2);
                        redo = true;
                    }
                }
            }
        }
        if(redo==true) {
            CheckerBoard.printBoard();
            boolean invalid=false;
            Scanner s = new Scanner(System.in);
            System.out.println("Player Red multikill available for coordinates ("+ xx+","+ yy +"). Choose where you want the piece to go!");
            //coordinates for where person wants to move piece
            int  x= s.nextInt();
            s.nextLine();
            int y= s.nextInt();
            s.nextLine();
            boolean wrong=true;
            for(int g=0;g<cords.size();g+=2){
                if(x==cords.get(g)&&y==cords.get(g+1)){
                    CheckerBoard.moveR(xx, yy, x, y);
                    wrong=false;
                    //king converter
                    if(y==7){
                        Board[y][x].setColor("R");
                        Board[y][x].makeKing(true);
                    }
                    multiR(x,y);
                }
            }
            if(wrong==true){
                System.out.println("Invalid multikill coordinates, try again!");
                multiR(xx,yy);
            }
        }
            return redo;
    }
public static boolean mandoB(){
    pieces[][] Board = new pieces[8][8];
    Board = CheckerBoard.getBoard();
    ArrayList<Integer> cords = new ArrayList<Integer>();
    Scanner s = new Scanner (System.in);
    boolean mando=false;
    for(int i=0;i<8;i++){
        for( int z=0;z<8;z++){
            if(Board[i][z].getColor().equals("b")||Board[i][z].getColor().equals("B")){
                //top right
                if(i>2&&z<6){
                    if(Board[i-1][z+1].getColor().equals("r")||Board[i-1][z+1].getColor().equals("R")) {
                        if(Board[i-2][z+2].getColor().equals(" ")){
                            cords.add(z);
                            cords.add(i);
                            cords.add(z+2);
                            cords.add(i-2);
                            mando=true;
                        }
                    }
                }
                //top left
                if(i>2&&z>1){
                    if(Board[i-1][z-1].getColor().equals("r")||Board[i-1][z-1].getColor().equals("R")) {
                        if(Board[i-2][z-2].getColor().equals(" ")) {
                            cords.add(z);
                            cords.add(i);
                            cords.add(z-2);
                            cords.add(i-2);
                            mando=true;
                        }
                    }
                }
                //kings
                if(Board[i][z].kingStatus()==true){
                    //bottom right
                    if(i<6&&z<6){
                        if(Board[i+1][z+1].getColor().equals("r")||Board[i+1][z+1].getColor().equals("R")) {
                            if(Board[i+2][z+2].getColor().equals(" ")) {
                                cords.add(z);
                                cords.add(i);
                                cords.add(z+2);
                                cords.add(i+2);
                                mando=true;
                            }
                        }
                    }
                    //bottom left
                    if(i<6&&z>1){
                        if(Board[i+1][z-1].getColor().equals("r")||Board[i+1][z-1].getColor().equals("R")) {
                            if(Board[i+2][z-2].getColor().equals(" ")) {
                                cords.add(z);
                                cords.add(i);
                                cords.add(z-2);
                                cords.add(i+2);
                                mando=true;
                            }
                        }
                    }
                }
            }
        }
    }
    if (mando==true) {
        boolean correct = false;
        System.out.println("Player Black choose a piece, then choose where to move that piece in the order (X,Y):");
        //coordinates for piece on board
        int X = s.nextInt();
        s.nextLine();
        int Y = s.nextInt();
        s.nextLine();
        //coordinates for where person wants to move piece
        int posX = s.nextInt();
        s.nextLine();
        int posY = s.nextInt();
        s.nextLine();
        boolean invalid;
        invalid = InvalidB(X, Y, posX, posY);
        if (invalid == true) {
            mandoB();
        }
        if (invalid==false) {
            for (int t = 0; t < cords.size(); t += 4) {
                if (X == cords.get(t) && Y == cords.get(t + 1) && cords.get(t + 2) == posX && cords.get(t + 3) == posY) {
                    correct = true;
                }
            }
            boolean print=true;
            if (correct == false) {
                for (int t = 0; t < cords.size(); t += 4) {
                    Board[cords.get(t + 1)][cords.get(t)].setColor(" ");
                    Board[cords.get(t + 1)][cords.get(t)].makeKing(false);
                    System.out.println("Penalty administered for not taking a mandatory piece. Player Black loses their piece at (" + cords.get(t) + "," + cords.get(t + 1) + ")!");
                    if(X==cords.get(t)&&Y==cords.get(t+1)){
                        print=false;
                    }
                }
            }
            if(print==true) {
                CheckerBoard.moveB(X, Y, posX, posY);
            }
            //check if any have reached king row, if so that piece becomes a king
            if (posY == 0) {
                Board[posY][posX].setColor("B");
                Board[posY][posX].makeKing(true);
            }
            CheckerBoard.printBoard();
        }
    }
    return mando;
    }
    public static boolean mandoR(){
        pieces[][] Board = new pieces[8][8];
        Board = CheckerBoard.getBoard();
        ArrayList<Integer> cords = new ArrayList<Integer>();
        Scanner s = new Scanner (System.in);
        boolean mando=false;
        for(int i=0;i<8;i++){
            for( int z=0;z<8;z++){
                if(Board[i][z].getColor().equals("r")||Board[i][z].getColor().equals("R")){
                    //bottom right
                    if(i<6&&z<6){
                        if(Board[i+1][z+1].getColor().equals("b")||Board[i+1][z+1].getColor().equals("B")) {
                            if(Board[i+2][z+2].getColor().equals(" ")) {
                                cords.add(z);
                                cords.add(i);
                                cords.add(z+2);
                                cords.add(i+2);
                                mando=true;
                            }
                        }
                    }
                    //bottom left
                    if(i<6&&z>1){
                        if(Board[i+1][z-1].getColor().equals("b")||Board[i+1][z-1].getColor().equals("B")) {
                            if(Board[i+2][z-2].getColor().equals(" ")) {
                                cords.add(z);
                                cords.add(i);
                                cords.add(z-2);
                                cords.add(i+2);
                                mando=true;
                            }
                        }
                    }
                    //kings
                    if(Board[i][z].kingStatus()==true){
                        //top right
                        if(i>2&&z<6){
                            if(Board[i-1][z+1].getColor().equals("b")||Board[i-1][z+1].getColor().equals("B")) {
                                if(Board[i-2][z+2].getColor().equals(" ")){
                                    cords.add(z);
                                    cords.add(i);
                                    cords.add(z+2);
                                    cords.add(i-2);
                                    mando=true;
                                }
                            }
                        }
                        //top left
                        if(i>2&&z>1){
                            if(Board[i-1][z-1].getColor().equals("b")||Board[i-1][z-1].getColor().equals("B")) {
                                if(Board[i-2][z-2].getColor().equals(" ")) {
                                    cords.add(z);
                                    cords.add(i);
                                    cords.add(z-2);
                                    cords.add(i-2);
                                    mando=true;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (mando==true) {
            boolean correct = false;
            System.out.println("Player Red choose a piece, then choose where to move that piece in the order (X,Y):");
            //coordinates for piece on board
            int X = s.nextInt();
            s.nextLine();
            int Y = s.nextInt();
            s.nextLine();
            //coordinates for where person wants to move piece
            int posX = s.nextInt();
            s.nextLine();
            int posY = s.nextInt();
            s.nextLine();
            boolean invalid;
            invalid = InvalidR(X, Y, posX, posY);
            if (invalid == true) {
                mandoR();
            }
            if (invalid==false) {
                for (int t = 0; t < cords.size(); t += 4) {
                    if (X == cords.get(t) && Y == cords.get(t + 1) && cords.get(t + 2) == posX && cords.get(t + 3) == posY) {
                        correct = true;
                    }
                }
                boolean print=true;
                if (correct == false) {
                    for (int t = 0; t < cords.size(); t += 4) {
                        Board[cords.get(t + 1)][cords.get(t)].setColor(" ");
                        Board[cords.get(t + 1)][cords.get(t)].makeKing(false);
                        System.out.println("Penalty administered for not taking a mandatory piece. Player Red loses their piece at (" + cords.get(t) + "," + cords.get(t + 1) + ")!");
                        if(X==cords.get(t)&&Y==cords.get(t+1)){
                            print=false;
                        }
                    }
                }
                if(print==true) {
                    CheckerBoard.moveR(X, Y, posX, posY);
                }
                //check if any have reached king row, if so that piece becomes a king
                if (posY == 0) {
                    Board[posY][posX].setColor("R");
                    Board[posY][posX].makeKing(true);
                }
                CheckerBoard.printBoard();
            }
        }
        return mando;
    }
}