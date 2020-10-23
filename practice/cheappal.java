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
public class cheappal {
    public static void main(String[] args) {
        Scanner ms = new Scanner(System.in);
        int N = ms.nextInt();
        int M = ms.nextInt();
        int[] incost = new int[26];
        int[] decost = new int[26];
        String str = ms.next();
        char[] cha = new char[M];
        for(int i = 0; i < M;i++){
            cha[i] = str.charAt(i);
        }
        for(int i = 0; i < N;i++){
            String cur = ms.next();
            int a = ms.nextInt();
            int b = ms.nextInt();
            incost[cur.charAt(0)-97] = a;
            decost[cur.charAt(0)-97] = b;
        }
        int[][] dp = new int[M][M];
        for(int t = 1; t < M;t++){
            for(int i = 0; i < M-t;i++){
                int j = i+t;
                    if(cha[i] == cha[j])
                        dp[i][j] = dp[i+1][j-1];
                    else{
                        dp[i][j] = Integer.MAX_VALUE;
                        dp[i][j] = Math.min(dp[i][j], dp[i+1][j]+incost[cha[i]-97]);
                        dp[i][j] = Math.min(dp[i][j], dp[i+1][j]+decost[cha[i]-97]);
                        dp[i][j] = Math.min(dp[i][j], dp[i][j-1]+incost[cha[j]-97]);
                        dp[i][j] = Math.min(dp[i][j], dp[i][j-1]+decost[cha[j]-97]);
                    }
            }
        }
        System.out.println(dp[0][M-1]);
    }
}
