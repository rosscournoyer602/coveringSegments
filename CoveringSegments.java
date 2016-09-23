package signatures;

import java.util.*;

public class CoveringSegments {

    private static int[] optimalPoints(Segment[] segments) {
        
        ArrayList<Integer> pointList = new ArrayList<>();
        ArrayList<Segment> segList = new ArrayList<>();
        int minEnd;
        
        segList.addAll(Arrays.asList(segments));
        
        while (segList.size() > 0) {            //main greedy loop
            minEnd = calcMinEnd(segList);       //find minEnd
            pointList.add(minEnd);              //add to point list
            segList = segRemove(segList,minEnd);//remove segs covered by minEnd
        }
        
        int[] points = new int[pointList.size()];
        
        for (int i=0; i < pointList.size(); ++i) { //convert ArrayList back
            points[i] = pointList.get(i);          //to array
        }
        
        return points;
    }
    
    private static ArrayList<Segment> segRemove(ArrayList<Segment> segList, int minEnd) {
        //update segment list by removing all segments covered by current minEnd
        Iterator<Segment> it = segList.iterator();
        
        while (it.hasNext()) {
            Segment seg = it.next();
            if (seg.start <= minEnd && minEnd <= seg.end) {
                it.remove();
            }
        }
        
        return segList;
    }

    private static int calcMinEnd(ArrayList<Segment> segList) {
        //returns the lowest end point of all segments in the current list
        int minEnd = 0;
        int currentValue;
        
        for (int i=0; i < segList.size(); ++i) {
            currentValue = (int) segList.get(i).end;
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
            segments[i] = new Segment(start, end); //make a segment list out of cmd line args
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
