package _1_RangeQueries._3_DynamicRangeSumQ;
import java.io.*;

public class _3_DyRangeSumUsingFenwickTree {

    // ---------- FAST INPUT ----------
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

    // ---------- FENWICK TREE ----------
    static class FenwickTree {
        long[] bit;
        int n;

        FenwickTree(int n) {
            this.n = n;
            bit = new long[n + 1];
        }

        // add delta at index idx (0-based)
        void update(int idx, long delta) {
            idx++; // 1-based
            while (idx <= n) {
                bit[idx] += delta;
                idx += (idx & -idx);
            }
        }

        // prefix sum [0..idx]
        long prefixSum(int idx) {
            long sum = 0;
            idx++; // 1-based
            while (idx > 0) {
                sum += bit[idx];
                idx -= (idx & -idx);
            }
            return sum;
        }

        // range sum [l..r]
        long rangeSum(int l, int r) {
            return prefixSum(r) - (l > 0 ? prefixSum(l - 1) : 0);
        }
    }

    // ---------- MAIN ----------
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        StringBuilder out = new StringBuilder();

        int n = fs.nextInt();
        int q = fs.nextInt();

        long[] arr = new long[n];
        FenwickTree ft = new FenwickTree(n);

        // input + build fenwick
        for (int i = 0; i < n; i++) {
            arr[i] = fs.nextInt();
            ft.update(i, arr[i]);
        }

        while (q-- > 0) {
            int o = fs.nextInt();

            if (o == 2) { // range sum (1-based)
                int l = fs.nextInt() - 1;
                int r = fs.nextInt() - 1;
                out.append(ft.rangeSum(l, r)).append('\n');
            } else { // update (set value)
                int idx = fs.nextInt() - 1;
                long val = fs.nextInt();

                long diff = val - arr[idx];
                arr[idx] = val;
                ft.update(idx, diff);
            }
        }

        System.out.print(out);
    }
}
