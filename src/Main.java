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
        arr2.add("Hello");
        arr2.add("World");
        arr2.add("Mein");
        arr2.add("Gott");

//        for(String str : arr.toArray()){
//            System.out.println(str);
//        }
//
//        System.out.println();
//
//        arr.add(1, "Morgan");
//        for(String str : arr.toArray()){
//            System.out.println(str);
//        }
//        System.out.println();
//        arr.set(0, "Guten");
//        for(String str : arr.toArray()){
//            System.out.println(str);
//        }
        for(String str : arr.toArray()){
            System.out.println(str);
        }
        System.out.println();
        System.out.println("Удален: " + arr.remove("Hello"));
        System.out.println();
        for(String str : arr.toArray()){
            System.out.println(str);
        }
//        System.out.println();

//        System.out.println("Удален: " + arr.remove(2));
//        System.out.println();
//        for(String str : arr.toArray()){
//            System.out.println(str);
//        }
//        System.out.println();
//        System.out.println(arr.contains("World"));
//        System.out.println();
//        System.out.println(arr.indexOf("World"));
//        System.out.println();
//        System.out.println(arr.lastIndexOf("World"));
//        System.out.println();
//        System.out.println(arr.get(1));
//        System.out.println();
//        System.out.println(arr.equals(arr2));
//        System.out.println();
//        System.out.println(arr.size());
//        System.out.println();
//        System.out.println(arr.isEmpty());
//        System.out.println();
//        arr.clear();
//        System.out.println();

    }
}