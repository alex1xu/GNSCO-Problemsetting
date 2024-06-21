/*
 * Bored Person Finds Optimal Way to Take Pencil Out of Two Holes
 * AcceptedSolution
 * time: O(RC*4*4)
 * space: O(RC*4*2)
 *
 * couple of different solutions for this problem:
 * This solution will break the farm into 2 "components"
 * all locations which can be reached by travelling between some series of signs starting at the front is one component
 * likewise for the second component, except you start from the end
 * If there is only one component, print Moo
 * If there are no locations where the first component intersects the second, print impossible
 * otherwise keep track of all intersection points
 *
 * Alex Xu
 */
import java.util.*;
 
public class AcceptedSolution {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
 
		int R=scan.nextInt(),C=scan.nextInt(),N=scan.nextInt(),M=scan.nextInt();
		//forward is for / type signs
		//backward is for \ type signs
		boolean[][] forward=new boolean[R][C],backward=new boolean[R][C];
		for(int i=0;i<N;i++) forward[scan.nextInt()-1][scan.nextInt()-1]=true;
		for(int i=0;i<M;i++) backward[scan.nextInt()-1][scan.nextInt()-1]=true;

		/*
		 * dir:
		 * 0 ->
		 * 1 <-
		 * 2 down
		 * 3 up
		 */
		int[] dirX=new int[]{0,0,1,-1},dirY=new int[]{1,-1,0,0};

		ArrayDeque<State> que=new ArrayDeque<>();
		boolean[][][] reachFront=new boolean[R][C][4],reachBack=new boolean[R][C][4];
		boolean noneed=false;

		//first component
		que.add(new State(0,0,0));
		reachFront[0][0][0]=true;
		while(que.size()>0) {
			State cur=que.poll();

			int nextDir=cur.dir;
			if(forward[cur.x][cur.y]) nextDir=Math.abs(3-cur.dir);
			else if(backward[cur.x][cur.y]) nextDir=(cur.dir+2)%4;

			int nextX=cur.x+dirX[nextDir],nextY=cur.y+dirY[nextDir];
			if(nextX==R-1&&nextY==C&&nextDir==0) {
				noneed=true;
				break;
			}
			if(nextX<0||nextX>=R||nextY<0||nextY>=C) continue;
			if(reachFront[nextX][nextY][nextDir]) continue;
			
			que.add(new State(nextX,nextY,nextDir));
			reachFront[nextX][nextY][nextDir]=true;
		}

		//second component
		que.add(new State(R-1,C-1,1));
		reachBack[R-1][C-1][1]=true;
		while(que.size()>0) {
			State cur=que.poll();

			int nextDir=cur.dir;
			if(forward[cur.x][cur.y]) nextDir=Math.abs(3-cur.dir);
			else if(backward[cur.x][cur.y]) nextDir=(cur.dir+2)%4;

			int nextX=cur.x+dirX[nextDir],nextY=cur.y+dirY[nextDir];
			if(nextX<0||nextX>=R||nextY<0||nextY>=C) continue;
			if(reachBack[nextX][nextY][nextDir]) continue;
			
			que.add(new State(nextX,nextY,nextDir));
			reachBack[nextX][nextY][nextDir]=true;
		}

		if(noneed) System.out.println("Moo");
		else {
			//find overlap
			ArrayList<Sign> add=new ArrayList<>();
			for(int i=0;i<R;i++) {
		 o: for(int j=0;j<C;j++) {
					for(int di=0;di<4;di++) {
						for(int dj=0;dj<4;dj++) {
							if(reachFront[i][j][di]&&reachBack[i][j][dj]&&!forward[i][j]&&!backward[i][j]) {
								if(di<=1&&dj<=1) continue;
								if(di>=2&&dj>=2) continue;

								add.add(new Sign(i,j));
								continue o;
							}
						}
					}
				}
			}
			Collections.sort(add,(Sign s1,Sign s2)->s1.x==s2.x?Integer.compare(s1.y,s2.y):Integer.compare(s1.x,s2.x));
			if(add.size()==0) System.out.println("Impossible");
			else {
				System.out.println(add.size());
				for(Sign each:add) System.out.println((each.x+1)+" "+(each.y+1));
			}
		}
	}
	public static class Sign {
		Sign(int x,int y) {
			this.x=x;
			this.y=y;
		} int x,y;
	}
	public static class State {
		State(int x,int y,int dir) {
			this.x=x;
			this.y=y;
			this.dir=dir;
		} int x,y,dir;
	}
}
