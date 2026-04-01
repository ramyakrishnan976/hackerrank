import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // 1. Initial list setup
        int N = scan.nextInt();
        List<Integer> L = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            L.add(scan.nextInt());
        }

        // 2. Query processing
        int Q = scan.nextInt();
        for (int i = 0; i < Q; i++) {
            String queryType = scan.next();
            if (queryType.equals("Insert")) {
                int index = scan.nextInt();
                int value = scan.nextInt();
                L.add(index, value); // Shifts elements to the right
            } else if (queryType.equals("Delete")) {
                int index = scan.nextInt();
                L.remove(index); // Shifts elements to the left
            }
        }
        scan.close();

        // 3. Final output
        for (int j = 0; j < L.size(); j++) {
            System.out.print(L.get(j) + (j == L.size() - 1 ? "" : " "));
        }
    }
}
