iimport java.util.*;

public class CoveringSegments {

    private static int[] optimalPoints(Segment[] segments) {
        
        ArrayList<Integer> pointList = new ArrayList<>();
        ArrayList<Segment> segmentList = new ArrayList<>();
        int minEnd;
        
        segmentList.addAll(Arrays.asList(segments));
        
        while (segmentList.size() > 1) {  
            minEnd = calcMinEnd(segmentList);
            pointList.add(minEnd);
            segmentList = segmentRemover(segmentList,minEnd);
        }
        
        int[] points = new int[pointList.size()];
        
        for (int i=0; i < pointList.size(); ++i) {
            points[i] = pointList.get(i);
            System.out.println(points[i]);
        }
        
        return points;
    }
    
    private static ArrayList<Segment> segmentRemover(ArrayList<Segment> segmentList, int minEnd) {
        
        Iterator<Segment> it = segmentList.iterator();
        
        while (it.hasNext()) {
            Segment seg = it.next();
            if (seg.start <= minEnd && minEnd <= seg.end) {
                it.remove();
            }
        }
        
        return segmentList;
    }

    private static int calcMinEnd(ArrayList<Segment> segmentList) {
        
        int minEnd = 0;
        int currentValue;
        
        for (int i=0; i < segmentList.size(); ++i) {
            currentValue = (int) segmentList.get(i).end;
            if (minEnd == 0) {
                minEnd = currentValue;
            }
            if (currentValue < minEnd) {
                minEnd = currentValue;
            }
        }
        return minEnd;
    }

    private static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        //System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
 
