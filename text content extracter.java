import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCases = Integer.parseInt(in.nextLine());
        
        // Regex Breakdown:
        // <(.+)>       : Matches a start tag and captures the name in group 1
        // ([^<]+)      : Matches the content (anything that isn't a '<') in group 2
        // </\\1>       : Matches the closing tag using a backreference to group 1
        String regex = "<(.+)>([^<]+)</\\1>";
        Pattern pattern = Pattern.compile(regex);

        while (testCases-- > 0) {
            String line = in.nextLine();
            Matcher m = pattern.matcher(line);
            boolean found = false;

            while (m.find()) {
                System.out.println(m.group(2));
                found = true;
            }
            if (!found) {
                System.out.println("None");
            }
        }
    }
}
