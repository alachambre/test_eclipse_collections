package model.factory;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.list.mutable.FastList;

import model.Address;
import model.Person;

public class PersonFactory {

    private AddressFactory addressFactory;
    private MutableList<String> names;

    public PersonFactory() {
        this.addressFactory = new AddressFactory();
        this.names = new FastList<String>().with("Toto", "Patrique", "Geraldine", "Francis", "Jean", "Jeanne",
                "Emile-Didier", "Loulou");
    }

    public Person createRandomPerson() {
        return new Person(name(), age(), address());
    }

    private String name() {
        return names.shuffleThis().getFirst();
    }

    private int age() {
        return 1 + (int) (Math.random() * 100);
    }

    private Address address() {
        return addressFactory.createRandomAddress();
    }

}
