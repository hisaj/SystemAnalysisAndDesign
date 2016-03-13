
import java.util.*;

/**
 * 
 */
public class VendingMachine extends System {

    /**
     * Default constructor
     */
    public VendingMachine() {
    }

    /**
     * 
     */
    private Slot slots[ ];









    /**
     * @return
     */
    public void startInterface() {
        // TODO implement here
        return null;
    }

    /**
     * @param Slot
     */
    public void setSlot(void Slot) {
        // TODO implement here
    }

    /**
     * @return
     */
    public Slot getSlot() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public abstract void reorder();

    /**
     * @return
     */
    public abstract void errorMessage();

    /**
     * @param String String 
     * @return
     */
    public abstract boolean validateLogin(void String String);

    /**
     * @param String String 
     * @return
     */
    public abstract void setLoginDetails(void String String);

    /**
     * @return
     */
    public abstract void listenConnect();

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