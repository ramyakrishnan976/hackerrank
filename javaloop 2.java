import java.util.*;
import java.io.*;

class Solution {
    public static void main(String []argh) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt(); // Number of queries
        
        for(int i = 0; i < t; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int n = in.nextInt();
            
            int currentSum = a;
            for(int j = 0; j < n; j++) {
                // Each term adds (2^j * b) to the previous total
                currentSum += (Math.pow(2, j) * b);
                System.out.print(currentSum + " ");
            }
            System.out.println(); // Move to next line after each query
        }
        in.close();
    }
}
