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
public class skilevel2 {
    
    static edge[] e;
    static point[][] ps;
    static int N;
    static int M;
    static int K;
    static int[] union;
    static int[] value;
    
    static int[] result;
    
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        ps = new point[N][M];
        e = new edge[(N-1)*M+N*(M-1)];
        union = new int[N*M];
        value = new int[N*M];
        result = new int[N*M];
        for(int i =0; i < union.length;i++){
            union[i] = i;
            value[i] = 1;
            result[i] = Integer.MAX_VALUE;
        }
        for(int i = 0; i< N;i++){
            for(int j = 0; j < M;j++){
                ps[i][j] = new point(i,j,sc.nextInt());
            }
        }
        int counter = 0;
        for(int i = 0; i < N;i++){
            for(int j = 0; j < M-1;j++){
                e[counter]= new edge(ps[i][j],ps[i][j+1]);
                counter++;
            }
        }
        for(int j = 0; j < M;j++){
            for(int i = 0; i < N-1;i++){
                e[counter] = new edge(ps[i][j],ps[i+1][j]);
                counter++;
            }
        }
        Arrays.sort(e);
        for(int i = 0; i< e.length;i++){
           edge cur = e[i];
           int res = combine(cur.a.x*M+cur.a.y,cur.b.x*M+cur.b.y,cur.dis);
           
           int find = find(cur.a.x*M+cur.a.y);
           if(res >= K){
               for(int j = 0; j<result.length;j++){
                   if(find(j) == find)
                       result[j] = Math.min(result[j], cur.dis);
               }
           }
        }
        long ress = 0;
        for(int i = 0; i <N;i++){
            for(int j = 0; j < M;j++){
                if(sc.nextInt() == 1){
                    
                    ress+=result[i*M+j];
                }
            }
        }
        System.out.println(ress);
    }
    
    public static int find(int x){
        if(union[x] == x)
            return x;
        else{
             union[x] = find(union[x]);
             return union[x];
        }
    }
    
    public static int combine(int x, int y,int k){
        int xx = find(x);
        int yy = find(y);
        if(xx != yy)
            value[yy] = value[yy]+value[xx];
        union[xx] = yy;
        if(value[yy] >= K)
            result[xx] = Math.min(result[xx],k);
        return value[yy];
    }
    
    
    static class edge implements Comparable<edge>{
        point a;
        point b;
        int dis;
        
        public edge(point a, point b){
            this.a = a;
            this.b  = b;
            dis = Math.abs(a.el-b.el);
        }
        public int compareTo(edge a){
            return dis-a.dis;
        }
    }
    
    static class point{
        int x;
        int y;
        int el;
        public point(int x, int y, int el){
            this.x = x;
            this.y = y;  
            this.el = el;
        }
        
    }
}
