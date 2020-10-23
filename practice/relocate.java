/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice;
import java.util.*;
/**
 *
 * @author Vincent
 */
public class relocate {
    static int N;
    static int M;
    static node[] n;
    static int K;
    static int[] index;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        n = new node[N];
        int[] index = new int[K];
        for(int i = 0; i < N;i++)
            n[i] = new node(i);
        for(int i = 0; i < K;i++){
            int a = sc.nextInt()-1;
            n[a].m = true;
            index[i] = a;
        }
        for(int i = 0; i < M;i++){
            int a = sc.nextInt()-1;
            int b = sc.nextInt()-1;
            int c = sc.nextInt();
            n[a].c.add(new pair(b,c));
            n[b].c.add(new pair(a,c));
        }
        long[][] dist = new long[K][N];
        int counter = 0;
        for(int i = 0; i < N;i++){
            if(n[i].m){
                dist[i] = di(i);
                   }
        }
        boolean[] v = new boolean[K];
        
    }
    public static long dfs(int src, int depth,int o){
        long counter = 999999999999L;
        for(int i = 0; i < K;i++){
            counter = Math.min(counter, dist[i][src]+dfs(index[i],depth++,o));
        }
    }
   
    public static long[] di(int src){
        
        PriorityQueue<pair> pq = new PriorityQueue<pair>(M*4,new Comparator<pair>(){
            public int compare(pair a, pair b){
                return a.dis-b.dis;
            }
        });
        boolean[] v = new boolean[N];
        long[] dist = new long[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        pq.add(new pair(src,0));
        while(pq.size()!= 0){
            pair cur = pq.poll();
            v[cur.x] = true;
            
            for(pair i:n[cur.x].c){
                if(i.dis+dist[cur.x] < dist[i.x] && !v[i.x]){
                    dist[i.x] = i.dis+dist[cur.x];
                    pq.add(i);
                }
            }
        }
        return dist;
    }
    
    static class node{
        int index;
        LinkedList<pair> c;
        boolean m;
        public node(int i){
            index = i;
            c = new LinkedList<pair>();
            m = false;
        }
    }
    static class pair{
        int x;
        int dis;
              
        public pair(int x, int dis){
        this.x = x;
        this.dis = dis;
        }
    }
}
