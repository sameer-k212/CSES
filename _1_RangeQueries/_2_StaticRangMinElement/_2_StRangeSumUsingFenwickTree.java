package _1_RangeQueries._2_StaticRangMinElement;

public class _2_StRangeSumUsingFenwickTree {
    class FenwickTree {
        int[] bit;
        int n;

        FenwickTree(int[] arr) {
            n = arr.length;
            bit = new int[n + 1];
            build(arr);
        }

        // Build Fenwick Tree in O(n)
        void build(int[] arr) {
            for (int i = 1; i <= n; i++) {
                bit[i] += arr[i - 1];
                int parent = i + (i & -i);
                if (parent <= n) {
                    bit[parent] += bit[i];
                }
            }
        }

        // Prefix sum [1..idx]
        int prefixSum(int idx) {
            int sum = 0;
            idx++; // convert 0-based to 1-based
            while (idx > 0) {
                sum += bit[idx];
                idx -= (idx & -idx);
            }
            return sum;
        }

        // Range sum [l..r]
        int rangeSum(int l, int r) {
            return prefixSum(r) - (l > 0 ? prefixSum(l - 1) : 0);
        }
    }
}
