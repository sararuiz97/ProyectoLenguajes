
import java.util.LinkedList;
import java.util.Queue;
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
public class Buffer {
    
    private Queue<String[]> bufferOp;
    private int buffSize;
    private GUI gui;
    
    public Buffer(int buffSize, GUI gui) {
        this.buffSize = buffSize;
        this.gui = gui;
        this.bufferOp = new LinkedList<String[]>();
        
    }
    
    synchronized String[] consume() {
        while(true) {
            if(this.bufferOp.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                break;
            }
        }
        
        // get next operation from queue
        String[] op = (String[])this.bufferOp.remove();
        System.out.println("Consuming " + op.toString());
        // update progressbar
        // when there is only 1 producer the row will be removed so fast that there will be no rows printed on the to-do table
        this.gui.removeRowToDo();
        this.gui.updateProgressBar((this.bufferOp.size()/this.buffSize)*100);
        notifyAll();
        
        return op;
    }
    
    synchronized void produce(String[] op) {
        while(true) {
            if(this.buffSize == this.bufferOp.size()) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                break;
            }
        }
        
        //save operation on buffer
        System.out.println(" TAMAÑO BUFERRR   " + this.bufferOp.size());
        this.bufferOp.add(op);
        System.out.println("Produced " + op.toString());
        // display new task on table
        this.gui.addRowToDo(op);
        // update progress bar
        this.gui.updateProgressBar((this.bufferOp.size()/this.buffSize)*100);
        //notify
        notifyAll();
    }
}
