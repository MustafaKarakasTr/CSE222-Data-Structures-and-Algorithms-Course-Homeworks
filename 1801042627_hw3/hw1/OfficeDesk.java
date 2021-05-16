package mustafa.karakas.hw1;
/**
 * It represents a special product
 */
public class OfficeDesk extends Product{
    /**
     * It constructs and initializes the product
     * @param modelNum model of product
     * @param colorNum color code of product
     * @throws TheProductDoesNotExistException if the model or color is not available for the product 
     */
    public OfficeDesk(int modelNum,int colorNum) 
                        throws TheProductDoesNotExistException{
        super(modelNum,colorNum,5,4);
        
    }
    
    public String toString(){
        return "Office Desk "+super.toString();
    }
    /*
    public Object clone() throws CloneNotSupportedException{
        OfficeDesk temp = new OfficeDesk(model,color);
        return temp;
    }
    */
}
