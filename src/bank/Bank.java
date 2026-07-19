package bank;

public class Bank {
    private int totalBranch;
    private static int totalBanksCreated =0;
    private final String bankId, bankName, headOffice;
    private Branch[] branches;
    private final int established_year;
    public Bank(String bankId, String bankName, String headOffice, int year){
        established_year=year;
        this.bankId = bankId;
        this.bankName = bankName;
        this.headOffice=headOffice;
        branches = new Branch[10];
        totalBranch = 0 ;
        totalBanksCreated++;
    }

    public String getBankId() {
        return bankId;
    }

    public void addBranch(String branchName, String IFSC, String Address){
        if(totalBranch>=10) {System.out.println("Branch Limit reached"); return;}
        branches[totalBranch] = new Branch(branchName,IFSC,Address);
        totalBranch++;
    }
    public void displayAllBranch(){
        for(int i=0;i<totalBranch;i++){
            branches[i].displayBranch();
        }
    }
    public void displayBranch(String IFSC){
        for(int i=0;i<totalBranch;i++){
            if(branches[i].getIFSC() == IFSC){
                branches[i].displayBranch();
            }
        }
    }

    public int getTotalBranch(){
        return totalBranch;
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

}
