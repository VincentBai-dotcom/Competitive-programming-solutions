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
public class tri {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        long[][] pre = new long[N][];
        for(int i = 1; i <= N;i++){
            pre[i-1] = new long[i];
            for(int j = 0; j < i;j++){
                pre[i-1][j] = sc.nextInt();
                if(j >= 1)
                    pre[i-1][j]+=pre[i-1][j-1];
            }
        }
        long res = -999999999999L;
        for(int i = 0; i <= N-K;i++){
            for(int j = 0; j <=i;j++){
                long counter = 0;
                for(int x = 0;x < K;x++){
                    long start = 0;
                    if(j > 0)
                        start = pre[i+x][j-1];
                    counter += pre[i+x][j+x]-start;
                }
                res = Math.max(res, counter/(K*(K+1)/2));
            }
        }
        for(int i = K-1;i<=N-K;i++){
            for(int j = 0; j<= i+1-K;j++){
                long counter = 0;
                for(int k = 0; k < K;k++){
                    long start = 0;
                    int curR = i+k;
                    int curC = j+K-1;
                    int curCL = j+k;
                    if(curCL >0){
                        start = pre[curR][curCL-1];
                    }
                    counter+= pre[curR][curC]-start;
                }
                res = Math.max(res, counter/(K*(K+1)/2));
            }
        }
        System.out.println(res);
    }
}
