package mustafa.karakas.hw1;
/**
 * It represents a product which implements Thing interface
 */
public abstract class Product implements Thing {
    protected int model;
    protected int color;
    protected final int numOfModels;
    protected final int numOfColors;

    /**
     * Checks if the product is available
     * @param modelNum model number
     * @param colorNum color code  
     */
    public boolean isValid(int modelNum,int colorNum){
        return (modelNum<=numOfModels && modelNum>0 
                && colorNum<=numOfColors && colorNum>0 );
    }
    /**
     * constructs a product
     * @param modelNum model number
     * @param colorNum color code  
     * @param MaxNumberOfModels max possible model number
     * @param MaxNumberOfColorsmax possible color number
     * @throws TheProductDoesNotExistException if the model and color is not valid
     */
    public Product(int modelNum,int colorNum,int MaxNumberOfModels,int MaxNumberOfColors) throws TheProductDoesNotExistException{
        numOfModels = MaxNumberOfModels;
        numOfColors = MaxNumberOfColors;
        if(isValid(modelNum,colorNum)){
            model = modelNum;
            color = colorNum;
        }else{
            throw new TheProductDoesNotExistException();
        }
        
    }
    /**
     * @return color of product
     */
    public int getColor(){ return color; }
    /**
     * @return model of product
     */
    public int getModel(){ return model; }
    
    
    /**
     * @return max possible color number
     */
    public int getMaxNumberOfColors(){ return numOfColors; }
    
    /**
     * @return max possible model number
     */
    public int getMaxNumberOfModels(){ return numOfModels; }
    public String toString(){
        return ("Model :"+Integer.toString(model) + " Color Type :"+Integer.toString(color));
    }
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o.getClass().equals(getClass()))){
            return false;
        }
        Product product = (Product) o;
        // field comparison
        return (product.model == model && product.color==color);
    }
    
}
