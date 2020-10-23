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
public class GuardMark {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int H  = sc.nextInt();
        int[] h = new int[N];
        int[] w = new int[N];
        int[] c = new int[N];
        for(int i = 0; i <N;i++){
            h[i] = sc.nextInt();
            w[i] = sc.nextInt();
            c[i] = sc.nextInt();
        }
        long dp[] = new long[1 << N];
        dp[0] = Integer.MAX_VALUE;
        for(int i = 1; i < 1 << N;i++){
            dp[i] = -1;
            for(int j = 0; j <N;j++){
                if(((i >> j)&1) == 1){
                    int index = i - (1 << j);
                    if(dp[index] != -1 && w[j] <= dp[index]){
                       long cur = Math.min(dp[index]-w[j], c[j]);
                       dp[i] = Math.max(cur, dp[i]);
                    }
                }
            }
        }
        long res = -1;
        for(int i = 1; i < 1 << N;i++){
            if(dp[i] != -1){
                long counter = 0;
                for(int j = 0; j <N;j++){
                    if(((i >> j)&1) == 1)
                        counter+=h[j];
                }
                if(counter >= H)
                res = Math.max(res, dp[i]);
            }
        }
        if(res != -1)
        System.out.println(res);
        else
            System.out.println("Mark is too tall");
    }
}
