package _1_RangeQueries._3_DynamicRangeSumQ;

import java.io.*;
// use fanwick tree : range sum and point update.

public class _1_DyRangeSumUsingPrefixSum {
    static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final InputStream in = System.in;

        int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c, sign = 1, res = 0;
            do c = read(); while (c <= ' ');
            if (c == '-') { sign = -1; c = read(); }
            while (c > ' ') {
                res = res * 10 + (c - '0');
                c = read();
            }
            return res * sign;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        StringBuilder out = new StringBuilder();

        int n = fs.nextInt();
        int q = fs.nextInt();

        long[] arr = new long[n];
        long[] prefix = new long[n];

        // input + prefix (0-based)
        for (int i = 0; i < n; i++) {
            arr[i] = fs.nextInt();
            prefix[i] = arr[i] + (i > 0 ? prefix[i - 1] : 0);
        }

        while (q-- > 0) {
            int o = fs.nextInt();

            if (o == 2) { // range sum (1-based input)
                int l = fs.nextInt() - 1;
                int r = fs.nextInt() - 1;

                long ans = prefix[r] - (l > 0 ? prefix[l - 1] : 0);
                out.append(ans).append('\n');
            } else { // update (1-based index)
                int idx = fs.nextInt() - 1;
                int val = fs.nextInt();
                update(arr, prefix, idx, val);
            }
        }

        System.out.print(out);
    }

    static void update(long[] arr, long[] prefix, int idx, int val) {
        long diff = val - arr[idx];
        arr[idx] = val;
        for (int i = idx; i < prefix.length; i++) {
            prefix[i] += diff;
        }
    }
}

// cause tle
