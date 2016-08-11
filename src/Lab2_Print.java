import javax.swing.*;

/**
 * Created by Александр on 29.03.2016.
 */
public class Lab2_Print {
    private JTextArea tp;
    Lab2_Print(JTextArea tp){ this.tp = tp;

    }
    public void showLab2() {
        printLab2A();
        printLab2B();
        printLab2C();
    }


    public void printLab2A() {
        lab2_A A = new lab2_A(tp);
        A.a1();
        A.i1(true);
        A.i1(false);
        lab2_A B = new lab2_A(tp);

    }

    public  void printLab2B() {
        lab2_B B = new lab2_B(tp);
        B.b1();
        B.i1(true);
        B.i1();
        B.b1();
        B.a1();
        B.i2();
    }

    public void printLab2C() {
        lab2_C C = new lab2_C(tp);
        C.a1();
        C.i1(true);
        C.i1();
        C.c1();
    }
}
