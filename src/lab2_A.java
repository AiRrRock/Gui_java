import javax.swing.*;
import java.io.PrintStream;

/**
 * Created by Александр on 17.03.2016.
 */
public class lab2_A {
    private JTextArea tp;
    public String className =this.getClass().getName();

    public lab2_A(JTextArea tp){ this.tp =tp;
        PrintStream printStream=new PrintStream(new CustomOutputStream(tp));
        System.setOut(printStream);
        System.out.println(className + " Class A constructor");
    }

    public void i1(boolean flag){
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        if (flag) {
            System.out.println("Class = " + className + "  Method name = " + methodName + " " + flag);
        }else{
            System.out.println("Class = " + className + "  Method name = " + methodName + " " + flag);
        }
    }
    public void a1() {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();

        System.out.println("Class = " +className + "  Method name = " + methodName);
    }
}
