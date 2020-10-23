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
public class baric {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a= new int[110];
        for(int i = 1; i < n+1;i++){
            a[i] = sc.nextInt();
        }
        long[][] pre = new long[110][110];
        
        for(int i = 0; i<=n;i++){
            for(int j = i+1; j <=n+1;j++){
                if(i == 0)
                    for(int k = i+1;k < j;k++)
                        pre[i][j]+= 2*Math.abs(a[k]-a[j]);
                else if(j == n+1)
                    for(int k  = i+1; k < j;k++)
                        pre[i][j] += 2*Math.abs(a[k]-a[i]);
                else 
                    for(int k = i+1;k<j;k++)
                        pre[i][j]+=Math.abs(2*a[k]-a[i]-a[j]);
            }
        }
        pre[0][n+1] = 999999999999L;
        long[][] dp = new long[110][110];
        for(int i = 0; i <110;i++){
            Arrays.fill(dp[i],999999999999L);
        }
        dp[0][1] = 0;
        for(int j = 2; j  <=n+2;j++){
            for(int i = j-1; i <=n+1;i++){
                for(int k = 0; k < i;k++){
                    dp[i][j] = Math.min(dp[i][j], dp[k][j - 1] + pre[k][i]);
                }
            }
        }
        for(int i = 3;i<n+2;i++){
            if(dp[n+1][i] <=m){
                System.out.println(i-2+" "+dp[n+1][i]);
                System.exit(0);
            }
        }
        System.out.println(n+" "+0);
    }
}
