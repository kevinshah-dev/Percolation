public class PercolationDFSFast extends PercolationDFS{

    /**
     * Initialize a grid so that all cells are blocked.
     *
     * @param n is the size of the simulated (square) grid
     */
    public PercolationDFSFast(int n) {
        super(n);
    }

    @Override
    protected void updateOnOpen(int row, int col) {
        int[] rowD = {-1, 1, 0, 0};
        int[] colD = {0, 0, -1, 1};
        for (int i = 0; i < rowD.length; i++){
            if ((row == 0) || (inBounds(row + rowD[i], col + colD[i]) && isFull(row + rowD[i], col + colD[i]))){
                dfs(row, col);
                return;
            }
        }
    }
}
