public class StudentHashTable {

    // Each entry holds one student and a link to the next entry in the same bucket
    private static class Entry {
        Student data;
        Entry next;

        Entry(Student data) {
            this.data = data;
            this.next = null;
        }
    }

    private Entry[] table;
    private int capacity;
    private int size;

    public StudentHashTable() {
        this(11); // default number of buckets
    }

    public StudentHashTable(int capacity) {
        this.capacity = capacity;
        this.table = new Entry[capacity];
        this.size = 0;
    }

    // HASH FUNCTION: convert a Student ID into a bucket index
    private int hash(String id) {
        int hash = 0;
        String key = id.toLowerCase();
        for (int i = 0; i < key.length(); i++) {
            hash = (hash * 31 + key.charAt(i)) % capacity;
        }
        return hash;
    }

    // INSERT: add a student. Returns false if the ID already exists.
    public boolean insert(Student student) {
        if (search(student.getId()) != null) {
            return false; // duplicate ID
        }
        int index = hash(student.getId());
        Entry newEntry = new Entry(student);
        newEntry.next = table[index]; // add at the front of the bucket
        table[index] = newEntry;
        size++;
        return true;
    }

    // SEARCH: find a student by ID. Returns null if not found.
    public Student search(String id) {
        int index = hash(id);
        Entry current = table[index];
        while (current != null) {
            if (current.data.getId().equalsIgnoreCase(id)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    // DELETE: remove a student by ID. Returns true if deleted.
    public boolean delete(String id) {
        int index = hash(id);
        Entry current = table[index];
        Entry previous = null;
        while (current != null) {
            if (current.data.getId().equalsIgnoreCase(id)) {
                if (previous == null) {
                    table[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    // DISPLAY: show every bucket (useful for the demo video)
    public void displayTable() {
        if (size == 0) {
            System.out.println("Hash table is empty.");
            return;
        }
        for (int i = 0; i < capacity; i++) {
            System.out.print("Bucket " + i + ": ");
            Entry current = table[i];
            if (current == null) {
                System.out.println("(empty)");
                continue;
            }
            while (current != null) {
                System.out.print(current.data.getId() + " ");
                current = current.next;
            }
            System.out.println();
        }
    }

    public int getSize() { return size; }
    public boolean isEmpty() { return size == 0; }
}