/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice;
import java.io.*;
import java.util.*;
/**
 *
 * @author Vincent
 */
public class AirPort {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));
        int num = Integer.parseInt(br.readLine());
        StringTokenizer st;
        TreeSet<cow> senO = new TreeSet<cow>();
        ArrayList<cow> timeO = new ArrayList<cow>();
        for(int i = 0; i < num;i++){
            st = new StringTokenizer(br.readLine());
            cow cur = new cow(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),i+1);
            timeO.add(cur);
        }
        Collections.sort(timeO, new Comparator<cow>(){
            public int compare(cow a, cow b){
                if(a.arrive == b.arrive){
                    return a.sen-b.sen;
                }
                return a.arrive - b.arrive;
            }
        });
        pw.println(timeO);
        cow incow = timeO.get(0);
        int res = 0;
        int timeMark = timeO.get(0).arrive;
        int index = 1;
        while(senO.size() != 0 || index < timeO.size() ){
            if(index >= timeO.size()){
                res = Math.max(res, timeMark + incow.time - senO.first().arrive);
                timeMark += incow.time;
                incow = senO.first();
                senO.remove(incow);
                continue;
            }
            cow cur = timeO.get(index);
            if(cur.arrive <= timeMark +incow.time){
                senO.add(timeO.get(index));
                index++;
            }
            else if(senO.size() == 0){
                incow = cur;
                index++;
            }
            else if(senO.size() > 0){
                res = Math.max(res, timeMark + incow.time - senO.first().arrive);
                timeMark += incow.time;
                incow = senO.first();
                senO.remove(incow);
                index--;
            }
        }
        pw.println(res);
        pw.close();
    }
    
    static class cow implements Comparable<cow>{
        int arrive;
        int time;
        int sen;
        
        public cow(int a, int t,int s){
            arrive = a;
            time = t;
            sen = s;
        }
        
        public int compare(cow a){
            return a.time - time;
        }
        public String toString(){
            return ""+arrive + " " + time;
        }

        @Override
        public int compareTo(cow o) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
