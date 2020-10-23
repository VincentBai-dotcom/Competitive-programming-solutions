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
public class dec {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int B = sc.nextInt();
        int[][] p = new int[N][N];
        ArrayList<bonus>[] b = new ArrayList[N];
        for(int i = 0; i <N;i++){
            b[i] = new ArrayList<bonus>();
        }
        for(int i = 0;i < B;i++){
            b[sc.nextInt()-1].add(new bonus(sc.nextInt(),sc.nextInt()));
        }
        for(int i = 0;i <N;i++){
            for(int j = 0; j < N;j++){
                p[i][j] = sc.nextInt();
            }
        }
        
        long dp[] = new long[1 <<N];
        for(int i = 1; i < 1 << N;i++){
            int ev = 0;
            for(int j = 0; j<N;j++){
                if(((i >> j)&1) == 1){
                    ev++;
                }
            }
            for(int j = 0; j < N;j++){
                if(((i >> j)&1) == 1){
                    int index = i- (1 << j);
                    dp[i] = Math.max(dp[i], dp[index]+p[j][ev-1]);
                    
                }
            }
            for(bonus j:b[ev-1]){
                if(dp[i] >= j.a)
                    dp[i]+=j.b;
            }
        }
        System.out.println(dp[(1<<N)-1]);
    }
    
    static class bonus{
        int a;
        int b;
        public bonus(int x, int y){
            a = x;
            b = y;
        }
    }
}
