import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Map.Entry.comparingByKey;

public class FunctionalProgramming {
    public static void main(String[] args) {
        String str = "Vasya Roman Dimon Marina Vasya Artem Natasha Sergey Marina Roman Sergey Alexandr Dimon John Roman Marina Artem Eduard Ira Dimon Dimon Natasha Sergey Alexandr Eduard Gena Dimon John Natasha Vasya Ira Vasya Alexandr Eduard Roman Ira Gena Marina";
        Stream<String> stringStream = new ArrayList<>(List.of(str.split(" "))).stream();
        Map<String, Long> wordsMap = stringStream
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        wordsMap.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed().thenComparing(comparingByKey()))
                .limit(10)
                .forEach(System.out::println);
    }
}
