import java.util.Random;

/**
 *
 * @author Virginia Penn
 */
public class HillClimbingRandomRestart {
    private static int numStateChanges =0;
    private static int heuristic = 0;
    private static int randomRestarts = 0;
    private static int numLowerNeighborStates = 0;


    /**
     *
     * @return startBoard
     */
    public static EightQueens[] generateBoard() {
        EightQueens[] startBoard = new EightQueens[8];
        Random rnd = new Random();
        for(int i=0; i<8; i++){
            startBoard[i] = new EightQueens(rnd.nextInt(8), i);
        }
        return startBoard;
    }

    //Method to print out the current state board
    private static void printState (EightQueens[] state) {
        int[][] tempBoard = new int[8][8];
        for (int i=0; i<8; i++) {
            tempBoard[state[i].getRow()][state[i].getColumn()]=1;
        }
        System.out.println();
        for (int i=0; i < 8; i++) {
            for (int j= 0; j < 8; j++) {
                System.out.print(tempBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     *
     * @param EightQueens[] state
     * @return heuristic
     */
    public static int findHeuristic (EightQueens[] state) {
        int heuristic = 0;
        for (int i = 0; i< state.length; i++) {
            for (int j=i+1; j<state.length; j++ ) {
                if (state[i].ifConflict(state[j])) {
                    heuristic++;
                }
            }
        }
        return heuristic;
    }

    /**
     *
     * @param currentBoard
     * @return nextBoard
     */
    public static EightQueens[] nextBoard (EightQueens[] currentBoard) {
        EightQueens[] nextBoard = new EightQueens[8];
        EightQueens[] tempBoard = new EightQueens[8];
        int currentHeuristic = findHeuristic(currentBoard);
        int bestHeuristic = currentHeuristic;
        int tempHeuristic;
        
        for (int i=0; i<8; i++) {
            nextBoard[i] = new EightQueens(currentBoard[i].getRow(), currentBoard[i].getColumn());
            tempBoard[i] = nextBoard[i];
        }
        for (int i=0; i<8; i++) { 
            if (i>0){
                tempBoard[i-1] = new EightQueens (currentBoard[i-1].getRow(), currentBoard[i-1].getColumn());
                tempBoard[i] = new EightQueens (0, tempBoard[i].getColumn());
                }
                for (int j=0; j<8; j++) { 
                    tempHeuristic = findHeuristic(tempBoard);
                    if (tempHeuristic < bestHeuristic) {
                    numLowerNeighborStates ++;
                    bestHeuristic = tempHeuristic;
                        for (int k=0; k<8; k++) {
                        nextBoard[k] = new EightQueens(tempBoard[k].getRow(), tempBoard[k].getColumn());
                        }
                    }
                if (tempBoard[i].getRow()!=7)
                    tempBoard[i].move();
                }
        }
        System.out.println("Neighbors found with lower h: " + numLowerNeighborStates);
        if (bestHeuristic == currentHeuristic) {
            if(numLowerNeighborStates == 0){
                System.out.println("RESTART");
            }
            randomRestarts++;
            nextBoard = generateBoard();
            heuristic = findHeuristic(nextBoard);
        } else{
        System.out.println("Setting new current state");
        heuristic = bestHeuristic;
        numStateChanges++;
        }
        
        return nextBoard;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        int currentHeuristic;
        EightQueens[] currentBoard = generateBoard(); //create the initial Board
        currentHeuristic = findHeuristic(currentBoard); //get the initial heuristic
        while (currentHeuristic != 0) { //until a solution is found
            System.out.print("\nCurrent h: " + currentHeuristic + "\nCurrent State");
            printState(currentBoard); //print current board
            currentBoard = nextBoard(currentBoard); //generate next board
            numLowerNeighborStates = 0; //reset for new board
            currentHeuristic  = heuristic; //set new heuristic
        }
        
        /*Once a solution is found: print the board, total number of state changes,
        and number of random restarts required until solution was found*/
        System.out.print("\nCurrent State");
        printState(currentBoard); 
        System.out.println("Solution Found!\nState changes: " + numStateChanges);
        System.out.println("Restarts: " + randomRestarts);
    }
    
}
