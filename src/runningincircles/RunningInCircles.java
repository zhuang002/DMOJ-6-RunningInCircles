/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runningincircles;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author zhuang001
 */
public class RunningInCircles {
    static Scanner sc=new Scanner(System.in);
    static int firstNode=1;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        for (int i=0;i<1;i++) {
            HashMap<Integer,Set<Integer>> graph=readGraph();
            int ringSize=findCircle(graph);
            System.out.println(ringSize);
        }
    }
    
    private static int findCircle(HashMap<Integer,Set<Integer>> graph) {
        
        
        HashMap<Integer,Integer> stepsForNodes=new HashMap();
        Set<Integer> starts=new HashSet();
        starts.add(firstNode);
        stepsForNodes.put(firstNode,0);
        int steps=1;
        while (!starts.isEmpty()) {
            Set<Integer> newStarts=new HashSet();
            for (Integer start:starts) {
                if (graph.containsKey(start)) {
                    Set<Integer> ends=graph.get(start);
                    for (Integer end:ends) {
                        if (stepsForNodes.containsKey(end)) {
                            return steps-stepsForNodes.get(end);
                        }
                        stepsForNodes.put(end, steps);
                    }
                    newStarts.addAll(ends);
                }
            }
            steps++;
            starts=newStarts;
        }
        return -1;
    }

    private static HashMap<Integer,Set<Integer>> readGraph() {
        int n=sc.nextInt();
        HashMap<Integer,Set<Integer>> hashMap=new HashMap();
        for (int i=0;i<n;i++) {
            int start=sc.nextInt();
            if (i==0) firstNode=start;
            int end=sc.nextInt();
            if (hashMap.containsKey(start)) {
                hashMap.get(start).add(end);
            } else {
                hashMap.put(start, new HashSet<Integer>());
                hashMap.get(start).add(end);
            }
        }
        return hashMap;
    }
}
