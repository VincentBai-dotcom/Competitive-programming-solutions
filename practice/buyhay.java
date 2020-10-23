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
public class buyhay {
    public static void main(String[] args){
        Scanner ms = new Scanner(System.in);
        int n = ms.nextInt();
        int h = ms.nextInt();
        int[] c = new int[n];
        int[] p = new int[n];
        for(int i = 0; i< n;i++){
            p[i] = ms.nextInt();
            c[i] = ms.nextInt();
        }
        int[][] dp = new int[n][55001];
        for(int i = 1; i <= 55001;i++){
            if(i % p[0] !=0){
                continue;
            }
        }
        for(int i = 1; i< n;i++){
            for(int j = 1; j < 55001;j++){
                if(dp[i-1][j] !=0)
                dp[i][j] = dp[i-1][j];
                if(j % p[i] !=0)
                    continue;
                else{
                    if(dp[i][j] != 0)
                    dp[i][j] = Math.min(dp[i][j-p[i]]+c[i],dp[i][j]);
                    else
                        dp[i][j] = dp[i][j-p[i]]+c[i];
                }
            }
        }
        int result = Integer.MAX_VALUE;
        for(int i = 0; i< 55001;i++){
            if(dp[n-1][i] != 0 && i >= h)
                result = Math.min(result,dp[n-1][i]);
        }
        System.out.println(result);
    }
}
