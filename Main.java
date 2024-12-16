import java.util.*;
public class Main {
    public static void main(String[] args) {
        try {
            /**
             * Створюємо кілька квітів.
             */
            Flower rose = new Rose(70.0, 8, 55);
            Flower tulip = new Tulip(40.0, 7, 35);
            Flower lily = new Lily(105.0, 9, 50);

            /**
             * Створюємо нашу типізовану колекцію FlowerSet
             */
            FlowerSet flowerSet = new FlowerSet();

            /**
             *Додаємо квіти
             */
            flowerSet.add(rose);
            flowerSet.add(tulip);
            flowerSet.add(lily);

            /**
             * Спробуємо додати ще раз ті ж квіти, щоб перевірити, що дублікатів не буде
             */
            flowerSet.add(rose);
            flowerSet.add(tulip);

            /**
             * Виводимо всі квіти в наборі
             */
            System.out.println("квіти у FlowerSet після додавання:");
            for (Flower f : flowerSet) {
                System.out.println(f.getInfo());
            }

            /**
             * Видаляємо тюльпан
             */
            flowerSet.remove(tulip);

            System.out.println("\nквіти у FlowerSet після видалення тюльпана:");
            for (Flower f : flowerSet) {
                System.out.println(f.getInfo());
            }

            /**
             * Додаємо колекцію квітів
             */
            List<Flower> newFlowers = Arrays.asList(
                    new Rose(80.0, 7, 50),
                    new Tulip(35.0, 9, 40)
            );
            flowerSet.addAll(newFlowers);

            System.out.println("\nквіти у FlowerSet після додавання нових квітів:");
            for (Flower f : flowerSet) {
                System.out.println(f.getInfo());
            }

            /**
             * Перевіримо розмір
             */
            System.out.println("\nрозмір FlowerSet: " + flowerSet.size());

        } catch (IllegalArgumentException | NoSuchElementException e) {
            System.out.println("помилка: " + e.getMessage());
        }
    }
}
