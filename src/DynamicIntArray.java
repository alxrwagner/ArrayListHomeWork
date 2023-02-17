import api.StringList;
import exeptions.IllegalParamExeption;

import java.util.Arrays;
import java.util.Collection;

public class DynamicIntArray<T> implements StringList<Integer> {
    private final int DEFAULT_SIZE_ARRAY = 10;
    private int size;

    private Integer[] internalArray;

    public DynamicIntArray() {
        internalArray = new Integer[DEFAULT_SIZE_ARRAY];
    }

    public DynamicIntArray(int initialSize) throws IllegalParamExeption {
        if (initialSize > 0) {
            internalArray = new Integer[initialSize];
        } else if (initialSize == 0) {
            internalArray = new Integer[DEFAULT_SIZE_ARRAY];
        } else {
            throw new IllegalParamExeption("Illegal size " + initialSize);
        }
    }

    public DynamicIntArray(Collection<String> initialArray) {
        Object[] array = initialArray.toArray();

        if (array.length > 0) {
            size = array.length;
            internalArray = Arrays.copyOf(array, size, Integer[].class);
        } else {
            internalArray = new Integer[DEFAULT_SIZE_ARRAY];
        }
    }

    @Override
    public Integer add(Integer item) throws IllegalParamExeption {
        if (item == null) {
            throw new IllegalParamExeption("The element being added cannot be null");
        } else {
            growSize();
            internalArray[size] = item;
            size++;
        }

        return item;
    }

    @Override
    public Integer add(int index, Integer item) throws IllegalParamExeption {
        if (item == null) {
            throw new IllegalParamExeption("The element being added cannot be null");
        } else {
            if (index > size || index < 0) {
                throw new IllegalParamExeption("Going out of range");
            } else {
                growSize();
                System.arraycopy(internalArray, index, internalArray, index + 1, size);
                internalArray[index] = item;
                size++;
            }
        }
        return item;
    }

    public void growSize() {
        if (internalArray.length - size < 3) {
            Integer[] newSize = new Integer[internalArray.length + DEFAULT_SIZE_ARRAY];
            System.arraycopy(internalArray, 0, newSize, 0, size);
            internalArray = newSize;
        }
    }

    @Override
    public Integer set(int index, Integer item) throws IllegalParamExeption {
        if (item == null) {
            throw new IllegalParamExeption("The element being added cannot be null");
        } else {
            if (index > size || index < 0) {
                throw new IllegalParamExeption("Going out of range");
            } else {
                internalArray[index] = item;
            }
        }
        return item;
    }

    @Override
    public Integer remove(Integer item) throws IllegalParamExeption {

        Integer result = null;
        if (item == null) {
            throw new IllegalParamExeption("The element being added cannot be null");
        } else {
            for (int i = 0; i < size; i++) {
                if (internalArray[i].equals(item)) {
                    result = remove(i);
                }
            }
        }
        if (result == null) {
            throw new IllegalParamExeption("Not found element: " + item);
        }
        return result;
    }

    @Override
    public Integer remove(int index) throws IllegalParamExeption {
        Integer result;

        if (index < 0 || index > size) {
            throw new IllegalParamExeption("Going out of range");
        } else {
            result = internalArray[index];
            System.arraycopy(internalArray, index + 1, internalArray, index, size);
            size--;
        }
        return result;
    }

    @Override
    public boolean contains(Integer item) {
        sortInsertion();
        return binarySearch(item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (internalArray[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        if (!isEmpty()) {
            for (int i = size - 1; i >= 0; i--) {
                if (internalArray[i].equals(item)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) throws IllegalParamExeption {
        if (index < 0 || index > size) {
            throw new IllegalParamExeption("Going out of range");
        } else {
            return internalArray[index];
        }
    }

    @Override
    public boolean equals(StringList<Integer> otherList) throws IllegalParamExeption {
        if (otherList == null) {
            throw new IllegalParamExeption("The list cannot be null");
        } else if (size != otherList.size()) {
            return false;
        } else {
            for (int i = 0; i < size; i++) {
                if (!internalArray[i].equals(otherList.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return !(size > 0);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            internalArray[i] = null;
        }
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(internalArray, size);
    }

    private boolean binarySearch(int num) {
        int min = 0;
        int max = internalArray[internalArray.length - 1];

        while (min <= max) {
            int mid = (min + max) / 2;

            if (internalArray[mid] == num) {
                return true;
            }

            if (num < internalArray[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    private void sortInsertion() {
        for (int i = 1; i < internalArray.length; i++) {
            int j = i;
            int num = internalArray[i];

            while (j > 0 & internalArray[j - 1] >= num) {
                internalArray[j] = internalArray[j - 1];
                j--;
            }
            internalArray[j] = num;
        }
    }
}
