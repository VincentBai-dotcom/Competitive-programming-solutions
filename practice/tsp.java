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
public class tsp {
    static int N;
    static int M;
    static int[][] graph;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        graph = new int[N][N];
        for(int i = 0; i < M;i++){
            int a = sc.nextInt()-1;
            int b = sc.nextInt()-1;
            int c = sc.nextInt();
            graph[a][b] = c;
            graph[b][a] = c;
        }
        int[][] dp = new int[N][1 <<N];
        for(int[] i :dp){
            Arrays.fill(i, -1);
        }
        dp[0][1] = 0;
        for(int i = 0; i< N;i++){
            for(int m = 0;m < (1 << N);m++){
                if(dp[i][m] != -1)
                    for(int j = 0; j< N;j++){
                        if(graph[i][j] != 0 && ((m >> j)&1) == 0){
                            int cur = dp[i][m] +graph[i][j];
                            if(dp[j][m|(1<<j)] == -1||dp[j][m|(1<<j)] >cur){
                                dp[j][m|(1<<j)] = cur;
                            }
                        }
                    }
            }
        }
        int ans = -1;
        for(int i = 0; i<N;i++){
            for(int j = 0; j < 1<<N;j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println("");
        }
        for(int i = 0; i< N;i++){
            if(dp[i][1<<N-1]!= -1 && graph[i][0] != 0)
                if(ans == -1 || dp[i][(1<<N)-1]<ans)
                    ans = dp[i][(1<<N)-1];
        }
        System.out.println(ans);
    }
    
}
