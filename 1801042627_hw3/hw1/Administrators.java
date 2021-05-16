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
    DoubleLL<Branch> branchesOfCompany=null;
    MArrayList<MArrayList<BranchEmployee>> employeesOfCompany = null;
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
            employeesOfCompany = new MArrayList<MArrayList<BranchEmployee>>();
            for(Branch branch : branchesOfCompany){
                employeesOfCompany.add(branch.getEmployees());
            }
            comp.addAdmin(authority, this);
            
        }
    }
    /*for(int i=0;i<branchesOfCompany.getSize();i++){
                employeesOfCompany.add(branchesOfCompany.get(i).getEmployees());
      }*/
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
        branchesOfCompany.add(new Branch(company));
    }
    
    /*public void addBranch(vector<Product> products , vector<BranchEmployee> branchEmployees){
        branchesOfCompany.add(new Branch(products,branchEmployees));
    }*/
    /**
     * It shows all products of branch which has the given index 
     * @param branchIndex index of branch at company
     * @throws IndexOutOfBoundsException if given index of branch is not valid
     */
    public void showBranchsProducts(int branchIndex)throws IndexOutOfBoundsException {
        if(branchIndex >= branchesOfCompany.getSize()){
            throw new IndexOutOfBoundsException();
        }
        System.out.println("Products in the "+branchIndex+". Branch");
        branchesOfCompany.get(branchIndex).showProducts();
    }
    /**
     * It shows all branchEmployees of branch which has the given index 
     * @param branchIndex index of branch at company
     * @throws IndexOutOfBoundsException if given index of branch is not valid
     */
    public void showBranchEmployees(int branchIndex)throws IndexOutOfBoundsException {
        if(branchIndex >= branchesOfCompany.getSize()){
            throw new IndexOutOfBoundsException();
        }
        System.out.println("BranchEmployees in the "+branchIndex+". Branch");
        branchesOfCompany.get(branchIndex).showEmployees();
    }
    /**
     * adds product to branch which has given index
     * @param p product which is wanted to be added to the branch
     * @param branchIndex index of branch at company
     */
    public void addProduct(Product p,int branchIndex) throws IndexOutOfBoundsException{
        if(branchIndex >= branchesOfCompany.getSize()){
            throw new IndexOutOfBoundsException();
        }
        branchesOfCompany.get(branchIndex).getProducts().add(p);
        System.out.println(p + " added to "+(branchIndex+1)+". branch" );
    }
    /**
     * adds employee to branch which has given index
     * @param emp branchEmployee which is wanted to be added to the branch
     * @param branchIndex index of branch at company
     */
    public void addEmployee(BranchEmployee emp,int branchIndex)throws IndexOutOfBoundsException{
        if(branchIndex >= branchesOfCompany.getSize()){
            throw new IndexOutOfBoundsException();
        }
        Branch branch = branchesOfCompany.get(branchIndex);
        branch.getEmployees().add(emp);
        emp.setBranch(authority,branch);
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
        if(branchIndex >= branchesOfCompany.getSize()){
            throw new IndexOutOfBoundsException();
        }
        return branchesOfCompany.get(branchIndex).getProducts().remove(p);
    }
    /**
     * removes the given employee from the branch which has given index
     * @param emp employee which is wanted to be removed
     * @param branchIndex index of branch at company
     * @return returns true if the given employee is found and removed otherwise returns false
     * @throws IndexOutOfBoundsException if the given index is not valid 
     */
    public boolean removeEmployee(BranchEmployee emp,int branchIndex)throws IndexOutOfBoundsException{
        if(branchIndex >= branchesOfCompany.getSize()){
            throw new IndexOutOfBoundsException();
        }
        BranchEmployee deletedEmployee = emp;
        if( branchesOfCompany.get(branchIndex).getEmployees().remove(emp)){
            deletedEmployee.setBranch(authority,null);
            //emp.setBranch() -> is not correct because this function do not have to be called with the reference to exact employee
            //it can be called with constructer which has same name and surname with the employee which is wanted to be deleted
            return true;
        }
        return false;
    }
    /**
     * It does not matter in which branch the employee has been working
     * If the admin has a authority over the branch in which the employee working,
     * then it will get the sack
     * @param emp employee to be removed
     * @return returns true if the given employee has been found and removed otherwise false
     */
    public boolean removeEmployee(BranchEmployee emp){ 

        BranchEmployee deletedEmployee = emp;
       for(Branch bra : branchesOfCompany){
            if( bra.getEmployees().remove(emp)){
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
        if(BranchIndex >= branchesOfCompany.getSize()){
            throw new IndexOutOfBoundsException();
        }
        branchesOfCompany.get(BranchIndex).addAllProducts(authority);
    }
    /**
     * if the given product is not available in the branch which has the given index then supplies it to the branch
     * @param p product to be added
     * @param branchIndex index of branch at company
     * @throws IndexOutOfBoundsException if the given index is not valid  
     */
    public boolean askForProductNeed(Product p,int branchIndex)throws IndexOutOfBoundsException{
        if(branchIndex >= branchesOfCompany.getSize()){
            throw new IndexOutOfBoundsException();
        }
        if(branchesOfCompany.getSize()>branchIndex){
            if(!branchesOfCompany.get(branchIndex).getProducts().contains(p)){
                addProduct(p, branchIndex);
                return true;
            }
        }
        System.out.println(p+" has not added to "+(branchIndex+1)+". branch because there is already");
        return false;
    }
    public boolean removeBranch(int branchIndex)throws IndexOutOfBoundsException{
        if(branchIndex >= branchesOfCompany.getSize()){
            throw new IndexOutOfBoundsException();
        }

        Branch branch = company.getBranches().get(branchIndex);
        MArrayList<BranchEmployee> emps = branch.getEmployees();
        for(int i=0; i<emps.size(); i++){
            emps.get(i).setBranch(authority,null); // make employees unemployed
        }
        company.getBranches().remove(branch);
        System.out.println("the branch at the " +branchIndex+". index is removed. It's branch employees are now unemployed" );
        return true;
    }


}
