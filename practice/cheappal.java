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
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        String str = sc.next();
        char[] c = str.toCharArray();
        int[][] cost = new int[26][2];
        for(int i = 0; i < N;i++){
            String cur = sc.next();
            cost[cur.charAt(0)-'a'][0] = sc.nextInt();
            cost[cur.charAt(0)-'a'][1] = sc.nextInt();
        }
        long[][] dp = new long[M][M];
        for(int i = 1; i < M;i++){
            for(int j = 0; j < M-i;j++){
                dp[j][j+i] = Math.min(dp[i][j+i-1]+cost[j+i][0],dp[i][j+i-1]+cost[j+i][1]);
                dp[j][j+i] = Math.min(dp[j][j+i], dp[j+1][j+i]+cost[j][0]);
                dp[j][j+i] = Math.min(dp[j][j+i], dp[j+1][j+i]+cost[j][1]);
            }
        }
        System.out.println(dp[0][M-1]);
    }
}
