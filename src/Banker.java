/*
Test case:
    total resources R1 R2 R3
                    10 5  7
    process     allocation      Max
    P1          0,1,0           7,5,3
    P2          2,0,0           3,2,2
    P3          3,0,2           9,0,2
    P4          2,1,1           2,2,2


*/


import java.util.*;
public class Banker {
    public static int P = 4;//number of process
    public static int R = 3; // nunber of resources
    public static int total = 0;
    public static int allocated[][] = {{0, 1, 0}, 
    {2, 0, 0}, 
    {3, 0, 2}, 
    {2, 1, 1}}; 
    public static int resources[] = {10, 5, 7};
    public static int max[][] = {{7, 5, 3}, 
    {3, 2, 2}, 
    {9, 0, 2}, 
    {2, 2, 2}};// the maxium resources needed by processs 
    public static int need[][] = new int[P][R];// claim matrix(need[i][j]:process i need resource j)
    public static int avaliable[] = new int[R];// avaliable resource: maxium resources - allocated resources
    public static boolean is_avaliable(int process_id,int allocated[][],int max[][],int need[][],int avaliable[])
    {
        boolean flag = true;
        for(int i=0;i<R;i++)
        {
            if(need[process_id][i]>avaliable[i])
            {
                flag = false;
            }
        }
        return flag;
    }
    public static void safe_sequence(boolean marked[],int allocated[][],int max[][],int need[][],int avaliable[],Vector<Integer> safe)
    {
        for(int i=0;i<P;i++)
        {
            if(!marked[i] && is_avaliable(i, allocated, max, need, avaliable))
            {
                marked[i] = true;// assume that process i has already allocated
                for(int j=0;j<R;j++)
                
                {
                    avaliable[j]+=allocated[i][j]; // avaliable resource + process j released resource

                }
                safe.add(i);
                safe_sequence(marked, allocated, max, need, avaliable, safe);// test whether it is safe or not
                safe.removeElementAt(safe.size()-1);

                marked[i] = false;
                for(int j=0;j<R;j++)
                {
                    avaliable[j] -= allocated[i][j];
                }
            }
        }
        if(safe.size() == P)
        {
            total++;
            for(int i=0;i<P;i++)
            {
                System.out.print("P" + (safe.get(i) + 1));
                if(i!=(P-1))
                {
                    System.out.print("-->");
                }
            }
            System.out.println(" ");
        }
    }
    public static void main(String[] args)
    {
       Banker banker = new Banker();
       boolean[] marked = new boolean[P];
       for(int i=0;i<R;i++)
       {
           int sum=0;
           for(int j=0;j<P;j++)
           {
               sum += allocated[j][i];
           }
           avaliable[i] = resources[i]-sum;
       }
       Vector<Integer> safe = new Vector<Integer>();
       for(int i=0;i<P;i++)
       {
           for(int j=0;j<R;j++)
           {
               need[i][j] = max[i][j] -allocated[i][j];
           }
       }
       System.out.println("Safe sequences are:"); 
       banker.safe_sequence(marked, allocated, max, need, avaliable, safe);
       
      
       System.out.println("\n There are total " + banker.total + " safe-sequences"); 
    }
}
