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
public class curling {
    static point[] A;
    static point[] B;
    static int N;
    static ArrayList<point> conAup;
    static ArrayList<point> conAdo;
    static ArrayList<point> conBup;
    static ArrayList<point> conBdo;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        A = new point[N];
        B = new point[N];
        for(int i =0; i<N;i++){
            A[i] = new point(sc.nextInt(),sc.nextInt());
        }
        for(int i =0; i<N;i++){
            B[i] = new point(sc.nextInt(),sc.nextInt());
        }
        Arrays.sort(A);
        Arrays.sort(B);
        conAup = new ArrayList<point>();
        conAdo = new ArrayList<point>();
        conBup = new ArrayList<point>();
        conBdo = new ArrayList<point>();
        
        for(int i = 0;i <= 1;i++){
            conAup.add(A[i]);
            conAdo.add(A[i]);
            conBup.add(B[i]);
            conBdo.add(B[i]);
        }
        
        conAup = conUp(A,conAup);
        conAdo = conDo(A,conAdo);
        conBup = conUp(B,conBup);
        conBdo = conDo(B,conBdo);
        int resa = 0;
        int resb = 0;
        for(int i = 0; i < N;i++){
            if(withinB(A[i]))
                resb++;
            if(withinA(B[i]))
                resa++;
        }
        System.out.println(resa+" "+resb);
    }
    public static boolean withinA(point a){
        if(a.x < conAup.get(0).x || a.x > conAup.get(conAup.size()-1).x)
            return false;
        int up = bs(a.x,conAup);
        int down = bs(a.x,conAdo);
        return up(conAdo.get(down),conAdo.get(down+1),a) && under(conAup.get(up),conAup.get(up+1),a);
    }
    public static boolean withinB(point a){
        if(a.x < conBup.get(0).x || a.x > conBup.get(conBup.size()-1).x)
            return false;
        int up = bs(a.x,conBup);
        int down = bs(a.x,conBdo);
        return up(conBdo.get(down),conBdo.get(down+1),a) && under(conBup.get(up),conBup.get(up+1),a);
    }
    
    public static int bs(int x, ArrayList<point> ps){
        int a = 0;
        int b = ps.size()-1;
        while(a != b ){
            int mid = (a+b)/2;
            if(ps.get(mid).x == x)
                return mid;
            if(ps.get(mid).x > x){
                b = mid;
            }
            else
                a = mid+1;
        }
        return a-1;
    }
    
    public static boolean under(point a, point b, point c){
        double m = (double)(b.y-a.y)/(b.x-a.x);
        double C = a.y-m*a.x;
        double y = m*c.x+C;
        return c.y <=y;
    }
    public static boolean up(point a, point b, point c){
        double m = (double)(b.y-a.y)/(b.x-a.x);
        double C = a.y-m*a.x;
        double y = m*c.x+C;
        return c.y >=y;
    }
    public static ArrayList<point> conUp(point[] A,ArrayList<point> list){
        point cur = A[2];
        int i = 2;
        while(i < A.length){
            i++;
            if(ccw(list.get(list.size()-2),list.get(list.size()-1),cur)){
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
    
    public static ArrayList<point> conDo(point[] A,ArrayList<point> list){
        point cur = A[2];
        int i = 2;
        while(i < A.length){
            i++;
            if(!ccw(list.get(list.size()-2),list.get(list.size()-1),cur)){
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
        public String toString(){
            return x+" "+y+"\t";
        }
        public int compareTo(point a){
            return x - a.x;
        }
    }
}
