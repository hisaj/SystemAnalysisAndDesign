
import java.util.*;

public class Slot extends System {
    
    private Product product;
    private String location;
    private double weight;
    private double temperature;

    /**
     * Default constructor
     */
    public Slot() {
    }
    
     public Slot(Product myProduct, String slotLocation, double rowSlotWeight, double slotTemperature ) {
         product = myProduct;
         location = slotLocation;
         weight = rowSlotWeight;
         temperature = slotTemperature;
    }
    
    /**
     * @return
     */
    public String getLocation() {
       return location;
    }

    public double getTemperature() {
       return temperature;
    }

    public double getWeight() {
       return weight;
    }
    
    public void setLocation(String loc) {
        this.location = loc;
    }

    public void setTemperature(Product aProduct) {
        this.product = aProduct;
    }

    public void setWeight(void Product) {
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
