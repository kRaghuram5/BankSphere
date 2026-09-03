package com.banksphere.customer;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

/**
 * Base class for all people in the system.
 * Marked @MappedSuperclass so JPA includes its fields
 * in the Customer table (no separate Person table).
 */
@MappedSuperclass
public abstract class Person {

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private long phone;

    @Column(nullable = false, length = 200)
    private String address;

    protected Person() {}

    public Person(String name, int age, long phone, String address) {
        this.name    = name;
        this.age     = age;
        this.phone   = phone;
        this.address = address;
    }

    public String getName()    { return name; }
    public int getAge()        { return age; }
    public long getPhone()     { return phone; }
    public String getAddress() { return address; }

    @Override
    public String toString() {
        return "name='" + name + ",\n" +
               "age="   + age  +
               ", \nphone="   + phone   +
               ", \naddress='" + address;
    }
}
