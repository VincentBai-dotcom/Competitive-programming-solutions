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
public class vacation {
    static node[] n;
    static int[] v;
    static int[] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        n = new node[N];
        for(int i = 0; i < N;i++){
            n[i] = new node(i);
        }
        for(int i = 0; i <N-1;i++){
            int a = sc.nextInt()-1;
            int b = sc.nextInt()-1;
            n[a].c.add(b);
            n[b].c.add(a);
        }
        v = new int[N];
        dp = new int[N];
        bfs(0,-1);
        System.out.println(Math.max(dp[0],v[0]));
    }
    
    public static void bfs(int index, int p){
        v[index] = 1;
        dp[index] = 0;
        for(int i:n[index].c){
            if(i== p)
                continue;
            bfs(i,index);
            v[index] += dp[i];
            dp[index] += Math.max(v[i], dp[i]);
        }
    }
    
    
    
    static class node{
        int index;
        LinkedList<Integer> c;
        public node(int i){
            index = i;
            c = new LinkedList<Integer>();
        }
    }
}
