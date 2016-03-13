
import java.util.*;

/**
 * 
 */
public abstract class System {

    /**
     * Default constructor
     */
    public System() {
    }

    /**
     * 
     */
    public DataBaseHelper database;

    /**
     * 
     */
    public Server server;









    /**
     * @return
     */
    public void setUpDatabase() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void runServer() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void reset() {
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