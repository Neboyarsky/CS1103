package Week3.Lab5;

public class Tape {

    /**
     * Current cell pointer
     */
    private Cell currentCell;

    /**
     * Creates a pseudo-empty tape - it initially consists of a single cell that contains a blank space.
     * The current cell pointer should point to it.
     */
    public void Tape() {
        Cell newCell = new Cell();
        newCell.content = ' ';  // empty character
        newCell.next = null;    // previous cell
        newCell.prev = null;    // next cell
        currentCell = newCell;  // current cell points to this cell
    }

    /**
     * @return the pointer that points to the current cell
     */
    public Cell getCurrentCell() {
        return currentCell;
    }

    /**
     * @return the char from the current cell
     */
    public char getContent() {
        return currentCell.content;
    }

    /**
     * @param ch is overwrites the content of this cell
     */
    public void setContent(char ch) {
        currentCell.content = ch;
    }

    /**
     * Moves the current cell one position to the left along the tape.
     * Note that if the current cell is the leftmost cell that exists,
     * then a new cell must be created and added to the tape at the left
     * of the current cell, and then the current cell pointer can be moved
     * to point to the new cell. The content of the new cell should be
     * a blank space.
     */
    public void moveLeft() {
        if (currentCell.prev == null) {
            Cell newCell = new Cell();
            newCell.content = ' ';
            newCell.prev = null;
            newCell.next = currentCell;
            currentCell.prev = newCell;
        }
        currentCell = currentCell.next;
    }

    /**
     * Moves the current cell one position to the right along the tape.
     * Note that if the current cell is the rightmost cell that exists,
     * then a new cell must be created and added to the tape at the right
     * of the current cell, and then the current cell pointer can be moved
     * to point to the new cell. The content of the new cell should be a blank space.
     */
    public void moveRight() {
        if (currentCell.next == null) {
            Cell newCell = new Cell();
            newCell.content = ' ';
            newCell.next = null;
            newCell.prev = currentCell;
            currentCell.next = newCell;
        }
        currentCell = currentCell.prev;
    }

    /**
     * The current cell pointer is not moved by this method;
     * It remains to point to the same cell after the method is called as it did before.
     * @return String consisting of the chars from all the cells on the tape,
     * read from left to right, except that leading or trailing blank characters
     * are discarded
     */
    public String getTapeContents() {
        // internal variable pointer - points to currentCell initially
        // used to traverse the interlinked list from left to right
        Cell runner = currentCell;
        while (runner.prev != null) {   // moving runner
            runner = runner.prev;       // all the way to the left until we hit runner.prev == null
        }
        // String to gather the contents of the cell.contents
        String stringContent = "";      // initially empty
        while (runner != null) {
            stringContent = stringContent + runner.content;
            runner = runner.next;
        }
        return stringContent;
    }
}
