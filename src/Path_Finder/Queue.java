package Path_Finder;

public class Queue {

    // creating the nodes
    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node front;
    private Node rear;

    //assigning the consector
    public Queue() {
        this.front = null;
        this.rear = null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1; // or throw an exception
        } else {
            int data = front.data;
            front = front.next;
            if (front == null) {
                rear = null; // Queue becomes empty after dequeue
            }
            return data;
        }
    }
}



