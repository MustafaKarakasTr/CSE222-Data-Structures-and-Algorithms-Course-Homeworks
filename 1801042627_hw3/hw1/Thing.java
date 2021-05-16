package mustafa.karakas.hw1;
/**
 * the Thing has model and color 
 */
public interface Thing {
    /**
     *
     * @return model
     */
    int getModel();
    /**
     * 
     * @return color
     */
    int getColor();
    /**
     * cheks if the model and color is available for the thing
     * @param model
     * @param color
     * @return if the product available returns true else false
     */
    boolean isValid(int model,int color);
    
    
}
