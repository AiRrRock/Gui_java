import javax.swing.*;
import java.io.PrintStream;
import java.util.List;

/**
 * Created by Александр on 23.03.2016.
 */
public class VowelThread implements Runnable {
    private List<Character> sharedVariable;
    private char[] Vowels;
    private int number;
    private int numberOfRuns;
    private int last;
    private JTextArea tp;
    public VowelThread(List<Character> sharedVariable,  char[] Vowels, int number, int numberOfRuns, int last, JTextArea tp) {
        this.sharedVariable = sharedVariable;
        this.Vowels = Vowels;
        this.number = number;
        this.numberOfRuns = numberOfRuns;
        this.last = last;
        this.tp =tp;
        PrintStream printStream=new PrintStream(new CustomOutputStream(tp));
        System.setOut(printStream);
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfRuns; i++) {
            do{
                try {
                    printLetter();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }while(sharedVariable.size()<last);
            System.out.print(" ");
            sharedVariable.clear();
        }
        return;
    }


    private void printLetter() throws InterruptedException {
        int randomIndexVow = (int) (Math.random() * Vowels.length);
        synchronized (sharedVariable) {
            if (sharedVariable.size() == number) {
                sharedVariable.add(Vowels[randomIndexVow]);
                System.out.print(Vowels[randomIndexVow]);
                sharedVariable.notifyAll();
                sharedVariable.wait();
            } else {
                sharedVariable.notifyAll();
                sharedVariable.wait();
            }
        }
    }
}

