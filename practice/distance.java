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
public class distance {
    static node[] n;
    static int[] d;
    static int[] P;
    static int[][] anc;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int Q = sc.nextInt();
        n = new node[N];
        d = new int[N];
        P = new int[N];
        for(int i = 0; i< N;i++){
            n[i] = new node(i);
        }
        for(int i =0;i  < N-1;i++){
            int a = sc.nextInt()-1;
            int b= sc.nextInt()-1;
            n[a].c.add(b);
            n[b].c.add(a);
        }
        dfs(0,-1,0);
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
        
        for(int i = 0; i < Q;i++){
            int a = sc.nextInt()-1;
            int b = sc.nextInt()-1;
            int c = lca(a,b);
            System.out.println(d[a]+d[b]-2*d[c]);
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
    
    
    public static void dfs(int cur, int pa,int depth){
        d[cur] = depth;
        node curn = n[cur];
        P[cur] = pa;
        curn.c.remove((Integer)pa);
        for(int i : curn.c){
            dfs(i,cur,depth+1);
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
