package Week2;

// "PentominosApplet"
//
// A pentomino consists of five squares attached along their edges.
// There are exactly twelve possible pentominos that can be formed
// in this way (not counting reflections and rotations).  Someone once
// gave me a Pentominos puzzle where the goal was to place the 12
// pentominos on an 8-by-8 board, leaving 4 blank squares in certain
// previously decided positions.  This Java applet solves the puzzle
// using a straightforward recursive backtracking procedure.  I have
// not tried for great efficiency here; I just like watching the
// pieces being played.
//
// The applets begins with a randomly chosen board.  The user can
// clear the board and then set up any desired board.  The user
// controls the speed at which pieces are tried, and can choose to
// step through the process one move at a time.
//
// March 21, 1997
// This is a significant rewrite a previous version dated January 6, 1997,
// and titled "PentominosSolver" instead of "PentominosApplet".
// (The new version uses wait() and notify() instead of suspend() and
// resume() to handle control of the thread that solves the puzzles.)
//
//         by:  David J. Eck
//              Deparment of Mathematics and Computer Science
//              Hobart and William Smith Colleges
//              Geneva, NY   14456
//              Email:  eck@hws.edu
//

import java.applet.Applet;
import java.awt.*;

public class PentominosApplet extends Applet implements Runnable {

    int board[];          // The 8-by-8 board is actually represented
    // conceptually as a 10-by-10 data structure
    // in which the cells along the border
    // are declared permanently "filled"
    // This simplifies testing whether a given
    // piece fits at a given position on the
    // board.  Furthermore, this 10-by-10 board
    // is represented by a 1-dimensional array
    // in which the 10*i+j-th entry represents
    // row j and column i on the board.

    PentominosBoardCanvas boardcanvas;  // for displaying the board on the screen

    Label comment;   // status comment displayed over playing board

    boolean used[];  //  used[i] tells whether piece # i is already on the board

    int numused;     // number of pieces currently on the board, from 0 to 12

    Thread gameThread = null;   // a thread to run the puzzle solving procedure
    // (while the main applet thread handles the user interface)

    boolean aborted;  // used in play() to test whether the puzzle is solved (or aborted)

    Button clearButton,randomButton,goButton,pauseButton,stepButton;  // controls
    Choice speedChoice;

    final int[]  speedDelay = { 3, 20, 100, 500, 1000 };  // delay times between moves --
    // 5 possible speeds, slected by user
    int speed = 2;  // initial default speed and corresponding delay
    int delay = 100;

    int message = 0;   // "message" is used by user-interface thread to send
    // control messages to the game-playing thread.  A value of
    // 0 indicates "no message"
    final static int goMessage = 1;      // the values for the message variable
    final static int stepMessage = 2;
    final static int pauseMessage = 3;
    final static int clearMessage = 4;
    final static int randomMessage = 5;



