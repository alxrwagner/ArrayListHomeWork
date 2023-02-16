import api.StringList;
import exeptions.IllegalParamExeption;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IllegalParamExeption {
        StringList arr = new DynamicArray();
        arr.add("Hello");
        arr.add("World");
        arr.add("Mein");
        arr.add("Gott");

        StringList arr2 = new DynamicArray();
        arr.add("Hello");
        arr.add("World");
        arr.add("Mein");
        arr.add("Gott");

        arr.add(1, "Morgan");
        System.out.println(arr);
        arr.set(0, "Guten");
        System.out.println(arr);
        arr.remove("Guten");
        System.out.println(arr);
        arr.remove(0);
        System.out.println(arr);
        System.out.println(arr.contains("World"));
        System.out.println(arr.indexOf("World"));
        arr.lastIndexOf("World");
        System.out.println(arr.get(1));
        System.out.println(arr.equals(arr2));
        System.out.println(arr.size());
        System.out.println(arr.isEmpty());
        System.out.println(arr.toArray());
        arr.clear();
        System.out.println(arr);

    }
}