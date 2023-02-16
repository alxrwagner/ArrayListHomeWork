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
            growSize();
            internalArray[size] = item;
            size++;
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
            String[] newSize = new String[internalArray.length + DEFAULT_SIZE_ARRAY];
            System.arraycopy(internalArray, 0, newSize, 0, size);
            internalArray = newSize;
        }
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
        if (item == null) {
            throw new IllegalParamExeption("The element being added cannot be null");
        } else {
            for (int i = 0; i < size; i++) {
                if (internalArray[i].equals(item)) {
                    result = remove(i);
                }
            }
        }
        if (result == null){
            throw new IllegalParamExeption("Not found element: " + item);
        }
        return result;
    }

    @Override
    public String remove(int index) throws IllegalParamExeption {
        String result;

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
