
import java.util.*;

/**
 * 
 */
public class VendingMachine extends System {
    
    private Slot slots[ ];
    /**
     * Default constructor
     */
    public VendingMachine() {}
    
    public VendingMachine() {
         
    }
    /**
     * @return
     */
    public void startInterface() {
       boolean begin = false;
       if(!begin){
           listenConnect();
       }
    }

    /**
     * @param Slot
     */
    public void setSlot(Slot mySlot) {
       this.slots = mySlot;
    }

    /**
     * @return
     */
    public Slot getSlot() {
       return this.slots;
    }

    /**
     * @return
     */
    public abstract void reorder();

    /**
     * @return
     */
    public abstract void errorMessage();
         System.out.print("This activity was not successful");
    /**
     * @param String String 
     * @return
     */
    public abstract boolean validateLogin( String n){
        
    }
        
    /**
     * @param String String 
     * @return
     */
    public abstract void setLoginDetails(void String String);

    /**
     * @return
     */
    public abstract void listenConnect();
    {
       boolean yesConnect = true;
    }
    
    /**
     * @return
     */
    public abstract double getConfig();
     
    /**
     * @param String 
     * @return
     */
    public abstract void setConfig(void String);

}
