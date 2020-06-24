import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ccTechSol1 {
	static double INF = Double.MAX_VALUE;
	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		List<Pair> endPoints = new ArrayList<Pair>();
		for(int i=0;i<n;i++){
			endPoints.add(new Pair(s.nextDouble(), s.nextDouble()));
		}
		//System.out.println(endPoints);
		Pair testPoint = new Pair(s.nextDouble(), s.nextDouble());
		//System.out.println(testPoint);
		boolean isInside = findIfPointIsInside(endPoints, testPoint, n);
		System.out.println(isInside);
		s.close();
	}
	
	static boolean findIfPointIsInside(List<Pair> endPoints, Pair testPoint, int n){
		boolean isInside = false;
		if(n < 3) {
			return isInside;
		}
        Pair lineEndPoint = new Pair(INF, testPoint.y); 
        int count = 0, i = 0;
        int next = (i + 1) % n;  
        if (doIntersect(endPoints.get(i), endPoints.get(next), testPoint, lineEndPoint))  
        {
            if (orientation(endPoints.get(i), testPoint, endPoints.get(next)) == 0) 
            { 
                return checkColinearity(endPoints.get(i), testPoint, endPoints.get(next)); 
            } 
            count++; 
        }
        i = next;
        while(i!=0){
        	next = (i + 1) % n;
            if (doIntersect(endPoints.get(i), endPoints.get(next), testPoint, lineEndPoint))  
            { 
            	if (orientation(endPoints.get(i), testPoint, endPoints.get(next)) == 0) 
                { 
                    return checkColinearity(endPoints.get(i), testPoint, endPoints.get(next)); 
                }
                count++; 
            } 
            i = next;          	
        }
        if(count%2==1) {
        	return true;
        }
        return isInside;
	}
	static boolean checkColinearity(Pair a, Pair b, Pair c)  
    { 
        if (b.x <= Math.max(a.x, c.x) && 
            b.x >= Math.min(a.x, c.x) && 
            b.y <= Math.max(a.y, c.y) && 
            b.y >= Math.min(a.y, c.y)) 
        { 
            return true; 
        } 
        return false; 
    } 
	static int orientation(Pair p, Pair q, Pair r)  
    { 
        double val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        if (val == 0)  
        { 
            return 0; 
        } 
        if(val>0)
        	return 1;
        return 2;
    } 
	static boolean doIntersect(Pair p1, Pair q1,  
            Pair p2, Pair q2)  
			{
			int o1 = orientation(p1, q1, p2); 
			int o2 = orientation(p1, q1, q2); 
			int o3 = orientation(p2, q2, p1); 
			int o4 = orientation(p2, q2, q1); 
			if (o1 != o2 && o3 != o4) 
			{ 
			return true; 
			}
			if (o1 == 0 && checkColinearity(p1, p2, q1))  
			{ 
			return true; 
			}
			if (o2 == 0 && checkColinearity(p1, q2, q1))  
			{ 
			return true; 
			}
			if (o3 == 0 && checkColinearity(p2, p1, q2)) 
			{ 
			return true; 
			}
			if (o4 == 0 && checkColinearity(p2, q1, q2)) 
			{ 
			return true; 
			} 
			return false;  
		} 
		public static class Pair{
		double x;
		double y;
		Pair(double x,double y){
			this.x = x;
			this.y = y;
		}
		@Override
			public String toString() {
				// TODO Auto-generated method stub
				String str = "(" + this.x +", " + this.y +")";
			return str;
			}
	}
}