/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

/**
 *
 * @author Vincent
 */
public class lites {
    static int MAX;
    static int[] arr;
    static int[] tree;
    static int[] tree2;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        OutputStream output = System.out;
        PrintWriter out = new PrintWriter(output);
        int N = sc.nextInt();
        int Q = sc.nextInt();
        int x = (int)(Math.ceil(Math.log(N)/Math.log(2)));
        MAX = 2*(int)Math.pow(2, x)-1; 
        tree = new int[MAX];
        arr = new int[N];
        tree2 = new int[MAX];
        for(int i = 0; i < N;i++){
            arr[i] = sc.nextInt();
        }
        build(1,0,N-1);
        build2(1,0,N-1);
        for(int i = 0;i < Q;i++){
            int a = sc.nextInt()-1;
            int b  = sc.nextInt()-1;
            out.println(query(1,0,N-1,a,b)-query2(1,0,N-1,a,b));
        }
        out.close();
    }
    public static void build(int node, int a, int b){
        if(a > b)
            return ;
        if(a == b){
            tree[node] = arr[a];
            return;
        }
        build(node*2,a,(a+b)/2);
        build(node*2+1,(a+b)/2+1,b);
            tree[node] = Math.max(tree[node*2], tree[node*2+1]);
    }
    public static void build2(int node, int a, int b){
        if(a > b)
            return ;
        if(a == b){
            tree2[node] = arr[a];
            return;
        }
        build2(node*2,a,(a+b)/2);
        build2(node*2+1,(a+b)/2+1,b);
            tree2[node] = Math.min(tree2[node*2], tree2[node*2+1]);
    }
    
    public static int query(int node, int a, int b, int i, int j){
        if(a > b || a > j || b < i)
            return Integer.MIN_VALUE;
        if(a >= i && b<=j)
            return tree[node];
        int q1 = query(node*2,a,(a+b)/2,i,j);
        int q2 = query(node*2+1,(a+b)/2+1,b,i,j);
        return Math.max(q1, q2);
    }
    public static int query2(int node, int a, int b, int i, int j){
        if(a > b || a > j || b < i)
            return Integer.MAX_VALUE;
        if(a >= i && b<=j)
            return tree2[node];
        int q1 = query2(node*2,a,(a+b)/2,i,j);
        int q2 = query2(node*2+1,(a+b)/2+1,b,i,j);
        return Math.min(q1, q2);
    }
}
