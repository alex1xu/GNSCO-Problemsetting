//{{{
#include "bits/stdc++.h"
using namespace std;
using ll = long long;
using I = int;
template <class T>
using V = vector<T>;
using vi = V<I>;
using vii = V<vi>;
using pii = pair<I, I>;
template <class T>
using uset = unordered_set<T>;
template <class K, class U>
using umap = unordered_map<K, U>;
using useti = uset<I>;
using umapi = umap<I, I>;
template <class T>
bool ckx(T& x, T v) {
  return v > x && (x = v, 1);
}
template <class T>
bool ckn(T& x, T v) {
  return v < x && (x = v, 1);
}
#define ftc  \
  I TC;      \
  cin >> TC; \
  f0(TCS, TC)
#define P push_back
#define E emplace_back
#define PP pop_back
#define F first
#define S second
#define L(x) ((int)(x).size())
#define ben(x) begin(x), end(x)
#define ST(l) sort(ben(l));
#define FOR(i, l, r, k) for (I(i) = (l); (i) < (r); (i) += (k))
#define fo(i, l, r) FOR(i, l, r, 1)
#define f0(i, r) fo(i, 0, r)
#define forr(i, r, l, k) for (I(i) = (r); (i) >= (l); (i) -= (k))
#define forl(i, r, l) FORR(i, r, l, 1)
#define fR(i, r) forl(i, r, 0)
#define fr(i, r) fR(i, (r)-1)
#define fi(r) f0(i, r)
#define fj(r) f0(j, r)
#define fk(r) f0(k, r)
#define fin fi(n)
#define fjn fj(n)
#define fkn fk(n)
//}}} const I M= 998244353;// 1000000007;

int main() {
  cin.tie(0)->sync_with_stdio(0);
  int n, k;
  cin >> n >> k;
  vi g(n);
  vi gt;
  vi lt;
  f0(i, n) {
    int gi;
    cin >> gi;
    if (gi >= 93) {
      gt.P(gi);
    } else {
      lt.P(gi);
    }
  }
  ST(gt);
  ST(lt);
  int dead = n - (L(gt) * (k + 1));
  if (dead > 0) {
    f0(i, dead) {
      cout << lt[i] << ' ';
    }
  }
  int li = max(0, dead);
  int gi = 0;
  while (1) {
    if (gi >= L(gt)) {
      break;
    }
    cout << gt[gi] << ' ';
    gi++;
    f0(i, k) {
      if (li >= L(lt)) {
        break;
      }
      cout << lt[li] << ' ';
      li++;
    }
  }
  cout << '\n';
}
