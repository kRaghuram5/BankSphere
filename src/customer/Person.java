package customer;

public class Person {
    private String name;
    private int age;
    private long phone;
    private String address;
    public Person(String name, int age, long phone, String address){
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.address=address;
    }

    @Override
    public String toString() {
        return "name='" + name + '\n' +
                ", age=" + age +
                ", \nphone=" + phone +
                ", \naddress='" + address ;
    }
}
