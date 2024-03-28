package Wasif;

import java.util.NoSuchElementException;

public class CustomQueue<T> {
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

