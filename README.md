# University Student Record and Campus Route Management System

**Module:** CIT300 Data Structures and Algorithms
**Assignment:** Graded Practical Assignment 1
**Language:** Java (console application)

## Group Members

| Member | Name | Student ID | Assigned Responsibility | Individual Contribution |
|--------|------|-----------|------------------------|------------------------|
| Member 1 | Sifar | 23DA2-0559 | Linked list implementation and student-record management | Created the `Student` class (ID, Name, Programme, Marks with 0-100 validation) and the `StudentLinkedList` class. Implemented add, update, delete, search and display operations for student records, including duplicate ID and missing record handling (Menu options 1, 2, 3, 4)for my  Individual Contribution . |
| Member 2 | Fazlan | 23DA2-0717 | Stack and queue implementation and related operations | Implemented `ActionStack` (recent actions / history of added, updated and deleted students and processed requests) and `RequestQueue` (student service requests in order of arrival). Implemented push, pop, peek, enqueue, dequeue and display operations (Menu options 5, 6, 7) For my individual. |
| Member 3 | Akshan | 23DA2-1020 | BST/AVL tree implementation and hashing/search functionality | Implemented `StudentBST` (insert, search, delete, in-order display sorted by Student ID) and `StudentHashTable` (hash function, chaining for collisions, insert, search, delete, display) for fast Student ID searching (Menu options 8,9)tested For My indiviul. |
| Member 4 | Hanoos | 23DA2-0560 | Graph implementation, campus locations, connections, and BFS/DFS traversal | Implemented `CampusGraph` using an adjacency list. Added operations to add/remove campus locations and roads, display the campus network, and traverse locations using BFS (Menu options 10, 11, 12, 13, 14, 15). |
| All Members | Sifar, Fazlan, Akshan, Hanoos | - | Integration, validation, testing, debugging, documentation, GitHub collaboration | Integrated all components in `Main.java` (16-option menu), tested input validation, debugged the system, prepared the README and demo video, and collaborated using GitHub. |

## Data Structures Used

| Requirement | Class | Description |
|-------------|-------|-------------|
| Student record | `Student.java` | Stores Student ID, Name, Programme and Marks (0-100 validated) |
| Linked list | `StudentLinkedList.java` | Stores and manages student records |
| Stack | `ActionStack.java` | Recent actions / history |
| Queue | `RequestQueue.java` | Service requests in order of arrival |
| BST | `StudentBST.java` | Students organised and searched by Student ID |
| Hashing | `StudentHashTable.java` | Fast Student ID search (separate chaining) |
| Graph | `CampusGraph.java` | Adjacency list representation, BFS traversal |
| Menu / integration | `Main.java` | Menu-driven console interface with input validation |

## Project Structure

```
CampusSystem/
├── README.md
└── src/
    ├── Student.java
    ├── StudentLinkedList.java
    ├── ActionStack.java
    ├── RequestQueue.java
    ├── StudentBST.java
    ├── StudentHashTable.java
    ├── CampusGraph.java
    └── Main.java
```

## How to Run

```
cd src
javac *.java
java Main
```

## Menu Options

1. Add Student Record
2. Update Student Record
3. Delete Student Record
4. Display All Records using Linked List
5. Add Service Request to Queue
6. Process Next Service Request
7. Display Recent Actions using Stack
8. Display Students using BST
9. Search Student using Hashing
10. Add Campus Location
11. Remove Campus Location
12. Add Campus Connection/Road
13. Remove Campus Connection/Road
14. Display Campus Connections
15. Traverse Campus Locations using BFS
16. Exit

## Features

- Add, update, delete, search and display student records
- Student records stored in a linked list, a BST and a hash table at the same time
- Service request queue (add / process in order of arrival)
- Recent actions history using a stack
- Campus graph: add/remove locations and roads, display the network, BFS traversal
