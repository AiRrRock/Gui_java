import javax.swing.*;
import java.io.PrintStream;
import java.util.List;

public class Producer implements Runnable {
    private final List<Integer> sharedVariable;
    private final int fibonachySize;
    private int numberOfRuns;
    private JTextArea tp;
    public Producer(List<Integer> sharedQueue, int fibonchySize, int numberOfRuns, JTextArea tp) {
        this.sharedVariable = sharedQueue;
        this.fibonachySize = fibonchySize;
        this.numberOfRuns = numberOfRuns+1;
        this.tp =tp;
        PrintStream printStream=new PrintStream(new CustomOutputStream(tp));
        System.setOut(printStream);
    }

    @Override
    public void run() {
        int[] Fibonachy = makeFibonachy();
        for (int i = 1; i< numberOfRuns; i++){
            try {
                System.out.println(i);
                produce(Fibonachy);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }


        }
    }
    private void produce(int[] Fibo) throws InterruptedException {
        synchronized (sharedVariable) {
            int randomIndex = (int) (Math.random()*fibonachySize);
            sharedVariable.add(Fibo[randomIndex]);
            System.out.println("Producer random Febonachy number is: " + sharedVariable);
            sharedVariable.notify();
            sharedVariable.wait();
        }

    }

    private int[] makeFibonachy() {
        int[] madeFibonachy = new int[fibonachySize];
        madeFibonachy[0] = 0;
        madeFibonachy[1] = 1;
        for (int i = 2; i < madeFibonachy.length; i++) {
            madeFibonachy[i] = madeFibonachy[i - 1] + madeFibonachy[i - 2];
        }
        return madeFibonachy;


    }
}

