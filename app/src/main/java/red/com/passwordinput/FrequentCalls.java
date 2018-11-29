package red.com.passwordinput;

import java.util.ArrayList;

public class FrequentCalls {

    public boolean checkForMatches(ArrayList<String> stringList,
                                   String regex, int n, String comparision) {

        for (String i : stringList) {
            if (i.split(regex)[n].equalsIgnoreCase(comparision)) return true;
        }
        return false;
    }

    public boolean checkForMatches(ArrayList<String> stringList,
                                   String regex, String[] comparisionArray) {

        int iterationsRight = 0;
        for (String i : stringList) {
            for (int x = 0; x < comparisionArray.length * 2 - 2; x++) {
                if (i.split(regex)[x].equalsIgnoreCase(comparisionArray[x])) iterationsRight++;
            }
        }

        return iterationsRight == comparisionArray.length;
    }
}
