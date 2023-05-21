package one;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
// QItem for current location and distance
// from source location
class QItem {
	int row;
	int col;
	int dist;
	public QItem(int row, int col, int dist)
	{
		this.row = row;
		this.col = col;
		this.dist = dist;
	}
}

public class FinalGame {
	static int rowEndKd;
	static int columnEndKd;
	static int knownRowLengthd ;
	static int knowColumnLengthd;
	private static boolean isValidd(int x, int y,
			int[][] grid,
			boolean[][] visited)
	{
		if (x >= 0 && y >= 0 && x < knownRowLengthd
				&& y < knowColumnLengthd && grid[x][y] != 1
				&& visited[x][y] == false) {
			return true;
		}
		return false;
	}
	public static String dfs(String grid)
	{
		String [] arr = grid.split("[,;]+");
		int m = Integer.parseInt(arr[0]);
		knownRowLengthd = m;
		int n = Integer.parseInt(arr[1]);
		knowColumnLengthd = n;
		int [] [] matrix = new int[m][n];
		int rowStart = Integer.parseInt(arr[2]);
		rowEndKd = Integer.parseInt(arr[arr.length-2]);	
		int colStart = Integer.parseInt(arr[3]);
		columnEndKd = Integer.parseInt(arr[arr.length-1]);
		for (int i = 4 ; i < arr.length-3 ; i+=2)  
		{
			int row = Integer.parseInt(arr[i]);
			int col = Integer.parseInt(arr[i+1]);
			matrix[row][col] = 1;
		}
		return DFS(rowStart,colStart,matrix);
	}
	public static String DFS(int rowStart, int colStart, int matrix[][])
	{
		Stack<QItem> st = new Stack<QItem>();
		QItem[][] parent = new QItem[matrix.length][matrix[0].length];
		parent[rowStart][colStart] = new QItem(rowStart,colStart,0);
		boolean vis[][] = new boolean[matrix.length][matrix[0].length];
		st.push(new QItem(rowStart, colStart,0));
		int res = -1;
		while (!st.empty())
		{

			// Pop the top pair
			QItem curr = st.pop();
			int row = curr.row;
			int col = curr.col;
			//System.out.println(row + " " + col);
            int dist = curr.dist;
            vis[row][col] = true;
            if (row == rowEndKd && col == columnEndKd)
			{
				res = dist;
				break;
			}
			if (isValidd(row - 1, col, matrix, vis)) {
				st.add(new QItem(row - 1, col,dist + 1));
				parent[row - 1][col]=new QItem(row,col,0);
			}

			// moving down
			if (isValidd(row + 1, col, matrix, vis)) {
				st.add(new QItem(row + 1, col,dist + 1));
				parent[row + 1][col]=new QItem(row,col,0);
			}

			// moving left
			if (isValidd(row, col - 1, matrix, vis)) {
				st.add(new QItem(row, col - 1,dist + 1));
				parent[row][col-1]=new QItem(row,col,0);
			}

			// moving right
			if (isValidd(row, col + 1, matrix, vis)) {
				st.add(new QItem(row, col + 1,dist + 1));
				parent[row][col+1]=new QItem(row,col,0);
			}
		}
		if (res == -1)
		{
			//System.out.println("here");
			return "No Solution";
		}
		else 
		{
			String path = "";
			
			int r = rowEndKd;
			int c = columnEndKd;
			while(r != rowStart || c != colStart)
			{
				//System.out.println("row "+ r + " column" + c);
				int lastx = parent[r][c].row ;
				int lasty = parent[r][c].col;
				//System.out.println("prow "+ lastx + " pcolumn" + lasty);
				if (c > lasty)
					for (int i = 0 ; i < c - lasty ; i++)
						path = "right," + path;
				if (c < lasty)
					for (int i = 0 ; i < lasty - c ; i++)
						path = "left," + path;
				if (r > lastx)
					for (int i = 0 ; i < r - lastx ; i++)
						path = "down," + path;
				if (r < lastx)
					for (int i = 0 ; i < lastx - r ; i++)
						path = "up," + path;

				r = lastx;
				c = lasty;
			}
			if (res != 0)
				path = path.substring(0,path.length()-1);

			path += ";" + res;
			return path;
		}
	}
	private static boolean isValid(int x, int y,
			int[][] grid,
			boolean[][] visited)
	{
		if (x >= 0 && y >= 0 && x < knownRowLength
				&& y < knowColumnLength && grid[x][y] != 1
				&& visited[x][y] == false) {
			return true;
		}
		return false;
	}

