:x
:x
using namespace std;
typedef	long long ll;
int main() {
	int idx=6;
	char x[]="out00000000000000000000000000000000.txt";
	for (int ii=0;ii<30;ii++){
		if(x[idx]=='9') idx++;
		freopen(x,"w",stdout);
		x[idx]++;
		
		int maxN=100000;
		int minN=1;
		int maxI=100;
		int minI=1;
		int n=rand()%(maxN-minN+1)+minN;
		int k=rand()%(min(n,20)+1);
		cout << n << " " << k << endl;
		for(int i=0;i<n;i++) {
			cout << rand()%(maxI-minI+1)+minI;
			if(i!=n-1) cout << " ";
		} cout << endl;
	}
}
