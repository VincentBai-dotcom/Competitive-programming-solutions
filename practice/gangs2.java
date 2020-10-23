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
public class gangs2 {
    static int N;
    static node[] n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int M = sc.nextInt();
        n = new node[2*M];
        for(int i = 0; i< M;i++){
            n[i*2] = new node(sc.nextInt(),0);
            n[i*2+1] = new node(sc.nextInt()+n[i*2].x,1);
            n[i*2+1].p = i*2;
        }
        Arrays.sort(n);
        
    }
    
    static class node implements Comparable<node>{
        int x;
        int state;
        int p;
        public node(int a, int b){
            x = a;
            state = b;
            p =-1;
        }
        
        public int compareTo(node a){
            return x-a.x;
        }
    }
}
