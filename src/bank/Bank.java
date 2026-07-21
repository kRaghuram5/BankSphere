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

    public void displayBankInfo(){
        System.out.println("========================\nBANK INFORMATION \n========================");
        System.out.println("Bank Name : "+bankName);
        System.out.println("Bank Id : "+bankId);
        System.out.println("HeadOffice : "+headOffice);
        System.out.println("Established : "+established_year);
        System.out.println("Total Branch : "+ totalBranch);
        System.out.println("========================");
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
            branch.displayBranch();
        }
    }
    public void displayBranch(String IFSC){
        for(int i=0;i<totalBranch;i++){
            if(branches.get(i).getIFSC() == IFSC){
                branches.get(i).displayBranch();
            }
        }
    }

    public int getTotalBranch(){
        return totalBranch;
    }

}
