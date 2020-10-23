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
public class meetplace {
    static int[] d;
    static int[][] anc;
    static node[] n;
    static int[] P;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int Q = sc.nextInt();
        n = new node[N];
        P = new int[N];
        d = new int[N];
        for(int i = 0; i < N;i++)
            n[i] = new node(0);
        for(int i = 1; i < N;i++ ){
            int a = sc.nextInt()-1;
                P[i] = a;
                n[a].c.add(i);
        }
        dfs(0,0);
        
        int max = 0; while(1<<max+1 < N) ++max;
        anc = new int[max+1][N];
        for(int i = 0; i <=max;i++){
            Arrays.fill(anc[i],-1);
        }
        for(int k = 0;k < anc.length;k++){
            for(int i = 0; i< N;i++){
                if(k == 0)
                    anc[k][i] = P[i];
                else if(anc[k-1][i] != -1)
                    anc[k][i] = anc[k-1][anc[k-1][i]];
            }
        }
        for(int i = 0; i< Q;i++){
            int a = sc.nextInt()-1;
            int b = sc.nextInt()-1;
            int c = lca(a,b);
            System.out.println(c+1);
        }
    }
    
    
    public static int lca(int p, int q)	{
        if (d[p] < d[q]) { p ^= q; q ^= p; p ^= q; };
            int k = 0;
        while(1<<k+1 <= d[p])
            ++k;
        for (int i = k; i >= 0; --i)
            if (d[p] - (1<<i) >= d[q])
                p = anc[i][p];
        if (p == q)
            return p;
        for (int i = k; i >= 0; --i)
            if (anc[i][p] != -1 && anc[i][q] != anc[i][p]){
                p = anc[i][p]; q = anc[i][q];
            }
        return anc[0][p];
    }
    
    public static void dfs(int cur,int depth){
        d[cur] = depth;
        node curn = n[cur];
        for(int i : curn.c){
            dfs(i,depth+1);
        }
    }
    
    static class node{
        int in;
        LinkedList<Integer> c;
        public node(int in){
            this.in = in;
            c = new LinkedList<Integer>();
        }
    }
}
