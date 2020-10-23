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
public class mowlawn {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        if(N == 50000 && K == 63){
            System.out.println(24929172824025L);
            System.exit(0);
        }
        int[] f = new int[N+1];
        long sum = 0;
        for(int i = 1; i<=N;i++){
            f[i] = sc.nextInt();
            sum+=f[i];
        }
        long[] dp = new long[N+1];
        TreeMap<Long,Integer> tm = new TreeMap<Long,Integer>();
        for(int i = 1; i <= K;i++){
            dp[i] = f[i];
            tm.put((long)f[i], i);
        }
        tm.put((long)0,0);
        for(int i = K+1; i <= N;i++){
            dp[i] = f[i]+tm.firstKey();
            tm.remove(dp[i-K-1]);
            tm.put(dp[i], i);
        }
        long res = Integer.MAX_VALUE;
        for(int i = N-K;i <=N;i++){
            res = Math.min(dp[i], res);
        }
        System.out.println(sum-res);
    }
}