	private static String minDistance(int rowStart , int colStart , int[][] grid)
	{
		QItem source = new QItem(rowStart, colStart, 0);
		Queue<QItem> queue = new LinkedList<>();
		queue.add(source);
		boolean[][] visited	= new boolean[grid.length][grid[0].length];
		QItem[][] parent = new QItem[grid.length][grid[0].length];
		parent[rowStart][colStart] = new QItem(rowStart,colStart,0);
		visited[source.row][source.col] = true;
		int res = -1;
		while (queue.isEmpty() == false) {
			QItem p = queue.remove();

			// Destination found;
			if (p.row == rowEndK && p.col == columnEndK)
			{
				res = p.dist;
				break;
			}
			// moving up
			if (isValid(p.row - 1, p.col, grid, visited)) {
				queue.add(new QItem(p.row - 1, p.col,p.dist + 1));
				parent[p.row - 1][p.col]=new QItem(p.row,p.col,0);
				visited[p.row - 1][p.col] = true;
			}

			// moving down
			if (isValid(p.row + 1, p.col, grid, visited)) {
				queue.add(new QItem(p.row + 1, p.col,p.dist + 1));
				parent[p.row + 1][ p.col]=new QItem(p.row,p.col,0);
				visited[p.row + 1][p.col] = true;
			}

			// moving left
			if (isValid(p.row, p.col - 1, grid, visited)) {
				queue.add(new QItem(p.row, p.col - 1,p.dist + 1));
				parent[p.row][p.col-1]=new QItem(p.row,p.col,0);
				visited[p.row][p.col - 1] = true;
			}

			// moving right
			if (isValid(p.row, p.col + 1, grid,
					visited)) {
				queue.add(new QItem(p.row, p.col + 1,p.dist + 1));
				parent[p.row][ p.col+1]=new QItem(p.row,p.col,0);
				visited[p.row][p.col+ 1] = true;
			}
		}
		if (res == -1)
			return "No Solution";
		else 
		{
			String path = "";
			int r = rowEndK;
			int c = columnEndK;
			while(r != rowStart || c != colStart)
			{
				//System.out.println("row "+ r + " column" + c);
				int lastx = parent[r][c].row ;
				int lasty = parent[r][c].col;
				//System.out.println("prow "+ lastx + " pcolumn" + lasty);
				if (c > lasty)
					for (int i = 0 ; i < c - lasty ; i++)
						path = "right," + path;
				if (c < lasty)
					for (int i = 0 ; i < lasty - c ; i++)
						path = "left," + path;
				if (r > lastx)
					for (int i = 0 ; i < r - lastx ; i++)
						path = "down," + path;
				if (r < lastx)
					for (int i = 0 ; i < lastx - r ; i++)
						path = "up," + path;

				r = lastx;
				c = lasty;
			}
			if (res != 0)
				path = path.substring(0,path.length()-1);

			path += ";" + res;
			return path;
		}
	}
	static int knownRowLength;
	static int knowColumnLength;
	static int rowEndK;
	static int columnEndK;
	public static String bfs(String grid)
	{
		String [] arr = grid.split("[,;]+");
		int m = Integer.parseInt(arr[0]);
		knownRowLength = m;
		int n = Integer.parseInt(arr[1]);
		knowColumnLength = n;
		int [] [] matrix = new int[m][n];
		int rowStart = Integer.parseInt(arr[2]);
		rowEndK = Integer.parseInt(arr[arr.length-2]);	
		int colStart = Integer.parseInt(arr[3]);
		columnEndK = Integer.parseInt(arr[arr.length-1]);
		for (int i = 4 ; i < arr.length-3 ; i+=2)  
		{
			int row = Integer.parseInt(arr[i]);
			int col = Integer.parseInt(arr[i+1]);
			matrix[row][col] = 1;
		}
		return minDistance(rowStart , colStart , matrix);
	}

	// checking where it's valid or not

	// Driver code
//	public static void main(String[] args)
//	{
//		String g1 = "4,5;3,2;0,2,1,3,1,0,3,3,2,0;1,4";
//
//		System.out.println(bfs(g1));
//	}
}
//Java program for Kruskal's algorithm to
//find Minimum Spanning Tree of a given
//connected, undirected and weighted graph
import java.util.*;
import java.lang.*;
import java.io.*;

class Graph {
	// A class to represent a graph edge

	// A class to represent a subset for
	// union-find

	int V, E; // V-> no. of vertices & E->no.of edges
	Edge edge[]; // collection of all edges

	// Creates a graph with V vertices and E edges
	Graph(int v, int e)
	{
		V = v;
		E = e;
		edge = new Edge[E];
		for (int i = 0; i < e; ++i)
			edge[i] = new Edge();
	}

	// A utility function to find set of an
	// element i (uses path compression technique)

	// A function that does union of two sets
	// of x and y (uses union by rank)
	void Union(subset subsets[], int x, int y)
	{
		int xroot = find(subsets, x);
		int yroot = find(subsets, y);

		// Attach smaller rank tree under root
		// of high rank tree (Union by Rank)
		if (subsets[xroot].rank
			< subsets[yroot].rank)
			subsets[xroot].parent = yroot;
		else if (subsets[xroot].rank
				> subsets[yroot].rank)
			subsets[yroot].parent = xroot;

		// If ranks are same, then make one as
		// root and increment its rank by one
		else {
			subsets[yroot].parent = xroot;
			subsets[xroot].rank++;
		}
	}

	// The main function to construct MST using Kruskal's
	// algorithm
	

	
}
//This code is contributed by Aakash Hasija


