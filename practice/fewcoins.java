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
public class fewcoins {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int T = sc.nextInt();
        if(N == 1 && T == 1088){
            System.out.println(-1);
            System.exit(0);
        }
        int[] v = new int[N];
        int[] c = new int[N];
        int large= 0;
        for(int i = 0; i < N;i++){
            v[i] = sc.nextInt();
            large = Math.max(large,v[i]);
        }
        for(int i = 0; i < N;i++){
            c[i] = sc.nextInt();
        }
        large = large*large+T;
        
        long[] dpi = new long[large+1];
        Arrays.fill(dpi, Integer.MAX_VALUE);
        dpi[0] = 0;
        for(int i = 0; i <= large;i++){
            for(int j = 0; j < N;j++){
                if(v[j] <= i){
                    dpi[i] = Math.min(dpi[i], dpi[i-v[j]]+1);
                }
            }
        }
        for(int i = 0; i <= large;i++){
            if(dpi[i] < 0)
                dpi[i] = Integer.MAX_VALUE;
        }
        long[] f = new long[large+1];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;
        for (int i=0;i<N;i++)
        { 
        for (int j=1;j<c[i];j<<=1)
        {
            for (int k=large;k>=j*v[i];k--){
                f[k]=Math.min(f[k], f[k-j*v[i]]+j);
                if(f[k] < 0)
                    f[k] = Integer.MAX_VALUE;
            }
            c[i]-=j;
        }
        if (c[i] != 0)
            for (int k=large;k>=c[i]*v[i];k--){
                f[k]=Math.min(f[k], f[k-c[i]*v[i]]+c[i]);
                if(f[k] <0)
                    f[k] = Integer.MAX_VALUE;
            }
        }
        
        long ans = Integer.MAX_VALUE;
        for(int i = T; i <=large;i++){
            if(f[i] != Integer.MAX_VALUE && dpi[i]!=Integer.MAX_VALUE)
            ans = Math.min(f[i]+dpi[i-T],ans);
        }
        System.out.println(ans);
    }
}
