package _1_RangeQueries._2_StaticRangMinElement;
import java.io.*;
// segment tree solution.
//Segment Tree → Range query + complex operations
//        (min, max, gcd, sum, custom merge)

class _1_StRangeMinElementUsingSegmentTree {
    static class FastScanner {
        private final byte[] buffer = new byte[1 << 16]; // 64 KB
        private int ptr = 0, len = 0;
        private final InputStream in = System.in;

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int c, sign = 1;
            long res = 0;
            do c = read(); while (c <= ' ');
            if (c == '-') {
                sign = -1;
                c = read();
            }
            while (c > ' ') {
                res = res * 10 + (c - '0');
                c = read();
            }
            return res * sign;
        }
    }
    long segmentTree[];

    public _1_StRangeMinElementUsingSegmentTree(int n) {
        segmentTree = new long[n * 4]; // Allocate enough space for segment tree
    }

    public void buildSegmentTree(long arr[], int idx, int low, int heigh) {
        if (low == heigh) {
            segmentTree[idx] = arr[low];
            return;
        }
        int mid = (low + heigh) / 2;
        buildSegmentTree(arr, 2 * idx + 1, low, mid);
        buildSegmentTree(arr, 2 * idx + 2, mid + 1, heigh);
        segmentTree[idx] = Math.min(segmentTree[2 * idx + 1], segmentTree[2 * idx + 2]);
    }

    public long minNumber(int idx, int low, int heigh, int left, int right) {
        if (left <= low && heigh <= right) { // Complete overlap
            return segmentTree[idx];
        }
        if (heigh < left || low > right) { // No overlap
            return Integer.MAX_VALUE;
        }

        int mid = (low + heigh) / 2;
        long l = minNumber(2 * idx + 1, low, mid, left, right);
        long r = minNumber(2 * idx + 2, mid + 1, heigh, left, right);
        return Math.min(l, r);
    }

    public static void main(String[] args) throws IOException {

        FastScanner fs = new FastScanner();
        StringBuilder out = new StringBuilder();

        int n = (int) fs.nextLong();
        int q = (int) fs.nextLong();

        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = fs.nextLong();
        }

        _1_StRangeMinElementUsingSegmentTree obj = new _1_StRangeMinElementUsingSegmentTree(n);
        obj.buildSegmentTree(arr, 0, 0, n - 1);
        while (q-- > 0) {
            int left = (int) fs.nextLong() - 1;
            int right = (int) fs.nextLong() - 1;

            out.append(obj.minNumber(0, 0, arr.length - 1, left, right)).append('\n');
        }

        System.out.print(out);
    }
}