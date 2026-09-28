public class StudentBST {

    // Each node holds one student, plus left and right children
    private static class Node {
        Student data;
        Node left;
        Node right;

        Node(Student data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    public StudentBST() {
        root = null;
        size = 0;
    }

    // INSERT: add a student. Returns false if the ID already exists.
    public boolean insert(Student student) {
        if (search(student.getId()) != null) {
            return false; // duplicate ID
        }
        root = insertRec(root, student);
        size++;
        return true;
    }

    private Node insertRec(Node node, Student student) {
        if (node == null) {
            return new Node(student);
        }
        int cmp = student.getId().compareToIgnoreCase(node.data.getId());
        if (cmp < 0) {
            node.left = insertRec(node.left, student);
        } else {
            node.right = insertRec(node.right, student);
        }
        return node;
    }

    // SEARCH: find a student by ID. Returns null if not found.
    public Student search(String id) {
        Node current = root;
        while (current != null) {
            int cmp = id.compareToIgnoreCase(current.data.getId());
            if (cmp == 0) {
                return current.data;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    // DELETE: remove a student by ID. Returns true if deleted.
    public boolean delete(String id) {
        if (search(id) == null) {
            return false;
        }
        root = deleteRec(root, id);
        size--;
        return true;
    }

    private Node deleteRec(Node node, String id) {
        if (node == null) {
            return null;
        }
        int cmp = id.compareToIgnoreCase(node.data.getId());
        if (cmp < 0) {
            node.left = deleteRec(node.left, id);
        } else if (cmp > 0) {
            node.right = deleteRec(node.right, id);
        } else {
            // Case 1 & 2: zero or one child
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            // Case 3: two children -> replace with the smallest node on the right
            Node successor = node.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            node.data = successor.data;
            node.right = deleteRec(node.right, successor.data.getId());
        }
        return node;
    }

    // DISPLAY: in-order traversal (sorted by Student ID)
    public void displayInOrder() {
        if (root == null) {
            System.out.println("No student records in the tree.");
            return;
        }
        int[] count = {1};
        inOrder(root, count);
    }

    private void inOrder(Node node, int[] count) {
        if (node == null) {
            return;
        }
        inOrder(node.left, count);
        System.out.println(count[0] + ". " + node.data);
        count[0]++;
        inOrder(node.right, count);
    }

    public int getSize() { return size; }
    public boolean isEmpty() { return root == null; }
}