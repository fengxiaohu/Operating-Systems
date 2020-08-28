import java.util.LinkedList;
public class MultiThreadTest {
    // the class contains producer and consumer function
    public static class PC
    {
        LinkedList<Integer> list = new LinkedList<>();
        int capacity = 2;
        public void producer() throws InterruptedException
        {
            int value = 0;
            while(true)
            {
                synchronized(this)
                {
                    while(list.size() == capacity)
                    {
                        wait();
                    }
                    System.out.println("producer produced-"+value);
                }
                list.add(value++);
                notify();
                Thread.sleep(1000);
            }
        }
        public void consume() throws InterruptedException
        {
            while(true)
            {
                synchronized(this)
                {
                    while(list.size()==0)
                    {
                        wait();
                    
                    }
                    int val = list.removeFirst();
                    System.out.println("Consumer consumed-"+ val);
                    notify();
                    Thread.sleep(1000);
                }
            }
        }
    }
    public static void main(String[] args)
        throws InterruptedException
    {
        final PC pc = new PC();
        Thread t1 = new Thread(new Runnable(){
            @Override
            // rewrite run()
            public void run()
            {
                try{pc.producer();}
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run()
            {
                try{pc.consume();}
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }




}
