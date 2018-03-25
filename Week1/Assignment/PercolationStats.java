public class PercolationStats {
    public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
    {
        if (n <= 0 || trials <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
    }

    public double mean()                          // sample mean of percolation threshold
    {
        return Double.NaN;
    }

    public double stddev()                        // sample standard deviation of percolation threshold
    {
        return Double.NaN;
    }

    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
        return Double.NaN;
    }

    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
        return Double.NaN;
    }

    public static void main(String[] args)        // test client (described below)
    {
    }
}