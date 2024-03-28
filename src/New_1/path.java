package New_1;

import java.util.NoSuchElementException;

public class path {
    // Define a class to represent a cell in the grid

    // Define a custom Queue implementation
    static class CustomQueue<T> {
        private static class Node<T> {
            T data;
            Node<T> next;

            Node(T data) {
                this.data = data;
                this.next = null;
            }
        }

        private Node<T> front;
        private Node<T> rear;

        CustomQueue() {
            this.front = null;
            this.rear = null;
        }

        void enqueue(T data) {
            Node<T> newNode = new Node<>(data);
            if (isEmpty()) {
                front = newNode;
                rear = newNode;
            } else {
                rear.next = newNode;
                rear = newNode;
            }
        }

        T dequeue() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue is empty");
            }
            T data = front.data;
            front = front.next;
            if (front == null) {
                rear = null;
            }
            return data;
        }

        boolean isEmpty() {
            return front == null;
        }
    }

    // Define a custom LinkedList implementation
    class CustomLinkedList<T> {
        private static class Node<T> {
            T data;
            Node<T> next;

            Node(T data) {
                this.data = data;
                this.next = null;
            }
        }

        private Node<T> head;

        void add(T data) {
            Node<T> newNode = new Node<>(data);
            if (head == null) {
                head = newNode;
            } else {
                Node<T> current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
        }

        T removeFirst() {
            if (head == null) {
                throw new NoSuchElementException("List is empty");
            }
            T data = head.data;
            head = head.next;
            return data;
        }

        boolean isEmpty() {
            return head == null;
        }
    }



    static class Cell {
        int row;
        int col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }


        public static void main(String[] args) {
            // Define the grid (10x10) with obstacles represented by 1s
            int[][] grid = {
                    {0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                    {0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            };

            // Define the start and goal cells
            Cell start = new Cell(0, 0);
            Cell goal = new Cell(9, 9);

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
        public static int[][] bfsPathPlanning(int[][] grid, Cell start, Cell goal) {
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
        private static boolean isValidCell(int row, int col, int n) {
            return row >= 0 && row < n && col >= 0 && col < n;
        }

        // Function to reconstruct the path from start to goal
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
    }


