import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static class Address {
        String street, number;
        int apartment;

        Address(String street, String number, int apartment) {
            this.street = street;
            this.number = number;
            this.apartment = apartment;
        }

        @Override
        public String toString() {
            return "вул. " + street + ", буд. "  + number + ", кв. " + apartment ;
        }
    }
    static class Zapys {
        String name, lastName, birthday, phone;
        Address address;

        Zapys(String name, String lastName, String birthday, String phone, Address address) {
            this.name = name;
            this.lastName = lastName;
            this.birthday = birthday;
            this.phone = phone;
            this.address = address;
        }

        @Override
        public String toString() {
            return name + " " + lastName + " " + birthday + " " + phone + " " + address;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Zapys> zapysList = new ArrayList<>();

        while (true) {
            System.out.println("1. Додати запис / 2. Переглянути наявні записи ");
            System.out.print("Вибір: ");
            String input = scanner.nextLine();

            if (input.equals("Додати запис") || input.equals("1")) {
                String name, lastName, birthday, phone, street, number;
                int apartment;

                while (true) {
                    System.out.print("Ім'я: ");
                    name = scanner.nextLine();
                    if (name.matches("^[A-Za-zА-Яа-яІіЇїЄє'\\-]{2,30}$")) {
                        break;
                    }
                    System.out.println("Некоректне ім'я");
                }

                while (true) {
                    System.out.print("Прізвище: ");
                    lastName = scanner.nextLine();
                    if (lastName.matches("^[A-Za-zА-Яа-яІіЇїЄє'\\-]{2,30}$")) {
                        break;
                    }
                    System.out.println("Некоректне прізвище");
                }

                while (true) {
                    System.out.print("Дата народження: ");
                    birthday = scanner.nextLine();
                    if (birthday.matches("^(0[1-9]|[12][0-9]|3[01])[-.](0[1-9]|1[0-2])[-.](19\\d{2}|20\\d{2})$")) {
                        break;
                    }
                    System.out.println("Некоректна дата, формат має бути ДД-ММ-РРРР");
                }

                while (true) {
                    System.out.print("Телефон (+380ХХХХХХХХХ): ");
                    phone = scanner.nextLine();
                    if (phone.matches("^\\+?380\\d{9}$")) {
                        break;
                    }
                    System.out.println("Некоректний номер");
                }

                while (true) {
                    System.out.print("Вулиця: ");
                    street = scanner.nextLine();
                    if (street.matches("^[A-Za-zА-Яа-яІіЇїЄє'\\s.\\-]{2,30}$")) {
                        break;
                    }
                    System.out.println("Некоректна назва вулиці");
                }

                while (true) {
                    System.out.print("Номер будинку: ");
                    number = scanner.nextLine();
                    if (number.matches("^\\d+([a-zA-Zа-яА-ЯіІїЇєЄ\\-]|(/\\d+))?$")) {
                        break;
                    }
                    System.out.println("Некоректний номер будинку");
                }

                while (true) {
                    System.out.print("Номер квартири (0 якщо приватний будинок): ");
                    try {
                        apartment = Integer.parseInt(scanner.nextLine());
                        if (apartment >= 0) {
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Введіть ціле число");
                    }
                }

                Address NewAddress = new Address(street, number, apartment);
                Zapys newZapys = new Zapys(name, lastName, birthday, phone, NewAddress);
                zapysList.add(newZapys);

                System.out.println("Запис додано");
            }

            if (input.equals("Переглянути наявні записи") || input.equals("2")) {
                if (zapysList.isEmpty()) {
                    System.out.println("Журнал порожній");
                } else {
                    System.out.println("\nЖурнал куратора");
                    for (int i = 0; i < zapysList.size(); i++) {
                        System.out.println((i + 1) + ". " + zapysList.get(i));
                    }
                }
            }
        }
    }
}