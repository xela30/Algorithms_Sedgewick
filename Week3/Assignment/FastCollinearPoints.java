import java.util.Arrays;

public class FastCollinearPoints {
    private LineSegment[] _segments = new LineSegment[1];
    int _segmentsCount = 0;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        checkNulls(points);

        Arrays.sort(points);

        checkDuplicatedEntries(points);

        for (int i = 0; i < points.length; i++) {
            Point[] aux = Arrays.copyOf(points, points.length);
            Arrays.sort(aux, points[i].slopeOrder());
            int head = 0;
            int tail = 0;
            for (int j = 1; j < aux.length - 1; j++) {
                while (j < aux.length - 1 && aux[0].slopeTo(aux[j]) == aux[0].slopeTo(aux[j + 1])) {
                    if (head == 0) {
                        head = j;
                    }
                    tail = ++j;
                }
                if (tail - head > 1) {
                    Point[] collinearPoints = new Point[tail - head + 2];
                    collinearPoints[0] = aux[0];
                    int m = 1;
                    for (int k = head; k <= tail; k++) {
                        collinearPoints[m++] = aux[k];
                    }
                    Arrays.sort(collinearPoints);

                    if (_segmentsCount == _segments.length) {
                        resize();
                    }
                    if (aux[0].compareTo(collinearPoints[0]) == 0) {
                        _segments[_segmentsCount++] = new LineSegment(collinearPoints[0], collinearPoints[collinearPoints.length - 1]);
                    }
                }
                head = 0;
                tail = 0;
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

    private void resize() {
        LineSegment[] copy = new LineSegment[_segments.length * 2];
        System.arraycopy(_segments, 0, copy, 0, _segmentsCount);
        _segments = copy;
    }

    private void checkNulls(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException("Points collection is null");

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null || (i < points.length - 1 && points[i] == points[i + 1])) {
                throw new IllegalArgumentException("Points collection contains null elements.");
            }
        }
    }
}