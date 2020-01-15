package in.vikk.hackerrank.ipk.wamup;

public class CountingVally {
    static int countingValleys(int n, String s) {
        final char up = 'U';
        final char down = 'D';
        int level = 0;
        int vallyDepth = 0;
        int mountainHeight = 0;
        int vallyCount = 0;
        int mountainCount = 0;
        for (char ch : s.toCharArray()) {
            if (ch == up) {
                level += 1;
            }
            else if (ch == down) {
                level -= 1;
            }

            if (level > 0) {
                if (level > mountainHeight) {
                    mountainHeight = level;
                }
            }
            else if (level < 0) {
                if (level < vallyDepth) {
                    vallyDepth = level;
                }
            }
            else {
                if (vallyDepth < 0) {
                    vallyCount += 1;
                    vallyDepth = 0;
                }
                else if (mountainHeight > 0) {
                    mountainCount += 1;
                    mountainHeight = 0;
                }
            }
        }

        return vallyCount;
    }

    public static void main(String[] args) {
        int vallyCount = countingValleys(8, "UDDDUDUU");
        System.out.println(vallyCount);
    }
}
