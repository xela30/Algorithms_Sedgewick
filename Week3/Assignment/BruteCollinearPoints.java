import java.util.Arrays;

public class BruteCollinearPoints {

    private LineSegment[] _segments;
    private int _segmentsCount = 0;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {

        checkNulls(points);

        checkDuplicatedEntries(points);

        Arrays.sort(points);

        _segments = new LineSegment[points.length];

        for (int p = 0; p < points.length - 3; p++) {
            for (int q = p + 1; q < points.length - 2; q++) {
                for (int r = q + 1; r < points.length - 1; r++) {
                    for (int s = r + 1; s < points.length; s++) {
                        double slope_pq = points[p].slopeTo(points[q]);
                        double slope_pr = points[p].slopeTo(points[r]);
                        double slope_ps = points[p].slopeTo(points[s]);
                        if (slope_pq == slope_pr && slope_pq == slope_ps) {
                            _segments[_segmentsCount++] = new LineSegment(points[p], points[s]);
                        }
                    }
                }
            }
        }
    }


    // the number of line segments
    public int numberOfSegments() {
        return _segmentsCount;
    }

    // the line segments
    public LineSegment[] segments() {
        return Arrays.copyOf(_segments, _segmentsCount);
    }

    private void checkDuplicatedEntries(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Duplicates found in points collection.");
                }
            }
        }
    }

    private void checkNulls(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException("Points collection is null");

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null || (points[i] == points[i + 1] && i < points.length - 1)) {
                throw new IllegalArgumentException("Points collection contains null elements.");
            }
        }
    }
}
