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
public class empty {
    public static void main(String[] args) {
        Scanner ms = new Scanner(System.in);
        int N = ms.nextInt();
        int K = ms.nextInt();
        int[] f = new int[N];
        for(int i = 0; i < K;i++){
            int x = ms.nextInt();
            int y = ms.nextInt();
            long a = ms.nextLong();
            long b = ms.nextLong();
            for(int j = 1; j <= y;j++){
                f[(int)(((a*j+b) % N))] += x;
            }
        }
        int mark = -1;
        int cur = 0;
        for(int i = 0; i < N;i++){
            if(f[i] == 1)
                continue;
            if(f[i] != 0)
                cur+=f[i];
            if(cur != 0){
                f[i] = 1;
                cur--;
            }
        }
        for(int i = 0; i < N;i++){
            if(f[i] == 1)
                continue;
            if(f[i] != 0)
                cur+=f[i];
            if(cur != 0){
                f[i] = 1;
                cur--;
            }
        }
        for(int i = 0; i < N;i++){
            if(f[i] == 0){
                mark = i;
                break;
            }
        }
        System.out.println(mark);
    }
}