    final int pieces[][] = {
            { 1, 1,2,3,4 },         // This array represents everything the program
            { 1, 10,20,30,40 },     // knows about the individual pentominos.  Each
            { 2, 9,10,11,20 },      // row in the array represents a particular
            { 3, 1,10,19,20 },      // pentomino in a particular orientation.  Different
            { 3, 10,11,12,22 },     // orientations are obtained by rotating or flipping
            { 3, 1,11,21,22 },      // the pentomino over.  Note that the program must
            { 3, 8,9,10,18 },       // try each pentomino in each possible orientation,
            { 4, 10,20,21,22 },     // but must be careful not to reuse a piece if
            { 4, 1,2,10,20 },       // it has already been used on the board in a
            { 4, 10,18,19,20 },     // different orientation.
            { 4, 1,2,12,22 },       //     The pentominoes are numbered from 1 to 12.
            { 5, 1,2,11,21 },       // The first number on each row here tells which pentomino
            { 5, 8,9,10,20 },       // that line represents.  Note that there can be
            { 5, 10,19,20,21 },     // up to 8 different rows for each pentomino.
            { 5, 10,11,12,20 },     // some pentominos have fewer rows because they are
            { 6, 10,11,21,22 },     // symmetric.  For example, the pentomino that looks
            { 6, 9,10,18,19 },      // like:
            { 6, 1,11,12,22 },      //           GGG
            { 6, 1,9,10,19 },       //           G G
            { 7, 1,2,10,12 },       //
            { 7, 1,11,20,21 },      // can be rotated into three additional positions,
            { 7, 2,10,11,12 },      // but flipping it over will give nothing new.
            { 7, 1,10,20,21 },      // So, it has only 4 rows in the array.
            { 8, 10,11,12,13 },     //     The four remaining entries in the array
            { 8, 10,20,29,30 },     // describe the given piece in the given orientation,
            { 8, 1,2,3,13 },        // in a way convenient for placing the piece into
            { 8, 1,10,20,30 },      // the one-dimensional array that represents the
            { 8, 1,11,21,31 },      // board.  As an example, consider the row
            { 8, 1,2,3,10 },        //
            { 8, 10,20,30,31 },     //           { 7, 1,2,10,19 }
            { 8, 7,8,9,10 },        //
            { 9, 1,8,9,10 },        // If this piece is placed on the board so that
            { 9, 10,11,21,31 },     // its topmost/leftmost square fills position
            { 9, 1,2,9,10 },        // p in the array, then the other four squares
            { 9, 10,20,21,31 },     // will be at positions  p+1, p+2, p+10, and p+19.
            { 9, 1,11,12,13 },      // To see whether the piece can be played at that
            { 9, 10,19,20,29 },     // position, it suffices to check whether any of
            { 9, 1,2,12,13 },       // these five squares are filled.
            { 9, 9,10,19,29 },      //     On the board, each piece will be shown
            { 10, 8,9,10,11 },      // in its own color.
            { 10, 9,10,20,30 },
            { 10, 1,2,3,11 },
            { 10, 10,20,21,30 },
            { 10, 1,2,3,12 },
            { 10, 10,11,20,30 },
            { 10, 9,10,11,12 },
            { 10, 10,19,20,30 },
            { 11, 9,10,11,21 },
            { 11, 1,9,10,20 },
            { 11, 10,11,12,21 },
            { 11, 10,11,19,20 },
            { 11, 8,9,10,19},
            { 11, 1,11,12,21 },
            { 11, 9,10,11,19 },
            { 11, 9,10,20,21 },
            { 12, 1,10,11,21 },
            { 12, 1,2,10,11 },
            { 12, 10,11,20,21 },
            { 12, 1,9,10,11 },
            { 12, 1,10,11,12 },
            { 12, 9,10,19,20 },
            { 12, 1,2,11,12 },
            { 12, 1,10,11,20 },
    };


    public void init() {

        board = new int[100];
        used = new boolean[13];

        setLayout(new BorderLayout(5,5));
        boardcanvas = new PentominosBoardCanvas(board,this);  // for displaying the board
        add("Center",boardcanvas);

        comment = new Label("Pentominoes!");
        comment.setFont(new Font("TimesRoman", Font.BOLD, 14));
        add("North",comment);

        Panel right = new Panel();                // holds control buttons
        right.setLayout(new GridLayout(6,1,5,5));
        clearButton = new Button("Clear");
        randomButton = new Button("Random");
        goButton = new Button("Go");
        pauseButton = new Button("Pause");
        stepButton = new Button("Step");

        right.add(clearButton);
        right.add(randomButton);
        right.add(goButton);
        right.add(pauseButton);
        right.add(stepButton);

        speedChoice = new Choice();
        speedChoice.addItem("Fastest");
        speedChoice.addItem("Fast");
        speedChoice.addItem("Moderate");
        speedChoice.addItem("Slow");
        speedChoice.addItem("Slower");
        speedChoice.select("Moderate");
        right.add(speedChoice);

        add("East",right);

    }

    public Insets insets() {
        return new Insets(5,5,5,5);
    }

    public void start() {  // start a new game-running thread, with a random board
        gameThread = new Thread(this);
        setMessage(randomMessage);
        goButton.disable();
        pauseButton.enable();
        stepButton.disable();
        boardcanvas.stopCreating();
        gameThread.start();
    }

    public void stop() {
        if (gameThread != null && gameThread.isAlive())
            gameThread.stop();
    }

