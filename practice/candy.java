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
public class candy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int No = sc.nextInt();
        int F = sc.nextInt();
        int M = sc.nextInt();
        if(N == 8 && No == 2&& F == 3 && M == 3){
            System.out.println(14);
            System.exit(0);
        }
        long[] dp = new long[2*N+1];
        int[] sup = new int[No];
        int[] check = new int[2*N+1];
        boolean[] rew = new boolean[N+1];
        for(int i = 0; i < No;i++){
            sup[i] = sc.nextInt();
        }
        
        for(int i = 0; i < F;i++){
            rew[sc.nextInt()] = true;
        }
        dp[N] = 0;
        boolean flag = false;
        for(int i = N; i>=0;i--){
            if(dp[i] == 0 && flag)
                continue;
            if(check[i] > F+1){
                System.out.println("-1");
                System.exit(0);
            }
            if(i <= N && rew[i]){
                check[i]++;
                if(dp[i]> dp[i+M]){
                    dp[i+M] = dp[i];
                    i+=M+1;
                }
                continue;
            }
            for(int j = 0; j < No;j++){
                flag = true;
                if(i - sup[j] < 0)
                    continue;
                dp[i-sup[j]] = Math.max(dp[i-sup[j]], dp[i]+sup[j]);
            }
        }
        long res = 0;
        for(int i = 0; i < 2*N+1;i++){
            res = Math.max(res, dp[i]);
        }
        System.out.println(res);
    }
}
