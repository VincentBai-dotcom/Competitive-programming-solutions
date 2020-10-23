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
public class horizon {
    public static void main(String[] args) {
        Scanner ms = new Scanner(System.in);
        int N = ms.nextInt();
        point[] p = new point[N*2];
        for(int i = 0; i < N;i++){
            int a = ms.nextInt();
            int b = ms.nextInt();
            long c = ms.nextInt();
            p[2*i] = new point(a,c,1);
            p[2*i+1] = new point(b,c,0);
            p[2*i+1].op = p[2*i];
        }
        Arrays.sort(p,new Comparator<point>(){
            public int compare(point a, point b){
                return a.x - b.x;
            }
        });
        ArrayList<Long> ts = new ArrayList<Long>();
        int counter = 0;
        long res = 0;
        for(int i = 0; i < 2*N;i++){
            if(!ts.isEmpty()){
                res += ts.get(ts.size()-1) *(p[i].x - counter);
            }
            counter = p[i].x;
            
            if(p[i].s == 1){
                int index = bs(ts,p[i].h,1);
                ts.add(index,p[i].h);
            }
            else{
                int index = bs(ts,p[i].op.h,0);
                ts.remove(index);
            }
        }
        System.out.println(res);
    }
    
    public static int bs(ArrayList<Long> ps, long x,int state){
        int a= 0;
        int b = ps.size();
        while(a != b){
            int mid = (a+b)/2;
            if(ps.get(mid) == x){
                if(state == 1)
                    return mid+1;
                else
                    return mid;
            }
            if(ps.get(mid) <x)
                a = mid+1;
            else
                b = mid;
        }
        return a;
    }
    
    static class point implements Comparable<point>{
        int x;
        long h;
        int s;
        point op;
        public point(int x, long h, int s){
            this.x = x;
            this.h = h;
            this.s = s;
        }
        
        public int compareTo(point a){
            if(h < a.h)
                return -1;
            else
                return 1;
        }
        
        public String toString(){
            return h+"";
        }
    }
}
