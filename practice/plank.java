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
public class plank {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        PriorityQueue<Integer>pq = new PriorityQueue<Integer>(N);
        for(int i = 0; i< N;i++){
            pq.add(sc.nextInt());
        }
        long counter = 0;
        while(pq.size()!= 1){
            int a = pq.poll();
            int b = pq.poll();
            counter+= a+b;
            pq.add(a+b);
        }
        System.out.println(counter);
    }
}
