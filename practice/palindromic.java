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
public class palindromic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        char[][] f = new char[N][N];
        for(int i = 0; i < N;i++){
            String s = sc.next();
            for(int j = 0; j< N;j++){
                f[i][j] = s.charAt(j);
            }
        }
        long[][][] dp = new long[N][N][N];
        if(f[0][0] == f[N-1][N-1])
            dp[0][0][N-1] = 1;
        for(int k = 0; k < N-1;k++){
            int i = 0;
            int j = k;
            int ii = N-1;
            int jj = N-k-1;
            while( j!= -1){
                while(jj != N){
                    if(f[i][j+1]== f[ii][jj-1])
                        dp[i][j+1][ii]++;
                    if(f[i][j+1] == f[ii-1][jj])
                        dp[i][j+1][ii-1]++;
                    if(f[i+1][j] == f[ii][jj-1])
                        dp[i+1][j][ii]++;
                    if(f[i+1][j] == f[ii-1][jj])
                        dp[i+1][j][ii-1]++;
                    jj++;
                    ii--;
                }
                i++;
                j--;
                ii = N-1;
                jj = N-k-1;
            }
        }
        int a = 0;
        int b = N-1;
        long counter = 0;
        for(int i = 0; i <N;i++){
            for(int j = 0; j < N;j++){
                for(int k = 0; k < N;k++){
                    System.out.println(dp[i][j][k]+" "+i+" "+j+" "+k);
                }
            }
        }
        while(a!=N){
            counter += dp[a][b][a];
            a++;
            b--;
        }
        System.out.println(counter);
    }
}
