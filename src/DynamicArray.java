import api.StringList;
import exeptions.IllegalParamExeption;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

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
                String[] newArray = Arrays.copyOf(internalArray, internalArray.length + DEFAULT_SIZE_ARRAY, String[].class);
                internalArray = newArray;
                internalArray[size + 1] = item;
            } else {
                internalArray[size + 1] = item;
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
                String[] part2 = new String[internalArray.length - index];
                System.arraycopy(internalArray, 0, part1, 0, index - 1);
                part1[index] = item;
                System.arraycopy(internalArray, index + 1, part2, 0, internalArray.length - index);
                internalArray = new String[part1.length + part2.length];
                System.arraycopy(part1, 0, internalArray, 0, part1.length);
                System.arraycopy(part2, 0, internalArray, part1.length - 1, part2.length);
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
        String[] resultArray = new String[internalArray.length - 1];

        if (item == null) {
            throw new IllegalParamExeption("The element being added cannot be null");
        } else {
            int indexResultArray = 0;

            if (Arrays.stream(internalArray).anyMatch(i -> i == item)) {
                for (int i = 0; i < resultArray.length; i++) {
                    if (!internalArray[i].equals(item)) {
                        resultArray[indexResultArray] = internalArray[i];
                        indexResultArray++;
                    } else {
                        result = item;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public String remove(int index) throws IllegalParamExeption {
        String result = null;
        String[] resultArray = new String[internalArray.length - 1];

        if (index < 0 || index > size){
            throw new IllegalParamExeption("Going out of range");
        }else {
            int indexResultArray = 0;
            for (int i = 0; i < resultArray.length; i++){
                if (i != index){
                    resultArray[indexResultArray] = internalArray[i];
                    indexResultArray++;
                }
                else{
                    result = internalArray[i];
                }
            }
        }
        return result;
    }

    @Override
    public boolean contains(String item) {
        return false;
    }

    @Override
    public int indexOf(String item) {
        return 0;
    }

    @Override
    public int lastIndexOf(String item) {
        return 0;
    }

    @Override
    public String get(int index) {
        return null;
    }

    @Override
    public boolean equals(StringList otherList) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public String[] toArray() {
        return new String[0];
    }
}
