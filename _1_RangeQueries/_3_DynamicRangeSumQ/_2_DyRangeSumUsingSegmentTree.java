package _1_RangeQueries._3_DynamicRangeSumQ;

public class _2_DyRangeSumUsingSegmentTree {
    class SegmentTree {
        int[] tree;
        int n;

        SegmentTree(int[] arr) {
            n = arr.length;
            tree = new int[4 * n];
            build(0, 0, n - 1, arr);
        }

        // Build tree
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
            if (r < start || end < l) return 0;          // No overlap
            if (l <= start && end <= r) return tree[node]; // Complete overlap

            int mid = (start + end) / 2;
            return queryUtil(2 * node + 1, start, mid, l, r)
                    + queryUtil(2 * node + 2, mid + 1, end, l, r);
        }

        // Point update
        void update(int idx, int val) {
            updateUtil(0, 0, n - 1, idx, val);
        }

        void updateUtil(int node, int start, int end, int idx, int val) {
            if (start == end) {
                tree[node] = val;
                return;
            }
            int mid = (start + end) / 2;
            if (idx <= mid)
                updateUtil(2 * node + 1, start, mid, idx, val);
            else
                updateUtil(2 * node + 2, mid + 1, end, idx, val);

            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

}
