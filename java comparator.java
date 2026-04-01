import java.util.*;
class Checker implements Comparator<Player> {
    
    // 2. Implement the compare method
    @Override
    public int compare(Player a, Player b) {
        // If scores are different, sort by score descending
        if (a.score != b.score) {
            // Returning (b - a) results in descending order
            return b.score - a.score;
        }
        
        // If scores are equal, sort by name ascending (alphabetical)
        // String's compareTo already returns the correct values for ascending order
        return a.name.compareTo(b.name);
    }
}
// Write your Checker class here

class Player{
    String name;
    int score;
    
    Player(String name, int score){
        this.name = name;
        this.score = score;
    }
}

class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        Player[] player = new Player[n];
        Checker checker = new Checker();
        
        for(int i = 0; i < n; i++){
            player[i] = new Player(scan.next(), scan.nextInt());
        }
        scan.close();
     
        Arrays.sort(player, checker);
        for(int i = 0; i < player.length; i++){
            System.out.printf("%s %s\n", player[i].name, player[i].score);
        }
    }
}
