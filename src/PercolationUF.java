public class PercolationUF implements IPercolate {

    private IUnionFind myFinder;
    private boolean[][] myGrid;
    private final int VTOP;
    private final int VBOTTOM;
    private int myOpenCount;
    private int mySize;


    public PercolationUF(IUnionFind finder, int size) {
        myGrid = new boolean[size][size];
        myFinder = finder;
        myFinder.initialize(size*size + 2);
        VTOP = size*size;
        VBOTTOM = size*size + 1;
        mySize = size;
        myOpenCount = 0;
    }


    /**
     * Checks if the given cell is not open then if it is not open, it is opened. Then checks the neighbouring cells if they are full or not, and if they are, fills the current cell as well. If it is a cell in the top row, it is connected to VTOP by a call to myFinder.union. If it is in the bottom row, percolation is begun by a call to myFinder.union with VBOTTOM.
     * @param row
     *            row index in range [0,N-1]
     * @param col col index in range [0,N-1]
     */
    @Override
    public void open(int row, int col) {
        if (!isOpen(row, col)){
            myGrid[row][col] = true;
            myOpenCount++;
            int[] rowDelta = {-1, 1, 0, 0};
            int[] colDelta = {0, 0, -1, 1};
            int pTemp = row * myGrid.length + col;
            if (row == 0) {
                myFinder.union(pTemp, VTOP);
            }
            if (row == myGrid.length - 1){
                myFinder.union(pTemp, VBOTTOM);
            }
            for (int i = 0; i < colDelta.length; i++){
                int rowTemp = row + rowDelta[i];
                int colTemp = col + colDelta[i];
                if (!(rowTemp < 0 || colTemp < 0 || rowTemp >= myGrid.length || colTemp >= myGrid.length) && isOpen(rowTemp, colTemp)){
                    myFinder.union(pTemp, ((rowTemp) * myGrid.length) + colTemp);
                }
            }
        }
    }

    /**
     * Returns true or false depending on whether the cell are open in the current myGrid instance variable or not
     * @param row
     *            row index in range [0,N-1]
     * @param col col index in range [0,N-1]
     * @return boolean if parameter is open in the grid
     */
    @Override
    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0 ||  row >= myGrid.length|| col >= myGrid[0].length) {throw new IndexOutOfBoundsException("Not in bounds");}
        return myGrid[row][col];
    }

    /**
     * Returns true of false depending on if the cell are connected to the VTOP, basically if they percolates
     * @param row
     *            row index in range [0,N-1]
     * @param col col index in range [0,N-1]
     * @return boolean if the parameters are connected to VTOP
     */
    @Override
    public boolean isFull(int row, int col) {
        if (row < 0 ||  row >= myGrid.length || col < 0 || col >= myGrid[0].length) {throw new IndexOutOfBoundsException("Not in bounds");}
        return myFinder.connected((row * myGrid.length) + col, VTOP);
    }

    /**
     * Check to see if VTOP is connected to VBOTTOM
     * @return boolean if VTOP is connected to VBOTTOM
     */
    @Override
    public boolean percolates() {
        return myFinder.connected(VTOP, VBOTTOM);
    }

    /**
     * Returns the value of how many open sites there are
     * @return myOpenCount instance variable
     */
    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }
}
