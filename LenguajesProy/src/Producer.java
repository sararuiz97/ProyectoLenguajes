
import java.util.Random;
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
public class Producer extends Thread{
    private Buffer buffer;
    private int min, max;
    private int sleepTime;
    private boolean running;
    private GUI gui;
    private Random r;
    private int id;
    
    public Producer(int id, Buffer buff, int min, int max, int sleepTime, GUI gui) {
        this.buffer = buff;
        this.min = min;
        this.max = max;
        this.sleepTime = sleepTime;
        this.running = true;
        this.gui = gui;
        this.r = new Random();
        this.id = id;
    }
    
    @Override
    public void run() {
        System.out.println("Running producer.......");
        
        while(running) {
            
            //create operation
            String[] op = newOperation();
            //send operation to buffer produce()
            this.buffer.produce(op);
            //update table to do operations gui methods
            
            try {
                Thread.sleep(this.sleepTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String[] newOperation(){
        String[] op = new String[4];
        String operands = "+-*/";
        op[3] = "" + this.id;
        op[0] = "" + operands.charAt(this.r.nextInt(4));
        
        int a = this.r.nextInt(this.max - this.min) + this.min;
        op[1] = "" + a;
        int b = this.r.nextInt(this.max - this.min) + this.min;
        op[2] = "" + b;
        System.out.println("New Op " + op[0] + " " + op[1] + " " + op[2]);
        return op;
    }
}
