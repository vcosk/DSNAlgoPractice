package in.vikk.hackerrank.ipk.wamup;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JumpingOnClouds {
    static int jumpingOnClouds(int[] c) {
        LinkedList<Integer> path = new LinkedList<>();
        path.add(0);
        int counter = 1;
        while (counter < c.length) {
            int possibleJump1 = path.peekLast() + 1;
            int possibleJump2 = possibleJump1 + 1;

            if (possibleJump2 < c.length && c[possibleJump2] == 0) {
                path.add(possibleJump2);
            }
            else {
                if (possibleJump1 < c.length) {
                    path.add(possibleJump1);
                }
                else {
                    break;
                }
            }
            counter = path.peekLast();
        }
        return path.size() - 1;
    }

    public static void main(String[] args) {
        System.out.println(jumpingOnClouds(new int[] {
                0,1,0,0,0,1,0
        }));

        System.out.println(jumpingOnClouds(new int[] {
                0,0,1,0,0,1,0
        }));

        System.out.println(jumpingOnClouds(new int[] {
                0,0,0,0,1,0
        }));

        System.out.println(jumpingOnClouds(new int[] {
                0,0
        }));
    }
}
