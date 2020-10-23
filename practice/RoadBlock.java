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
public class RoadBlock {
    static node[] n;
    static int N;
    static int M;
    static PriorityQueue<pair> pq;
    static LinkedList<Integer>[] path;
    
      @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        n = new node[N];
        path = new LinkedList[N];
        for(int i =0; i < N;i++){
            n[i] = new node(i);
            path[i] = new LinkedList<Integer>();
        }
        path[0].add(0);
        for(int i = 0; i < M;i++){
            int a = sc.nextInt()-1;
            int b = sc.nextInt()-1;
            int c = sc.nextInt();
            n[a].c.add(new pair(b,c));
            n[b].c.add(new pair(a,c));
        }
        int[] dist = di(true);
        long max = Integer.MIN_VALUE;
        for(int i =0; i < path[N-1].size()-1;i++){
            int a = path[N-1].get(i);
            int b = path[N-1].get(i+1);
            for(pair j: n[a].c){
                if(j.x == b){
                    j.dis *= 2;
                    break;
                }
            }
            for(pair j: n[b].c){
                if(j.x == a){
                    j.dis *= 2;
                    break;
                }
            }
            max = Math.max(max, di(false)[N-1]);
            for(pair j: n[a].c){
                if(j.x == b){
                    j.dis /= 2;
                    break;
                }
            }
            for(pair j: n[b].c){
                if(j.x == a){
                    j.dis /= 2;
                    break;
                }
            }
            
        }
        System.out.println(max-dist[N-1]);
    }
    
 
    @SuppressWarnings("unchecked")
    public static int[] di(boolean flag){
        pq = new PriorityQueue<pair>(M*4,new Comparator<pair>(){
            public int compare(pair a, pair b){
                return a.dis-b.dis;
            }
        });
        boolean[] v = new boolean[N];
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        pq.add(new pair(0,0));
        int dis = 0;
        while(pq.size()!= 0){
            pair cur = pq.poll();
            v[cur.x] = true;
            dis+=cur.dis;
            
            for(pair i:n[cur.x].c){
                if(i.dis+dist[cur.x] < dist[i.x] && !v[i.x]){
                    if(flag){
                    path[i.x] = (LinkedList<Integer>)path[cur.x].clone();
                    path[i.x].add(i.x);
                    }
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
        
        public node(int i){
            index = i;
            c = new LinkedList<pair>();
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
