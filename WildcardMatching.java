import java.util.ArrayList;
import java.util.List;

public class WildcardMatching {
    public static void main(String[] args) {
        String s = "acdcb";
        String p = "a*c?b";
        System.out.println(isMatch(s,p));
    }

    public static boolean isMatch(String s, String p) {
        int i = 0;
        int j = 0;
        int starIndex = -1;
        int iIndex = -1;

        while (i < s.length()) {
            if (j < p.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
                ++i;
                ++j;
            } else if (j < p.length() && p.charAt(j) == '*') {
                starIndex = j;
                iIndex = i;
                j++;
            } else if (starIndex != -1) {
                j = starIndex + 1;
                i = iIndex+1;
                iIndex++;
            } else {
                return false;
            }
        }

        while (j < p.length() && p.charAt(j) == '*') {
            ++j;
        }

        return j == p.length();
    }

    public static void displayMatrix(boolean[][] dp){
        System.out.println("dp is as follows:");
        for(boolean[] array:dp){
            for(boolean element: array){
                System.out.print(element+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void displayCharArray(char[] charArray){
        for(char ch: charArray){
            System.out.print(ch+" ");
        }
        System.out.println();
    }
}
