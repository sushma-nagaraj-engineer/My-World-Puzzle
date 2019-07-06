package com.project3;

public class FindWord {

	public int wordCheck(char[][] grid, LinearProbingHashTable1<String> H,
			boolean check) {
		int count = 0;
		boolean enhance1 = check;
		int cols = grid[0].length;
		int rows = grid.length;
		System.out.println("rows" + rows + ",columns" + cols);

		// Here search is conducted in all possible directions of grid including
		// the diagonal as well
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (j >= 0 && j < cols) {
					count += moveE(grid, H, i, j, rows, cols, check);
				}
				if (i >= 0 && i < rows) {
					count += moveS(grid, H, i, j, rows, cols, check);
				}
				if (j > 0 && j <= cols) {
					count += moveW(grid, H, i, j, rows, cols, check);
				}
				if (i > 0 && i <= rows) {
					count += moveN(grid, H, i, j, rows, cols, check);
				}

				if (j >= 0 && i >= 0 && j < cols && i < rows) {
					count += moveSE(grid, H, i, j, rows, cols, check);
				}
				if (j >= 0 && j < cols && i > 0 && i <= rows) {
					count += moveNE(grid, H, i, j, rows, cols, check);
				}
				if (j > 0 && j <= cols && i >= 0 && i < rows) {
					count += moveSW(grid, H, i, j, rows, cols, check);
				}
				if (j > 0 && j <= cols && i > 0 && i <= rows) {
					count += moveNW(grid, H, i, j, rows, cols, check);
				}
			}
		}
		return count;
	}

	// Search in West

	private static int moveW(char[][] grid, LinearProbingHashTable1<String> H,
			int row1, int col1, int row, int col, boolean enhance1) {
		int count1 = 0;
		StringBuilder word = new StringBuilder();
		for (int j = col1; j >= 0; j--) {
			word.append(grid[row1][j]);

			if (H.contains(word.toString()) && !H.isPrefix(word.toString())
					&& j != col1) {
				if (enhance1 == true)
					System.out.println("found " + word.toString() + " at ("
							+ row1 + "," + col1 + ") to (" + row1 + "," + j
							+ ")");
				count1++;
			}
			// else return here only for prefix mode
			else if (!H.contains(word.toString()) && enhance1 == true)
				break;
		}
		return count1;
	}

	// Search in East
	private static int moveE(char[][] grid, LinearProbingHashTable1<String> H,
			int row1, int col1, int row, int col, boolean enhance1) {
		int count1 = 0;
		StringBuilder word = new StringBuilder();
		for (int j = col1; j < col; j++) {
			word.append(grid[row1][j]);

			if (H.contains(word.toString()) && !H.isPrefix(word.toString())) {
				if (enhance1 == true)
					System.out.println("found " + word.toString() + " at ("
							+ row1 + "," + col1 + ") to (" + row1 + "," + j
							+ ")");
				count1++;
			}

			else if (!H.contains(word.toString()) && enhance1 == true)
				break;
		}
		return count1;
	}

	// Search in North
	private static int moveN(char[][] grid, LinearProbingHashTable1<String> H,
			int row1, int col1, int row, int col, boolean enhance1) {
		int count1 = 0;
		StringBuilder word = new StringBuilder();
		for (int i = row1; i >= 0; i--) {
			word.append(grid[i][col1]);

			if (H.contains(word.toString()) && !H.isPrefix(word.toString())
					&& i != row1) {
				if (enhance1 == true)
					System.out.println("found " + word.toString() + " at ("
							+ row1 + "," + col1 + ") to (" + i + "," + col1
							+ ")");
				count1++;
			}

			else if (!H.contains(word.toString()) && enhance1 == true)
				break;
		}
		return count1;
	}

	// //Search in South
	private static int moveS(char[][] grid, LinearProbingHashTable1<String> H,
			int row1, int col1, int row, int col, boolean enhance1) {
		int count1 = 0;
		StringBuilder word = new StringBuilder();
		for (int i = row1; i < row; i++) {
			word.append(grid[i][col1]);

			if (H.contains(word.toString()) && !H.isPrefix(word.toString())
					&& i != row1) {
				if (enhance1 == true)
					System.out.println("found " + word.toString() + " at ("
							+ row1 + "," + col1 + ") to (" + i + "," + col1
							+ ")");
				count1++;
			}

			else if (!H.contains(word.toString()) && enhance1 == true)
				break;
		}
		return count1;
	}

	// //Search in South East
	private static int moveSE(char[][] grid, LinearProbingHashTable1<String> H,
			int row1, int col1, int row, int col, boolean enhance1) {
		int count1 = 0;
		StringBuilder word = new StringBuilder();
		for (int i = row1, j = col1; i < row && j < col; i++, j++) {
			word.append(grid[i][j]);

			if (H.contains(word.toString()) && !H.isPrefix(word.toString())
					&& i != row1 && j != col1) {
				if (enhance1 == true)
					System.out.println("found " + word.toString() + " at ("
							+ row1 + "," + col1 + ") to (" + i + "," + j + ")");
				count1++;
			}

			else if (!H.contains(word.toString()) && enhance1 == true)
				break;
		}
		return count1;
	}

	// Search in North West
	private static int moveNW(char[][] grid, LinearProbingHashTable1<String> H,
			int row1, int col1, int row, int col, boolean enhance1) {
		int count1 = 0;
		StringBuilder word = new StringBuilder();
		for (int i = row1, j = col1; i >= 0 && j >= 0; i--, j--) {
			word.append(grid[i][j]);

			// System.out.println("MoveNW "+word.toString());
			if (H.contains(word.toString()) && !H.isPrefix(word.toString())
					&& i != row1 && j != col1) {
				if (enhance1 == true)
					System.out.println("found " + word.toString() + " at ("
							+ row1 + "," + col1 + ") to (" + i + "," + j + ")");
				count1++;
			}
			// else return here only for prefix mode
			else if (!H.contains(word.toString()) && enhance1 == true)
				break;
		}
		return count1;
	}

	// Search in North East
	private static int moveNE(char[][] grid, LinearProbingHashTable1<String> H,
			int row1, int col1, int row, int col, boolean enhance1) {
		int count1 = 0;
		StringBuilder word = new StringBuilder();
		for (int i = row1, j = col1; i >= 0 && j < col; i--, j++) {
			word.append(grid[i][j]);

			if (H.contains(word.toString()) && !H.isPrefix(word.toString())
					&& i != row1 && j != col1) {
				if (enhance1 == true)
					System.out.println("found " + word.toString() + " at ("
							+ row1 + "," + col1 + ") to (" + i + "," + j + ")");
				count1++;
			}

			else if (!H.contains(word.toString()) && enhance1 == true)
				break;
		}
		return count1;
	}

	// Search in South West
	private static int moveSW(char[][] grid, LinearProbingHashTable1<String> H,
			int row1, int col1, int row, int col, boolean enhance1) {
		int count1 = 0;
		StringBuilder word = new StringBuilder();
		for (int i = row1, j = col1; i < row && j >= 0; i++, j--) {
			word.append(grid[i][j]);

			if (H.contains(word.toString()) && !H.isPrefix(word.toString())
					&& i != row1 && j != col1) {
				if (enhance1 == true)
					System.out.println("found " + word.toString() + " at ("
							+ row1 + "," + col1 + ") to (" + i + "," + j + ")");
				count1++;
			}

			else if (!H.contains(word.toString()) && enhance1 == true)
				break;
		}
		return count1;
	}

}
