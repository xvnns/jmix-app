package com.company.jmixtest1;

public class MagicSquare {
    public static int findMinimum(int[][] arr, int[][] ms) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[i][j] != ms[i][j])
                    count++;
            }
        }
        return count;
    }

    public static int[][] find(int[] arr) {
        int[][] a = new int[3][3];
        for (int i = 0; i < 9; ++i) {
            a[i / 3][i % 3] = arr[i];
        }
        int[][][] ms = {
                {{8, 1, 6}, {3, 5, 7}, {4, 9, 2}},
                {{6, 1, 8}, {7, 5, 3}, {2, 9, 4}},
                {{4, 9, 2}, {3, 5, 7}, {8, 1, 6}},
                {{2, 9, 4}, {7, 5, 3}, {6, 1, 8}},
                {{8, 3, 4}, {1, 5, 9}, {6, 7, 2}},
                {{4, 3, 8}, {9, 5, 1}, {2, 7, 6}},
                {{6, 7, 2}, {1, 5, 9}, {8, 3, 4}},
                {{2, 7, 6}, {9, 5, 1}, {4, 3, 8}},
        };

        int min = 100;
        int idx = -1;
        for (int i = 0; i < 8; i++) {
            int x = findMinimum(a, ms[i]);
            if (x < min) {
                min = x;
                idx = i;
            }
        }
        return ms[idx];
    }
}
