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
public class feed {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int E = sc.nextInt();
        int N = sc.nextInt();
        int[] p = new int[N];
        int[] s = new int [N];
        int[] c = new int[N];
        for(int i = 0; i< N;i++){
            p[i] = sc.nextInt();
            s[i] = sc.nextInt();
            c[i] = sc.nextInt();
        }
        
        long[] f = new long[K+1];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;
        for(int i = 0; i < N;i++){
            for(int j = 1; j <= s[i];j++){
                for(int k = 1; k <= K;k++){
                    if(k >= c[i]){
                        
                    }
                }
            }
        }
    }
}
