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
public class gangs {
    static node[] n;
    static int[] jump;
    static int[] tempcos;
    static int[] next;
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int M = sc.nextInt();
        n = new node[M];
        for(int i = 0; i< M;i++){
            int a = sc.nextInt();
            int b = sc.nextInt()+a;
            n[i] = new node(a,b);
        }
        Arrays.sort(n);
        int temp = -1;
        int mark = -1;
        for(int i = 0; i < M;i++){
            if(n[i].e > mark){
                temp++;
                n[temp] = n[i];
                mark = n[temp].e;
            }
        }
        for(int i = 0; i < temp+1;i++){
            System.out.println(n[i].s);
        }
    }
    
    public static void get(int x){
        if(jump[x] != 0)
            return;
        if(n[x].e >= N){
            jump[x] = x;
            tempcos[x] = 0;
        }
        else{
            get(next[x]);
            jump[x] = jump[next[x]];
            tempcos[x] = tempcos[next[x]]+1;
        }
    }
    
    static class node implements Comparable<node>{
        int s;
        int e;
        public  node(int a, int b){
             s= a;
             e = b;
        }
        
        public int compareTo(node a){
            return s == a.s ? a.e-e:s-a.s;
        }
        
    }
}
