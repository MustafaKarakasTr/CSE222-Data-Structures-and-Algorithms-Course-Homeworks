package mustafa.karakas.hw1;
/**
 * the job can not be done because there is no branchEmployee to do that job
 */
public class ThereIsNoBranchEmployeeException extends Exception{
    /**
     * shows that there is no branchEmployee to do that job
     */
    public ThereIsNoBranchEmployeeException(){
        super("There is no branchEmployee to do that job");
    }
}