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
public class chain {
    static int N;
    public static void main(String[] args){
        Scanner mc = new Scanner(System.in);
        N = mc.nextInt();
        
        for(int i = 0; i <= N;i++){
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(1);
            dfs(list,i,N);
        }
    }
    public static int dfs(ArrayList<Integer> list, int limit, int target){
        if(limit <=0)
            return -1;
        int initial = list.get(list.size()-1);
        if(initial == target)
        return list.size();
        
        for(int i = list.size()-1; i>=0; i--){
            ArrayList<Integer> list2 = (ArrayList<Integer>)list.clone(); 
            if(initial+list.get(i) > target)
                continue;
            list2.add(initial+list.get(i));
            int result = dfs(list2,limit-1,target);
            if(result != -1){
                System.out.println(result);
                System.exit(0);
            }
        }
        return -1;
    }
}
