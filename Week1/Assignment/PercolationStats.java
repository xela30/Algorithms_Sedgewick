import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;
    private double[] x;

    public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
    {
        if (n <= 0 || trials <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        x = new double[trials];

        for (int i = 0; i < trials; i++)
        {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates())
            {
                int a = StdRandom.uniform(n)+1;
                int b = StdRandom.uniform(n)+1;

                percolation.open(a, b);
            }
            x[i] = (double)percolation.numberOfOpenSites()/(n*n);
        }

        mean = StdStats.mean(x);
        stddev = StdStats.stddev(x);
        confidenceLo = mean - 1.96*stddev/Math.sqrt(trials);
        confidenceHi = mean + 1.96*stddev/Math.sqrt(trials);
    }

    public double mean()                          // sample mean of percolation threshold
    {
        return mean;
    }

    public double stddev()                        // sample standard deviation of percolation threshold
    {
        return stddev;
    }

    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
        return confidenceLo;
    }

    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
        return confidenceHi;
    }

    public static void main(String[] args)        // test client
    {
        int n = 200;
        int trials = 10000;

        PercolationStats stats = new PercolationStats(n, trials);

        StdOut.printf("java-algs4 PercolationStats %d %d\n", n, trials);
        StdOut.printf("mean                    = %f\n", stats.mean());
        StdOut.printf("stddev                  = %f\n", stats.stddev());
        StdOut.printf("95 confidence interval = [%f, %f]\n", stats.confidenceLo(), stats.confidenceHi());
    }
}