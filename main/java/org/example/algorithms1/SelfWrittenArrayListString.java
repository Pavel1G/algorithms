package org.example.algorithms1;

import java.util.Arrays;

public class SelfWrittenArrayListString implements StringList {

    private final String[] storage;
    private int size;

    public SelfWrittenArrayListString() {
        storage = new String[10];
    }

    public SelfWrittenArrayListString(int size) {
        storage = new String[size];
    }

    @Override
    public String add(String item) {
        if (size == storage.length) {
            new InvalidSizeException("Массив полон");
        }
        if (item == null) {
            new InvalidItemException("Некорректная строка.");
        }
        storage[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        if (size == storage.length) {
            new InvalidSizeException("Массив полон");
        }
        if (item == null) {
            new InvalidItemException("Некорректная строка.");
        }
        if (index > size || index > storage.length) {
            new InvalidIndexException("Некорректный индекс.");
        }

        if (index == size) {
            storage[size++] = item;
            return item;
        }

        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        if (index > size || index > storage.length) {
            new InvalidIndexException("Некорректный индекс.");
        }
        if (item == null) {
            new InvalidItemException("Некорректная строка.");
        }
        storage[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        if (item == null) {
            new InvalidItemException("Некорректная строка.");
        }

        int index = indexOf(item);

        if (index == -1) {
            throw new ElementNotFoundException();
        }

        if (index != size) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
        }

        size--;
        return item;
    }

    @Override
    public String remove(int index) {
        if (index == -1) {
            throw new ElementNotFoundException();
        }

        String item = storage[index];

        if (index != size) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
        }

        size--;
        return item;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i >= 0; i--) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if (index > size || index > storage.length) {
            new InvalidIndexException("Некорректный индекс.");
        }
        return storage[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(storage, size);
    }
}
