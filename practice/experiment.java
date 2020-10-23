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
public class experiment {
    public static void main(String[] args) {
        int[] a = {1,2,3,4};
        fdsa(a);
        System.out.println(Arrays.toString(a));
    }
    
    public static void fdsa(int[] a){
        int[] b = {4,3,2,1};
        a = b;
    }
}
