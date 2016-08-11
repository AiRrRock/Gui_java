import javax.swing.*;

/**
 * Created by Александр on 17.03.2016.
 */
public class lab2_C extends lab2_A implements lab2_I1 {
    public lab2_C(JTextArea tp){
        super(tp);
    }
    public void i1() {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println("Class = " +className + "  Method name = " + methodName +  " I'm a creep from interface");
    }
    public void c1() {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println("Class = " +className + "  Method name = " + methodName);
    }


}

