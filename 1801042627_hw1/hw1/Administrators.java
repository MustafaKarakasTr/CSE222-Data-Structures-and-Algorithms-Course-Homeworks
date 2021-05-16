package mustafa.karakas.hw1;

import mustafa.karakas.hw1.Branch;
import mustafa.karakas.hw1.BranchEmployee;
/**
 * Administrators manage the system by adding and removing branches and branch employees.
 * 
 * It can also supply product for the branches
 * It derives from the Person class
 */
public class Administrators extends Person{
    Company company=null;
    vector<Branch> branchesOfCompany=null;
    vector<vector<BranchEmployee>> employeesOfCompany = null;
    /**
     * Constructs and initializes a Administrator at given company
     * @param _name name of the Administrator
     * @param _surname surname of the Administrators
     * @param comp the Company which the Administrator is working at
     */
    public Administrators(String _name,String _surname,Company comp){
        super(_name, _surname);
        if(comp!=null){
            company = comp;
            branchesOfCompany = company.getBranches(); 
            employeesOfCompany = new vector<vector<BranchEmployee>>();
            for(int i=0;i<branchesOfCompany.getUsed();i++){
                employeesOfCompany.push_back(branchesOfCompany.at(i).getEmployees());
            }
            comp.addAdmin(authority, this);
            
        }
    }
    
    //https://stackoverflow.com/questions/182278/is-there-a-way-to-simulate-the-c-friend-concept-in-java
    /**
     * Authority makes possible that only Administrators can call some methods which belong to other classes.
     * Because of that Authority's constructer is private , only Administrator can call it 
     */
    public static final class Authority {
        /**
         * Authority has no data field or method ,It makes Administrator access to methods which can be called only by Administrator   
        */ 
        private Authority() {}
     } 
    private static final Authority authority = new Authority();
    /**
     * adds branch to the company of Administrator
     */
    public void addBranch(){
        branchesOfCompany.push_back(new Branch(company));
    }
    
