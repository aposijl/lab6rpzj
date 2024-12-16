// клас-нащадок Rose
class Rose extends Flower {
    /**
     * конструктор класу Rose, що викликає конструктор батьківського класу Flower.
     * @param price          ціна троянди
     * @param freshnessLevel рівень свіжості троянди (від 1 до 10)
     * @param length         довжина стебла троянди
     */
    public Rose(double price, int freshnessLevel, int length) {
        super("троянда", price, freshnessLevel, length);
    }
}