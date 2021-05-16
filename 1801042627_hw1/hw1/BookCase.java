package mustafa.karakas.hw1;
/**
 * It represents a special product
 */
public class BookCase extends Product{
    /**
     * It constructs and initializes the product
     * @param modelNum model of product
     * @param colorNum color code of product
     * @throws TheProductDoesNotExistException if the model or color is not available for the product 
     */
    public BookCase(int modelNum,int colorNum) 
                        throws TheProductDoesNotExistException{
        super(modelNum,colorNum,12,1);
        
    }
    public String toString(){
        return "Book Cases"+super.toString();
    }
}
