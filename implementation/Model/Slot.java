
import java.util.*;

/**
 * 
 */
public class Slot extends System {

    /**
     * Default constructor
     */
    public Slot() {
    }

    /**
     * 
     */
    private Product product;

    /**
     * 
     */
    private String location;

    /**
     * 
     */
    private double weight;

    /**
     * 
     */
    private double temperature;








    /**
     * @return
     */
    public String getLocation() {
        // TODO implement here
        return "";
    }

    /**
     * @return
     */
    public double getTemperature() {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @return
     */
    public double getWeight() {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @param String 
     * @return
     */
    public void setLocation(void String) {
        // TODO implement here
        return null;
    }

    /**
     * @param Product 
     * @return
     */
    public void setTemperature(void Product) {
        // TODO implement here
        return null;
    }

    /**
     * @param Product 
     * @return
     */
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