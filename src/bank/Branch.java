package bank;

public class Branch {
    private String branchName,IFSC,Address;
    Branch(String branchName,String IFSC,String Address){
        this.branchName = branchName;
        this.IFSC = IFSC;
        this.Address = Address;
    }

    public void getBranch(){
        System.out.println("========================\nBANK INFORMATION \n========================");
        System.out.println("Branch Name : "+branchName);
        System.out.println("Branch IFSC : "+IFSC);
        System.out.println("Address : "+Address);
        System.out.println("========================");
    }


}

