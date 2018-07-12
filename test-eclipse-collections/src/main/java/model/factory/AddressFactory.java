package model.factory;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.list.mutable.FastList;

import model.Address;

public class AddressFactory {

    public static MutableList<String> cities;
    public static MutableList<String> streets;

    public AddressFactory() {
        this.cities = new FastList<String>().with("Grenoble", "Lyon", "Toulouse", "Marseille", "Lille", "Paris", "Bordeaux");
        this.streets = new FastList<String>().with("Rue du loup", "Avenue du soleil", "Boulevard Michel Delpech",
                "Rue de la peche");
    }

    public Address createRandomAddress() {
        return new Address(city(), street(), number(), zipCode());
    }

    private String city() {
        return cities.shuffleThis().getFirst();
    }

    private int zipCode() {
        int res;
        do {
            res = (int) (Math.random() * 100000);
        } while (res < 10000);
        return res;
    }

    private int number() {
        return 1 + (int) (Math.random() * 100);
    }

    private String street() {
        return streets.shuffleThis().getFirst();
    }

}
