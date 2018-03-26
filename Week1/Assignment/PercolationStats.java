import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
    private final int T;
    private double[] x;

    public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
    {
        if (n <= 0 || trials <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        x = new double[trials];
        T = trials;
        Percolation percolation = new Percolation(n);
        for (int i = 0; i < trials; i++)
        {
            while (!percolation.percolates())
            {
                int a = StdRandom.uniform(n-1)+1;
                int b = StdRandom.uniform(n-1)+1;

                percolation.open(a, b);
            }
            x[i] = percolation.numberOfOpenSites()/n*n;
        }
    }

    public double mean()                          // sample mean of percolation threshold
    {
        return StdStats.mean(x);
    }

    public double stddev()                        // sample standard deviation of percolation threshold
    {
        return StdStats.stddev(x);
    }

    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
        return mean() - 1.96*stddev()/Math.sqrt(T);
    }

    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
        return mean() + 1.96*stddev()/Math.sqrt(T);
    }

    public static void main(String[] args)        // test client
    {
        int n = 10;
        int trials = 1;

        PercolationStats stats = new PercolationStats(n, trials);

        StdOut.printf("% java-algs4 PercolationStats %d %d\n", n, trials);
        StdOut.printf("mean                    = %d\n", stats.mean());
        StdOut.printf("stddev                  = %d\n", stats.stddev());
        StdOut.printf("95% confidence interval = [%d, %d]\n", stats.confidenceLo(), stats.confidenceHi());
    }
}