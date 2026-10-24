import java.util.ArrayList;
import java.util.List;

abstract class Animal {}
abstract class Mammal extends Animal {}
abstract class Bird extends Animal {}

class Lion extends Mammal {}
abstract class Hoofed extends Mammal {}
class Zebra extends Hoofed {}
class Giraffe extends Hoofed {}
class Eagle extends Bird {}

class CageFullException extends RuntimeException {}
class AnimalNotFoundException extends RuntimeException {}

class Cage<T extends Animal> {
    private int maxCapacity;
    private List<T> animals;

    public Cage(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.animals = new ArrayList<>();
    }

    public int getMaxCapacity() { return maxCapacity; }
    public int getOccupiedCount() { return animals.size(); }

    public void addAnimal(T animal) {
        if (animals.size() >= maxCapacity) {
            throw new CageFullException();
        }
        animals.add(animal);
    }

    public void removeAnimal(T animal) {
        if (!animals.contains(animal)) {
            throw new AnimalNotFoundException();
        }
        animals.remove(animal);
    }
}

class MammalCage<T extends Mammal> extends Cage<T> {
    public MammalCage(int maxCapacity) { super(maxCapacity); }
}

class BirdCage extends Cage<Bird> {
    public BirdCage(int maxCapacity) { super(maxCapacity); }
}

class LionCage extends MammalCage<Lion> {
    public LionCage(int maxCapacity) { super(maxCapacity); }
}

class HoofedCage extends MammalCage<Hoofed> {
    public HoofedCage(int maxCapacity) { super(maxCapacity); }
}

class Zoo {
    public List<Cage<? extends Animal>> cages = new ArrayList<>();

    public int getCountOfAnimals() {
        int count = 0;
        for (Cage<? extends Animal> cage : cages) {
            count += cage.getOccupiedCount();
        }
        return count;
    }

    public void addCage(Cage<? extends Animal> cage) {
        cages.add(cage);
    }
}

public class Main {
    public static void main(String[] args) {
        Zoo zoo = new Zoo();

        LionCage lionCage = new LionCage(2);
        lionCage.addAnimal(new Lion());

        HoofedCage hoofedCage = new HoofedCage(3);
        hoofedCage.addAnimal(new Zebra());
        hoofedCage.addAnimal(new Giraffe());

        BirdCage birdCage = new BirdCage(2);
        birdCage.addAnimal(new Eagle());

        zoo.addCage(lionCage);
        zoo.addCage(hoofedCage);
        zoo.addCage(birdCage);

        System.out.println("Загальна кількість тварин та птахів: " + zoo.getCountOfAnimals());
    }
}