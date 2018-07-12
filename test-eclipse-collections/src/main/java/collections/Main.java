package collections;

import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.list.mutable.FastList;

import model.Address;
import model.Person;
import model.factory.AddressFactory;
import model.factory.PersonFactory;

public class Main {

    public static void main(String[] args) {
        testPersons();
    }

    private static void testPersons() {
        PersonFactory personFactory = new PersonFactory();
        FastList<Person> myList = FastList.newListWith(personFactory.createRandomPerson(),
                personFactory.createRandomPerson(), personFactory.createRandomPerson(), personFactory.createRandomPerson(),
                personFactory.createRandomPerson(), personFactory.createRandomPerson(), personFactory.createRandomPerson());
        System.out.println(myList.makeString("\n"));

        System.out.println("\n");

        // allMatch behavior with a parameter
        System.out
                .println("All are at least 18: " + myList.allSatisfyWith((person, ageMin) -> person.getAge() >= ageMin, 18));

        System.out.println("\n");

        // Aggregation: first param determines the key, second param is the initial value for each key, and third param is the way to agregate values
        MutableMap<String, String> aggregateByCity = myList.aggregateBy(person -> person.getAddress().getCity(), () -> "",
                (name, person) -> name = name.isEmpty() ? person.getName() : name + " - " + person.getName());

        myList.collect(Person::getAddress).collect(Address::getCity).distinct()
                .forEach(city -> System.out.println(city + ": " + aggregateByCity.get(city)));

        System.out.println("\n");

        // orElse behavior on a map
        AddressFactory.cities.forEach(
                city -> System.out.println(city + ": " + aggregateByCity.getIfAbsent(city, () -> "No man's land ...")));

        System.out.println("\n");

        Predicate2<Person, String> cityPredicat = (person, city) -> person.getAddress().getCity().equals(city);
        // anymatch behavior with a parameter
        boolean someoneInGrenoble = myList.anySatisfyWith(cityPredicat, "Grenoble");
        System.out.println("There is at least one person in Grenoble: " + someoneInGrenoble);

        // find behavior, with a parameter
        myList.detectWithOptional(cityPredicat, "Grenoble")
                .ifPresent(person -> System.out.println(person.getName() + " lives in Grenoble"));

        // Reject: same thing that filter(obj -> !predicat.test(a)) --> amazing
        System.out.println(myList.rejectWith(cityPredicat, "Grenoble").collect(Person::getName)
                .makeString("Person not in Grenoble: ", " - ", "."));

        System.out.println("\n");

        // Sort the collection regarding any operation done on the object
        System.out.println(myList.sortThisBy(person -> person.getAge())
                .collect(person -> String.format("%s(%s)", person.getName(), person.getAge())).makeString(" - "));
    }

}
