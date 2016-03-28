import java.util.*;

public class VendingMachine  extends Systems { 
    
    private Slot slots[];
    /**
     * Default constructor
     */
    public VendingMachine() {}
    
    public VendingMachine(Slot [] slots) {
         slots = new Slot[20];
         //Slot tmp; 
         for(int i=0;i<slots.length;i++){
        	//tmp = new Slot();
        	 slots[i]= null; 
        	 slots[i].setUsed(false);	 
         }
    }
   
    public void startInterface() {
       boolean begin = false;
       if(!begin){
            listenConnect();
       }
    }

    public void setSlot(int slotIndex, double productWeight, double slotTemperature, Product aProduct){
    	Product prod;
    	int i;
		for(i= 0; i < slots.length; i++ ){
			
			prod = new Product(aProduct.getName(), aProduct.getPrice(), aProduct.getCurrencyType(), aProduct.getWeight());	
			slots[i].setLocation(slotIndex);
			slots[i].setWeight(productWeight);
			slots[i].setTemperature(slotTemperature);
			slots[i].getProduct(prod);
			slots[i].setUsed(true);
		}	
		slots = getSlot();	
	}
    
    public Slot [] getSlot() {
    	
       	for(Slot s: slots){
       		s.getProduct();
    	}
    	  return  slots;
    }

	@Override
	public void reorder() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void errorMessage() {
		System.out.print("This activity was not successful");
		
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
		boolean yesConnect = true;
		if(yesConnect)
			this.equals(true);
		
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
}
