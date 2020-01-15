package in.vikk.hackerrank.ipk.wamup;

public class RepeatedString {
    static long repeatedString(String s, long n) {
        int aStrCounter = 0;
        for (char c : s.toCharArray()) {
            if (c == 'a') {
                aStrCounter += 1;
            }
        }
        long growthFactor = n / s.length();
        long reminder = n % s.length();
        int reminderCount = 0;

        for (int i = 0; i < reminder ; i++) {
            if (s.charAt(i) == 'a') {
                reminderCount += 1;
            }
        }

        return (aStrCounter * growthFactor) + reminderCount;
    }

    public static void main(String[] args) {
        System.out.println(repeatedString("abcac", 10));
        System.out.println(repeatedString("aba", 10));
        System.out.println(repeatedString("a", 1000000000000l));
        System.out.println(repeatedString("b", 1000000000000l));
    }
}
