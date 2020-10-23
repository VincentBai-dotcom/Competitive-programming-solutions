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
public class movie {
    static int[] dp;
    static int[][] st;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int L = sc.nextInt();
        st = new int[N][];
        int[] t = new int[N];
        for(int i = 0; i<N;i++){
            t[i] = sc.nextInt();
            int l = sc.nextInt();
            st[i] = new int[l];
            for(int j = 0; j < l;j++){
                st[i][j] = sc.nextInt();
            }
        }
        dp = new int[1 << N];
        int[] two = new int[N];
        two[0] = 1;
        for(int i = 1; i < N;i++){
            two[i] = 2*two[i-1];
        }
        for(int i = 1; i < 1 <<N;i++){
            dp[i] = -1;
            for(int j = 0; j < N;j++){
                if(((i >> j) & 1)==1){
                    int index = i-two[j];
                    int index2 = bs(j,dp[index]);
                    if(dp[index] != -1 && index2 != -1){
                        dp[i] = Math.max(dp[i],index2+t[j]);
                    }
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < 1 <<N;i++){
            if(dp[i] >= L){
                int counter = 0;
                for(int j = 0; j < N;j++){
                    if((i >> j & 1) == 1)
                        counter++;
                }
                res = Math.min(res, counter);
            }
        }
        if(res != Integer.MAX_VALUE)
            System.out.println(res);
        else System.out.println(-1);
    }
    
    public static int bs(int i, int val){
        int a = 0;
        int b = st[i].length-1;
        
        while(a != b){
            int mid = (a+b)/2;
            if(st[i][mid] == val)
                return val;
            if(st[i][mid] < val)
                a = mid+1;
            else
                b = mid;
        }
        if(st[i][a] <=val)
            return st[i][a];
        if(st[i][a] > val && a>0)
            return st[i][a-1];
        return -1;
    }
}
