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
public class hayturn {
    public static void main(String[] args) {
        Scanner ms = new Scanner(System.in);
        int N = ms.nextInt();
        int[] field = new int[N];
        for(int i = 0; i < N;i++){
            field[i] = ms.nextInt();
        }
        int x = 0;
        int y = 0;
        for(int i = N-1; i>=0;i--){
            if(y+field[i] >=x){
                int t=x;
                x=y+field[i];
                y=t;
            }
        }
        System.out.println(x+" "+y);
    }
}