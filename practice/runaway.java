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
public class runaway {
    static LinkedList<Integer>[] n;
    static int[] d;
    static int[] P;
    static int[][] dist;
    static int N;
    static int L;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        int max = 0; while(1<<max+1 < N) ++max;
        dist = new int[max+1][N];
        P = new int[N];
        P[0] = -1;
        n = new LinkedList[N];
        d = new int[N];
        for(int i = 0;i < N;i++){
            n[i] = new LinkedList<Integer>();
        }
        dist[0][0] = -1;
        for(int i = 0; i< N-1;i++){
            int a = sc.nextInt()-1;
            int b=  sc.nextInt();
            n[a].add(i+1);
            dist[0][i+1] = b;
            P[i+1] = a;
        }
        dfs(0,0);
        
    }
    
    public static void setup(){
        for(int k = 1; k < dist.length;k++){
            for(int i = 0; i < N;i++){
                if(dist[k-1][i] != -1)
                    dist[k][i] = dist[k-1][i]+dist[k-1][dist[k-1][i]];
            }
        }
    }
    
    public static void dfs(int in,int depth){
        d[in] = depth;
        for(int i :n[in]){
            dfs(i,depth+1);
        }
    }
}
