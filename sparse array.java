import java.util.*;
import java.util.stream.*;
import java.util.function.Function;

public class Solution {

    public static List<Integer> matchingStrings(List<String> stringList, List<String> queries) {
        // Use Java 8 Streams to create a frequency map: {string -> count}
        Map<String, Long> frequencyMap = stringList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Map each query to its count from the map, defaulting to 0 if not found
        return queries.stream()
                .map(q -> frequencyMap.getOrDefault(q, 0L).intValue())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read stringList
        int n = scanner.nextInt();
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            stringList.add(scanner.next());
        }

        // Read queries
        int qCount = scanner.nextInt();
        List<String> queries = new ArrayList<>();
        for (int i = 0; i < qCount; i++) {
            queries.add(scanner.next());
        }

        // Get results and print them
        List<Integer> results = matchingStrings(stringList, queries);
        results.forEach(System.out::println);

        scanner.close();
    }
}
