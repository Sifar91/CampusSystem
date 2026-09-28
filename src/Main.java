import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    // All the data structures used in the system
    private static final StudentLinkedList list = new StudentLinkedList();
    private static final StudentBST bst = new StudentBST();
    private static final StudentHashTable hash = new StudentHashTable();
    private static final ActionStack stack = new ActionStack();
    private static final RequestQueue queue = new RequestQueue();
    private static final CampusGraph graph = new CampusGraph();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readMenuChoice();
            System.out.println();
            switch (choice) {
                case 1:  addStudent(); break;
                case 2:  updateStudent(); break;
                case 3:  deleteStudent(); break;
                case 4:  list.displayAll(); break;
                case 5:  addServiceRequest(); break;
                case 6:  processNextRequest(); break;
                case 7:  stack.display(); break;
                case 8:  bst.displayInOrder(); break;
                case 9:  searchUsingHash(); break;
                case 10: addLocation(); break;
                case 11: removeLocation(); break;
                case 12: addConnection(); break;
                case 13: removeConnection(); break;
                case 14: graph.displayNetwork(); break;
                case 15: traverseCampus(); break;
                case 16:
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 16.");
            }
        }
    }

    // ---------------- MENU ----------------

    private static void printMenu() {
        System.out.println();
        System.out.println("===== University Student Record & Campus Route System =====");
        System.out.println(" 1. Add Student Record");
        System.out.println(" 2. Update Student Record");
        System.out.println(" 3. Delete Student Record");
        System.out.println(" 4. Display All Records using Linked List");
        System.out.println(" 5. Add Service Request to Queue");
        System.out.println(" 6. Process Next Service Request");
        System.out.println(" 7. Display Recent Actions using Stack");
        System.out.println(" 8. Display Students using BST");
        System.out.println(" 9. Search Student using Hashing");
        System.out.println("10. Add Campus Location");
        System.out.println("11. Remove Campus Location");
        System.out.println("12. Add Campus Connection/Road");
        System.out.println("13. Remove Campus Connection/Road");
        System.out.println("14. Display Campus Connections");
        System.out.println("15. Traverse Campus Locations using BFS");
        System.out.println("16. Exit");
        System.out.print("Enter your choice: ");
    }

    // ---------------- INPUT HELPERS (validation) ----------------

    // Reads a menu number. Returns -1 if the input is not a number.
    private static int readMenuChoice() {
        String input = scanner.nextLine().trim();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Keeps asking until the user types something that is not empty.
    private static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    // Keeps asking until the user types a valid number between 0 and 100.
    private static double readMarks(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double marks = Double.parseDouble(input);
                if (Double.isNaN(marks) || marks < 0 || marks > 100) {
                    System.out.println("Invalid marks. Marks must be between 0 and 100.");
                } else {
                    return marks;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // ---------------- STUDENT OPERATIONS ----------------

    private static void addStudent() {
        String id = readNonEmpty("Enter Student ID: ");
        if (hash.search(id) != null) {
            System.out.println("Error: A student with ID " + id + " already exists.");
            return;
        }
        String name = readNonEmpty("Enter Name: ");
        String programme = readNonEmpty("Enter Programme: ");
        double marks = readMarks("Enter Marks (0-100): ");

        Student student = new Student(id, name, programme, marks);
        list.add(student);    // linked list
        bst.insert(student);  // binary search tree
        hash.insert(student); // hash table
        stack.push("Added student " + id);
        System.out.println("Student added successfully.");
    }

    private static void updateStudent() {
        String id = readNonEmpty("Enter Student ID to update: ");
        if (hash.search(id) == null) {
            System.out.println("Error: No student found with ID " + id + ".");
            return;
        }
        String name = readNonEmpty("Enter new Name: ");
        String programme = readNonEmpty("Enter new Programme: ");
        double marks = readMarks("Enter new Marks (0-100): ");

        // All three structures share the same Student object,
        // so updating it once updates it everywhere.
        list.update(id, name, programme, marks);
        stack.push("Updated student " + id);
        System.out.println("Student updated successfully.");
    }

    private static void deleteStudent() {
        String id = readNonEmpty("Enter Student ID to delete: ");
        Student removed = list.delete(id);
        if (removed == null) {
            System.out.println("Error: No student found with ID " + id + ".");
            return;
        }
        bst.delete(id);
        hash.delete(id);
        stack.push("Deleted student " + removed.getId() + " (" + removed.getName() + ")");
        System.out.println("Student deleted successfully.");
    }

    private static void searchUsingHash() {
        String id = readNonEmpty("Enter Student ID to search: ");
        Student s = hash.search(id);
        if (s == null) {
            System.out.println("No student found with ID " + id + ".");
        } else {
            System.out.println("Student found:");
            System.out.println(s);
        }
    }

    // ---------------- QUEUE OPERATIONS ----------------

    private static void addServiceRequest() {
        String id = readNonEmpty("Enter Student ID making the request: ");
        if (hash.search(id) == null) {
            System.out.println("Error: No student found with ID " + id + ".");
            return;
        }
        String description = readNonEmpty("Enter request (e.g. Transcript, ID card): ");
        queue.enqueue("Student " + id + " - " + description);
        System.out.println("Request added. Pending requests: " + queue.getSize());
    }

    private static void processNextRequest() {
        String request = queue.dequeue();
        if (request == null) {
            System.out.println("No pending service requests.");
            return;
        }
        System.out.println("Processing request: " + request);
        stack.push("Processed request: " + request);
        System.out.println("Remaining requests: " + queue.getSize());
    }

    // ---------------- GRAPH OPERATIONS ----------------

    private static void addLocation() {
        String name = readNonEmpty("Enter location name: ");
        if (graph.addLocation(name)) {
            System.out.println("Location added.");
        } else {
            System.out.println("Error: Location already exists.");
        }
    }

    private static void removeLocation() {
        String name = readNonEmpty("Enter location name to remove: ");
        if (graph.removeLocation(name)) {
            System.out.println("Location and all its connections removed.");
        } else {
            System.out.println("Error: Location does not exist.");
        }
    }

    private static void addConnection() {
        String a = readNonEmpty("Enter first location: ");
        String b = readNonEmpty("Enter second location: ");
        String result = graph.addConnection(a, b);
        System.out.println(result.equals("OK") ? "Connection added." : "Error: " + result);
    }

    private static void removeConnection() {
        String a = readNonEmpty("Enter first location: ");
        String b = readNonEmpty("Enter second location: ");
        String result = graph.removeConnection(a, b);
        System.out.println(result.equals("OK") ? "Connection removed." : "Error: " + result);
    }

    private static void traverseCampus() {
        String start = readNonEmpty("Enter start location: ");
        graph.bfs(start);
    }
}