import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int openSitesCount = 0;
    private final int N;
    private WeightedQuickUnionUF uf;
    private boolean[] opened;

    public Percolation(int n)                // create n-by-n grid, with all sites blocked
    {
        if (n <= 0)
            throw new java.lang.IllegalArgumentException();

        N = n;

        uf = new WeightedQuickUnionUF(n * n + 2);

        opened = new boolean[n * n + 1];

        // connect top line to virtual node
        for (int i = 1; i <= n; i++) {
            uf.union(i, 0);
        }

        // connect bottom line to virtual node
        for (int i = n * n - n + 1; i <= n * n; i++) {
            uf.union(i, n * n + 1);
        }
    }

    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        ValidateCoordinates(row, col);

        int indice = xyTo1D(row, col);

        if (!opened[indice]) {
            opened[indice] = true;
            openSitesCount++;

            if (col > 1) { // left neighbor
                if (isOpen(row, col - 1)) {
                    uf.union(indice, indice - 1);
                }
            }

            if (col < N) { // right neighbor
                if (isOpen(row, col + 1)) {
                    uf.union(indice, indice + 1);
                }
            }

            if (row > 1) { // top neighbor
                if (isOpen(row - 1, col)) {
                    uf.union(indice - N, indice);
                }
            }

            if (row < N) { // bottom neighbor
                if (isOpen(row + 1, col)) {
                    uf.union(indice + N, indice);
                }
            }
        }
    }

    private int xyTo1D(int row, int col) {
        ValidateCoordinates(row, col);

        return N * (row - 1) + col;
    }

    private void ValidateCoordinates(int row, int col) {
        if (row < 1 || row > N || col < 1 || col > N) {
            throw new java.lang.IllegalArgumentException();
        }
    }

    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        ValidateCoordinates(row, col);
        int indice = xyTo1D(row, col);
        return opened[indice];
    }

    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        ValidateCoordinates(row, col);
        int indice = xyTo1D(row, col);
        return uf.connected(indice, 0) && opened[indice];
    }

    public int numberOfOpenSites()       // number of open sites
    {
        return openSitesCount;
    }

    public boolean percolates()              // does the system percolate?
    {
        return openSitesCount > 0 && uf.connected(0, N * N + 1);
    }
}
