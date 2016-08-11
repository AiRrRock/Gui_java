import javax.swing.*;
import java.io.PrintStream;

public class Consumer implements Runnable
{
    private final java.util.List<Integer> sharedVariable;
    private final int fibonachySize;
    private int numberOfRuns;
    private JTextArea tp;
    public Consumer(java.util.List<Integer> sharedQueue, int fibonchySize, int numberOfRuns, JTextArea tp) {
        this.sharedVariable = sharedQueue;
        this.fibonachySize = fibonchySize;
        this.numberOfRuns = numberOfRuns+1;
        //JTextArea textArea= new JTextArea();
        this.tp =tp;
        PrintStream printStream=new PrintStream(new CustomOutputStream(tp));
        System.setOut(printStream);
    }

    @Override
    public void run()
    {
        for (int i =0; i< numberOfRuns;i++){
            try {
                consume();
            } catch (InterruptedException ex){
                ex.printStackTrace();}
        }

        //return;
    }

    private void consume() throws InterruptedException
    {
        synchronized (sharedVariable){
            sharedVariable.wait();
            int i = (Integer) sharedVariable.remove(0);
            if (i % 2 == 0) {
                System.out.println("Consumer recieved: " + i);
            } else {
                System.out.println("Consumer recieved: " + 0);
            }
            sharedVariable.notify();
        }

    }
}

