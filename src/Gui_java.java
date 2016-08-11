import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.io.PrintStream;
import java.util.List;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/*
 * ButtonDemo.java requires the following files:
 *   images/right.gif
 *   images/middle.gif
 *   images/left.gif
 */
public class Gui_java extends JPanel
        implements ActionListener {
    protected JButton runButton, cleanButton;
    private JTextField inputTextField1, inputTextField2;
    private  JRadioButton rButtonLab1, rButtonLab2, rButtonLab3, rButtonLab4;
    private JTextArea outputField;
    private double[] args =new double[2];
    public Gui_java() {

        setLayout(new GridBagLayout());
        inputTextField1 = new JTextField("The field is disabled");
        inputTextField1.setEnabled(false);
        inputTextField2 =new JTextField("The field is disabled");
        inputTextField2.setEnabled(false);
        outputField =new JTextArea();

        ButtonGroup radioGroup =new ButtonGroup();

        rButtonLab1 = new JRadioButton("Лабораторная работа 1");
        rButtonLab2 = new JRadioButton("Лабораторная работа 2");
        rButtonLab3 = new JRadioButton("Лабораторная работа 3");
        rButtonLab4 = new JRadioButton("Лабораторная работа 4");

        radioGroup.add(rButtonLab1);
        radioGroup.add(rButtonLab2);
        radioGroup.add(rButtonLab3);
        radioGroup.add(rButtonLab4);


        rButtonLab1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                inputTextField2.setEnabled(true);
                inputTextField1.setEnabled(true);
                inputTextField1.setText("740         ");
                inputTextField2.setText("5           ");

            }
        });

        rButtonLab2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                inputTextField1.setEnabled(false);
                inputTextField2.setEnabled(false);
                inputTextField1.setText("Field disabled");
                inputTextField2.setText("Field disabled");
            }
        });

        rButtonLab3.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                inputTextField2.setEnabled(true);
                inputTextField1.setEnabled(true);
                inputTextField1.setText("100    ");
                inputTextField2.setText("50     ");

            }
        });


        rButtonLab4.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                inputTextField2.setEnabled(true);
                inputTextField1.setEnabled(true);
                inputTextField1.setText("100   ");
                inputTextField2.setText("20    ");

            }
        });

        
        runButton = new JButton("Run chosen Lab");
        runButton.setActionCommand("run");
        runButton.setEnabled(true);

        cleanButton = new JButton("Clean output");
        cleanButton.setActionCommand("clean");
        cleanButton.setEnabled(true);

        //Listen for actions on buttons 1 and 3.
        runButton.addActionListener(this);
        cleanButton.addActionListener(this);
        //b3.addActionListener(this);

        runButton.setToolTipText("Click this button to run the desired Lab");
        cleanButton.setToolTipText("Click this button to clean output");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx =0;
        constraints.gridy=0;
        constraints.insets = new Insets(10,10,10,10);
        constraints.gridx =0;
        constraints.gridy=1;
        add(rButtonLab1,constraints);
        constraints.gridx=1;
        add(inputTextField1,constraints);
        constraints.gridy=2;
        add(inputTextField2,constraints);
        constraints.gridx=0;
        constraints.gridy =2;
        add(rButtonLab2,constraints);
        constraints.gridy =3;
        add(rButtonLab3,constraints);
        constraints.gridx=1;
        add(runButton,constraints);
        constraints.gridy=4;
        add(cleanButton,constraints);
        constraints.gridy =4;
        constraints.gridx =0;
        add(rButtonLab4,constraints);
        constraints.gridy =5;
        constraints.gridwidth = 20;
        constraints.fill =GridBagConstraints.BOTH;
        constraints.weightx =1.0;
        constraints.weighty =1.0;

        add(new JScrollPane(outputField),constraints);

    }

    public void actionPerformed(ActionEvent e) {
        if ("run".equals(e.getActionCommand())) {
            if(rButtonLab3.isSelected()) {
                args[0] = Double.valueOf(inputTextField1.getText());
                args[1] = Double.valueOf(inputTextField2.getText());
                List<Integer> taskQueue = new ArrayList<Integer>();
                int fibonachySize = (int) args[1];
                Thread tProducer = new Thread(new Producer(taskQueue, fibonachySize, (int) (args[0]), outputField), "Producer");
                Thread tConsumer = new Thread(new Consumer(taskQueue, fibonachySize, (int) (args[0]), outputField), "Consumer");
                tProducer.start();
                tConsumer.start();
            } else if (rButtonLab1.isSelected()){
                PrintStream printStream=new PrintStream(new CustomOutputStream(outputField));
                System.setOut(printStream);
                args[0] = Double.valueOf(inputTextField1.getText());
                args[1] = Double.valueOf(inputTextField2.getText());
                if (args.length == 2) {
                    double initAmount = args[0];
                    double percent = args[1] / 100;
                    double endAmount = 2.0 * initAmount;
                    int month = 0;
                    while (initAmount < endAmount) {
                        initAmount *= (1 + percent / 12);
                        month++;
                    }
                    int years = month / 12;
                    System.out.println("years = " + years);

                } else {
                    System.out.println("It's dead!");
                }
            }else if(rButtonLab2.isSelected()){
                Lab2_Print lab2=new Lab2_Print(outputField);
                lab2.showLab2();
            }else if(rButtonLab4.isSelected()){
                int[] arg =new int[]{100,20};
                args[0] = Double.valueOf(inputTextField1.getText());
                args[1] = Double.valueOf(inputTextField2.getText());
                lab4_Print lab4=new lab4_Print(outputField);
                lab4.showLab4(arg);}
            // outputField.setText(lab1.showLab1(args));

        } else if ("clean".equals(e.getActionCommand())){
            try{
                outputField.getDocument().remove(0, outputField.getDocument().getLength());
            }catch(BadLocationException ex){
                ex.printStackTrace();
            }
        }
    }


    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Gui_java.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("Java GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content p
        // ane.
        Gui_java newContentPane = new Gui_java();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        //JTextArea textArea= new JTextArea();
        //PrintStream printStream=new PrintStream(new CustomOutputStream(textArea));
        //System.setOut(printStream);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}