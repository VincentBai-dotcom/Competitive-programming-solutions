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
public class route {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int r = sc.nextInt();
        int[] a = new int[A];
        int[] b = new int[B];
        long[] dpa = new long[A];
        long[] dpb = new long[B];
        for(int i = 0; i < A;i++){
            a[i] = sc.nextInt();
            dpa[i] = a[i];
        }
        for(int i = 0; i < B;i++){
            b[i] = sc.nextInt();
            dpb[i] = b[i];
        }
        edge[] e = new edge[r];
        for(int i = 0; i < r;i++){
            e[i] = new edge(sc.nextInt()-1,sc.nextInt()-1);
        }
        Arrays.sort(e);
        for(int i = 0; i<r;i++){
            int u = e[i].f;
            int v = e[i].s;
            long v1 = dpb[v]+a[u];
            long v2 = dpa[u]+b[v];
            dpa[u] = Math.max(dpa[u], v1);
            dpb[v] = Math.max(dpb[v], v2);
        }
        long res = 0;
        for(int i = 0; i < A;i++){
            res = Math.max(res, dpa[i]);
        }
        for(int i = 0; i<B;i++){
            res = Math.max(res,dpb[i]);
        }
        System.out.println(res);
    }
    
    static class edge implements Comparable<edge>{
        int f;
        int s;
        public edge(int a, int b){
            f = a;
            s = b;
        }
        
        public int compareTo(edge a){
            return f == a.f ? s-a.s : f -a.f;
        }
    }
}
