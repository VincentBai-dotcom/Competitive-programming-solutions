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
public class bclgold {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        char[] chars = new char[N];
        for(int i = 0; i< N;i++){
            chars[i] = sc.next().charAt(0);
        }
        char[] res = new char[N];
        int a = 0;
        int b = N-1;
        for(int i = 0; i < N;i++){
            int j = 0;
            int flag = -2;
            while(a+j < b-j){
                if(chars[a+j] < chars[b-j]){
                    flag = 1;
                    break;
                }
                if(chars[a+j] > chars[b-j]){
                    flag = -1;
                    break;
                }
                j++;
            }
            if(a+j >= b-j)
                flag = 1;
            if(flag == 1){
                res[i] = chars[a];
                a++;
            }
            else{
                res[i] = chars[b];
                b--;
            }
        }
        for(int i = 0; i < N;i++){
            if(i % 80 == 0 && i != 0)
            System.out.println("");
            System.out.print(res[i]);
        }
    }
}
