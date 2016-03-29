
import java.util.*;
public class Slot extends Systems {
    
    private Product product;
    private int location;
    private double weight;
    private double temperature;
    private boolean used;
    /**
     * Default constructor
     */
    public Slot() {
    	used = false;
    	setUsed(used);
    }
    
    public void setUsed(boolean used){
    	this.used=used;
    }
    
	public boolean getUsed(){
		return this.used;
	}
	
    public int getLocation() {
       return location;
    }

    public double getTemperature() {
       return temperature;
    }

    public double getWeight() {
       return weight;
    }
    
    public void setLocation(int loc) {
        this.location = loc;
    }

    public void setTemperature(double temp) {
        this.temperature = temp;
    }

    public void setWeight(double w){
    	this.weight = w;
    }
    
    public void setProduct() {
        this.product = new Product();  	
    	
    }
    
    public void getProduct(Product anItem) {
        this.product = anItem;  	
    	
    }
    
    public Product getProduct() {
        return product;  	
    	
    }

	@Override
	public void reorder() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validateLogin(String aString) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setLoginDetails(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listenConnect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getConfig() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setConfig(String n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void errorMessage() {
		// TODO Auto-generated method stub
		
	}
}
