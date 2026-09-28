public class ActionStack {

    // Each node holds one action message and a link to the node below it
    private static class Node {
        String action;
        Node next;

        Node(String action) {
            this.action = action;
            this.next = null;
        }
    }

    private Node top;
    private int size;

    public ActionStack() {
        top = null;
        size = 0;
    }

    // PUSH: add a new action on top of the stack
    public void push(String action) {
        Node newNode = new Node(action);
        newNode.next = top;
        top = newNode;
        size++;
    }

    // POP: remove and return the most recent action (null if empty)
    public String pop() {
        if (top == null) {
            return null;
        }
        String action = top.action;
        top = top.next;
        size--;
        return action;
    }

    // PEEK: view the most recent action without removing it
    public String peek() {
        if (top == null) {
            return null;
        }
        return top.action;
    }

    // DISPLAY: show all actions, most recent first
    public void display() {
        if (top == null) {
            System.out.println("No recent actions.");
            return;
        }
        Node current = top;
        int count = 1;
        while (current != null) {
            System.out.println(count + ". " + current.action);
            current = current.next;
            count++;
        }
    }

    public int getSize() { return size; }
    public boolean isEmpty() { return top == null; }
}