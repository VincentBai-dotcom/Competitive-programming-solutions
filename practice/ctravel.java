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
public class ctravel {
    static int[][][] dp;
    static int N;
    static int M;
    static int T;
    public static void main(String[] args){
        Scanner ms = new Scanner(System.in);
        N = ms.nextInt();
        M = ms.nextInt();
        T = ms.nextInt();
        boolean[][]map = new boolean[N][M];
        for(int i = 0; i <N;i++){
            String cur = ms.next();
            for(int j = 0; j < M;j++){
                if(cur.charAt(j) == '*')
                    map[i][j] = true;
            }
        }
        int q1 = ms.nextInt()-1;
        int p1 = ms.nextInt()-1;
        int q2 = ms.nextInt()-1;
        int p2 = ms.nextInt()-1;
        dp = new int[T+1][N][M];
        dp[0][q1][p1] = 1;
        for(int t = 1; t <= T;t++){
            for(int i = 0; i < N;i++){
                for(int j = 0; j < M;j++){
                    if(map[i][j] == true)
                        continue;
                    if(i>0 )
                        dp[t][i][j] += dp[t-1][i-1][j];
                    if(i< N-1 )
                        dp[t][i][j] += dp[t-1][i+1][j];
                    if(j>0)
                        dp[t][i][j] += dp[t-1][i][j-1];
                    if(j<M-1)
                        dp[t][i][j] += dp[t-1][i][j+1];
                        
                }
            }
        }
        System.out.println(dp[T][q2][p2]);
    }
}
