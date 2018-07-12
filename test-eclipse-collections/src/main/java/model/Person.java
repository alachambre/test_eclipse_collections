package model;

public class Person {

    private String name;
    private int age;
    private Address address;

    public Person(String name, int age, Address adress) {
        this.name = name;
        this.age = age;
        this.address = adress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return String.format("%s is %s years old and lives in %s", name, age, address.getCity());
    }

}
