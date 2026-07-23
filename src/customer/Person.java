package customer;

public class Person {
    private final String name;
    private final int age;
    private final long phone;
    private final String address;
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
