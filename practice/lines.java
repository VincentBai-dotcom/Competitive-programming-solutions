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
public class lines {
    public static void main(String[] args) {
        Scanner ms = new Scanner(System.in);
        int N = ms.nextInt();
        point[] ps = new point[N];
        for(int i = 0; i < N;i++){
            ps[i] = new point(ms.nextInt(),ms.nextInt());
        }
        HashSet<Double> set = new HashSet<Double>();
        for(int i = 0; i < N-1;i++){
            for(int j = i+1; j < N;j++){
                set.add(s(ps[i],ps[j]));
            }
        }
        boolean flag1 = false;
        boolean flag2 = false;
        for(Double i:set){
            if(i.equals(-0.0))
                flag1 = true;
            if(i.equals(0.0))
                flag2 = true;
        }
        int res = set.size();
        if(flag1 == true && flag2 == true)
            res--;
        System.out.println(res);
    }
    public static double s(point a, point b){
        if(b.x == a.x)
            return Integer.MAX_VALUE;
        return (double)(b.y-a.y)/(b.x-a.x);
    }
            
    static class point{
        int x;
        int y;
        
        public point(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
    
    
}
