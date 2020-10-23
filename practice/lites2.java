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
public class lites2 {
    static int MAX;
    static int[] tree;
    static int[] lazy;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int Q = sc.nextInt();
        int x = (int)(Math.ceil(Math.log(N)/Math.log(2)));
        OutputStream output = System.out;
        PrintWriter out = new PrintWriter(output);
        MAX = 2*(int)Math.pow(2, x)-1; 
        tree = new int[MAX+1];
        lazy = new int[MAX+1];
        for(int i = 0; i < Q;i++){
            int a = sc.nextInt();
            int b = sc.nextInt()-1;
            int c = sc.nextInt()-1;
            if(a == 0){
                update(1,0,N-1,b,c);
            }
            else{
                out.println(query(1,0,N-1,b,c));
            }
        }
        out.close();
    }
    
    public static void update(int node, int a, int b, int i, int j){
        
        if(lazy[node] % 2 == 1){
            tree[node] = b-a+1-tree[node];
            if(a != b){
                lazy[node*2]++;
                lazy[node*2+1]++;
            }
            lazy[node]--;
        }
        if(a > b || a > j || b < i)
            return;
        if(a >=i && b <=j){
            tree[node] = b-a+1-tree[node];
            
            if(a != b){
                lazy[node*2]++;
                lazy[node*2+1]++;
            }
            return;
        }
        
        update(node*2,a,(a+b)/2,i,j);
        update(node*2+1,(a+b)/2+1,b,i,j);
        tree[node] = tree[node*2]+tree[node*2+1];
    }
    
    public static int query(int node, int a, int b, int i, int j){
        if(lazy[node] % 2 == 1){
            tree[node] = b-a+1-tree[node];
            if(a != b){
                lazy[node*2]++;
                lazy[node*2+1]++;
            }
            lazy[node]--;
        }
        if(a > b || a > j || b < i)
            return 0;
        if(a >= i && b <=j)
            return tree[node];
        return query(node*2,a,(a+b)/2,i,j)+query(node*2+1,(a+b)/2+1,b,i,j);
    }
}
