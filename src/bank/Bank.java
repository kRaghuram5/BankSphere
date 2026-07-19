package bank;

public class Bank {
    public int totalBranch;
    public final String bankId, bankName, headOffice;
    public Branch[] branch;
    public final int established_year;
    public Bank(String bankId, String bankName, String headOffice, int year){
        established_year=year;
        this.bankId = bankId;
        this.bankName = bankName;
        this.headOffice=headOffice;
        branch = new Branch[10];
        totalBranch = 0 ;
    }

    public void addBranch(String branchName, String IFSC, String Address){
        if(totalBranch>=10) System.out.println("Branch Limit reached");
        branch[totalBranch] = new Branch(branchName,IFSC,Address);
        totalBranch++;
    }
    public void displayBranch(){
        for(int i=0;i<totalBranch;i++){
            branch[i].getBranch();
        }
    }
    public void displayBranch(String IFSC){
        int i = Integer.parseInt(IFSC.substring(3,7));
        branch[i].getBranch();
    }
    public void displayBankinfo(){
        System.out.println("========================\nBANK INFORMATION \n========================");
        System.out.println("Bank Name : "+bankName);
        System.out.println("Bank Id : "+bankId);
        System.out.println("HeadOffice : "+headOffice);
        System.out.println("Established : "+established_year);
        System.out.println("Total Branch : "+ totalBranch);
        System.out.println("========================");
    }

}
