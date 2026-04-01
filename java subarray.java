import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        // Step 1: Read the length of the array
        int n = scan.nextInt();
        int[] a = new int[n];
        
        // Step 2: Fill the array with input values
        for (int i = 0; i < n; i++) {
            a[i] = scan.nextInt();
        }
        scan.close();

        int negativeCount = 0;

        // Step 3: Iterate through all possible subarrays
        for (int i = 0; i < n; i++) {
            int currentSum = 0;
            for (int j = i; j < n; j++) {
                // Add the current element to the running sum of the subarray [i...j]
                currentSum += a[j];
                
                // If the sum is negative, increment our counter
                if (currentSum < 0) {
                    negativeCount++;
                }
            }
        }

        // Step 4: Print the final count
        System.out.println(negativeCount);
    }
}
