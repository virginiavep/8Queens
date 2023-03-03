
/**
 *
 * @author Virginia Penn
 */
public class EightQueens {
    private int row = 8;
    private int column = 8;

    /**
     *
     * @param row
     * @param column
     */
    public EightQueens(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     *
     */
    public void move () {
        row++;
    }

    /**
     *
     * @param q
     * @return true if there is a queen in the row or column.If not return false.
     */
    public boolean ifConflict(EightQueens q){
        //Check rows and columns
        if(row == q.getRow() || column == q.getColumn())
            return true;
        //Check diagonals
        else if(Math.abs(column-q.getColumn()) == Math.abs(row-q.getRow()))
            return true;
        return false;
    }

    /**
     *
     * @return row
     */
    public int getRow() {
        return row;
    }

    /**
     *
     * @return column
     */
    public int getColumn() {
        return column;
    }
}