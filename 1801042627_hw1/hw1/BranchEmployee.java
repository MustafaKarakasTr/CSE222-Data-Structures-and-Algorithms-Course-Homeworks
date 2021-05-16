package mustafa.karakas.hw1;

import mustafa.karakas.hw1.Administrators;
import mustafa.karakas.hw1.Branch;
import mustafa.karakas.hw1.BranchEmployeeDoesNotHaveAuthority;
import mustafa.karakas.hw1.Customer;
import mustafa.karakas.hw1.Product;
/**
 * rRepresents a employee which works for branch
 */
public class BranchEmployee extends Person{
    private Branch branch;
    /**
     * Constructs and initializes the employee which do not have any branch to work at
     * @param _name name of employee
     * @param _surname surname of employee
     */
    public BranchEmployee(String _name,String _surname){
        super(_name, _surname);
    }
    
    /**
     * Authority makes possible that only branchEmployee can call some methods which belong to other classes.
     * Because of that Authority's constructer is private , only BranchEmployee can call it 
     */
    public static final class Authority { 
        /**
         * Authority has no data field or method ,It makes BranchEmployee access to methods which can be called only by BranchEmployee   
        */ 
        private Authority() {} 
    } 
    private static final Authority authority = new Authority();
    /**
     * If administrator hires the employee this method is called so that employee knows which branch he is working at
     * @param I_am_an_Administrator make impossible that any class other than Administrator can not hire employee 
     * @param branch the branch which employee will be working at
     * @throws NullPointerException if administrator did not call the method throws it
     */
    public void setBranch(Administrators.Authority I_am_an_Administrator ,Branch branch)throws NullPointerException
    // only admins can access to Authority so only admins can add employee to the branches
    {
        if(I_am_an_Administrator != null){
            this.branch =branch; 
        }
        else throw new NullPointerException();
    }
    /**
     * shows product of branches
     */
    public void showBranchProducts()throws BranchEmployeeDoesNotHaveAuthority{
        if(branch == null){
            throw new BranchEmployeeDoesNotHaveAuthority();
        }
        branch.showProducts();
    }
    /**
     * adds product to the branch
     * @param p the product to be added
     * @throws BranchEmployeeDoesNotHaveAuthority if the employee does not work for any branch
     */
    public void addProduct(Product p)throws BranchEmployeeDoesNotHaveAuthority{
        if(branch == null){
            throw new BranchEmployeeDoesNotHaveAuthority();
        }
        branch.getProducts().push_back(p);
    }
    /**
     * removes product from the branch
     * @param p product to be removed
     * @return  if the product was removed returns true, else false
     * @throws BranchEmployeeDoesNotHaveAuthority if the employee does not work for any branch
     */
    public boolean removeProduct(Product p) throws BranchEmployeeDoesNotHaveAuthority{
        if(branch != null){
            return branch.getProducts().remove(p) != null;
        }
        throw new BranchEmployeeDoesNotHaveAuthority();
    }
    /**
     * remove product at the given index
     * @param indexOfProduct index of product at the branch
     * @return removed product if it does not removed any product than returns false
     * @throws BranchEmployeeDoesNotHaveAuthority if the employee does not work for any branch
     */
    public Product removeProduct(int indexOfProduct)throws BranchEmployeeDoesNotHaveAuthority,IndexOutOfBoundsException{
        if(branch == null){
            throw new BranchEmployeeDoesNotHaveAuthority();
        }
        if(branch.getProducts().getUsed()<indexOfProduct){
            throw new IndexOutOfBoundsException();
        }
        Product pro = branch.getProducts().at(indexOfProduct);
        branch.getProducts().delete(indexOfProduct);
        return pro;
    }
    /**
     * Searches for product
     * @param p the product which is being searched
     * @return if product is found at branch returns true otherwise false
     * @throws BranchEmployeeDoesNotHaveAuthority if the employee does not work for any branch
     */
    public boolean isProductAvailable(Product p)throws BranchEmployeeDoesNotHaveAuthority{
        if(branch == null){
            throw new BranchEmployeeDoesNotHaveAuthority();
        }
        return branch.getProducts().isAvailable(p) != -1;
    }
    /**
     * returns the branch that employee is working
     * @return the branch that employee is working
     */
    public Branch getBranch(){
        return branch;
    }
    /**
     * makes sales
     * @param c the customer which wants to buy product
     * @param p the product which is wanted
     * @throws BranchEmployeeDoesNotHaveAuthority if the employee does not work for any branch
     */
    public void makeSale(Customer c,Product p)throws BranchEmployeeDoesNotHaveAuthority{
        
        if(branch == null){
            throw new BranchEmployeeDoesNotHaveAuthority();
        }
        

        if(c.Register(authority,branch.getCompany(authority))){  // if it is for the first time, so that customer did not register to the company it will register it
            System.out.println("This is "+c+"'s first purchasing and he/she is registered to the company ");
        }
        if(!isProductAvailable(p)){
            informManager(branch.getCompany(authority), p,branch.getIndex());
            
        }
        c.addOrder(authority,p);
        branch.getProducts().remove(p);
        

    }
    /**
     * returns the previous orders of the customer which has given index
     * @param company the company branchEmployee is working on
     * @param customerNumber index of customer at the company
     * @return preious orders of the given customer
     */
    public vector<Product> getPreviousOrders(Company company,int customerNumber){
        if(company == null) return null;
        Customer customer = company.getCustomer(authority,customerNumber);
        if(customer == null) return null;
        return customer.getPreviousOrders(authority);
    }
    /**
     * informs administrator about there is need for special product
     * @param c company in which employee is working
     * @param p wanted product
     * @param branchIndex index of branch which needs a product
     */
    public void informManager(Company c,Product p,int branchIndex){
        Administrators admin= c.getFirstAdmin(authority);
        if(admin !=null){
            System.out.println(this+ " informed manager to add "+p);
            admin.addProduct(p, branchIndex);
        }
        else{
            System.out.println(this+ " did not informed manager to add "+p);

        }
    }

}
