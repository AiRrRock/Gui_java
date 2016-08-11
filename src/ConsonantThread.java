import javax.swing.*;
import java.io.PrintStream;
import java.util.List;

/**
 * Created by Александр on 23.03.2016.
 */
public class ConsonantThread implements Runnable{
    private List<Character> sharedVariable;
    private char[] Consonants;
    private int number;
    private int numberOfRuns;
    private int last;
    private JTextArea tp;  
    public ConsonantThread(List<Character> sharedVariable, char[] Consonants,int number,int numberOfRuns, int last,JTextArea tp )
    {
        this.sharedVariable =sharedVariable;
        this.Consonants = Consonants;
        this.number = number;
        this.numberOfRuns=numberOfRuns;
        this.last =last;
        this.tp =tp;
        PrintStream printStream=new PrintStream(new CustomOutputStream(tp));
        System.setOut(printStream);

    }

    @Override
    public void run() {
        for (int i = 1; i < numberOfRuns; i++) {
            do {
                //if (sharedVariable.size() == number){
                try {
                    printLetter();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }while (sharedVariable.size()!=last);

            if(number == last) {
                try {
                    cleanSharedVar();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }//}
            //  System.out.println();
        }
        //  return;
    }


    private void printLetter() throws InterruptedException {
        int randomIndexCons = (int) (Math.random()*Consonants.length);
        synchronized (sharedVariable){
            if(sharedVariable.size()==number){
                sharedVariable.add(Consonants[randomIndexCons]);
                System.out.print(Consonants[randomIndexCons]);
                sharedVariable.notify();
                sharedVariable.wait();
            }else{
                sharedVariable.notify();
                sharedVariable.wait();}


        }}



    private void cleanSharedVar() throws InterruptedException {
        synchronized (sharedVariable) {
            sharedVariable.clear();
            System.out.println(" ");
            sharedVariable.notifyAll();
            sharedVariable.wait();

        }
    }
}
