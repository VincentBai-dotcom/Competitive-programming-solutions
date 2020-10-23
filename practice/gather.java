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
public class gather {
    static int N;
    static int[] C;
    static long[] R;
    static node[] n;
    static long[] c;
    static int[] P;
    static long[] disTo;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        C = new int[N];
        n = new node[N];
        for(int i=0; i< N;i++){
            C[i] = sc.nextInt();
            n[i] = new node(i);
        }
        c = new long[N];
        P = new int[N];
        for(int i = 0; i < N-1;i++){
            int a = sc.nextInt()-1;
            int b = sc.nextInt()-1;
            int c = sc.nextInt();
            n[a].c.add(new Pair(b,c));
            n[b].c.add(new Pair(a,c));
        }
        disTo = new long[N];
        dfs(0,-1);
        rec(0,-1);
        R = new long[N];
        R[0] = disTo[0];
        res(0,-1);
        long res = 999999999999L;
        for(int i = 0; i < N;i++){
            res = Math.min(res,R[i]);
        }
        System.out.println(res);
    }
    
    public static void res(int index,int p){
        for(Pair i:n[index].c){
            if(i.x!= p){
                R[i.x] = R[index]-i.dis*c[i.x]+i.dis*(c[0]-c[i.x]);
                res(i.x,index);
            }
        }
    }
    
    public static long rec(int index, int p){
        long counter = 0;
        for(Pair i:n[index].c){
            if(i.x != p)
                counter += i.dis*c[i.x]+rec(i.x,index);
        }
        disTo[index] = counter;
        return counter;
    }
    
    public static int dfs(int index,int p){
        P[index] = p;
        int counter = C[index];
        for(Pair i:n[index].c){
            if(i.x != p){
                counter += dfs(i.x,index);
            }
        }
        c[index] = counter;
        return counter;
    }
    
    static class node{
        int index;
        LinkedList<Pair> c;
        
        public node(int i){
            index = i;
            c = new LinkedList<Pair>();
        }
    }
    
    static class Pair{
        int x;
        int dis;
        
        public Pair(int x, int dis){
            this.x = x;
            this.dis = dis;
        }
    }
}
