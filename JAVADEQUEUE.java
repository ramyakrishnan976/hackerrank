import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Deque<Integer> deque = new ArrayDeque<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = in.nextInt();
        int m = in.nextInt();
        int maxUnique = 0;

        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            
            // Add current element to Deque and Map
            deque.addLast(num);
            map.put(num, map.getOrDefault(num, 0) + 1);
            
            // Once window reaches size M
            if (deque.size() == m) {
                // Check unique elements (map size is the count of unique keys)
                if (map.size() > maxUnique) maxUnique = map.size();
                
                // If we found the maximum possible unique numbers (m), we can exit early
                if (maxUnique == m) break;

                // Slide the window: remove the first element
                int first = deque.removeFirst();
                int count = map.get(first);
                if (count == 1) {
                    map.remove(first);
                } else {
                    map.put(first, count - 1);
                }
            }
        }
        System.out.println(maxUnique);
    }
}
