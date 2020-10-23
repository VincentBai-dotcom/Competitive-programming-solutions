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
public class island2 {
    static char[][] f;
    static int R;
    static int C;
    static int[][][] dis;
    static int num;
    static node[] n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        f = new char[R][C];
        num = 0;
        for(int i = 0;i < R;i++){
            String cur = sc.next();
            for(int j = 0; j < C;j++){
                f[i][j] = cur.charAt(j);
                if(f[i][j] == 'X')
                    num++;
            }
        }
        
        n = new node[num];
        dis = new int[num][R][C];
        for(int i = 0; i < num;i++){
            for(int j = 0; j < R;j++){
                Arrays.fill(dis[i][j], Integer.MAX_VALUE);
            }
        }
        num = 0;
        for(int i = 0; i < R;i++){
            for(int j = 0; j < C;j++){
                if(f[i][j] == 'X'){
                    n[num] = new node(i,j,num);
                    num++;
                }
                    
            }
        }
        for(int i = 0; i < num;i++){
            
            ff(i,n[i].r,n[i].c,0,new boolean[R][C]);
        }
        
        int[][] dp = new int[1 << num][num];
        for(int i = 0; i < 1 << num;i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        System.out.println(num);
        for(int i = 0; i<num;i++){
            System.out.println(Integer.toBinaryString(1<<i)+" "+i);
            dp[1<<i][i] = 0;
            
        }
        for(int i = 1; i < 1 << num;i++){
            for(int j = 0; j < num;j++){
                if(((i >> j) & 1) != 1)
                    continue;
                if(i - (1 << j) == 0)
                    continue;
                int index = i - (1 << j);
                int counter = dp[i][j];
                for(int k = 0; k < num;k++){
                    if(((index >> k) & 1) != 1)
                        continue;
                    counter = Math.min(counter,dp[index][k]+dis[k][n[j].r][n[j].c]); 
                }
                dp[i][j] = counter;
            }
        }
        int res = Integer.MAX_VALUE;
        for(int i = 0; i<num;i++){
            res = Math.min(res, dp[(1<<num)-1][i]);
        }
        System.out.println(res);
    }
    public static void ff(int o, int r, int c, int counter, boolean[][] v){
        if(r < 0 || r >= R||c <0 || c >=C )
            return;
        if(f[r][c] == '.')
            return;
        if(f[r][c] == 'S')
            counter++;
        if(f[r][c] == 'X'){
            if(dis[o][r][c] > counter){
                dis[o][r][c] = counter;
                v[r][c] = false;
            }
        }
        
        if(!v[r][c] ){
            v[r][c] = true;
            ff(o,r+1,c,counter,v);
            ff(o,r-1,c,counter,v);
            ff(o,r,c+1,counter,v);
            ff(o,r,c-1,counter,v);
        }
        v[r][c] = true;
    }
    static class node{
        int r;
        int c;
        int index;
        public node(int a, int b, int x){
            r = a;
            c = b;
            index = x;
        }
    }
}

