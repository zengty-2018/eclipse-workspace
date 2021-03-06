import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This is a simple calculator.
 * @author z18342007
 *
 */
public class Calculator {
	public static final int WIDTH = 100, LENGTH=30, BUTTON_NUM=5;
	public static final int GLOBAL_WIDTH = 550, GLOBAL_LENGTH = 120;
	private JTextField firstNum, secondNum;
    private JLabel op, equal, result;
    private JButton[] ops = new JButton[BUTTON_NUM];
    
    /**
	 * The constructed function
	 * call the process to run the calculator
	 */
    private Calculator(){
    	
    	EventQueue.invokeLater(new Runnable(){
            public void run(){
            	try{
            		setCalculatorGUI();
            	}catch(Exception e) {
            		e.printStackTrace();
            	}
            }
        });
    }
	
    /**
     * set the GUI of calculator
     */
	private void setCalculatorGUI(){
		String[] label = new String[] {"+", "-", "*", "/", "OK"};
        JFrame frame = new JFrame("Easy Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container cp = frame.getContentPane();
        cp.setLayout(new FlowLayout());

        firstNum = new JTextField("1834");
        secondNum = new JTextField("2007");
        op = new JLabel(" ");
        equal = new JLabel("=");
        result = new JLabel(" ");

        firstNum.setPreferredSize(new Dimension(WIDTH, LENGTH));
        firstNum.setHorizontalAlignment(JTextField.CENTER);
        op.setPreferredSize(new Dimension(WIDTH, LENGTH));
        op.setHorizontalAlignment(JLabel.CENTER);
        secondNum.setPreferredSize(new Dimension(WIDTH, LENGTH));
        secondNum.setHorizontalAlignment(JTextField.CENTER);
        equal.setPreferredSize(new Dimension(WIDTH, LENGTH));
        equal.setHorizontalAlignment(JLabel.CENTER);
        result.setPreferredSize(new Dimension(WIDTH, LENGTH));
        result.setHorizontalAlignment(JLabel.CENTER);
        cp.add(firstNum);
        cp.add(op);
        cp.add(secondNum);
        cp.add(equal);
        cp.add(result);

        ActionListener setOp = new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		op.setText(((JButton)e.getSource()).getText());
        	}
        };
        
        for(int i = 0; i < BUTTON_NUM; i++) {
        	ops[i] = new JButton(label[i]);
        	ops[i].setPreferredSize(new Dimension(WIDTH, LENGTH));
        	cp.add(ops[i]);
        	if(i == BUTTON_NUM-1) {
        		break;
        	}
        	ops[i].addActionListener(setOp);
        } 
        ops[BUTTON_NUM-1].addActionListener(new CalcResult());

        frame.setSize(new Dimension(GLOBAL_WIDTH,GLOBAL_LENGTH));
        frame.setVisible(true);
    }
	
	/**
	 * the class to get the result of calculation.
	 * @author z18342007
	 *
	 */
	class CalcResult implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Double num1, num2, calculateResult;
            num1 = Double.parseDouble(firstNum.getText());
            num2 = Double.parseDouble(secondNum.getText());
            String x = op.getText();
            
            switch(x){
                case "+":
                    calculateResult = num1 + num2;
                    break;
                case "-":
                    calculateResult = num1 - num2;
                    break;
                case "*":
                    calculateResult = num1 * num2;
                    break;
                case "/":
                    calculateResult = num1 / num2;
                    break;
                default:
                    return;
            }
            result.setText(Double.toString(calculateResult));
        }
    }
    
    public static void main(String[] args) {
        Calculator cal = new Calculator();
    }
}