package org.example;

import java.io.*;
import java.util.*;

public class FindSystem {
    public static void find(String request, int columnNumber) {
        columnNumber = columnNumber - 1;
        long time = System.currentTimeMillis();
        Map<String, String> map = new TreeMap<>();
        if (!Character.isDigit(request.charAt(0)) && request.charAt(0) != '-') {
            request = "\"" + request.toLowerCase();
        }

        try (InputStream is = FindSystem.class.getResourceAsStream("/airports.csv")) {
            if (is == null) {
                System.out.println("File not found");
                return;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] elements = line.split(",");
                String elem = elements[columnNumber];
                if (elem.toLowerCase().startsWith(request)){
                    map.put(Arrays.toString(elements), elem);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        } catch (Exception e) {
            System.out.println(e.getClass());
            return;
        }
        valueSort(map).keySet().forEach(key -> {
            System.out.print(map.get(key));
            System.out.println(key);
        });
        System.out.println("Количество найденных строк: " + map.size() +
                "\t\t" +
                "Время, затраченное на поиск: " + (System.currentTimeMillis() - time) + "мс");
    }

    public static <K, V extends Comparable<V> > Map<K, V> valueSort(final Map<K, V> map)
    {
        Comparator<K> valueComparator = (k1, k2) -> {
            int comp = map.get(k1).compareTo(
                    map.get(k2));
            if (comp == 0)
                return 1;
            else
                return comp;
        };

        Map<K, V> sorted = new TreeMap<K, V>(valueComparator);

        sorted.putAll(map);

        return sorted;
    }

}
