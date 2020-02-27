import java.util.Arrays;

public class FastCollinearPoints {
    private LineSegment[] _segments = new LineSegment[10];
    int _segmentsCount = 0;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        checkNulls(points);

        Arrays.sort(points);

        checkDuplicatedEntries(points);

        for (int i = 0; i < points.length - 4; i++) {
            Arrays.sort(points, points[i].slopeOrder());
            Point[] aux = new Point[points.length];
            aux[0] = points[i];
            int auxCount = 1;
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[j + 1])) {
                    aux[auxCount++] = points[j];
                    aux[auxCount++] = points[j + 1];
                } else {
                    if (auxCount > 4) {
                        Point[] aux2 = Arrays.copyOf(aux, auxCount - 1);;
                        Arrays.sort(aux2);
                        _segments[_segmentsCount++] = new LineSegment(aux2[0], aux2[auxCount--]);
                        aux = new Point[points.length];
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
        return Arrays.copyOf(_segments, _segmentsCount - 1);
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
            if (points[i] == null || (i < points.length - 1 && points[i] == points[i + 1])) {
                throw new IllegalArgumentException("Points collection contains null elements.");
            }
        }
    }
}