import java.io.*;
import java.util.*;

public class Maxgen {
	public static FScanner scan;
	public static PrintWriter out;
	public static void main(String[] args) throws IOException {
		String file="test4";
//		scan=new FScanner();
//		out=new PrintWriter(System.out);
        scan=new FScanner(file);
        out=new PrintWriter(file+".out");

		int R=1000,C=1000,N=1000*1000,M=0;
		out.println(R+" "+C+" "+N+" "+M);
		for(int i=1;i<=R;i++) {
			for(int j=1;j<=C;j++) out.println(i+" "+j);
		}

		out.close();
	}


//---------------------------------SCANNER------------------------------------//

	public static class FScanner {
		BufferedReader br;
		StringTokenizer st;

		public FScanner(String file) throws IOException {
			br=new BufferedReader(new FileReader(file+".in"));
		}

		public FScanner() throws IOException {
			br=new BufferedReader(new InputStreamReader(System.in));
		}

		String next() {
			while(st==null||!st.hasMoreElements()) {
				try {
					st=new StringTokenizer(br.readLine());
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		String nextLine() {
			String str="";
			try {
				str=br.readLine();
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}

