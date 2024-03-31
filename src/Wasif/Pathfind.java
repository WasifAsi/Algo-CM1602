package Wasif;

public class Pathfind {
    private String[][] gridCells;
    private int cellSize;

    public Pathfind(String[][] gridCells, int cellSize) {
        this.gridCells = gridCells;
        this.cellSize = cellSize;
    }

    private class Cell {
        int row;
        int col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static class Queue {
        private int[] array;
        private int front;
        private int rear;
        private int size;

        public Queue(int capacity) {
            array = new int[capacity];
            front = 0;
            rear = -1;
            size = 0;
        }

        public void enqueue(int data) {
            if (size == array.length) {
                throw new IllegalStateException("Queue is full");
            }
            rear = (rear + 1) % array.length;
            array[rear] = data;
            size++;
        }

        public int dequeue() {
            if (isEmpty()) {
                throw new IllegalStateException("Queue is empty");
            }
            int data = array[front];
            front = (front + 1) % array.length;
            size--;
            return data;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    public void pathImport(int startX, int startY, int endX, int endY) {
        int[][] grid = new int[cellSize][cellSize];

        for (int i = 0; i < cellSize; i++) {
            for (int j = 0; j < cellSize; j++) {
                if (gridCells[i][j].equals(" - ") || gridCells[i][j].equals(" S ") || gridCells[i][j].equals(" E ")) {
                    grid[i][j] = 1;
                } else if (gridCells[i][j].equals(" X ")) {
                    grid[i][j] = 0;
                }
            }
        }

        Cell start = new Cell(startX, startY);
        Cell end = new Cell(endX, endY);

        int[][] path = bfsPathPlanning(grid, start, end);

        if (path != null) {
            markShortestPath(gridCells, path);
            System.out.println("Path found:");

        } else {
            System.out.println("No path found!");
        }
    }

    public static int[][] bfsPathPlanning(int[][] grid, Cell start, Cell end) {
        int n = grid.length;
        boolean[] visited = new boolean[n * n];
        int[] parent = new int[n * n];
        int[][] path = new int[n * n][2];
        int pathLength = 0;

        int startIdx = start.row * n + start.col;
        int goalIdx = end.row * n + end.col;

        Queue queue = new Queue(n * n);
        queue.enqueue(startIdx);
        visited[startIdx] = true;
        parent[startIdx] = -1;

        while (queue.size != 0) {
            int currentIdx = queue.dequeue();

            if (currentIdx == goalIdx) {
                pathLength = reconstructPath(parent, startIdx, goalIdx, n, path);
                return path;
            }

            int row = currentIdx / n;
            int col = currentIdx % n;
            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                int newIdx = newRow * n + newCol;

                if (isValidCell(newRow, newCol, n) && grid[newRow][newCol] != 0 && !visited[newIdx]) {
                    queue.enqueue(newIdx);
                    visited[newIdx] = true;
                    parent[newIdx] = currentIdx;
                }
            }
        }

        return null;
    }

    private static boolean isValidCell(int row, int col, int n) {
        return row >= 0 && row < n && col >= 0 && col < n;
    }

    private static int reconstructPath(int[] parent, int startIdx, int goalIdx, int n, int[][] path) {
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

    private static void markShortestPath(String[][] gridCells, int[][] path) {
        for (int[] cell : path) {
            int row = cell[0];
            int col = cell[1];
            if (!gridCells[row][col].equals(" S ") && !gridCells[row][col].equals(" E ")) {
                gridCells[row][col] = " * ";
            }
        }
    }
}
