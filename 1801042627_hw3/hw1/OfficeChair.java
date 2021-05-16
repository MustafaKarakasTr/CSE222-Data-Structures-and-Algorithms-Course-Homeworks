package mustafa.karakas.hw1;
/**
 * It represents a special product
 */
public class OfficeChair extends Product{
    /**
     * It constructs and initializes the product
     * @param modelNum model of product
     * @param colorNum color code of product
     * @throws TheProductDoesNotExistException if the model or color is not available for the product 
     */
    public OfficeChair(int modelNum,int colorNum) 
                        throws TheProductDoesNotExistException{
        super(modelNum,colorNum,7,5);
        
    }
    
    public String toString(){
        return "Office Chair "+super.toString();
    }
    /*public Object clone() throws CloneNotSupportedException{
        OfficeChair temp = new OfficeChair(model,color);
        return temp;
    }*/
}
