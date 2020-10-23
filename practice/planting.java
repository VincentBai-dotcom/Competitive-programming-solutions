/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
import java.math.BigInteger;
public class planting {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        line[] line = new line[2*N];
        ArrayList<point> vps = new ArrayList<point>();
        for(int i = 0; i< N;i++){
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x4 = sc.nextInt();
            int y4 = sc.nextInt();
            int x2 = x4;
            int y2 = y1; 
            int x3 = x1;
            int y3 = y4;
            point p1 = new point(x1,y1,1);
            point p2 = new point(x2,y2,1);
            point p3 = new point(x3,y3,0);
            point p4 = new point(x4,y4,0);
            line[2*i] = new line(p1,p3,1);
            line[2*i+1] = new line(p2,p4,0);
            line[2*i+1].op = line[2*i];
        }
        Arrays.sort(line);
        int marker = line[0].pos;
        vps.add(line[0].a);
        vps.add(line[0].b);
        long result = 0;
        for(int i = 1; i < 2*N;i++){
            line cur = line[i];
            int side = cur.pos - marker;
            marker = cur.pos;
            if(!vps.isEmpty()){
                int side2 = area(vps);
                result += side2*side;
            }
            if(cur.state == 1){
                vps.add(cur.a);
                vps.add(cur.b);
                Collections.sort(vps);
            }
            else{
                for(int j = 0; j < vps.size();j++){
                    int x1 = cur.op.a.x;
                    int y1 = cur.op.a.y;
                    int x2 = cur.op.b.x;
                    int y2 = cur.op.b.y;
                    if((vps.get(j).x == x1 && vps.get(j).y == y1)||(vps.get(j).x == x2&& vps.get(j).y == y2)){
                        vps.remove(j);
                        j--;
                    }
                }
            }
        }
        System.out.println(result);
    }
    
    public static int area(ArrayList<point> ps){
        int counter = 0;
        int marker = ps.get(0).y;
        int state = 1;
        for(int i = 1; i < ps.size();i++){
            point cur = ps.get(i);
            if(marker == -1)
                marker = ps.get(i).y;
            if(cur.state == 0)
                state--;
            else
                state++;
            if(state == 0){
                counter += cur.y - marker;
                marker =-1;
            }
        }
        return Math.abs(counter);
    }
    
    static class line implements Comparable<line>{
        point a;
        point b;
        int pos;
        int state;
        line op;
        public line(point x, point y,int state){
            this.a = x;
            b = y;
            pos = x.x;
            this.state = state;
        }
        
        public int compareTo(line a){
            return pos - a.pos;
        }
    }
    static class point implements Comparable<point>{
        int x;
        int y;
        int state;
        
        public point(int x, int y, int state){
            this.x = x;
            this.y = y;
            this.state = state;
        }
        
        public int compareTo(point a){
            return a.y-y;
        }
        
        public String toString(){
            return x+" "+y+" "+state+"|||";
        }
    }
}

