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
public class median {
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] pre = new int[N];
        int c = sc.nextInt();
        if(c >= M)
            pre[0] = 1;
        else
            pre[0] = -1;
        int res = 0;
        for(int i = 1; i < N;i++){
            int cc = sc.nextInt();
            int mark = 0;
            if(cc >= M){
                mark++;
                res++;
            }
            else
                mark--;
            pre[i] = pre[i-1] + mark;
        }
        res += solve(pre).b;
        System.out.println(res);
    }
    public static pair solve(int[] prefix){
        int[] a = Arrays.copyOfRange(prefix, 0, prefix.length/2);
        int[] b = Arrays.copyOfRange(prefix, prefix.length/2, prefix.length);
        pair p1 = new pair(a,0);
        pair p2 = new pair(b,0);
        if(a.length>1){
            p1 = solve(a);
            a = p1.a;
        }
        if(b.length > 1){
            p2 = solve(b);
            b = p2.a;
        }
        pair p3 = merge(a,b);
        p3.b += p1.b+p2.b;
        return p3;
    }
    public static pair merge(int[] a, int[] b){
        int[] c = new int[a.length+b.length];
        int aa = 0;
        int bb = 0;
        int i = 0;
        int counter = 0;
        while(aa != a.length && bb!=b.length){
            if(aa == a.length){
                bb++;
                c[i] = b[bb];
            }
            else if(bb == b.length){
                aa++;
                c[i] = a[aa];
            }
            else if(a[aa] >= b[bb]){
                c[i] = a[aa];
                aa++;
                counter += b.length-bb;
            }
            else{
                c[i] = b[bb];
                bb++;
            }
            i++;
        }
        return new pair(c,counter);
    }
    
    static class pair{
        int[] a;
        int b;
        
        public pair(int[] a, int b){
            this.a = a;
            this.b = b;
        }
    }
}
