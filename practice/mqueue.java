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
public class mqueue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        node[] n = new node[N];
        for(int i = 0; i < N;i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            n[i] = new node(a,b);
            if(a < b)
                n[i].c = a;
            else
                n[i].c = Integer.MAX_VALUE-b;
        }
        
        Arrays.sort(n);
        int t1 = 0;
        int t2 = 0;
        for(int i = 0; i< N;i++){
            t1+=n[i].a;
            if(t2 < t1)
                t2 = t1;
            t2+=n[i].b;
            
        }
        System.out.println(t2);
    }
    
    static class node implements Comparable<node>{
        int a;
        int b;
        int c;
        
        public node(int x, int y){
            a = x;
            b = y;
        }
        
        public int compareTo(node z){
            return c - z.c;
        }
    }
}
