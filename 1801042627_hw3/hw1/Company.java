package mustafa.karakas.hw1;

import mustafa.karakas.hw1.Administrators;
import mustafa.karakas.hw1.Branch;
import mustafa.karakas.hw1.BranchEmployee;
import mustafa.karakas.hw1.Customer;
import mustafa.karakas.hw1.MArrayList;
import mustafa.karakas.hw1.Product;
import mustafa.karakas.hw1.TheProductDoesNotExistException;
/**
 * Represents the company
 * The class which includes all others 
 */
public class Company{
   //private int numOfEmployeesPerBranch = 5;
   private int numberOfBranches = 4;
   private MArrayList<Administrators> admins = new MArrayList<Administrators>();
   private DoubleLL<Branch>branches = new DoubleLL<Branch>();
   public DoubleLL<Branch> getBranches(){ return branches;} // admin should be able to add branch
   private MArrayList<Customer> customers = new MArrayList<Customer>();
   /**
    * returns customer of company
    * @param authority makes it mandatory that only branchEmployee can call the method
    * @param _customerNumber customer id
    * @return returns the wanted customer
    */
   public Customer getCustomer(BranchEmployee.Authority authority ,int _customerNumber){
      if(authority!=null && _customerNumber<customers.size()){
         return customers.get(_customerNumber);
      }
      return null;
   }
   /**
    * Constructs and initializes the company
    * Creates branches
    */
   public Company(){
      for(int branchIndex=0;branchIndex<numberOfBranches;branchIndex++){ // for every branch in company
         branches.add(new Branch(this));
         /*
         vector<BranchEmployee> emps = new vector<BranchEmployee>();
         for(int j = 0;j < numOfEmployeesPerBranch ; j++){ 
            emps.add(new BranchEmployee("EmployeeName"+Integer.toString(i+1)+" "+Integer.toString(j+1),"EmployeeSurname"+Integer.toString(i+1)+" "+Integer.toString(j+1)));
         }
         */
        /*vector<BranchEmployee> emps = Branch.createEmployees(numOfEmployeesPerBranch,branchIndex);
         branches.add(new Branch(emps));
         branches.at(branchIndex).mytoString();
         */
      }
      
   }
   /**
    * Adds customer to the company
    * @param c customer to be added
    * @return returns id of customer
    */
   public int addCustomer(Customer c){ 
      if(c == null ) return -1;
      if(customers.indexOf(c) == -1){ // if available returns index else -1
         customers.add(c);
         return customers.size()-1; // returns index of customer (it is his/her customerNumber)
      }
      return -1;
   }
   /**
    * initializes the company with the given administrator
    * @param a the administrator to be added
    */
   public Company(Administrators a){
      this();
      admins.add(a);
   }
   
   /**
    * returns the first Administrator available
    * @param a makes it mandatory that only branchEmployee can call this method
    * @return the first Administrator
    */
   public Administrators getFirstAdmin(BranchEmployee.Authority a){
      if(a != null && admins != null && admins.size() != 0){
         return admins.get(0);
      }
      return null;
   }
   /**
    * Adds admin to the company
    * @param a  makes it mandatory that only Administrators can call this method
    * @param admin admin which is wanted to be added
    * @return if addition is successful than returns true else false
    */
   public boolean addAdmin(Administrators.Authority a,Administrators admin){
      if(a!= null){
         admins.add(admin);
         return true;
      }
      return false;
   }
}

