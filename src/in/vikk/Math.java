package in.vikk;

public class Math {
    public static int gcd(int a,int b) {
        if (a == 0) {
            return b;
        }

        return gcd(b%a, a);
    }

    public static void main(String[] args) {
        System.out.println(gcd(5,10));
        System.out.println(gcd(32,10));
        System.out.println(gcd(15,35));
        System.out.println(gcd(31,2));
    }
}
