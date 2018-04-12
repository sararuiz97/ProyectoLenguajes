
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sara Ruiz
 * @author jumarogu
 */
public class Consumer extends Thread{
    
    private Buffer buffer;
    private boolean running;
    private int sleepTime;
    private GUI gui;
    private int id;
    
    public Consumer(int id, Buffer buff, int sleepTime, GUI gui) {
        this.buffer = buff;
        this.running = true;
        this.sleepTime = sleepTime;
        this.gui = gui;
        this.id = id;
    }
    
    @Override
    public void run() {
        System.out.println("Running consumer .....");
        
        while(running) {
            
            //get operation from buffer consume()
            String[] op = this.buffer.consume();
            int value = this.evaluate(op);
            System.out.println("Consumed op result " + value);
            //update table consumed
            this.gui.addRowDone(this.id, op, value);
            
            try {
                Thread.sleep(this.sleepTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public int evaluate(String[] op) {
        
        String operation = op[0];
        int a = Integer.parseInt(op[1]);
        int b = Integer.parseInt(op[2]);
        int value = 0;
        
        System.out.println("evaluating values "+ operation + " " + a + " " + b);
        if(operation.equals("+")){
            value = a + b;
        } else if(operation.equals("-")){
            value = a - b;
        } else if(operation.equals("*")){
            value = a * b;
        } else if(operation.equals("/")){
            value = a / b;
        }
        return value;
    }
}
