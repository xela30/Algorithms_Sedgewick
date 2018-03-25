import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int N;

    public Percolation(int n)                // create n-by-n grid, with all sites blocked
    {
        if (n <= 0)
            throw new java.lang.IllegalArgumentException();

        N = n;
    }

    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        ValidateIndicies(row, col);
    }

    private void ValidateIndicies(int row, int col) {
        if (row < 1 || row > N || col < 1 || col > N)
        {
            throw new java.lang.IllegalArgumentException();
        }
    }

    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        ValidateIndicies(row, col);
        return false;
    }

    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        ValidateIndicies(row, col);
        return false;
    }

    public int numberOfOpenSites()       // number of open sites
    {
        return 0;
    }

    public boolean percolates()              // does the system percolate?
    {
        return false;
    }

    public static void main(String[] args)   // test client (optional)
    {
    }
}
