package mustafa.karakas.hw1;

import mustafa.karakas.hw1.ThereIsNoBranchEmployeeException;

/**
 * Represents the customer which can buy products
 */
public class Customer extends Person{
    private String e_mail;
    private String password;
    private Company company =null;
    private String address = null;
    private String phone = null;

    int customerNumber=-1;
    private vector<Product> previousOrders=new vector<Product>();
    /**
     * Constructs and initializes the customer without a company
     * @param _name name of customer
     * @param _surname  surname of customer
     * @param _e_mail   e mail of customer
     * @param pw    password of customer
     */
    public Customer(String _name,String _surname,String _e_mail,String pw){
        super(_name,_surname);
        e_mail = _e_mail;
        password = pw;
    }
    /**
     * registers to the given company
     * @param comp company to be registered
     * @return returns true if the registration is completed
     */
    public boolean Register(BranchEmployee.Authority authority,Company comp){
        if(company != null) return false; // customer is member of another company ( this may lead to problems so I avoid taking this action)
        company = comp;
        int custNum = comp.addCustomer(this);

        if(custNum != -1){ // if customer is already register to this company then addCustomer returns -1
                            // we do not want to lost it's customerNumber 
            customerNumber = custNum;
            return true;

        }
        return false; // customer have already registered to the system
    }
    /**
     * buys product
     * @param p product which is wanted to be bought
     * @return returns true if buying is successful else false
     * @throws ThereIsNoBranchEmployeeException if  there is no employee at the company 
     */
    
     
    /**
     * shows the list of products at the company
     */
    public void seeTheListOfProducts(){
        if(company == null){
            System.out.println("You should register to company before buying something");
            return;
        }
        else{
            int indexOfProduct;
            vector<Branch> branches = company.getBranches();
            for(int branchIndex=0;branchIndex<branches.getUsed();branchIndex++){
                System.out.println("List of products at the " + (branchIndex+1)+" . branch");
                branches.at(branchIndex).getProducts().show();
                
            }
        }
    }
    /**
     * searches for the given product and returns index of branch which has the given product
     * @param p product which is being searched
     * @return index of branch which has the product
     */
    public int inWhichStoreIsProduct(Product p){
        if(company == null){
            System.out.println("You should register to company before searching something");
            return -1;
        }
        else{
            int indexOfProduct;
            vector<Branch> branches = company.getBranches();
            for(int branchIndex=0;branchIndex<branches.getUsed();branchIndex++){
                if((indexOfProduct = branches.at(branchIndex).getProducts().isAvailable(p)) != -1){
                    return branchIndex;
                }
            }
        }
        return -1;
    }
    /**
     * searches for given product
     * @param p product which is being searched
     * @return if the company has the product returns true otherwise returns false
     */
    public boolean searchForProduct(Product p,Company c){ // he or she can search in any company do not have to register
        
            int indexOfProduct;
            vector<Branch> branches = c.getBranches();
            for(int branchIndex=0;branchIndex<branches.getUsed();branchIndex++){
                if((indexOfProduct = branches.at(branchIndex).getProducts().isAvailable(p)) != -1){
                    return true;
                }
            }
        
            return false;
    }
    /**
     * returns customer id
     * @return customer id
     */
    public int getCustomerNumber(){
        return customerNumber;
    }
    /**
     * checks if the Customers are equal
     * @param o other Person
     */
    public boolean equals(Object o) {
        if(! (o instanceof Customer) ) 
            return false;
        Customer person = (Customer) o;
        // field comparison
        return super.equals(o) && ((person.e_mail == e_mail &&person.password == password)||(person.e_mail.equals(e_mail) && person.password.equals(password)));
      
    }
    /**
     * when customer buys something the product is added to the previous orders
     * @param authority it makes it mandatory that only BranchEmployees can addOrder
     * @param p product to be added
     */
    public void addOrder(BranchEmployee.Authority authority,Product p){ // only branchemployee can add product to previous orders of customer
        if(authority != null && p != null){
            previousOrders.push_back(p);
        }
    }
    /**
     * return previous orders of customer
     * @param authority it makes it mandatory that only BranchEmployees can get previous orders
     * @return previous orders
     */
    public vector<Product> getPreviousOrders(BranchEmployee.Authority authority){
        if(authority != null){
            return previousOrders;
        }
        return null;
        
    }
    /**
     * shows previous orders of customer
     */
    public void viewPreviousOrders(){
        if(previousOrders.getUsed() == 0){
            System.out.println("There is no previous order of "+this);
        }
        else{
            System.out.println(this+"'s previous orders:");
            previousOrders.show();
        }
    }
    private boolean Register(Company comp,String Address,String phone ){
        if(comp != null && comp == company) return true;
        if(company != null) return false; // customer is member of another company ( this may lead to problems so I avoid taking this action)
        company = comp;
        int custNum = comp.addCustomer(this);

        if(custNum != -1){ // if customer is already register to this company then addCustomer returns -1
                            // we do not want to lost it's customerNumber 
            customerNumber = custNum;
            return true;
        }
        return false; // customer have already registered to the system
    }
    /**
     * to be able to shop online customer should enter address and phone number
     * @param p produt to be bought
     * @param Address address of customer
     * @param phone phone number of customer
     * @return if the product has been bought then returns true otherwise false
     */
    public boolean shopOnline(Product p,String Address,String phone,Company comp) 
                                throws ThereIsNoBranchEmployeeException{
        if(Address == null  || phone == null || p == null || comp == null){
            return false;
        }

        this.address = Address;
        this.phone = phone; 
        BranchEmployee backupEmployee = null;
        if(Register(comp,Address,phone)){
            
            for(int i=0;i<company.getBranches().getUsed();i++){
                 try{   
                    BranchEmployee emp = company.getBranches().at(i).getFirstBranchEmployee();
                    if(emp != null ){

                        backupEmployee = emp;
                        if( emp.isProductAvailable(p)){
                            try{
                                emp.makeSale(this,p);
                            }catch(BranchEmployeeDoesNotHaveAuthority e){
                    
                            }
                            return true;
                        }
                        
                    }
                }catch(ThereIsNoBranchEmployeeException e){
                }catch(BranchEmployeeDoesNotHaveAuthority e){
                }
            }
                
            
            if(backupEmployee!=null){
                try{
                backupEmployee.makeSale(this, p);
            }catch(BranchEmployeeDoesNotHaveAuthority e){
                    
            }
            }else{
                throw new ThereIsNoBranchEmployeeException();
            }
            
        }

        return false;
         
       
    }
}
