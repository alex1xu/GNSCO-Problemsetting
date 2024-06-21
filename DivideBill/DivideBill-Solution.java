import java.util.*;
import java.io.*;
public class mainsol {
	public static FastScanner scan;
	public static PrintWriter out;
	public static void main(String[] args) throws Exception {
		scan=new FastScanner(System.in);
		out=new PrintWriter(System.out);

		int T=1;
// 		int T=scan.nextInt();
		while(T-->0) {

			int n=scan.nextInt();
			DSU dsu=new DSU(n);
			int[] spent=new int[n];
			for(int i=0;i<n;i++) {
			    spent[i]=scan.nextInt();
			    int p=scan.nextInt();
			    while(p-->0) dsu.union(i,scan.nextInt()-1);
			}
			
			HashMap<Integer,Long> sum=new HashMap<Integer,Long>();
			HashMap<Integer,Integer> size=new HashMap<Integer,Integer>();
			for(int i=0;i<n;i++) {
			    int par=dsu.find(i);
			    sum.put(par,sum.getOrDefault(par,0L)+spent[i]);
			    size.put(par,size.getOrDefault(par,0)+1);
			}
			
			for(int i=0;i<n;i++) {
			    int par=dsu.find(i);
			    out.print(((double)sum.get(par)/size.get(par))+" ");
			} out.println();

		} out.close();
	}
}

class DSU {
	public int n;
	public int[] parent;

	public DSU(int myn) {
		n=myn;
		parent=new int[n];
		for(int i=0;i<n;i++)
			parent[i]=i;
	}

	public int find(int u) {
		if(parent[u]==u) return u;
		return parent[u]=find(parent[u]);
	}

	public boolean union(int u,int v) {
		int r1=find(u), r2=find(v);

		if(r1==r2) return false;

		parent[r2]=r1;
		return true;
	}
}

class Triple {
	Triple(int a,int b,int c) {
		this.l=a;
		this.r=b;
		this.c=c;
	} int l,r;
	int c;
}

class Pair {
	int a,b;
	Pair(int a,int b) {
		this.a=a;
		this.b=b;
	}
}

class FastScanner {
	private InputStream stream;
	private byte[] buf=new byte[1024];
	private int curChar;
	private int numChars;

	public FastScanner(InputStream stream) { this.stream=stream; }

	int read() {
		if(numChars==-1) throw new InputMismatchException();
		if(curChar>=numChars) {
			curChar=0;
			try { numChars=stream.read(buf); }
			catch(IOException e) { throw new InputMismatchException(); }
			if(numChars<=0) return -1;
		} return buf[curChar++];
	}

	boolean isSpaceChar(int c) { return c==' '||c=='\n'||c=='\r'||c=='\t'||c==-1; }

	boolean isEndline(int c) { return c=='\n'||c=='\r'||c==-1; }

	int nextInt() { return Integer.parseInt(next()); }

	long nextLong() { return Long.parseLong(next()); }

	double nextDouble() { return Double.parseDouble(next()); }

	String next() {
		int c=read();
		while(isSpaceChar(c)) c=read();
		StringBuilder res=new StringBuilder();
		do {
			res.appendCodePoint(c);
			c=read();
		} while(!isSpaceChar(c));
		return res.toString();
	}

	String nextLine() {
		int c=read();
		while(isEndline(c)) c=read();
		StringBuilder res=new StringBuilder();
		do {
			res.appendCodePoint(c);
			c=read();
		} while(!isEndline(c));
		return res.toString();
	}
}
