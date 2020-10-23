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
public class airline_crews {
    static int V;
    static int[] res;
    public static void main(String[] args){
        Scanner  sc = new Scanner(System.in);
        int a= sc.nextInt();
        int b = sc.nextInt();
        V = a+b+2;
        res = new int[V];
        int[][] graph = new int[V][V];
        for(int i = 0;i < a;i++){
            for(int j = 0; j < b;j++){
                if(sc.nextInt() == 1)
                    graph[i+1][j+1+a] = 1;
            }
        }
        for(int i = a+1; i < V-1;i++){
            graph[i][V-1]= 1;
        }
        for(int i = 1; i < a+1;i++){
            graph[0][i] = 1;
        }
        
        for(int i = 0; i< V;i++){
            System.out.println(Arrays.toString(graph[i]));
        }
        System.out.println(fordFulkerson(graph,0,V-1));
        System.out.println(Arrays.toString(res));
        for(int i = 1; i < a+1;i++){
            if(res[i] == 0)
                System.out.print(-1+" ");
            else
                System.out.print(res[i]-a+" ");
        }
    }
    public static boolean bfs(int rGraph[][], int s, int t, int parent[]) 
    { 
        boolean visited[] = new boolean[V]; 
        for(int i=0; i<V; ++i) 
            visited[i]=false; 
  
        LinkedList<Integer> queue = new LinkedList<Integer>(); 
        queue.add(s); 
        visited[s] = true; 
        parent[s]=-1; 
  
        while (queue.size()!=0) 
        { 
            int u = queue.poll(); 
  
            for (int v=0; v<V; v++) 
            { 
                if (visited[v]==false && rGraph[u][v] > 0) 
                { 
                    queue.add(v); 
                    parent[v] = u; 
                    visited[v] = true; 
                } 
            } 
        } 
        if(visited[t] == true)
            res[parent[parent[V-1]]] = parent[V-1];
        return (visited[t] == true); 
    } 
  
    public static int fordFulkerson(int graph[][], int s, int t) 
    { 
        int u, v; 
  
        int rGraph[][] = new int[V][V]; 
  
        for (u = 0; u < V; u++) 
            for (v = 0; v < V; v++) 
                rGraph[u][v] = graph[u][v]; 
  
        // This array is filled by BFS and to store path 
        int parent[] = new int[V]; 
  
        int max_flow = 0;  // There is no flow initially 
  
        // Augment the flow while tere is path from source 
        // to sink 
        while (bfs(rGraph, s, t, parent)) 
        { 
            int path_flow = Integer.MAX_VALUE; 
            for (v=t; v!=s; v=parent[v]) 
            { 
                u = parent[v]; 
                path_flow = Math.min(path_flow, rGraph[u][v]); 
            } 
  
            for (v=t; v != s; v=parent[v]) 
            { 
                u = parent[v]; 
                rGraph[u][v] -= path_flow; 
                rGraph[v][u] += path_flow; 
            } 
  
            // Add path flow to overall flow 
            max_flow += path_flow; 
        } 
  
        // Return the overall flow 
        return max_flow; 
    } 
}
