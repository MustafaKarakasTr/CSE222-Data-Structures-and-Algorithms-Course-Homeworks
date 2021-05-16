package mustafa.karakas.hw1;


/**
 * represents a Branch at Company
 */
public class Branch {
    private vector<Product> products;
    private vector<BranchEmployee> branchEmployees;

    private Product[] distinctProducts = new Product[5];
    private Company company;
    /*public Branch(vector<Product> products , vector<BranchEmployee> branchEmployees){
        this.products = new vector<Product>(products);  
        this.branchEmployees = new vector<BranchEmployee>(branchEmployees);  
        
    }*/
    /*public Branch(vector<BranchEmployee> branchEmployees){ // creates a default branches with defined employees
        distinctProducts = createDistinctProducts();
        this.products = createProductsForOneBranch(distinctProducts);
        this.branchEmployees = new vector<BranchEmployee>(branchEmployees);
    }*/
    /**
     * constructs and initializes empty Branch
     */
    public Branch(Company company){
        products = new vector<Product>();
        branchEmployees = new vector<BranchEmployee>();
        this.company = company;    
   }
   public Company getCompany(BranchEmployee.Authority authority){
      return company;
   }
   public int getIndex(){
      return company.getBranches().isAvailable(this);
   }
    /**
     * adds all possible products to the branch 
     * @param authority do not let other classes to call this method other than Administrator
     */
    public void addAllProducts(Administrators.Authority authority)
    {
       if(authority != null){
         distinctProducts = createDistinctProducts();
         for(int i=0;i<distinctProducts.length;i++){
            for(int j = 1;j<=distinctProducts[i].getMaxNumberOfModels();j++){
               for(int x = 1 ; x<= distinctProducts[i].getMaxNumberOfColors();x++){
                  Product temp = null;
                  try{
                     switch(i){
                        case 0:
                           temp = new OfficeChair(j,x);
                           break;
                        case 1:
                           temp = new OfficeDesk(j,x);
                           break;
                        case 2:
                           temp = new MeetingTable(j,x);
                           break;
                        case 3:
                           temp = new BookCase(j,x);
                           break;
                        case 4:
                           temp = new OfficeCabinet(j,x);
                           break;
                     }
                     products.push_back(temp);
                     
                  }catch(TheProductDoesNotExistException e){
                     System.out.println(e);
                  }
                  
                     
   
               }
                  
            }
         }
       }
    }
    /*
   
    private vector<Product> createProductsForOneBranch(Product[] distinctProducts){ // as default adds all products to the vector
        vector<Product> products = new vector<Product>();
        for(int i=0;i<distinctProducts.length;i++){
           for(int j = 1;j<=distinctProducts[i].getMaxNumberOfModels();j++){
              for(int x = 1 ; x<= distinctProducts[i].getMaxNumberOfColors();x++){
                 Product temp = null;
                 try{
                    switch(i){
                       case 0:
                          temp = new OfficeChair(j,x);
                          break;
                       case 1:
                          temp = new OfficeDesk(j,x);
                          break;
                       case 2:
                          temp = new MeetingTable(j,x);
                          break;
                       case 3:
                          temp = new BookCase(j,x);
                          break;
                       case 4:
                          temp = new OfficeCabinet(j,x);
                          break;
                    }
                    products.push_back(temp);
                    
                 }catch(TheProductDoesNotExistException e){
                    System.out.println(e);
                 }
                 
                    
  
              }
                 
           }
        }
        //products.show();
        return products;
     }*/
     /**
      * creates every available product with the model 1 and color code 1
      * it is called by addAllProducts
      * @return product array which has different products
      */
     private Product[] createDistinctProducts(){
        // all of them are first model and have first color available
        try{
           distinctProducts[0] = new OfficeChair(1,1);
           distinctProducts[1] = new OfficeDesk(1,1);
           distinctProducts[2] = new MeetingTable(1,1);
           distinctProducts[3] = new BookCase(1,1);
           distinctProducts[4] = new OfficeCabinet(1,1);
        }catch(TheProductDoesNotExistException e){
           System.out.println(e);
        }
        
        return distinctProducts;
     }
     
    /*
     public static vector<BranchEmployee> createEmployees(int numberOfEmployees,int indexOfBranch){ 
         
        //    indexOfBranch will be added to employee names to be able to understand in which branch it works 
                                                                                     
        vector<BranchEmployee> emps = new vector<BranchEmployee>();
         for(int j = 0;j < numberOfEmployees ; j++){ 
            emps.push_back(new BranchEmployee("EmployeeName"+Integer.toString(indexOfBranch+1)+" "+Integer.toString(j+1),"EmployeeSurname"+Integer.toString(indexOfBranch+1)+" "+Integer.toString(j+1)));
         }
        // emps.show();
         return emps;
     }
     */
     /**
      * shows all products at the branch
      */
     public void showProducts(){
         products.show();
     }
     /**
      * shows all employees at the branch
      */
     public void showEmployees(){
         branchEmployees.show();
     }
     /**
      * return products at the branch
      * @return products at the branch
      */
     public vector<Product>getProducts(){
         return products;
     }
     /**
      * return employees at the branch
      * @return employees at the branch
      */
     public vector<BranchEmployee> getEmployees(){
         return branchEmployees;
     }
     /**
      * calls first branch employee to the special job
      * @return the first employee available
      * @throws ThereIsNoBranchEmployeeException if the branch does not have any employee 
      */
     public BranchEmployee getFirstBranchEmployee()throws ThereIsNoBranchEmployeeException{
         if(branchEmployees.getUsed() == 0){
            throw new ThereIsNoBranchEmployeeException();
         }
         return branchEmployees.at(0);
     }

}
