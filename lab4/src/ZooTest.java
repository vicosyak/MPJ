import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ZooTest {

    @Test
    void testCageFullException() {
        LionCage cage = new LionCage(1);
        cage.addAnimal(new Lion());
        assertThrows(CageFullException.class, () -> cage.addAnimal(new Lion()));
    }

    @Test
    void testAnimalNotFoundException() {
        BirdCage cage = new BirdCage(5);
        Eagle eagle = new Eagle();
        assertThrows(AnimalNotFoundException.class, () -> cage.removeAnimal(eagle));
    }

    @Test
    void testHoofedCage() {
        HoofedCage cage = new HoofedCage(2);
        cage.addAnimal(new Zebra());
        cage.addAnimal(new Giraffe());
        assertEquals(2, cage.getOccupiedCount());
    }

    @Test
    void testZooAnimalsCount() {
        Zoo zoo = new Zoo();
        LionCage lionCage = new LionCage(2);
        lionCage.addAnimal(new Lion());

        HoofedCage hoofedCage = new HoofedCage(3);
        hoofedCage.addAnimal(new Zebra());
        hoofedCage.addAnimal(new Giraffe());

        zoo.addCage(lionCage);
        zoo.addCage(hoofedCage);

        assertEquals(3, zoo.getCountOfAnimals());
    }
}