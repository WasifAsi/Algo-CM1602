package Wasif;

public class Pathfind {
private String [][] grid_cells;
private int cell_size;

// Define a custom Queue implementation
    public Pathfind (String [][] grid_cells, int cell_size){
        this.grid_cells = grid_cells;
        this.cell_size = cell_size;

    }

// Define a class to represent a cell in the grid
// Define a class to represent a cell in the grid
    static class Cell {
        int row;
        int col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }


    public  void path_import (int x, int y, int a, int b) {
        // Define the grid (10x10) with obstacles represented by 1s
        int[][] grid = new int[cell_size][cell_size];

        for (int i = 0; i < cell_size; i++) {
            for (int j = 0; j < cell_size; j++) {
                if (grid_cells[i][j] == " - ") {
                    grid[i][j] = 1;
                } else if ((grid_cells[i][j] == " S ") || (grid_cells[i][j] == " E ")) {
                    grid[i][j] = 1;
                } else if (grid_cells[i][j] == " X ") {
                    grid[i][j] = 0;

                }
            }
        }
//        for (int i = 0; i < cell_size; i++) {
//            for (int j = 0; j < cell_size; j++) {
//                System.out.print(grid[i][j]);
//            }
//            System.out.print(" " + (i + 1));
//            System.out.println();
//        }


        // Define the start and goal cells
        Cell start = new Cell(x,y);
        Cell goal = new Cell(a,b);

        // Perform path planning using BFS with custom Queue and LinkedList
        int[][] path = bfsPathPlanning(grid, start, goal);

        // Print the path if found
        if (path != null) {
            System.out.println("Path found:");
            for (int i = 0; i < path.length; i++) {
                System.out.println("(" + path[i][0] + ", " + path[i][1] + ")");
            }
        } else {
            System.out.println("No path found!");
        }
    }

    // Function to perform BFS path planning using adjacency matrix and custom Queue
    public static int[][] bfsPathPlanning ( int[][] grid, Cell start, Cell goal){
        int n = grid.length;
        boolean[] visited = new boolean[n * n];
        int[] parent = new int[n * n];
        int[][] path = new int[n * n][2];
        int pathLength = 0;

        // Convert start and goal cells to indices
        int startIdx = start.row * n + start.col;
        int goalIdx = goal.row * n + goal.col;

        CustomQueue<Integer> queue = new CustomQueue<>();
        queue.enqueue(startIdx);
        visited[startIdx] = true;
        parent[startIdx] = -1;

        while (!queue.isEmpty()) {
            int currentIdx = queue.dequeue();

            // Check if the goal is reached
            if (currentIdx == goalIdx) {
                pathLength = reconstructPath(parent, startIdx, goalIdx, n, path);
                return path;
            }

            // Explore neighboring cells
            int row = currentIdx / n;
            int col = currentIdx % n;
            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                int newIdx = newRow * n + newCol;

                // Check if the new cell is within bounds and not an obstacle
                if (isValidCell(newRow, newCol, n) && grid[newRow][newCol] != 1 && !visited[newIdx]) {
                    queue.enqueue(newIdx);
                    visited[newIdx] = true;
                    parent[newIdx] = currentIdx;
                }
            }
        }

        // No path found
        return null;
    }

    // Function to check if a cell is within the grid bounds
    private static boolean isValidCell ( int row, int col, int n){
        return row >= 0 && row < n && col >= 0 && col < n;
    }

    // Function to reconstruct the path from start to goal
    private static int reconstructPath ( int[] parent, int startIdx, int goalIdx, int n, int[][] path){
        int pathLength = 0;
        int currentIdx = goalIdx;
        while (currentIdx != startIdx) {
            int row = currentIdx / n;
            int col = currentIdx % n;
            path[pathLength][0] = row;
            path[pathLength][1] = col;
            pathLength++;
            currentIdx = parent[currentIdx];
        }
        path[pathLength][0] = startIdx / n;
        path[pathLength][1] = startIdx % n;
        return pathLength + 1;
    }
}




