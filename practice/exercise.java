/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.OutputStream;
import java.io.*;
import java.util.*;
/**
 *
 * @author Vincent
 */
public class exercise {
    static node[] n;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        
        OutputStream output = System.out;
        PrintWriter out = new PrintWriter(output);
        n = new node[N];
        for(int i = 0;i <  N;i++)
            n[i] = new node(i);
        for(int i=  0; i< N-1;i++){
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            n[a].c.add(b);
            n[b].c.add(a);
        }
        boolean flag = true;
        for(int i = 0; i < N;i++){
            if(n[i].c.size()>2)
                flag = false;
        }
        if(flag == true){
            System.out.println((int)Math.ceil((double)(N-1-C)/(C+1)));
            System.exit(0);
        }
        int hi = (N+1)/3;
        
        int lo = 0;
        while(lo < hi){
            int mid = (lo+hi)/2;
            boolean[] v = new boolean[N];
            pair ans = dfs(0,v,mid);
            if(ans.k > C)
                lo = mid+1;
            else
                hi = mid;
        }
        br.close();
        out.println(hi);
        out.close();
    }
    public static pair dfs(int index, boolean[] v,int max){
        v[index] = true;
        ArrayList<Integer> al = new ArrayList<Integer>();
        int counter = 0;
        for(int i:n[index].c){
            if(!v[i]){
                pair cur = dfs(i,v,max);
                al.add(cur.depth);
                counter+=cur.k;
            }
        }
        if(al.size() == 0)
            return new pair(counter,1);
        Collections.sort(al);
        al.add(0,0);
        int mark = al.size()-1;
        for(int i = al.size()-1;i > 0; i--){
            if(al.get(i)+al.get(i-1)>max){
                counter++;
                mark = i-1;
            }
            else
                break;
        }
        return new pair(counter,al.get(mark)+1);
    }
    static class pair{
        int k;
        int depth;
        
        public pair(int k, int depth){
            this.k = k;
            this.depth = depth;
            
        }
    }
    
    static class node{
        int index;
        LinkedList<Integer> c;
        
        public node(int index){
            this.index = index;
            c = new LinkedList<Integer>();
            
        }
    }
}
