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
public class marathon {
    static int[] tree;
    static long[] tree1;
    static point[] ps;
    static int[] dis;
    static int[] dis2;
    static Scanner sc;
    static int N;
    public static void main(String[] args) {
        sc = new Scanner(System.in);
        N = sc.nextInt();
        int Q = sc.nextInt();
        ps = new point[Q];
        int xx = (int)(Math.ceil(Math.log(N-2)/Math.log(2)));
        int MAX = 2*(int)Math.pow(2, xx);
        tree = new int[MAX];
        xx = (int)(Math.ceil(Math.log(N-1)/Math.log(2)));
        MAX = 2*(int)Math.pow(2, xx);
        tree1 = new long[MAX];
        dis = new int[N-2];
        dis2 = new int[N-1];
        for(int i = 0; i < N;i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            ps[i] = new point(x,y);
        }
        for(int i = 0; i < N-1;i++){
            dis2[i] = dis(ps[i],ps[i+1]);
        }
        for(int i =0; i < N-2;i++){
            dis[i] = dis(ps[i],ps[i+1])+dis(ps[i+1],ps[i+2])-dis(ps[i],ps[i+2]);
        }
        build(1,0,N-3);
        build2(1,0,N-2);
        for(int i = 0; i <Q;i++){
            String s = sc.next();
            if(s.equals("U"))
                U();
            else
                System.out.println(Q());
        }
    }
    public static long Q(){
        int a = sc.nextInt()-1;
        int b = sc.nextInt()-1;
        return query2(1,0,N-2,a,b-1)-query(1,0,N-3,a,b-2);
    }
    public static void U(){
        int index = sc.nextInt()-1;
        int x = sc.nextInt();
        int y = sc.nextInt();
        ps[index] = new point(x,y);
        if(index >0){
            dis2[index-1] = dis(ps[index-1],ps[index]);
            update2(1,0,N-2,index-1,dis2[index-1]);
        }
        if(index < N-1){
            dis2[index] = dis(ps[index],ps[index+1]);
            update2(1,0,N-2,index,dis2[index]);
        }
        for(int i = index-2; i <= index;i++){
            if(i <0)
                continue;
            if(i > N-3)
                continue;
            dis[i] = dis2[i]+dis2[i+1]-dis(ps[i],ps[i+2]);
            update(1,0,N-3,i,dis[i]);
        }
    }
    public static void build(int node, int a, int b){
        if(a > b)
            return;
        if(a == b){
            tree[node] = dis[a];
            return;
        }
        build(node*2,a,(a+b)/2);
        build(node*2+1,(a+b)/2+1,b);
        tree[node] = Math.max(tree[node*2], tree[node*2+1]);
    }
    public static void build2(int node, int a, int b){
        if(a >b)
            return;
        if(a == b){
            tree1[node] = dis2[a];
            return;
        }
        build2(node*2,a,(a+b)/2);
        build2(node*2+1,(a+b)/2+1,b);
        tree1[node] = tree1[node*2]+tree1[node*2+1];
    }
    public static void update(int node, int a, int b, int i,int value){
        if(a >b || a > i || b < i)
            return;
        if(a == b){
            tree[node] = value;
            return;
        }
        update(node*2,a,(a+b)/2,i,value);
        update(node*2+1,(a+b)/2+1,b,i,value);
        tree[node] = Math.max(tree[node*2], tree[node*2+1]);
    }
    public static void update2(int node, int a, int b, int i,int value){
        if(a >b || a > i || b < i)
            return;
        if(a == b){
            tree1[node] = value;
            return;
        }
        update2(node*2,a,(a+b)/2,i,value);
        update2(node*2+1,(a+b)/2+1,b,i,value);
        tree1[node] =tree1[node*2]+tree1[node*2+1];
    }
    
    public static int query(int node, int a, int b, int i, int j){
        if(a > b || a > j || b < i)
            return Integer.MIN_VALUE;
        if(a>=i && b <= j)
            return tree[node];
        return Math.max(query(node*2,a,(a+b)/2,i,j),query(node*2+1,(a+b)/2+1,b,i,j));
    }
    public static long query2(int node, int a, int b, int i, int j){
        if(a > b || a > j || b < i)
            return 0;
        if(a>=i && b <= j)
            return tree1[node];
        return query2(node*2,a,(a+b)/2,i,j)+query2(node*2+1,(a+b)/2+1,b,i,j);
    }
    
    public static int dis(point a, point b){
        return Math.abs(a.x-b.x)+Math.abs(a.y-b.y);
    }
    static class point{
        int x;
        int y;
        public point (int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
