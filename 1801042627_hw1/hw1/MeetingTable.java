package mustafa.karakas.hw1;

/**
 * It represents a special product
 */
public class MeetingTable extends Product{
    /**
     * It constructs and initializes the product
     * @param modelNum model of product
     * @param colorNum color code of product
     * @throws TheProductDoesNotExistException if the model or color is not available for the product 
     */
    public MeetingTable(int modelNum,int colorNum) 
                        throws TheProductDoesNotExistException{
        super(modelNum,colorNum,10,4);
        
    }
    
    public String toString(){
        return "Meeting Table "+super.toString();
    }
    /*public Object clone() throws CloneNotSupportedException{
        MeetingTable temp = new MeetingTable(model,color);
        return temp;
    }*/
}