    /*public void addBranch(vector<Product> products , vector<BranchEmployee> branchEmployees){
        branchesOfCompany.push_back(new Branch(products,branchEmployees));
    }*/
    /**
     * It shows all products of branch which has the given index 
     * @param branchIndex index of branch at company
     * @throws IndexOutOfBoundsException if given index of branch is not valid
     */
    public void showBranchsProducts(int branchIndex)throws IndexOutOfBoundsException {
        if(branchIndex >= branchesOfCompany.getUsed()){
            throw new IndexOutOfBoundsException();
        }
        System.out.println("Products in the "+branchIndex+". Branch");
        branchesOfCompany.at(branchIndex).showProducts();
    }
    /**
     * It shows all branchEmployees of branch which has the given index 
     * @param branchIndex index of branch at company
     * @throws IndexOutOfBoundsException if given index of branch is not valid
     */
    public void showBranchEmployees(int branchIndex)throws IndexOutOfBoundsException {
        if(branchIndex >= branchesOfCompany.getUsed()){
            throw new IndexOutOfBoundsException();
        }
        System.out.println("BranchEmployees in the "+branchIndex+". Branch");
        branchesOfCompany.at(branchIndex).showEmployees();
    }
    /**
     * adds product to branch which has given index
     * @param p product which is wanted to be added to the branch
     * @param branchIndex index of branch at company
     */
    public void addProduct(Product p,int branchIndex) throws IndexOutOfBoundsException{
        if(branchIndex >= branchesOfCompany.getUsed()){
            throw new IndexOutOfBoundsException();
        }
        branchesOfCompany.at(branchIndex).getProducts().push_back(p);
        System.out.println(p + " added to "+(branchIndex+1)+". branch" );
    }
    /**
     * adds employee to branch which has given index
     * @param emp branchEmployee which is wanted to be added to the branch
     * @param branchIndex index of branch at company
     */
    public void addEmployee(BranchEmployee emp,int branchIndex)throws IndexOutOfBoundsException{
        if(branchIndex >= branchesOfCompany.getUsed()){
            throw new IndexOutOfBoundsException();
        }
        branchesOfCompany.at(branchIndex).getEmployees().push_back(emp);
        emp.setBranch(authority, branchesOfCompany.at(branchIndex));
        // only admins can access to Authority so only admins can add employee to the branches
    }
    /**
     * removes the given product from the branch which has given index
     * @param p product which is wanted to be removed
     * @param branchIndex index of branch at company
     * @return returns true if the given product is found and removed otherwise returns false
     * @throws IndexOutOfBoundsException if the given index is not valid
     */
    public boolean removeProduct(Product p,int branchIndex)throws IndexOutOfBoundsException{
        if(branchIndex >= branchesOfCompany.getUsed()){
            throw new IndexOutOfBoundsException();
        }
        return branchesOfCompany.at(branchIndex).getProducts().remove(p) != null;
    }
    /**
     * removes the given employee from the branch which has given index
     * @param emp employee which is wanted to be removed
     * @param branchIndex index of branch at company
     * @return returns true if the given employee is found and removed otherwise returns false
     * @throws IndexOutOfBoundsException if the given index is not valid 
     */
    public boolean removeEmployee(BranchEmployee emp,int branchIndex)throws IndexOutOfBoundsException{
        if(branchIndex >= branchesOfCompany.getUsed()){
            throw new IndexOutOfBoundsException();
        }
        BranchEmployee deletedEmployee;
        if((deletedEmployee = branchesOfCompany.at(branchIndex).getEmployees().remove(emp)) != null){
            deletedEmployee.setBranch(authority,null);
            //emp.setBranch() -> is not correct because this function do not have to be called with the reference to exact employee
            //it can be called with constructer which has same name and surname with the employee which is wanted to be deleted
            return true;
        }
        return false;
    }
    /**
     * removes employee from the company
     * @param emp employee to be removed
     * @return returns true if the given employee has been found and removed otherwise false
     */
    public boolean removeEmployee(BranchEmployee emp){ 
        /*
            It does not matter in which branch the employee has been working
            If the admin has a authority over the branch in which the employee working,
                then it will get the sack
        */
        BranchEmployee deletedEmployee;
        for(int i=0;i<branchesOfCompany.getUsed();i++){
            if((deletedEmployee = branchesOfCompany.at(i).getEmployees().remove(emp)) != null){
                deletedEmployee.setBranch(authority,null);
                return true;
            }
        }
        return false;
    }
    /**
     * Adds all possible products to the branch which has given index, so the branch has all products
     * @param BranchIndex index of branch at company
     * @throws IndexOutOfBoundsException if the given index is not valid  
     */  
    public void addAllProducts(int BranchIndex)throws IndexOutOfBoundsException{
        if(BranchIndex >= branchesOfCompany.getUsed()){
            throw new IndexOutOfBoundsException();
        }
        if(BranchIndex < branchesOfCompany.getUsed()){
            branchesOfCompany.at(BranchIndex).addAllProducts(authority);
        }
    }
    /**
     * if the given product is not available in the branch which has the given index then supplies it to the branch
     * @param p product to be added
     * @param branchIndex index of branch at company
     * @throws IndexOutOfBoundsException if the given index is not valid  
     */
    public boolean askForProductNeed(Product p,int branchIndex)throws IndexOutOfBoundsException{
        if(branchIndex >= branchesOfCompany.getUsed()){
            throw new IndexOutOfBoundsException();
        }
        if(branchesOfCompany.getUsed()>branchIndex){
            if(branchesOfCompany.at(branchIndex).getProducts().isAvailable(p) == -1){
                addProduct(p, branchIndex);
                return true;
            }
        }
        System.out.println(p+" has not added to "+(branchIndex+1)+". branch because there is already");
        return false;
    }
    public boolean removeBranch(int branchIndex)throws IndexOutOfBoundsException{
        if(branchIndex >= branchesOfCompany.getUsed()){
            throw new IndexOutOfBoundsException();
        }

        Branch branch = company.getBranches().at(branchIndex);
        vector<BranchEmployee> emps = branch.getEmployees();
        for(int i=0; i<emps.getUsed(); i++){
            emps.at(i).setBranch(authority,null); // make employees unemployed
        }
        company.getBranches().remove(branch);
        System.out.println("the branch at the " +branchIndex+". index is removed. It's branch employees are now unemployed" );
        return true;
    }


}
