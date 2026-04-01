#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define MAXN 500005
#define MAX_NODES 20000005

int n, a[MAXN], sorted[MAXN], unique_vals[MAXN], unique_cnt;
int st[20][MAXN], logs[MAXN];
int roots[MAXN], L[MAX_NODES], R[MAX_NODES], cnt[MAX_NODES], node_ptr;

// --- Sparse Table for RMQ ---
void build_sparse_table() {
    logs[1] = 0;
    for (int i = 2; i <= n; i++) logs[i] = logs[i / 2] + 1;
    for (int i = 1; i <= n; i++) st[0][i] = i;
    for (int j = 1; j < 20; j++) {
        for (int i = 1; i + (1 << j) - 1 <= n; i++) {
            int left = st[j - 1][i];
            int right = st[j - 1][i + (1 << (j - 1))];
            st[j][i] = (a[left] >= a[right]) ? left : right;
        }
    }
}

int query_max_idx(int l, int r) {
    int k = logs[r - l + 1];
    int left = st[k][l];
    int right = st[k][r - (1 << k) + 1];
    return (a[left] >= a[right]) ? left : right;
}

// --- Persistent Segment Tree ---
int compare(const void *a, const void *b) { return (*(int *)a - *(int *)b); }

int update(int prev, int l, int r, int val) {
    int node = ++node_ptr;
    L[node] = L[prev]; R[node] = R[prev]; cnt[node] = cnt[prev] + 1;
    if (l < r) {
        int mid = l + (r - l) / 2;
        if (val <= mid) L[node] = update(L[prev], l, mid, val);
        else R[node] = update(R[prev], mid + 1, r, val);
    }
    return node;
}

int query_pst(int u, int v, int l, int r, int k) {
    if (r <= k) return cnt[v] - cnt[u];
    int mid = l + (r - l) / 2;
    int res = query_pst(L[u], L[v], l, mid, k);
    if (k > mid) res += query_pst(R[u], R[v], mid + 1, r, k);
    return res;
}

int get_rank(int val) {
    int low = 0, high = unique_cnt - 1, ans = -1;
    while (low <= high) {
        int mid = (low + high) / 2;
        if (unique_vals[mid] <= val) { ans = mid; low = mid + 1; }
        else high = mid - 1;
    }
    return ans + 1;
}

// --- Recursive Solver ---
long long solve(int l, int r) {
    if (l >= r) return 0;
    int m = query_max_idx(l, r);
    long long total = 0;

    if (m - l < r - m) {
        for (int i = l; i <= m; i++) {
            int target_rank = get_rank(a[m] / a[i]);
            if (target_rank > 0) total += query_pst(roots[m < r ? m : m - 1], roots[r], 1, unique_cnt, target_rank);
        }
    } else {
        for (int j = m; j <= r; j++) {
            int target_rank = get_rank(a[m] / a[j]);
            if (target_rank > 0) total += query_pst(roots[l - 1], roots[m > l ? m - 1 : l - 1], 1, unique_cnt, target_rank);
        }
    }

    return total + solve(l, m - 1) + solve(m + 1, r);
}

int main() {
    if (scanf("%d", &n) != 1) return 0;
    for (int i = 1; i <= n; i++) {
        scanf("%d", &a[i]);
        sorted[i - 1] = a[i];
    }

    // Coordinate Compression
    qsort(sorted, n, sizeof(int), compare);
    unique_cnt = 0;
    for (int i = 0; i < n; i++) {
        if (i == 0 || sorted[i] != sorted[i - 1]) unique_vals[unique_cnt++] = sorted[i];
    }

    // Build PST
    for (int i = 1; i <= n; i++) {
        int rk = get_rank(a[i]);
        roots[i] = update(roots[i - 1], 1, unique_cnt, rk);
    }

    build_sparse_table();
    printf("%lld\n", solve(1, n));

    return 0;
}
