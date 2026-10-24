import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

interface Drawable {
    void draw();
}

abstract class Shape implements Drawable, Serializable {
    protected String shapeColor;

    public Shape(String shapeColor) {
        this.shapeColor = shapeColor;
    }

    public String getShapeColor() {
        return shapeColor;
    }

    public abstract double calcArea();

    @Override
    public String toString() {
        return "Shape [color=" + shapeColor + "]";
    }
}

class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(String shapeColor, double width, double height) {
        super(shapeColor);
        this.width = width;
        this.height = height;
    }

    @Override
    public double calcArea() {
        return width * height;
    }

    @Override
    public void draw() {
        System.out.println("Малюємо прямокутник кольору " + shapeColor);
    }

    @Override
    public String toString() {
        return String.format("Rectangle [color=%s, width=%.1f, height=%.1f, area=%.2f]",
                shapeColor, width, height, calcArea());
    }
}

class Triangle extends Shape {
    private double base;
    private double height;

    public Triangle(String shapeColor, double base, double height) {
        super(shapeColor);
        this.base = base;
        this.height = height;
    }

    @Override
    public double calcArea() {
        return 0.5 * base * height;
    }

    @Override
    public void draw() {
        System.out.println("Малюємо трикутник кольору " + shapeColor);
    }

    @Override
    public String toString() {
        return String.format("Triangle  [color=%s, base=%.1f, height=%.1f, area=%.2f]",
                shapeColor, base, height, calcArea());
    }
}

class Circle extends Shape {
    private double radius;

    public Circle(String shapeColor, double radius) {
        super(shapeColor);
        this.radius = radius;
    }

    @Override
    public double calcArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void draw() {
        System.out.println("Малюємо коло кольору " + shapeColor);
    }

    @Override
    public String toString() {
        return String.format("Circle    [color=%s, radius=%.1f, area=%.2f]",
                shapeColor, radius, calcArea());
    }
}

class FileHandler {
    public void saveToFile(Shape[] shapes, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(shapes);
        }
    }

    public Shape[] loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Shape[]) ois.readObject();
        }
    }
}

class ShapeModel {
    private Shape[] shapes;

    public void setShapes(Shape[] shapes) {
        this.shapes = shapes;
    }

    public Shape[] getShapes() {
        return shapes;
    }

    public double calcTotalArea() {
        double totalArea = 0;
        for (Shape shape : shapes) {
            totalArea += shape.calcArea();
        }
        return totalArea;
    }

    public double calcTotalAreaByType(Class<?> shapeType) {
        double totalArea = 0;
        for (Shape shape : shapes) {
            if (shapeType.isInstance(shape)) {
                totalArea += shape.calcArea();
            }
        }
        return totalArea;
    }

    public void sortByArea() {
        Arrays.sort(shapes, new Comparator<Shape>() {
            @Override
            public int compare(Shape s1, Shape s2) {
                return Double.compare(s1.calcArea(), s2.calcArea());
            }
        });
    }

    public void sortByColor() {
        Arrays.sort(shapes, new Comparator<Shape>() {
            @Override
            public int compare(Shape s1, Shape s2) {
                return s1.getShapeColor().compareToIgnoreCase(s2.getShapeColor());
            }
        });
    }
}

class ShapeView {
    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printShapes(Shape[] shapes) {
        if (shapes == null) {
            System.out.println("Масив порожній");
            return;
        }
        for (Shape shape : shapes) {
            System.out.println(shape.toString());
        }
        System.out.println(" ");
    }
}

class ShapeController {
    private ShapeModel model;
    private ShapeView view;
    private FileHandler fileHandler;

    public ShapeController(ShapeModel model, ShapeView view) {
        this.model = model;
        this.view = view;
        this.fileHandler = new FileHandler();
    }

    public void process() {
        Scanner scanner = new Scanner(System.in);
        model.setShapes(generateData());

        while (true) {
            view.printMessage("1 - Вивести всі фігури");
            view.printMessage("2 - Зберегти об'єкти у файл");
            view.printMessage("3 - Завантажити об'єкти з файлу");
            view.printMessage("0 - Вихід");
            System.out.print("Ваш вибір: ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        view.printShapes(model.getShapes());
                        break;
                    case "2":
                        System.out.print("Введіть шлях та ім'я файлу для збереження (напр. data.ser): ");
                        String saveName = scanner.nextLine();
                        fileHandler.saveToFile(model.getShapes(), saveName);
                        view.printMessage("Дані успішно збережено!\n");
                        break;
                    case "3":
                        System.out.print("Введіть ім'я файлу для завантаження: ");
                        String loadName = scanner.nextLine();
                        Shape[] loadedShapes = fileHandler.loadFromFile(loadName);
                        model.setShapes(loadedShapes);
                        view.printMessage("Дані успішно завантажено!\n");
                        break;
                    case "0":
                        return;
                    default:
                        view.printMessage("Невірний вибір. Спробуйте ще раз.\n");
                }
            } catch (IOException | ClassNotFoundException e) {
                view.printMessage("Помилка роботи з файлом: " + e.getMessage() + "\n");
            } catch (Exception e) {
                view.printMessage("Непередбачена помилка: " + e.getMessage() + "\n");
            }
        }
    }

    private Shape[] generateData() {
        Shape[] preparedPool = {
                new Rectangle("Red", 10.0, 5.0),
                new Circle("Blue", 7.5),
                new Triangle("Green", 8.0, 4.0),
                new Rectangle("Yellow", 6.0, 6.0),
                new Circle("Black", 10.0),
                new Triangle("White", 5.0, 12.0),
                new Rectangle("Blue", 4.0, 8.0),
                new Circle("Red", 3.0),
                new Triangle("Yellow", 7.0, 7.0),
                new Rectangle("Green", 12.0, 3.0),
                new Circle("White", 5.5),
                new Triangle("Black", 9.0, 6.0)
        };

        Shape[] data = new Shape[10];
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(preparedPool.length);
            data[i] = preparedPool[randomIndex];
        }

        return data;
    }
}

public class Task2 {
    public static void main(String[] args) {
        ShapeModel model = new ShapeModel();
        ShapeView view = new ShapeView();
        ShapeController controller = new ShapeController(model, view);

        controller.process();
    }
}