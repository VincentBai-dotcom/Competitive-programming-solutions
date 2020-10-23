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
public class balloc {
    static int[] tree;
    static int[] lazy;
    static cover[] c;
    static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        c = new cover[M];
        arr = new int[N];
        
        int x = (int)(Math.ceil(Math.log(N)/Math.log(2)));
        int MAX = 2*(int)Math.pow(2, x); 
        tree = new int[MAX];
        lazy = new int[MAX];
        for(int  i= 0; i < N;i++){
            arr[i] = sc.nextInt();
        }
        for(int i = 0; i<M;i++){
            c[i] = new cover(sc.nextInt(),sc.nextInt());
        }
        Arrays.sort(c);
        construct(1,0,N-1);
        int counter = 0;
        
        for(int i = 0; i <M;i++){
            int a = c[i].s-1;
            int b = c[i].e-1;
            if(query(1,0,N-1,a,b) >0){
                update(1,0,N-1,a,b);
                counter++;
            }
        }
        System.out.println(counter);
    }
    
    public static void construct(int node,int a, int b){
        
        if(a == b){
            tree[node] = arr[a];
            return;
        }
        construct(node*2,a,(a+b)/2);
        construct(node*2+1,(a+b)/2+1,b);
        tree[node] = Math.min(tree[node*2], tree[2*node+1]);
    }
    
    public static void update(int node, int a, int b, int i, int j){
        
        if(lazy[node]!= 0){
            tree[node] -= lazy[node];
            if(a != b){
                lazy[node*2] = lazy[node];
                lazy[node*2+1] = lazy[node];
            }
            lazy[node] = 0;
        }
        if(a > b || a > j || b < i)
            return;
        if(a >=i && b <=j){
            tree[node]--;
            
            if(a != b){
                lazy[node*2] ++;
                lazy[node*2+1]++;
            }
            return;
        }
        
        update(node*2,a,(a+b)/2,i,j);
        update(node*2+1,(a+b)/2+1,b,i,j);
        tree[node] = Math.min(tree[node*2],tree[node*2+1]);
    }
    public static int query(int node, int a, int b, int i, int j){
        if(lazy[node] != 0){
            tree[node] -= lazy[node];
            if(a != b){
                lazy[node*2]+= lazy[node];
                lazy[node*2+1]+=lazy[node];
            }
            lazy[node] = 0;
        }
        if(a > b || a > j || b < i)
            return Integer.MAX_VALUE;
        if(a >= i && b <=j)
            return tree[node];
        return Math.min(query(node*2,a,(a+b)/2,i,j), query(node*2+1,(a+b)/2+1,b,i,j));
    }
    static class cover implements Comparable<cover>{
        int s;
        int e;
        
        public cover(int a, int b){
            s = a;
            e = b;
        }
        
        public int compareTo(cover a){
            return e == a.e ? a.s-s : e-a.e;
        }
    }
}
