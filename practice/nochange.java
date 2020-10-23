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
public class nochange {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int N = sc.nextInt();
        int[] c = new int[K];
        int[] f = new int[N];
        for(int i = 0; i <K;i++){
            c[i] = sc.nextInt();
        }
        for(int i = 0; i<N;i++){
            f[i] = sc.nextInt();
            if(i >0)
                f[i]+=f[i-1];
        }
        long dp[] = new long[1<<K];
        for(int i = 1; i < 1<<K;i++){
            dp[i] = -1;
            for(int j = 0; j < K;j++){
                if(((i >>j)&1) == 1){
                    int index = i- (1<<j);
                    if(dp[index]!= -1)
                        dp[i] = Math.max(dp[i], bs(f,dp[index]+c[j]));
                }
            }
        }
        long res = -1;
        for(int i = 0; i < 1 <<K;i++){
            
            if(dp[i] == f[N-1]){
                long counter = 0;
                for(int j = 0; j < K;j++){
                    if(((i >> j)&1 )== 0){
                        counter += c[j];
                    }
                }
                res = Math.max(res, counter);
            }
        }
        System.out.println(res);
    }
    public static long bs( int[] f,long val){
        int a = 0;
        int b = f.length-1;
        
        while(a != b){
            int mid = (a+b)/2;
            if(f[mid] == val)
                return val;
            if(f[mid] < val)
                a = mid+1;
            else
                b = mid;
        }
        if(f[a] <=val)
            return f[a];
        if(f[a] > val && a>0)
            return f[a-1];
        return -1;
    }
}
