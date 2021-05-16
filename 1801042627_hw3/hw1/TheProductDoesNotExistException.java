package mustafa.karakas.hw1;
/**
 * the color or model of the product is not available
 */
public class TheProductDoesNotExistException extends Exception{
    /**
     * shows that product is not available
     */
    TheProductDoesNotExistException(){
        super("We do not have the product you wanted to order");
    }
}
