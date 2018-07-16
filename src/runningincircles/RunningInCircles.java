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
import java.util.Stack;

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
        for (int i=0;i<5;i++) {
            HashMap<Integer,Set<Integer>> graph=readGraph();
            int ringSize=findCircle(graph);
            System.out.println(ringSize);
        }
    }
    
    private static int findCircle(HashMap<Integer,Set<Integer>> graph) {
        HashMap<Integer,Integer> stepsForNodes=new HashMap();
        Stack<Integer> pathStack=new Stack();
        
        
        stepsForNodes.put(firstNode, 0);
        pathStack.push(firstNode);
        return tracePathCircle(graph,pathStack,stepsForNodes,1);
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
                hashMap.put(start, new HashSet<>());
                hashMap.get(start).add(end);
            }
        }
        return hashMap;
    }

    private static int tracePathCircle(HashMap<Integer,Set<Integer>> graph,
            Stack<Integer> pathStack,HashMap<Integer,Integer> stepsForNodes,
            Integer currentSteps) {
        if (pathStack.isEmpty()) return -1;
        int start=pathStack.peek();
        if (graph.containsKey(start)) {
            Set<Integer> ends=graph.get(start);
            for (Integer end : ends) {
                if (pathStack.contains(end)) {
                    return currentSteps-stepsForNodes.get(end);
                }
                pathStack.push(end);
                stepsForNodes.put(end,currentSteps);
                int returnSteps=tracePathCircle(graph,pathStack,stepsForNodes,currentSteps+1);
                if (returnSteps!=-1) {
                    return returnSteps;
                }
            }
        }
        pathStack.pop();
        stepsForNodes.remove(start);
        return -1;
    }
}
