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
public class telewire {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int C = sc.nextInt();
        int[] h = new int[N];
        int large = 0;
        for(int i = 0; i < N;i++){
            h[i] = sc.nextInt();
            large = Math.max(h[i], large);
        }
        long[] dp = new long[large+1];
        for(int i = h[0]+1;i <=large;i++){
            dp[i] = (long) Math.pow(i-h[0],2);
        }
        for(int i = 1; i < N;i++){
            long[] dp2 = new long[large+1];
            for(int j = h[i]; j <= large;j++){
                long min = 999999999999L;
                long cur =(long) Math.pow(j-h[i],2);
                for(int k = h[i-1];k <= large;k++){
                    min = Math.min(min, cur+dp[k]+C*(Math.abs(j-k)));
                }
                dp2[j] = min;
            }
            dp = dp2;
        }
        long res = 9999999998999L;
        for(int i = h[N-1];i <= large;i++){
            res = Math.min(res,dp[i]);
        }
        System.out.println(res);
    }
}