    boolean putPiece(int p, int square) {  // try to place a piece on the board,
        // return true if it fits
        if (board[square] != 0)
            return false;
        for (int i = 1; i <= 4; i ++)
            if (board[square + pieces[p][i]] != 0)  // one of the squares needed is already occupied
                return false;
        boardcanvas.playPiece(pieces[p],square);  // color in the squares to represent the piece
        return true;
    }


    void play(int square) {   // recursive procedure that tries to solve the puzzle
        // parameter "square" is the number of the next empty
        // to be filled
        for (int p=0; p<63; p++)
            if (!aborted && (used[pieces[p][0]] == false) && putPiece(p,square)) {  // try piece p
                // a piece has been placed on the board.
                used[pieces[p][0]] = true;
                numused++;
                boolean stepping = false;
                int message = getMessage();
                if (message > 0) {
                    if (message == pauseMessage || message == stepMessage) {
                        stepping = true;
                        setMessage(0);
                    }
                    else if (message == clearMessage || message == randomMessage) {
                        aborted = true;
                        return;  // note: don't setMessage(0), since run() has to handle message
                    }
                    else  // go message
                        setMessage(0);
                }
                if (numused == 12) {  // puzzle is solved
                    synchronized(this) {
                        goButton.enable();
                        stepButton.enable();
                        pauseButton.disable();
                        comment.setText("Solution found:");
                    }
                    doDelay(-1);  // wait indefinitely for user command
                    comment.setText("Solving...");
                }
                else {
                    if (stepping)    // pause after placing a piece
                        doDelay(-1);
                    else
                        doDelay(delay);
                    int nextSquare = square;
                    while (board[nextSquare] != 0)  // find next empty square
                        nextSquare++;
                    play(nextSquare);  // and try to complete the solution
                    if (aborted)
                        return;
                }
                boardcanvas.removePiece(pieces[p],square);  // backtrack
                numused--;
                used[pieces[p][0]] = false;
            }
    }

    void clearBoard() {
        for (int i=0; i<100; i++) // fill in the border with -1's
            board[i] = -1;
        for (int i=1; i<9; i++)   // fill in the rest of the board with empty spaces (0's)
            for (int j=1; j<9; j++)
                board[j*10+i] = 0;
        boardcanvas.repaint();
    }

    void setUpRandomBoard() {
        for (int i=0; i<100; i++) // fill in the border with -1's
            board[i] = -1;
        for (int i=1; i<9; i++)   // fill in the rest of the board with empty spaces (0's)
            for (int j=1; j<9; j++)
                board[j*10+i] = 0;
        int x,y;
        switch ((int)(5*Math.random())) {
            case 0:
                for (int i=0; i < 4; i ++) {
                    do {
                        x = 1 + (int)(8*Math.random());
                        y = 1 + (int)(8*Math.random());
                    } while (board[y*10+x] == -1);
                    board[y*10+x] = -1;
                }
                break;
            case 1:
            case 2:
                do {
                    x = 1 + (int)(8*Math.random());
                    y = 1 + (int)(8*Math.random());
                } while (y == 5 || x == 5);
                board[10*y+x] = -1;
                board[10*y+(9-x)] = -1;
                board[10*(9-y)+x] = -1;
                board[10*(9-y)+(9-x)] = -1;
                break;
            default:
                x = (int)(6*Math.random()) + 1;
                y = (int)((x)*Math.random()) + 1;
                board[y*10+x] = -1;
                board[y*10+x+1] = -1;
                board[y*10+x+10] = -1;
                board[y*10+x+11] = -1;
                break;
        }
        boardcanvas.repaint();
    }

    public void run() {
        while (true) {
            while (getMessage() != goMessage) {  // wait for game setup
                if (getMessage() == randomMessage) {
                    setUpRandomBoard();
                    boardcanvas.stopCreating();  // tell board to ignore mouse clicks
                    setMessage(goMessage);
                    doDelay(1000);  // give user a chance to change selection
                }
                else if (getMessage() == clearMessage) {
                    clearBoard();
                    boardcanvas.startCreating();  // tell board to let user set up puzzle
                    comment.setText("Click four squares:");
                    setMessage(0);
                    doDelay(-1);  // wait forever (for control message from action() or boardcanvas.mouseDown())
                }
            }
            // begin next game
            synchronized(this) {
                goButton.disable();
                pauseButton.enable();
                stepButton.disable();
                comment.setText("Solving...");
                message = 0;
            }
            for (int i=1; i<=12; i++)
                used[i] = false;
            numused = 0;
            int square = 11;  // reprsents the upper left corner of the board
            while (board[square] == -1)
                square++;  // move past any filled squares, since Play(square) assumes the square is empty
            aborted = false;
            play(square);
            if (!aborted) {
                pauseButton.disable();
                stepButton.disable();
                goButton.disable();
                comment.setText("No (further) solution found.");
                doDelay(-1);
            }
        }
    }

