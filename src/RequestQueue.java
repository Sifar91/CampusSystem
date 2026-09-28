public class RequestQueue {

    // Each node holds one service request and a link to the next node
    private static class Node {
        String request;
        Node next;

        Node(String request) {
            this.request = request;
            this.next = null;
        }
    }

    private Node front;  // requests are removed from the front
    private Node rear;   // requests are added at the rear
    private int size;

    public RequestQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    // ENQUEUE: add a new request at the rear
    public void enqueue(String request) {
        Node newNode = new Node(request);
        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    // DEQUEUE: remove and return the request at the front (null if empty)
    public String dequeue() {
        if (front == null) {
            return null;
        }
        String request = front.request;
        front = front.next;
        if (front == null) {
            rear = null; // queue became empty
        }
        size--;
        return request;
    }

    // PEEK: view the next request without removing it
    public String peek() {
        if (front == null) {
            return null;
        }
        return front.request;
    }

    // DISPLAY: show all pending requests in order of arrival
    public void display() {
        if (front == null) {
            System.out.println("No pending service requests.");
            return;
        }
        Node current = front;
        int count = 1;
        while (current != null) {
            System.out.println(count + ". " + current.request);
            current = current.next;
            count++;
        }
    }

    public int getSize() { return size; }
    public boolean isEmpty() { return front == null; }
}