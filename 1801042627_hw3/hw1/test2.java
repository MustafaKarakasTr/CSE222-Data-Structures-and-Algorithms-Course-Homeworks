import mustafa.karakas.hw1.vector;
import mustafa.karakas.hw1.OfficeCabinet;
import mustafa.karakas.hw1.TheProductDoesNotExistException;
import mustafa.karakas.hw1.Customer;
import mustafa.karakas.hw1.HybridList;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.LinkedList;

import mustafa.karakas.hw1.Administrators;
import mustafa.karakas.hw1.BranchEmployee;
import mustafa.karakas.hw1.Company;
import mustafa.karakas.hw1.OfficeChair;
import mustafa.karakas.hw1.MeetingTable;

import mustafa.karakas.hw1.OfficeDesk;
import mustafa.karakas.hw1.Product;
import mustafa.karakas.hw1.BranchEmployeeDoesNotHaveAuthority;
import mustafa.karakas.hw1.ThereIsNoBranchEmployeeException;





public class test2{
    
    public static void main(String[] args) {
        Company c = new Company();
        Administrators admin = new Administrators("Mustafa","Karakas",c);
        BranchEmployee ElonMusk = new BranchEmployee("Elon","Musk");
        BranchEmployee JeffBezos = new BranchEmployee("Jeff","Bezos");
        BranchEmployee Mark = new BranchEmployee("Mark","Zuckerberg");

        System.out.println();

        try{
            admin.addBranch();
            admin.addEmployee(ElonMusk,4);
            admin.addEmployee(JeffBezos,4);
            admin.addEmployee(Mark,4);
            ElonMusk.addProduct(new OfficeChair(1,1));
            ElonMusk.addProduct(new OfficeChair(2,1));
            ElonMusk.addProduct(new OfficeChair(3,1));


            Customer mike = new Customer("mike","tyson",null,null);
            
            ElonMusk.makeSale(mike, new OfficeChair(1,1));
            System.out.println(mike+"'s customer number :"+mike.getCustomerNumber());
            ElonMusk.makeSale(mike, new OfficeChair(2,1));

            // office chair 4,1 is not available employee will inform admin 
            //and the admin will supply the product 
            ElonMusk.makeSale(mike, new OfficeChair(4,1));

            //cust.buyProduct(new OfficeChair(2,1));
            System.out.println("The products "+mike+" has bought");
            System.out.println(ElonMusk.getPreviousOrders(c,mike.getCustomerNumber()));
            for(int i=0;i<5;i++){
                admin.addProduct(new MeetingTable(i+1,1),i);
                admin.addProduct(new OfficeDesk(i+1,1),i);

            }
            System.out.println("List of products:");
            mike.seeTheListOfProducts();
            Product p;

            System.out.println("check product availability\n"); 
            if(JeffBezos.isProductAvailable(p=new OfficeChair(1,1))){
                System.out.println("We have the product you want : "+p);
            }else{
                System.out.println("We do not have the product you want : "+p);
            }
            if(JeffBezos.isProductAvailable(p=new OfficeChair(3,1))){
                System.out.println("We have the product you want : "+p);
            }else{
                System.out.println("We do not have the product you want : "+p);
            }
            System.out.println("\ncheck product availability end\n"); 
            
            System.out.println("\nRemove employee\n"); 
            if(admin.removeEmployee(ElonMusk,4)){
                System.out.println(ElonMusk+" is fired by "+admin);
            }
            else{
                System.out.println("Given employee does not exist in the given branch");
            }
                System.out.println("This will throw an exception because "+ElonMusk+" has been fired\n"); 
                ElonMusk.makeSale(mike, new OfficeChair(5,1));
            }
            catch(BranchEmployeeDoesNotHaveAuthority e){
                System.out.println(e);
            }
        catch(TheProductDoesNotExistException e){
            System.out.println(e);
        }
        catch(IndexOutOfBoundsException e){
            System.out.println(e);
        }
        

        try{
            Product p;
            if(Mark.removeProduct(p=new MeetingTable(1,1)))
            {
                System.out.println("Given product is deleted "+p );
            }
            else{
                System.out.println("Given product does not exist in the branch "+Mark+ " is working");
            }

            System.out.println("\nProduct is not valid exception test\n");
            p = new MeetingTable(3,10);
        
        }catch(TheProductDoesNotExistException e){
            System.out.println(e);
        }catch(BranchEmployeeDoesNotHaveAuthority e){
            System.out.println(e);
        }
        finally{
            admin.showBranchsProducts(4);
            admin.showBranchEmployees(4);
        }
        try{
            Customer Ahmet= new Customer("Ahmet","Yurt",null,null);// he can not buy online 
            JeffBezos.makeSale(Ahmet,new MeetingTable(1,1));
            System.out.println(Ahmet+"'s customer number:"+Ahmet.getCustomerNumber());
            admin.removeBranch(4); // employees are now unemployed
            
            System.out.println("This will throw an exception because "+JeffBezos+" 's branch is removed so he is unemployed:"); 

            JeffBezos.makeSale(Ahmet,new MeetingTable(1,2));
        }
        catch(BranchEmployeeDoesNotHaveAuthority e){
            System.out.println(e);
        }catch(TheProductDoesNotExistException e){
            System.out.println(e);
        }
        finally{
            admin.addEmployee(JeffBezos,3);
        }
        boolean seeAllProductsAreAdded = false; // this ruins the terminal appearance but it works 

        if(seeAllProductsAreAdded){
            try{
                admin.addAllProducts(3);
                JeffBezos.showBranchProducts();
            }
            catch(IndexOutOfBoundsException e){
                System.out.println(e);
            }
            catch(BranchEmployeeDoesNotHaveAuthority e){
                System.out.println(e);
            }
        }
        Customer Veli= new Customer("Veli","Turt",null,null);// he can not buy online 

        try{
            Product p;
            System.out.println("Search for product:");
            
            if(Veli.searchForProduct(p = new OfficeCabinet(4,1),c)){
                System.out.println(p+" is found at company");
            }
            else{
                System.out.println(p+" is not found at company");
            }
            for(int i=1;i<5;i++)
                JeffBezos.addProduct(new OfficeCabinet(i,1));
            if(Veli.searchForProduct(p = new OfficeCabinet(4,1),c)){
                System.out.println(p+" is found at company");
            }
            else{
                System.out.println(p+" is not found at company");
            }

            BranchEmployee b = new BranchEmployee("a", "b");

            admin.addEmployee(b,0);
            b.showBranchProducts();
            p = new OfficeCabinet(4,1);
            
            int w=Veli.inWhichStoreIsProduct(p);
            if(w!=-1){
                System.out.println(p+" is in the "+ (Veli.inWhichStoreIsProduct(p))+". branch"); // it prints the actual index, as a human we start from 1 to count so the true answer is +1
            }
            else{
                System.out.println(p+" does not exist in the company"); 
            }
            
            
            System.out.println("\nBranch products:");
            JeffBezos.showBranchProducts();
            System.out.println("\nVeli's customer number "+Veli.getCustomerNumber()); // it is -1 because veli did not buy anything and he does not have registration
            
            Veli.viewPreviousOrders();
            p = new OfficeCabinet(4,1);
            JeffBezos.makeSale(Veli, new OfficeCabinet(1,1));
            System.out.println(p+" is in the "+ (Veli.inWhichStoreIsProduct(p)+1)+". branch"); // it prints the actual index, as a human we start from 1 to count so the true answer is +1
            System.out.println("Veli's customer number :"+ Veli.getCustomerNumber());
            System.out.println("\nBranch products:");
            JeffBezos.showBranchProducts();
            Veli.viewPreviousOrders();
        }
        catch(TheProductDoesNotExistException e){
            System.out.println(e);
        }
        catch(BranchEmployeeDoesNotHaveAuthority e){
            System.out.println(e);
        }
        try{

            System.out.println("\nBranch products before addition:");
            JeffBezos.showBranchProducts();

            System.out.println("Admin asks for product need");
            admin.askForProductNeed(new MeetingTable(2,1), 3);
            admin.askForProductNeed(new MeetingTable(2,1), 3); // this does not add 

            System.out.println("\nBranch products after addition:");
            JeffBezos.showBranchProducts();
            JeffBezos.makeSale(Veli,new MeetingTable(2,1));
            System.out.println("\nBranch products after selling the item:");
            JeffBezos.showBranchProducts();

            System.out.println("\nOnline shopping");
            Customer Temel = new Customer("Temel","Turan","asdf@gmail.com","1234");
            Product p;
            if(Temel.shopOnline(p=new OfficeCabinet(2,1),"Gebze/Kocaeli","0543 543 5443",c)){
                System.out.println(p+" is selled"+ " Temel's customer number :"
                                   +Temel.getCustomerNumber());
            }
            else{
                System.out.println("You are registered to the another company");
            }
            System.out.println("\nBranch products after selling Online:");
            JeffBezos.showBranchProducts();
            // if the customer has registered to the another company shopOnline returns false
            
            Company c2 = new Company();
            Customer cust2 = new Customer("Unlucky","Customer","asdf@gmail.com","1234");
            if(Temel.shopOnline(p=new OfficeCabinet(3,1),"Gebze/Kocaeli","0543 543 5443",c2)){
                System.out.println(p+" is selled");
            }
            else{
                System.out.println(Temel+" registered to the another company");
            }

            // if customer tries to buy from a company which has no employee shopOnline throws error 

            cust2.shopOnline(p, "Gebze/Kocaeli","0543 543 5443",c2);
        }
        catch(ThereIsNoBranchEmployeeException e){
            System.out.println(e);
        }
        catch(IndexOutOfBoundsException e){
            System.out.println(e);
        }
        catch(TheProductDoesNotExistException e){
            System.out.println(e);
        }
        catch(BranchEmployeeDoesNotHaveAuthority e){
            System.out.println(e);
        }
       
        System.out.println("\n\nHybridList Test is Starting");
        boolean testHybrid = true;
        if(testHybrid){
            testHybridFunc();
        }
    }
    private static void testHybridFunc(){
       
        HybridList<Integer> x = new HybridList();
		 
        for(int i=0;i<32;i++) {
            x.add(i+32);
        }
        System.out.println(x);

        x.remove((Object)34);
        x.remove((Object)39);
        System.out.println(x);

        x.remove(0);
        x.remove(5);
        x.remove(1);
        System.out.println(x+"\n"+x.size());
        x.remove(26);
        x.remove(0);
        System.out.println(x+"\n"+x.size());

        for(int i=0;i<25;i++) {
            x.remove(0);
        }
        System.out.println(x+"\n"+x.size());

        for(int i=0;i<26;i++) {
            x.add(i+5);
        }
        System.out.println(x+"\n"+x.size());
        
        x.add(3,10);
        System.out.println(x+"\n"+x.size());
        x.add(0,10);
        System.out.println(x+"\n"+x.size());
        x.add(27,10);
        System.out.println(x+"\n"+x.size());
        x.add(29,35);
        System.out.println(x+"\n"+x.size());
        for(int i=0;i<25;i++) {
            x.remove(x.size()-1);
        }
        System.out.println(x+"\n"+x.size());
        x.add(0,1);
        System.out.println(x+"\n"+x.size());
        x.set(0,123);
        x.set(5,654);
        System.out.println(x+"\n"+x.size());
        x.clear();
        System.out.println(x+"\n"+x.size());
        for(int i=0;i<11;i++) {
            x.add(i,i*5);
        }
        System.out.println(x+"\n"+x.size());
        x.remove(x.size()-1);
        System.out.println(x+"\n"+x.size());
        System.out.println(x.get(1)+" "+ x.indexOf(4)+" "+ x.indexOf(30)+ " "+ x.contains(30)+ " "+ x.contains(31)+ " -----");
        
        for(int q:x) {
            System.out.print(q+" ");
        }
        System.out.println("\nSize : "+x.size());

        Iterator<Integer> HybridIterator = x.iterator();
        while(HybridIterator.hasNext()) {
            int qw = HybridIterator.next();
            if(qw == 5 || qw == 0 || qw == 20 ||qw==35 || qw == 30) {
                System.out.println(x);
                HybridIterator.remove();
            }
        }
        System.out.println(x+"\n"+x.size());
        x.add(2,5);
        System.out.println(x+"\n"+x.size());
        
        try{
            x.add(0,-1);
            x.add(13,0);//throws exception
        }catch(IndexOutOfBoundsException e){
            System.out.println(e+ " Add is unsuccessfull Size: "+x.size());
        }
        try{
            x.remove(-40);
        }catch(IndexOutOfBoundsException e){
            System.out.println(e+ " Remove is unsuccessfull Size: "+x.size());
        }
        System.out.println(x+"\n"+x.size());
        System.out.println("Is There 5: "+x.contains(5)); // returns actual index
        
        System.out.println("Index of 5 is "+x.indexOf(5)); // returns actual index
        x.remove((Object)5);
        System.out.println("5 is removed\nIs there 5: "+x.contains(5)); // returns actual index
        
        System.out.println("Index of 5 is "+x.indexOf(5)); // returns actual index
        
        System.out.println("Is there 99: "+x.contains(99)); // returns actual index
        
        System.out.println("Index of 99 is "+x.indexOf(99)); // returns -1
        System.out.println("Is 99 removed :"+x.remove((Object)99)); // returns false;


    }
}
    