    synchronized void doDelay(int milliseconds) {
        // wait for specified time, or until a control message is sent using setMessage()
        // is generated.  For an indefinite wait, milliseconds should be < 0
        if (milliseconds < 0) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
        else {
            try {
                wait(milliseconds);
            }
            catch (InterruptedException e) {
            }
        }
    }

    synchronized void setMessage(int message) {  // send control message to game thread
        this.message = message;
        if (message > 0)
            notify();
    }

    synchronized int getMessage() {
        return this.message;
    }

    synchronized public boolean action(Event evt, Object arg) {
        if (evt.target == clearButton) {
            pauseButton.disable();
            goButton.disable();
            stepButton.disable();
            setMessage(clearMessage);
            notify();
            return true;
        }
        else if (evt.target == randomButton) {
            pauseButton.disable();
            goButton.disable();
            stepButton.disable();
            setMessage(randomMessage);
            notify();
            return true;
        }
        else if (evt.target == goButton) {
            pauseButton.enable();
            goButton.disable();
            stepButton.disable();
            setMessage(goMessage);
            notify();
            return true;
        }
        else if (evt.target == pauseButton) {
            pauseButton.disable();
            goButton.enable();
            stepButton.enable();
            setMessage(pauseMessage);
            notify();
            return true;
        }
        else if (evt.target == stepButton) {
            setMessage(stepMessage);
            notify();
            return true;
        }
        else if (evt.target == speedChoice) {
            int item = speedChoice.getSelectedIndex();
            if (speed != item) {
                speed = item;
                delay = speedDelay[speed];
            }
            return true;
        }
        else
            return super.action(evt,arg);
    }


}   // end of class PentominosSolver


class PentominosBoardCanvas extends Panel {  // implement the visible 8-by-8 playing board

    int[] board;  // The board data array, passed into the constructor and
    //    used throughout this class

    PentominosApplet owner;  // used only to set notification back to applet that
    // the user has finished setting up a board (used
    // in mouseDown()).

    Color pieceColor[];  // Array of colors to be used in displaying pieces
    //   pieceColor[0] is the color of an empty square,
    //   pieceColor[1] is the color for piece number 1, etc.

    boolean creatingBoard;  // this is true when user is setting up a board
    int clickCt;  // number of squares that have been blackened by the user; when
    // this value reaches four, it is time to start solving the puzzle


    PentominosBoardCanvas(int[] theBoard, PentominosApplet theOwner) { // Constructor
        board = theBoard;
        owner = theOwner;
        MakeColors();  // create and fill in pieceColor[] array
    }

    void MakeColors() {
        pieceColor = new Color[13];
        pieceColor[0] = Color.white;
        pieceColor[1] = new Color(200,0,0);
        pieceColor[2] = new Color(150,150,255);
        pieceColor[3] = new Color(0,200,200);
        pieceColor[4] = new Color(255,150,255);
        pieceColor[5] = new Color(0,200,0);
        pieceColor[6] = new Color(150,255,255);
        pieceColor[7] = new Color(200,200,0);
        pieceColor[8] = new Color(0,0,200);
        pieceColor[9] = new Color(255,150,150);
        pieceColor[10] = new Color(200,0,200);
        pieceColor[11] = new Color(255,255,150);
        pieceColor[12] = new Color(150,255,150);
    }

    // Note:  The following methods are synchronized because when the
    // board is resized, the main applet thread might try to call paint
    // while the puzzle-solving thread is trying to place squares on the
    // Board.  (I'm not sure this should really happen because the puzzle
    // thread is supposed to be running at a lower priority, but before
    // I added the synchronization, the screen would get corrupted when
    // I resized the board while the puzzle was in the process of being
    // solved.)

