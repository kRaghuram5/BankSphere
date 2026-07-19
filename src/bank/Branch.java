package bank;

public class Branch {
    private String branchName,IFSC,address;
    public Branch(String branchName,String IFSC,String address){
        this.branchName = branchName;
        this.IFSC = IFSC;
        this.address = address;
    }

    public String getIFSC() {
        return IFSC;
    }

    public void displayBranch(){
        System.out.println("========================\nBANK INFORMATION \n========================");
        System.out.println("Branch Name : "+branchName);
        System.out.println("Branch IFSC : "+IFSC);
        System.out.println("Address : "+address);
        System.out.println("========================");
    }


}

