package _1_RangeQueries._1_SaticRangeSum;

public class _2_StRangeSumUsingSegmentTree {
    static class SegmentTree {
        int[] tree;
        int n;

        SegmentTree(int[] arr) {
            n = arr.length;
            tree = new int[4 * n];
            build(0, 0, n - 1, arr);
        }

        // Build the segment tree
        void build(int node, int start, int end, int[] arr) {
            if (start == end) {
                tree[node] = arr[start];
                return;
            }
            int mid = (start + end) / 2;
            build(2 * node + 1, start, mid, arr);
            build(2 * node + 2, mid + 1, end, arr);
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }

        // Range sum query
        int query(int l, int r) {
            return queryUtil(0, 0, n - 1, l, r);
        }

        int queryUtil(int node, int start, int end, int l, int r) {
            if (r < start || end < l) return 0;            // No overlap
            if (l <= start && end <= r) return tree[node]; // Complete overlap

            int mid = (start + end) / 2;
            return queryUtil(2 * node + 1, start, mid, l, r)
                    + queryUtil(2 * node + 2, mid + 1, end, l, r);
        }
    }
}