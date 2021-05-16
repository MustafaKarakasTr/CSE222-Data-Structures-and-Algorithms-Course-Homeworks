package mustafa.karakas.hw1;
/**
 * If the employee does not work at any branch and employee is wanted to do some job this exception is throwed
 */
public class BranchEmployeeDoesNotHaveAuthority extends Exception{
    /**
     * shows the message
     */
    public BranchEmployeeDoesNotHaveAuthority(){
        super("BranchEmployee does not work for any branch");
    }
}