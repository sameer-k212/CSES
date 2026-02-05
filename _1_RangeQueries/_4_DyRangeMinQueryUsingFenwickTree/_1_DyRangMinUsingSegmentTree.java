package _1_RangeQueries._4_DyRangeMinQueryUsingFenwickTree;//package _1_RangeQueries._4_DyRangeMinQueryUsingFenwickTree;//package _1_RangeQueries._4_DyRangeMinQueryUsingFenwickTree;

import java.io.IOException;
import java.io.InputStream;

class _1_DyRangMinUsingSegmentTree {

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

    // ---------- SEGMENT TREE ----------
    static class SegmentTreeMin {
        long[] tree;
        int n;

        SegmentTreeMin(long[] arr) {
            n = arr.length;
            tree = new long[4 * n];
            build(1, 0, n - 1, arr);
        }

        void build(int node, int start, int end, long[] arr) {
            if (start == end) {
                tree[node] = arr[start];
                return;
            }
            int mid = (start + end) / 2;
            build(2 * node, start, mid, arr);
            build(2 * node + 1, mid + 1, end, arr);
            tree[node] = Math.min(tree[2 * node], tree[2 * node + 1]);
        }

        void update(int idx, long val) {
            update(1, 0, n - 1, idx, val);
        }

        void update(int node, int start, int end, int idx, long val) {
            if (start == end) {
                tree[node] = val;
                return;
            }

            int mid = (start + end) / 2;
            if (idx <= mid)
                update(2 * node, start, mid, idx, val);
            else
                update(2 * node + 1, mid + 1, end, idx, val);

            tree[node] = Math.min(tree[2 * node], tree[2 * node + 1]);
        }

        long query(int l, int r) {
            return query(1, 0, n - 1, l, r);
        }

        long query(int node, int start, int end, int l, int r) {
            if (r < start || end < l)
                return Long.MAX_VALUE;

            if (l <= start && end <= r)
                return tree[node];

            int mid = (start + end) / 2;
            return Math.min(
                    query(2 * node, start, mid, l, r),
                    query(2 * node + 1, mid + 1, end, l, r)
            );
        }
    }

    // ---------- MAIN ----------
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        StringBuilder out = new StringBuilder();

        int n = fs.nextInt();
        int q = fs.nextInt();

        long[] arr = new long[n];
        for (int i = 0; i < n; i++)
            arr[i] = fs.nextInt();

        SegmentTreeMin st = new SegmentTreeMin(arr);

        while (q-- > 0) {
            int o = fs.nextInt();

            if (o == 2) {  // range minimum query
                int l = fs.nextInt() - 1;
                int r = fs.nextInt() - 1;
                out.append(st.query(l, r)).append('\n');
            } else {       // point update
                int idx = fs.nextInt() - 1;
                long val = fs.nextInt();
                arr[idx] = val;
                st.update(idx, val);
            }
        }

        System.out.print(out);
    }
}
