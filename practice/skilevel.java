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
public class skilevel {
    static PriorityQueue<pair> pq;
    static int[][] f;
    static int N;
    static int M; 
    static int T;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
         M = sc.nextInt();
        T = sc.nextInt();
        f = new int[N][M];
        for(int i = 0; i < N;i++){
            for(int j = 0; j < M;j++){
                f[i][j] = sc.nextInt();
            }
        }
        
        long counter = 0;
        for(int i = 0; i < N;i++){
            for(int j = 0; j < M;j++){
                
                int mark = sc.nextInt();
                if(mark == 1){
                    counter += (long)prim(i,j);
                }
            }
        }
        System.out.println(counter);
    }
    
    public static int prim(int x, int y){
        pq = new PriorityQueue<pair>(8*N,new Comparator<pair>(){
                    public int compare(pair a, pair b){
                        return a.dis-b.dis;
                    }
                });
        int counter = 0;
        int max = Integer.MIN_VALUE;
        pq.add(new pair(x,y,Integer.MIN_VALUE));
        boolean[][] v = new boolean[N][M];
        while(counter <= T && pq.size()!= 0){
            pair cur = pq.poll();
            v[cur.x][cur.y] = true;
            max = Math.max(max, cur.dis);
            addEdge(cur.x,cur.y,v);
            counter++;
        }
        return max;
    }
    
    public static  void addEdge(int x, int y,boolean[][] v){
        int value = f[x][y];
        if(x >0 && !v[x-1][y])
            pq.add(new pair(x-1,y,Math.abs(value-f[x-1][y])));
        if(x < N-1 && !v[x+1][y])
            pq.add(new pair(x+1,y,Math.abs(value-f[x+1][y])));
        if(y >0 && !v[x][y-1])
            pq.add(new pair(x,y-1,Math.abs(value-f[x][y-1])));
        if(y<M-1 && !v[x][y+1])
            pq.add(new pair(x,y+1,Math.abs(value-f[x][y+1])));
    }
    static class pair{
        int x;
        int y;
        int dis;
        
        public pair(int x,int y, int dis){
            this.x = x;
            this.y = y;
            this. dis = dis;
        }
    }
}
