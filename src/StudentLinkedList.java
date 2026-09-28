public class StudentLinkedList {

    // Each node holds one student and a link to the next node
    private static class Node {
        Student data;
        Node next;

        Node(Student data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public StudentLinkedList() {
        head = null;
        size = 0;
    }

    // ADD: insert at the end. Returns false if the ID already exists.
    public boolean add(Student student) {
        if (search(student.getId()) != null) {
            return false; // duplicate ID
        }
        Node newNode = new Node(student);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return true;
    }

    // SEARCH: find a student by ID. Returns null if not found.
    public Student search(String id) {
        Node current = head;
        while (current != null) {
            if (current.data.getId().equalsIgnoreCase(id)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    // UPDATE: change name, programme and marks of an existing student.
    public boolean update(String id, String newName, String newProgramme, double newMarks) {
        Student s = search(id);
        if (s == null) {
            return false; // not found
        }
        s.setName(newName);
        s.setProgramme(newProgramme);
        s.setMarks(newMarks); // throws exception if marks invalid
        return true;
    }

    // DELETE: remove a student by ID. Returns the deleted student, or null if not found.
    public Student delete(String id) {
        if (head == null) {
            return null;
        }
        // Case 1: the student to delete is the head
        if (head.data.getId().equalsIgnoreCase(id)) {
            Student removed = head.data;
            head = head.next;
            size--;
            return removed;
        }
        // Case 2: the student is somewhere else in the list
        Node current = head;
        while (current.next != null) {
            if (current.next.data.getId().equalsIgnoreCase(id)) {
                Student removed = current.next.data;
                current.next = current.next.next;
                size--;
                return removed;
            }
            current = current.next;
        }
        return null; // not found
    }

    // DISPLAY: print all students
    public void displayAll() {
        if (head == null) {
            System.out.println("No student records found.");
            return;
        }
        Node current = head;
        int count = 1;
        while (current != null) {
            System.out.println(count + ". " + current.data);
            current = current.next;
            count++;
        }
    }

    public int getSize() { return size; }
    public boolean isEmpty() { return head == null; }
}