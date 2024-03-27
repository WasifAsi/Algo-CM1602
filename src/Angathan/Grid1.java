package Angathan;


public class Grid1 {
    private int rows;
    private int cols;
    private boolean[][] grid;
    private int start;
    private int end;

    private static class Stack {
        private int[] array;
        private int size;
        private int capacity;

        public Stack(int capacity) {
            this.capacity = capacity;
            this.array = new int[capacity];
            this.size = 0;
        }

        public void push(int item) {
            if (size == capacity) {
                int[] newArray = new int[capacity * 2];
                System.arraycopy(array, 0, newArray, 0, capacity);
                array = newArray;
                capacity *= 2;
            }
            array[size++] = item;
        }

        public int pop() {
            if (size == 0) {
                throw new IllegalStateException("Stack is empty");
            }
            return array[--size];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }
    }

    public Grid1(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new boolean[rows][cols];
    }

    public void generateGrid() {
        System.out.println("Grid:");
        int cellNumber = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(cellNumber + "\t");
                cellNumber++;
            }
            System.out.println();
        }
    }

    public void setUserInput(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void placeObstaclesRandomly(int numObstacles) {
        java.util.Random random = new java.util.Random();

        // Place obstacles near start and end points
        for (int i = 0; i < numObstacles; i++) {
            int row = random.nextInt(3); // Obstacles placed in first three rows
            int col = random.nextInt(cols);
            grid[row][col] = true;

            row = random.nextInt(3) + rows - 3; // Obstacles placed in last three rows
            col = random.nextInt(cols);
            grid[row][col] = true;
        }
    }

    public int[] findShortestPath() {
        int[] queue = new int[rows * cols];
        boolean[][] visited = new boolean[rows][cols];
        int[][] parent = new int[rows][cols];
        int front = 0, rear = 0;

        queue[rear++] = start;
        visited[(start - 1) / cols][(start - 1) % cols] = true;

        while (front < rear) {
            int current = queue[front++];
            if (current == end) {
                break;
            }

            int row = (current - 1) / cols;
            int col = (current - 1) % cols;

            int[] dx = {0, 1, 0, -1}; // Directional offsets for rows
            int[] dy = {-1, 0, 1, 0}; // Directional offsets for columns

            for (int i = 0; i < 4; i++) {
                int newRow = row + dx[i];
                int newCol = col + dy[i];
                int newCell = newRow * cols + newCol + 1;

                if (isValidCell(newRow, newCol) && !visited[newRow][newCol] && !grid[newRow][newCol]) {
                    queue[rear++] = newCell;
                    visited[newRow][newCol] = true;
                    parent[newRow][newCol] = current;
                }
            }
        }

        // Reconstruct path using parent array
        Stack shortestPath = new Stack(rows * cols);
        int currentNode = end;
        while (currentNode != start) {
            shortestPath.push(currentNode);
            int row = (currentNode - 1) / cols;
            int col = (currentNode - 1) % cols;
            currentNode = parent[row][col];
        }
        shortestPath.push(start);

        // Convert stack to array
        int[] pathArray = new int[shortestPath.size()];
        int index = 0;
        while (!shortestPath.isEmpty()) {
            pathArray[index++] = shortestPath.pop();
        }
        return pathArray;
    }

    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public void printShortestPath(int[] shortestPath) {
        System.out.println("Shortest Path:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int cellNumber = i * cols + j + 1;
                if (contains(shortestPath, cellNumber)) {
                    if (cellNumber == start) {
                        System.out.print("S\t");
                    } else if (cellNumber == end) {
                        System.out.print("E\t");
                    } else {
                        System.out.print("*\t"); // Path cell
                    }
                } else if (grid[i][j]) {
                    System.out.print("X\t"); // Obstacle
                } else {
                    System.out.print(cellNumber + "\t"); // Empty cell
                }
            }
            System.out.println();
        }
    }

    private boolean contains(int[] arr, int target) {
        for (int num : arr) {
            if (num == target) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Enter start number: ");
        int start = scanner.nextInt();
        System.out.print("Enter end number: ");
        int end = scanner.nextInt();

        Grid1 grid1 = new Grid1(10, 10); // Assuming 10x10 grid
        grid1.generateGrid();
        grid1.setUserInput(start, end);
        grid1.placeObstaclesRandomly(10); // Assuming 10 obstacles

        int[] shortestPath = grid1.findShortestPath();
        grid1.printShortestPath(shortestPath);
    }
}

