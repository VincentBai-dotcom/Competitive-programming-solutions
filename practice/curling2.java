/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
/**
 *
 * @author Vincent
 */
public class curling2 {
    public static void main(String[] args) {
        Scanner ms = new Scanner(System.in);
        int N =ms.nextInt();
        point[] A = new point[N];
        point[] B = new point[N];
        for(int i = 0;i < N;i++){
            A[i] = new point(ms.nextInt(),ms.nextInt());
        }
        for(int i = 0;i < N;i++){
            B[i] = new point(ms.nextInt(),ms.nextInt());
        }
        Arrays.sort(A);
        Arrays.sort(A);
        System.out.println(solve(A,B));
        System.out.println(solve(B,A));
    }
    
    public static int solve(point[] A, point[] B){
        ArrayList<point> conUp = con(A,true);
        ArrayList<point> conDo = con(A,false);
        int counter = 0;
        for(int i = 0; i < B.length;i++){
            int index1 = bs(B[i].x,conUp);
            int index2 = bs(B[i].x,conDo);
            if(inRange(B[i],conUp,true,index1) && inRange(B[i],conDo,false,index2))
                counter++;
        }
        return counter;
    }
    
    public static boolean inRange(point a, ArrayList<point> con, boolean state,int index){
        point l = con.get(index);
        point r = con.get(index+1);
        if(a.x < l.x || a.x > r.x)
            return false;
        if(state == true)
            return under(l,r,a);
        else
            return above(l,r,a);
    }
    
    public static boolean under (point a, point b, point c){
        double m = (double)(b.y-a.y)/(b.x-a.x);
        double C = a.y-m*a.x;
        double y = m*c.x+C;
        return c.y <= y;
    }
    public static boolean above (point a, point b, point c){
        double m = (double)(b.y-a.y)/(b.x-a.x);
        double C = a.y-m*a.x;
        double y = m*c.x+C;
        return c.y >= y;
    }
    
     public static int bs(int x, ArrayList<point> ps){
        int a = 0;
        int b = ps.size()-1;
        while(a != b ){
            int mid = (a+b)/2;
            if(ps.get(mid).x == x)
                return mid;
            if(ps.get(mid).x > x){
                b = mid-1;
            }
            else
                a = mid;
        }
        return a;
    }
    
    public static ArrayList<point> con(point[] A,boolean state){
        ArrayList<point> list = new ArrayList<point>();
        list.add(A[0]);
        list.add(A[1]);
        point cur = A[2];
        int i = 2;
        while(i < A.length){
            i++;
            if(ccw(list.get(list.size()-2),list.get(list.size()-1),cur) == state){
                list.set(list.size()-1, cur);
            }
            else{
                list.add(cur);
            }
            if(i < A.length)
            cur = A[i];
        }
        list.add(cur);
        return list;
    }
    
    
    public static boolean ccw(point a, point b, point c){
        return (b.x-a.x)*(c.y-a.y)-(b.y-a.y)*(c.x-a.x) >= 0;
    }
            
    static class point implements Comparable<point>{ 
        int x;
        int y;
        
        public point(int x, int y){
            this.x = x;
            this.y = y;
        }
        public int compareTo(point a){
            return x - a.x;
        }
    }
}
