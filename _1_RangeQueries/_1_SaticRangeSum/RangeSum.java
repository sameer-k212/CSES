package _1_RangeQueries._1_SaticRangeSum;
import java.io.*;

public class RangeSum {
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

        long[] prefix = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + fs.nextInt();
        }

        while (q-- > 0) {
            int l = fs.nextInt();
            int r = fs.nextInt();
            out.append(prefix[r] - prefix[l - 1]).append('\n');
        }

        System.out.print(out);
    }
}
