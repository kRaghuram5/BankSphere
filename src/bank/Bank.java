package bank;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private int totalBranch;
    private static int totalBanksCreated =0;
    private final String bankId, bankName, headOffice;
    private final List<Branch> branches;
    private final int established_year;
    public Bank(String bankId, String bankName, String headOffice, int year){
        established_year=year;
        this.bankId = bankId;
        this.bankName = bankName;
        this.headOffice=headOffice;
        branches = new ArrayList<>();
        totalBranch = 0 ;
        totalBanksCreated++;
    }

    public static int getTotalBanksCreated() {
        return totalBanksCreated;
    }

    public String toString(){
        return ("========================\nBANK INFORMATION \n========================" +
                "\nBank Name : "+bankName +
                "\nBank Id : "+bankId +
                "\nHeadOffice : "+headOffice +
                "\nEstablished : "+established_year +
                "\nTotal Branch : "+ totalBranch +
                "\n========================"
        );
    }

    public static void showTotalBanks(){
        System.out.println("Total Banks Created : " + totalBanksCreated);
    }

    public void addBranch(String branchName, String IFSC, String Address){

        branches.add(new Branch(branchName,IFSC,Address));
        totalBranch++;
    }
    public void displayAllBranch(){
        for(Branch branch : branches){
            System.out.println(branch);;
        }
    }
    public void displayBranch(String IFSC){
        for(int i=0;i<totalBranch;i++){
            if(branches.get(i).getIFSC() == IFSC){
                System.out.println(branches.get(i));
            }
        }
    }

    public int getTotalBranch(){
        return totalBranch;
    }

}
