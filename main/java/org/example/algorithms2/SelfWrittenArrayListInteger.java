package org.example.algorithms2;

import org.example.algorithms1.ElementNotFoundException;
import org.example.algorithms1.InvalidIndexException;
import org.example.algorithms1.InvalidItemException;
import org.example.algorithms1.InvalidSizeException;

import java.util.Arrays;

public class SelfWrittenArrayListInteger implements IntegerList {

    private Integer[] storage;
    private int size;

    public SelfWrittenArrayListInteger() {
        storage = new Integer[10];
    }

    public SelfWrittenArrayListInteger(int size) {
        storage = new Integer[size];
    }

    @Override
    public Integer add(Integer num) {
        if (size == storage.length) {
            grow();
        }
        if (num == null) {
            new InvalidItemException("Некорректное число.");
        }
        storage[size++] = num;
        return num;
    }

    @Override
    public Integer add(int index, Integer num) {
        if (size == storage.length) {
            grow();
        }
        if (num == null) {
            new InvalidItemException("Некорректное число.");
        }
        if (index > size || index > storage.length) {
            new InvalidIndexException("Некорректный индекс.");
        }

        if (index == size) {
            storage[size++] = num;
            return num;
        }
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = num;
        size++;
        return num;
    }

    @Override
    public Integer set(int index, Integer num) {
        if (index > size || index > storage.length) {
            new InvalidIndexException("Некорректный индекс.");
        }
        if (num == null) {
            new InvalidItemException("Некорректная строка.");
        }
        storage[index] = num;
        return num;
    }

    @Override
    public Integer remove(Integer num) {
        if (num == null) {
            new InvalidItemException("Некорректная строка.");
        }

        int index = indexOf(num);

        if (index == -1) {
            throw new ElementNotFoundException();
        }

        if (index != size) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
        }

        size--;
        return num;
    }

    @Override
    public Integer remove(int index) {
        if (index == -1) {
            throw new ElementNotFoundException();
        }

        Integer num = storage[index];

        if (index != size) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
        }

        size--;
        return num;
    }

    @Override
    public boolean contains(Integer num) {
        Integer[] storageCopy = toArray();
        sort(storageCopy);
        return binarySearch(storageCopy, num);
    }

    @Override
    public int indexOf(Integer num) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(num)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer num) {
        for (int i = size - 1; i >= 0; i--) {
            if (storage[i].equals(num)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index > size || index > storage.length) {
            new InvalidIndexException("Некорректный индекс.");
        }
        return storage[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
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
    public Integer[] toArray() {
        return Arrays.copyOf(storage, size);
    }

    private void sort(Integer[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(Integer[] arr, int beginIndex, int endIndex) {
        if (beginIndex < endIndex) {
            int middleIndex = partition(arr, beginIndex, endIndex);

            quickSort(arr, beginIndex, middleIndex - 1);
            quickSort(arr, middleIndex + 1, endIndex);
        }
    }

    private int partition(Integer[] arr, int beginIndex, int endIndex) {
        int pivot = arr[endIndex];
        int i = (beginIndex - 1);

        for (int j = beginIndex; j < endIndex; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, endIndex);
        return i + 1;
    }

    private void swapElements(Integer[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    private boolean binarySearch(Integer[] arr, Integer element) {
        int min = 0;
        int max = arr.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (element == arr[mid]) {
                return true;
            }
            if (element < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    private void grow() {
        storage = Arrays.copyOf(storage, size + size / 2);
    }
}
