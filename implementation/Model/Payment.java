
public class Payment {
    
    private static final String AccelerometerActivated = null;
	private String currency;
    private int cashType;
    private double weight;
    
    /**
     * Default constructor
     */
   //public Payment() {}
    
    public Payment() {
       // this.currency;
        //this.cashType;
       // this.weight;
    }
    
    public void convert(double weight) {
       int aValue = 0;
       if(this.cashType == 1){
           switch(aValue){
               case 1: this.currency = "1"; break;
               case 2: this.currency = "2"; break;
               case 3: this.currency = "3"; break;
               case 4: this.currency = "4"; break;
           }
       }
       else if(this.cashType == 2){
           this.currency = readCard();
       }
    }

    public String readCard(){
        int []array = new int [16];
        String checkCard = "";
        if(isValidCreditCardNumber(array)){
         checkCard = AccelerometerActivated;
        }
        
        return checkCard;
    }
    
    public boolean isValidCreditCardNumber(int [] creditCard){
		  int timesTotal=0, sum=0, totalDigit;
		 
		  for(int i =0; i< creditCard.length; i=i+2)
		  {
		  timesTotal = creditCard[i]*2;
		  if(timesTotal > 9 )
		     timesTotal = (timesTotal % 10) + 1;
		    else
		     sum +=timesTotal;
		   }
		  for(int j =0; j< creditCard.length; j=j+2)
		  {
		    //int totalSum = creditCard[j];
		    totalDigit =(timesTotal + sum);    
		    if (totalDigit % 10==0)    
		      return true;	   
       }
		return false; 
    } 

    /**
     * @return
     */
    public void accept() {
        
    }

    /**
     * @return
     */
    public void dispenseChange() {
       
    }

    public void refund(double d) {
       
    }
}
