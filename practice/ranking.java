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
public class ranking {
    static node[] n;
    static int[] d;
    static int[][] save;
    static int N;
    static ArrayList<Integer> sorted;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int M = sc.nextInt();
        n = new node[N];
        d = new int[N];
        sorted = new ArrayList<Integer>();
        save = new int[N][N];
        
        OutputStream output = System.out;
        PrintWriter out = new PrintWriter(output);
        for(int i = 0; i< N;i++){
            n[i] = new node(i);
        }
        for(int i = 0; i < M;i++){
            int a = sc.nextInt()-1;
            int b = sc.nextInt()-1;
            n[a].c.add(b);
        }
        int counter = 0;
        for(int i =0; i < N;i++){
            boolean[] v = new boolean[N];
            counter+=dfs(v,i);
        }
        out.println(N*(N-1)/2-counter);
        out.close();
    }
    public static int dfs(boolean[] v,int index){
        int counter = 0;
        v[index] = true;
        for(int i:n[index].c){
            if(v[i] == false){
                counter ++;
                counter += dfs(v,i);
            }
        }
        return counter;
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
