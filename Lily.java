// клас-нащадок Lily
class Lily extends Flower {
    /**
     * конструктор класу Lily, що викликає конструктор батьківського класу Flower.
     * @param price          ціна лілії
     * @param freshnessLevel рівень свіжості лілії (від 1 до 10)
     * @param length         довжина стебла лілії
     */
    public Lily(double price, int freshnessLevel, int length) {
        super("лілія", price, freshnessLevel, length);
    }
}