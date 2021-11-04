import java.util.*;

public class PercolationBFS extends PercolationDFSFast {
    /**
     * Initialize a grid so that all cells are blocked.
     *
     * @param n is the size of the simulated (square) grid
     */
    public PercolationBFS(int n) {
        super(n);
    }


    /***
     * This function overrides the dfs function in DFS to make it faster by using a priority queue
     * @param row
     *            is the row coordinate of the cell being checked/marked
     * @param col is the col of the cell clicked
     */
    @Override
    protected void dfs(int row, int col) {

        // out of bounds?
        if (! inBounds(row,col)) return;

        // full or NOT open, don't process
        if (isFull(row, col) || !isOpen(row, col))
            return;

        Queue<Integer> qdfs = new LinkedList<>();
        myGrid[row][col] = FULL;
        qdfs.add((row*myGrid.length)+col);
        while(!qdfs.isEmpty()) {
            int dq = qdfs.poll();
            int rowval = dq / myGrid.length;
            int colval = dq % myGrid.length;

            if (rowval > 0) {
                if (isOpen(rowval - 1, colval) && !isFull(rowval - 1, colval)) {
                    myGrid[rowval - 1][colval] = FULL;
                    qdfs.add(((rowval - 1) * myGrid.length) + colval);
                }
            }
            if (rowval < myGrid.length - 1) {
                if (isOpen(rowval + 1, colval) && !isFull(rowval + 1, colval)) {
                    myGrid[rowval + 1][colval] = FULL;
                    qdfs.add(((rowval + 1) * myGrid.length) + colval);
                }
            }
            if (colval > 0) {
                if (isOpen(rowval, colval - 1) && !isFull(rowval, colval - 1)) {
                    myGrid[rowval][colval - 1] = FULL;
                    qdfs.add(((rowval) * myGrid.length) + colval - 1);
                }
            }
            if (colval < myGrid.length - 1) {
                if (isOpen(rowval, colval + 1) && !isFull(rowval, colval + 1)) {
                    myGrid[rowval][colval + 1] = FULL;
                    qdfs.add(((rowval) * myGrid.length) + colval + 1);
                }
            }
        }
    }
}