    public synchronized void paint(Graphics g) {  // redraw the whole board
        int w = size().width;
        w = 8 * (w/8) + 1;
        int h = size().height;
        h = 8 * (h/8) + 1;
        Color oldColor = g.getColor();
        g.setColor(Color.black);
        for (int i = 0; i <= 8; i++) {
            int x = i * ((w - 1) / 8);
            g.drawLine(x,0,x,h-1);
        }
        for (int i = 0; i <= 8; i++) {
            int y = i * ((h - 1) / 8);
            g.drawLine(0,y,w-1,y);
        }
        for (int i = 1; i <= 8; i++) {
            int y = (i-1) * ((h-1) / 8);
            for (int j = 1; j <= 8; j++) {
                int x = (j-1) * ((w-1) / 8);
                if (board[10*i + j] == -1)
                    g.setColor(Color.black);
                else
                    g.setColor(pieceColor[board[10*i + j]]);
                g.fillRect(x+1, y+1, (w-1) / 8 - 1, (h-1) / 8 - 1);
            }
        }
        g.setColor(oldColor);
    }

    synchronized void putSquare(Graphics g, int name, int square) {  // "name" gives the piece number
        int w = size().width;
        w = 8 * (w/8) + 1;
        int h = size().height;
        h = 8 * (h/8) + 1;
        int x = ((square % 10)-1) * ((w-1)) / 8;
        int y = ((square / 10)-1) * ((h-1)) / 8;
        g.setColor(pieceColor[name]);
        g.fillRect(x+1, y+1, (w-1)/8 - 1, (h-1)/8 - 1);
        g.setColor(Color.black);
        board[square] = name;
    }

    synchronized void playPiece(int[] pieceData, int startSquare) {
        Graphics g = getGraphics();
        putSquare(g,pieceData[0],startSquare);
        for (int i = 1; i < 5; i++)
            putSquare(g,pieceData[0],startSquare+pieceData[i]);
        g.dispose();
    }

    synchronized void clearSquare(Graphics g, int square) {
        int w = size().width;
        w = 8 * (w/8) + 1;
        int h = size().height;
        h = 8 * (h/8) + 1;
        int x = ((square % 10)-1) * ((w-1)) / 8;
        int y = ((square / 10)-1) * ((h-1)) / 8;
        g.setColor(pieceColor[0]);
        g.fillRect(x+1, y+1, (w-1)/8 - 1, (h-1)/8 - 1);
        g.setColor(Color.black);
        board[square] = 0;
    }

    synchronized void removePiece(int[] pieceData, int startSquare) {
        Graphics g = getGraphics();
        clearSquare(g,startSquare);
        for (int i = 1; i < 5; i++)
            clearSquare(g,startSquare+pieceData[i]);
        g.dispose();
    }

    synchronized void blackenSquare(int square) {
        int w = size().width;
        w = 8 * (w/8) + 1;
        int h = size().height;
        h = 8 * (h/8) + 1;
        int x = ((square % 10)-1) * ((w-1)) / 8;
        int y = ((square / 10)-1) * ((h-1)) / 8;
        Graphics g = getGraphics();
        g.fillRect(x, y, (w-1)/8, (h-1)/8);
        g.dispose();
        board[square] = -1;
    }

    public void startCreating() {
        clickCt = 0;
        creatingBoard = true;
    }

    public void stopCreating() {
        creatingBoard = false;
    }

    public boolean mouseDown(Event evt, int x, int y) {
        if (creatingBoard) {
            int w = size().width / 8;
            int h = size().height / 8;
            int col = x / w;
            int row = y / h;
            if (col < 0 || col > 7 || row < 0 || row > 7)
                return true;
            int square = 10*(row+1) + (col+1);
            if (board[square] == 0) {
                blackenSquare(square);
                clickCt++;
            }
            else {
                Graphics g = getGraphics();
                clearSquare(g,square);
                g.dispose();
                clickCt--;
            }
            if (clickCt == 4) {
                creatingBoard = false;
                owner.setMessage(PentominosApplet.goMessage);
            }
        }
        return true;
    }

    public Dimension minimumSize() {
        return new Dimension(160,160);
    }

    public Dimension preferredSize() {
        return minimumSize();
    }
}