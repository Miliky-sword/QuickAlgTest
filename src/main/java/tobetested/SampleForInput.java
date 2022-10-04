package tobetested;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SampleForInput {
    public static void main(String[] args) {
        // write your input here
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> ar = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ar.add(scanner.nextInt());
        }

        // deal your input here
        int count = getCount(ar);
        // write your output here
        System.out.println(count);
    }

    private static int getCount(List<Integer> ar) {
        return (int) Arrays.stream(ar.toArray()).count();
    }

}
