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
public class bigmac {
    static int N;
    static int M;
    static double V;
    static double[] dis;
    static pair[] e;
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        V = sc.nextDouble();
        int A = sc.nextInt()-1;
        int B = sc.nextInt()-1;
        dis = new double[N];
        Arrays.fill(dis, Double.POSITIVE_INFINITY);
        e = new pair[M];
        for(int i = 0; i <M;i++){
            int a = sc.nextInt()-1;
            int b = sc.nextInt()-1;
            double c = sc.nextDouble();
            e[i] = new pair(a,b,c);
        }
        bmf(A);
        if(!bmf2(A))
            System.out.println(0);
        else System.out.println((long)dis[B]);
    }
    
    public static void bmf(int src){
        dis[src] = V;
        
        for(int i =0; i < N-1;i++){
            for(int j = 0; j < M;j++){
                if(dis[e[j].x] >dis[e[j].o]*e[j].weight)
                    dis[e[j].x] = dis[e[j].o]*e[j].weight;
            }
        }
    }
    public static boolean bmf2(int src){
        dis[src] = V;
        
        for(int i =0; i < N-1;i++){
            for(int j = 0; j < M;j++){
                if(dis[e[j].x] >dis[e[j].o]*e[j].weight)
                    return false;
            }
        }
        return true;
    }
    
    
    
    static class pair{
        int o;
        int x;
        double weight;
        
        public pair(int o,int x, double weight){
            this.o = o;
            this.x = x;
            this.weight = weight;
        }
        
    }
}
