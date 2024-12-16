import java.util.*;

/**
 * Клас, що реалізує типізовану колекцію на базі масиву, яка імплементує інтерфейс Set<Flower>.
 *
 * Внутрішня структура:
 * - Масив, початкового розміру 15 елементів.
 * - У разі переповнення місткості, масив збільшується на 30%.
 */
public class FlowerSet implements Set<Flower> {

    /**
     * Початковий розмір масиву.
     */
    private static final int INITIAL_CAPACITY = 15;

    /**
     * Коефіцієнт збільшення місткості (30%).
     */
    private static final double INCREMENT_FACTOR = 1.3;

    /**
     * Масив для зберігання елементів.
     */
    private Flower[] elements;

    /**
     * Поточний розмір колекції (кількість дійсно збережених елементів).
     */
    private int size;

    /**
     * Порожній конструктор – створює порожню колекцію.
     */
    public FlowerSet() {
        this.elements = new Flower[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Конструктор, який приймає один об’єкт Flower.
     * Додає його до новоствореної колекції.
     *
     * @param flower об’єкт Flower, який буде додано
     */
    public FlowerSet(Flower flower) {
        this();
        add(flower);
    }

    /**
     * Конструктор, що приймає стандартну колекцію об’єктів Flower.
     * Всі об’єкти буде додано до новоствореної колекції.
     *
     * @param flowers колекція об’єктів Flower
     */
    public FlowerSet(Collection<? extends Flower> flowers) {
        this();
        addAll(flowers);
    }

    /**
     * Метод для перевірки та збільшення місткості масиву, якщо він заповнений.
     */
    private void ensureCapacity() {
        if (size >= elements.length) {
            int newCapacity = (int) (elements.length * INCREMENT_FACTOR);
            Flower[] newElements = new Flower[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }

    /**
     * Додає елемент до колекції, якщо його ще немає.
     *
     * @param f квітка, яку потрібно додати
     * @return true, якщо елемент було додано, false, якщо він вже існує.
     * @throws IllegalArgumentException якщо переданий об'єкт null
     */
    @Override
    public boolean add(Flower f) {
        if (f == null) {
            throw new IllegalArgumentException("Неможливо додати null-елемент до колекції.");
        }
        if (contains(f)) {
            return false;
        }
        ensureCapacity();
        elements[size++] = f;
        return true;
    }

    /**
     * Додає всі елементи з заданої колекції.
     *
     * @param c колекція, елементи якої необхідно додати
     * @return true, якщо принаймні один елемент було додано
     * @throws IllegalArgumentException якщо колекція c == null
     */
    @Override
    public boolean addAll(Collection<? extends Flower> c) {
        if (c == null) {
            throw new IllegalArgumentException("Колекція для додавання не може бути null.");
        }
        boolean modified = false;
        for (Flower f : c) {
            if (add(f)) {
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Видаляє всі елементи з колекції.
     */
    @Override
    public void clear() {
        // Занулення посилань для збирача сміття
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * Перевіряє, чи містить колекція заданий елемент.
     *
     * @param o елемент для перевірки
     * @return true, якщо елемент є в колекції
     */
    @Override
    public boolean contains(Object o) {
        if (o == null) return false;
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Перевіряє, чи містить колекція всі елементи з вказаної колекції.
     *
     * @param c колекція елементів для перевірки
     * @return true, якщо всі елементи c містяться в поточній колекції
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) return false;
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Перевіряє, чи колекція порожня.
     *
     * @return true, якщо колекція не містить елементів
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Повертає ітератор для перебору елементів колекції.
     *
     * @return ітератор
     */
    @Override
    public Iterator<Flower> iterator() {
        return new Iterator<Flower>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public Flower next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Немає більше елементів для ітерації.");
                }
                return elements[currentIndex++];
            }
        };
    }

    /**
     * Видаляє елемент із колекції, якщо він у ній є.
     *
     * @param o елемент, який потрібно видалити
     * @return true, якщо елемент було видалено
     */
    @Override
    public boolean remove(Object o) {
        if (o == null) return false;
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                // Зсув елементів після видаленого
                System.arraycopy(elements, i+1, elements, i, size - i - 1);
                elements[--size] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * Видаляє з колекції всі елементи, які містяться у вказаній колекції.
     *
     * @param c колекція елементів для видалення
     * @return true, якщо принаймні один елемент було видалено
     * @throws IllegalArgumentException якщо c == null
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new IllegalArgumentException("Колекція для видалення не може бути null.");
        }
        boolean modified = false;
        for (Object o : c) {
            while (contains(o)) {
                remove(o);
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Залишає у колекції лише ті елементи, які містяться у вказаній колекції.
     *
     * @param c колекція елементів, які треба зберегти
     * @return true, якщо колекцію було змінено
     * @throws IllegalArgumentException якщо c == null
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new IllegalArgumentException("Колекція для операції retainAll не може бути null.");
        }
        boolean modified = false;
        for (int i = 0; i < size; ) {
            if (!c.contains(elements[i])) {
                remove(elements[i]);
                modified = true;
            } else {
                i++;
            }
        }
        return modified;
    }

    /**
     * Повертає кількість елементів у колекції.
     *
     * @return розмір колекції
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Повертає масив об’єктів, який містить усі елементи з колекції.
     *
     * @return масив об’єктів
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    /**
     * Повертає масив, типізований під T, що містить усі елементи колекції.
     * Якщо розмір наданого масиву меньше за size, створюється новий масив потрібного розміру.
     *
     * @param <T> тип масиву
     * @param a масив, у який будуть скопійовані елементи
     * @return масив із елементами
     * @throws ArrayStoreException якщо тип масиву несумісний із типом елементів
     * @throws NullPointerException якщо масив a == null
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        if (a == null) {
            throw new NullPointerException("Масив не може бути null.");
        }
        if (a.length < size) {
            // Створюємо новий масив типу T
            return (T[]) Arrays.copyOf(elements, size, a.getClass());
        }
        System.arraycopy(elements, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    /**
     * Порівняння колекції з іншим об’єктом на рівність.
     * Відповідає специфікації інтерфейсу Set: два набори рівні, якщо містять ті самі елементи.
     *
     * @param o інший об’єкт
     * @return true, якщо об’єкти рівні
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Set)) return false;
        Set<?> other = (Set<?>) o;
        if (other.size() != this.size) return false;
        return containsAll(other) && other.containsAll(this);
    }

    /**
     * Обчислює хеш-код колекції.
     *
     * @return хеш-код
     */
    @Override
    public int hashCode() {
        int hash = 0;
        for (Flower f : this) {
            hash += f.hashCode();
        }
        return hash;
    }
}
