import java.util.*;

public class Solution {

    public static boolean canWin(int leap, int[] game, int i) {
        // Base Cases
        if (i >= game.length) return true; // Won the game
        if (i < 0 || game[i] == 1) return false; // Out of bounds or hit an obstacle/visited

        // Mark current cell as visited by setting it to 1
        game[i] = 1;

        // Recursive Steps: Try jumping forward, walking forward, walking backward
        return canWin(leap, game, i + leap) || 
               canWin(leap, game, i + 1) || 
               canWin(leap, game, i - 1);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int q = scan.nextInt();
        while (q-- > 0) {
            int n = scan.nextInt();
            int leap = scan.nextInt();
            int[] game = new int[n];
            for (int i = 0; i < n; i++) {
                game[i] = scan.nextInt();
            }

            System.out.println(canWin(leap, game, 0) ? "YES" : "NO");
        }
        scan.close();
    }
}
