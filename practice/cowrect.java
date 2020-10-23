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
public class cowrect {
    public static void main(String[] args) {
        Scanner mc = new Scanner(System.in);
        int N = mc.nextInt();
        ArrayList<point> v = new ArrayList<point>();
        point[] h = new point[N];
        for(int i = 0; i < N;i++){
            h[i] = new point(mc.nextInt(),mc.nextInt(),mc.next());
            if(h[i].s.equals("H"))
                v.add(h[i]);
        }
        Collections.sort(v);
        Arrays.sort(h, new Comparator<point>(){
            public int compare(point a, point b){
                if(a.x != b.x)
                    return a.x - b.x;
                else if(a.s.equals("H"))
                    return 1;
                else
                    return -1;
            }
        });
        int res = 0;
        int area = Integer.MAX_VALUE;
        for(int i = 0; i < v.size();i++){
            for(int j = i+1; j < v.size();j++){
                point lb = null;
                point rb = null;
                point ub = v.get(j);
                point bb = v.get(i);
                int counter =0;
                int curarea = 0;
                boolean flag = false;
                int rl = Math.max(ub.x,bb.x);
                int ll = Math.min(ub.x,bb.x);
                for(int z = 0;z < N;z++){
                    if(h[z].y < ub.y || h[z].y > bb.y)
                        continue;
                    if(h[z].s.equals("G")){
                        if(h[z].x <= rl && h[z].x >= ll){
                            flag = true;
                            break;
                        }
                    }
                }
                if(flag == true)
                    continue;
                int marker1 = -1;
                for(int z = 0; z < N;z++){
                    if(h[z].y > ub.y || h[z].y < bb.y){
                        continue;
                    }
                    if(h[z].s.equals("G")){
                        if(h[z].x > rl){
                            break;
                        }
                        counter = 0;
                        curarea = 0;
                        lb = null;
                        rb = null;
                        marker1 = h[z].x;
                        continue;
                    }
                    if(marker1 != -1 && h[z].x == marker1)
                        continue;
                    if(lb == null)
                        lb = h[z];
                    else 
                        rb = h[z];
                    counter++;
                    if(lb != null && rb != null){
                        curarea = (rb.x - lb.x)*(ub.y-bb.y);
                    }
                    if(counter > res){
                        res = counter;
                        area = curarea;
                    }
                    else if(counter == res){
                        area = Math.min(area, curarea);
                    }
                }
            }
        }
        System.out.println(res);
        System.out.println(area);
    }
    static class point implements Comparable<point>{
        int x;
        int y;
        String s;
        public point(int x, int y, String s ){
            this.x = x;
            this.y = y;
            this.s = s;
        }
        
        public int compareTo(point a){
            return y-a.y;
        }
    }
}
