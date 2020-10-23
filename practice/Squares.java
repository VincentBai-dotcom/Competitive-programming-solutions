/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice2;

/**
 *
 * @author Vincent
 */
public class Squares {
    public static void main(String[] args) {
        s(30);
    }
    
    public static void s(int n){
        for(int i = 11; i <= n;i++){
            System.out.println(i+" "+i*i);
        }
    }
}
