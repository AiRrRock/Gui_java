import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 29.03.2016.
 */
public class lab4_Print {
    private final char[] Consonats = {'b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','z'};
    private final char[] Vowels ={'a','e','i','o','u','y'};
    private JTextArea tp;
    private volatile  List<Character> sharedVariable = new ArrayList<Character>();
    public lab4_Print(JTextArea tp){
        this.tp =tp;
    }
    public void showLab4(int[] args) {
        int numberOfThreads =(args[0])+1;
        int numberOfRuns =(args[1]);

        Thread[] dummy = new Thread[numberOfThreads];
        for (int i  = 0; i < numberOfThreads; i++ ){
            if (i%2 ==0){
                dummy[i] = new Thread(new ConsonantThread(sharedVariable,Consonats,i,numberOfRuns,numberOfThreads-1,tp), Integer.toString(i));
            } else {
                dummy[i] = new Thread(new ConsonantThread(sharedVariable,Vowels,i,numberOfRuns,numberOfThreads-1,tp), Integer.toString(i));
            }

            dummy[i].start();

        }

    }

}
