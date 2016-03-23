
import java.util.*;
/**
 * 
 */
public class Payment {
    
   private String currency;
    private int type;
    private double weight;
    
    /**
     * Default constructor
     */
    public Payment() {}
    
    public Payment() {
        this.currency;
        this.type;
        this.weight;
    }
    
    public void convert(double weight) {
       int aValue;
       if(this.type = 1){
           switch(aValue){
               case 1: this.currency = "1"; break;
               case 2: this.currency = "2"; break;
               case 3: this.currency = "3"; break;
               case 4: this.currency = "4"; break;
           }
       }
       else if(this.type = 2){
           this.currency = readCard();
       }
    }

    public void readCard(){
        int []array = new int [16];
    }
    /**
     * @return
     */
    public void accept() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void dispenseChange() {
        // TODO implement here
        return null;
    }

    /**
     * @param double 
     * @return
     */
    public void refund(void double) {
        // TODO implement here
        return null;
    }

}
