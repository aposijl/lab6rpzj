/**
 * Абстрактний клас Flower, що описує загальні властивості квітки.
 */
abstract class Flower {
    String name;
    double price;
    int freshnessLevel;
    int length;

    /**
     * Конструктор класу Flower, що ініціалізує загальні параметри для квітки.
     * @param name           назва квітки
     * @param price          ціна квітки
     * @param freshnessLevel рівень свіжості квітки
     * @param length         довжина стебла квітки
     * @throws IllegalArgumentException якщо ціна <= 0, рівень свіжості не в діапазоні 1-10, або довжина <= 0
     */
    public Flower(String name, double price, int freshnessLevel, int length) {
        if (price <= 0 || freshnessLevel < 1 || freshnessLevel > 10 || length <= 0) {
            throw new IllegalArgumentException("Неправильні параметри для квітки.");
        }
        this.name = name;
        this.price = price;
        this.freshnessLevel = freshnessLevel;
        this.length = length;
    }

    /**
     * Метод для отримання інформації про квітку.
     * @return рядок з інформацією про назву, ціну, свіжість та довжину квітки
     */
    public String getInfo() {
        return name + " | ціна: " + price + " | свіжість: " + freshnessLevel + " | довжина: " + length;
    }

    /**
     * Метод перевизначення equals для коректної роботи зі структурами даних, що базуються на порівнянні об’єктів.
     * @param o об’єкт для порівняння
     * @return true, якщо об’єкти рівні за всіма ключовими параметрами
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flower)) return false;
        Flower other = (Flower) o;
        return Double.compare(other.price, price) == 0
                && freshnessLevel == other.freshnessLevel
                && length == other.length
                && name.equals(other.name);
    }

    /**
     * Метод перевизначення hashCode для узгодженості з equals().
     * @return хеш-код об’єкта
     */
    @Override
    public int hashCode() {
        int result = name.hashCode();
        long priceBits = Double.doubleToLongBits(price);
        result = 31 * result + (int)(priceBits ^ (priceBits >>> 32));
        result = 31 * result + freshnessLevel;
        result = 31 * result + length;
        return result;
    }
}
