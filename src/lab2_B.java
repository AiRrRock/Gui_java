import javax.swing.*;

/**
 * Created by Александр on 17.03.2016.
 */
public class lab2_B extends lab2_A implements lab2_I2{
    public lab2_B(JTextArea tp){
       super(tp);
    }
    public void b1(){
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println("Class " + className + " runButton");
    }
    public void i2(){
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println("Class = " + className + "  Method name = " + methodName);
    }
    public void i1(){
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println("Class = " + className + "  Method name = " + methodName + " I'm a creep from interface");
    }
}
