package bank;

public class Branch {
    private final String branchName;
    private final String IFSC;
    private final String address;
    public Branch(String branchName,String IFSC,String address){
        this.branchName = branchName;
        this.IFSC = IFSC;
        this.address = address;
    }

    public String getIFSC() {
        return IFSC;
    }

    @Override
    public String toString() {
        return "Branch: \n ========================\n" +
                "branchName='" + branchName + '\n' +
                ", IFSC='" + IFSC + '\n' +
                ", address='" + address + '\n' +
                "========================";
    }
}

