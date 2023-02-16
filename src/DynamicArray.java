import api.StringList;
import exeptions.IllegalParamExeption;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class DynamicArray implements StringList {
    private final int DEFAULT_SIZE_ARRAY = 10;
    private int size;

    private String[] internalArray;

    public DynamicArray() {
        internalArray = new String[DEFAULT_SIZE_ARRAY];
    }

    public DynamicArray(int initialSize) throws IllegalParamExeption {
        if (initialSize > 0) {
            internalArray = new String[initialSize];
        } else if (initialSize == 0) {
            internalArray = new String[DEFAULT_SIZE_ARRAY];
        } else {
            throw new IllegalParamExeption("Illegal size " + initialSize);
        }
    }

    public DynamicArray(Collection<String> initialArray) {
        Object[] array = initialArray.toArray();

        if (array.length > 0) {
            size = array.length;
            internalArray = Arrays.copyOf(array, size, String[].class);
        } else {
            internalArray = new String[DEFAULT_SIZE_ARRAY];
        }
    }

    @Override
    public String add(String item) throws IllegalParamExeption {
        if (item == null) {
            throw new IllegalParamExeption("The element being added cannot be null");
        } else {
            if (internalArray.length == size) {
                internalArray = Arrays.copyOf(internalArray, internalArray.length + DEFAULT_SIZE_ARRAY, String[].class);
                internalArray[size + 1] = item;
                size++;
            } else {
                internalArray[size + 1] = item;
                size++;
            }
        }

        return item;
    }

    @Override
    public String add(int index, String item) throws IllegalParamExeption {
        if (item == null) {
            throw new IllegalParamExeption("The element being added cannot be null");
        } else {
            if (index > size || index < 0) {
                throw new IllegalParamExeption("Going out of range");
            } else {
                String[] part1 = new String[index];
                String[] part2 = new String[size - index];
                System.arraycopy(internalArray, 0, part1, 0, index - 1);
                part1[index] = item;
                System.arraycopy(internalArray, index + 1, part2, 0, size - index);
                internalArray = new String[part1.length + part2.length];
                System.arraycopy(part1, 0, internalArray, 0, part1.length);
                System.arraycopy(part2, 0, internalArray, part1.length - 1, part2.length);
                size++;
            }
        }
        return item;
    }

    @Override
    public String set(int index, String item) throws IllegalParamExeption {
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
    public String remove(String item) throws IllegalParamExeption {
        String result = null;
        String[] resultArray = new String[size - 1];

        if (item == null) {
            throw new IllegalParamExeption("The element being added cannot be null");
        } else {
            int indexResultArray = 0;

            if (Arrays.stream(internalArray).anyMatch(i -> i.equals(item))) {
                for (int i = 0; i < resultArray.length; i++) {
                    if (!internalArray[i].equals(item)) {
                        resultArray[indexResultArray] = internalArray[i];
                        indexResultArray++;
                    } else {
                        size--;
                        result = item;
                    }
                }
                internalArray = resultArray;
            }
        }
        return result;
    }

    @Override
    public String remove(int index) throws IllegalParamExeption {
        String result = null;
        String[] resultArray = new String[size - 1];

        if (index < 0 || index > size) {
            throw new IllegalParamExeption("Going out of range");
        } else {
            int indexResultArray = 0;
            for (int i = 0; i < resultArray.length; i++) {
                if (i != index) {
                    resultArray[indexResultArray] = internalArray[i];
                    indexResultArray++;
                } else {
                    size--;
                    result = internalArray[i];
                }
            }
            internalArray = resultArray;
        }
        return result;
    }

    @Override
    public boolean contains(String item) {
        return Arrays.stream(internalArray).anyMatch(s -> s.equals(item));
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (internalArray[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
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
    public String get(int index) throws IllegalParamExeption {
        if (index < 0 || index > size) {
            throw new IllegalParamExeption("Going out of range");
        } else {
            return internalArray[index];
        }
    }

    @Override
    public boolean equals(StringList otherList) throws IllegalParamExeption {
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
    public String[] toArray() {
        String[] array = new String[size];
        System.arraycopy(internalArray, 0, array, 0, size);

        return array;
    }
}
