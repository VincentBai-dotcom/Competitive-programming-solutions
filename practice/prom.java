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
public class prom {
    static int N;
    static int M;
    static node[] n;
    static node[] n2;
    static Stack<node> st;
    static int res;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        n = new node[N];
        n2 = new node[N];
        for(int i = 0; i< N;i++){
            n[i] = new node(i);
            n2[i] = new node(i);
        }
        for(int i =0; i < M;i++){
            int a = sc.nextInt()-1;
            int b = sc.nextInt()-1;
            n[a].c.add(new edge(a,b));
        }
        st = new Stack<node>();
        boolean[] v= new boolean[N];
        scg(v);
        for(int i = 0; i < N;i++){
            for(edge j:n[i].c){
                int from = j.to;
                int to = j.from;
                n2[from].c.add(new edge(from,to));
            }
        }
        res = 0;
        v = new boolean[N];
        for(int i = 0; i < N;i++){
            node cur = st.pop();
            if(dfs2(cur.index,v) > 1)
                res++;
        }
        System.out.println(res);
    }
    
    public static int dfs2(int src, boolean[] v){
        if(v[src])
            return 0;
        v[src] = true;
        int counter = 1;
        for(edge i :n2[src].c){
            counter += dfs2(i.to,v);
        }
        return counter;
    }
    public static void scg(boolean[] v){
        for(int i = 0; i < N;i++){
            if(!v[i])
                dfs(i,v);
        }
    }
    
    public static void dfs(int src,boolean[] v){
        if(v[src])
            return;
        v[src] = true;
        for(edge i: n[src].c){
            dfs(i.to,v);
        }
        st.push(n[src]);
    }
    
    static class node{
        int index;
        LinkedList<edge> c;
        
               public node(int i){
                   index = i;
                   c = new LinkedList<edge>();
               } 
    }
    static class edge{
        int from;
        int to;
        
        public edge(int from, int to){
            this.from = from;
            this.to = to;
        }
    }
            
}
