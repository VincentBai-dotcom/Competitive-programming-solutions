/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice;
import java.util.*;
/**
 *
 * @author Vincent
 */
public class job {
    static int N;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int P = sc.nextInt();
        int K = sc.nextInt();
        int[][] graph = new int[N][N];
        for(int i = 0; i < P;i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt(); 
            graph[a-1][b-1] = c;
        }
    }
    public static TreeSet<Integer> rec(int[][] graph, boolean[] flag,int row,TreeSet<Integer> road){
        for(int i = 0; i < N;i++){
            if(graph[row][i] != 0 && flag[i] == false){
                flag[i] = true;
                return rec(graph,flag,i,road);
            }
        }
    }
}
