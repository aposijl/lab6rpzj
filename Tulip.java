// клас-нащадок Tulip, що представляє конкретну квітку "Тюльпан"
class Tulip extends Flower {
    /**
     * конструктор класу Tulip, що викликає конструктор батьківського класу Flower.
     * @param price          ціна тюльпана
     * @param freshnessLevel рівень свіжості тюльпана (від 1 до 10)
     * @param length         довжина стебла тюльпана
     */
    public Tulip(double price, int freshnessLevel, int length) {
        super("тюльпан", price, freshnessLevel, length);
    }
}
