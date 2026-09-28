import java.util.LinkedList;
import java.util.Queue;

public class CampusGraph {

    // A neighbour entry in a location's adjacency list
    private static class Edge {
        String destination;
        Edge next;

        Edge(String destination) {
            this.destination = destination;
            this.next = null;
        }
    }

    // A vertex (campus location) with its list of neighbours
    private static class Vertex {
        String name;
        Edge edges;      // head of adjacency list
        Vertex next;     // next vertex in the vertex list

        Vertex(String name) {
            this.name = name;
            this.edges = null;
            this.next = null;
        }
    }

    private Vertex head;
    private int vertexCount;

    public CampusGraph() {
        head = null;
        vertexCount = 0;
    }

    // Find a vertex by name (case-insensitive). Returns null if not found.
    private Vertex findVertex(String name) {
        Vertex current = head;
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public boolean hasLocation(String name) {
        return findVertex(name) != null;
    }

    // ADD LOCATION: returns false if it already exists
    public boolean addLocation(String name) {
        if (findVertex(name) != null) {
            return false;
        }
        Vertex v = new Vertex(name);
        v.next = head;
        head = v;
        vertexCount++;
        return true;
    }

    // Check whether a road exists from a to b
    public boolean hasConnection(String a, String b) {
        Vertex v = findVertex(a);
        if (v == null) return false;
        Edge e = v.edges;
        while (e != null) {
            if (e.destination.equalsIgnoreCase(b)) return true;
            e = e.next;
        }
        return false;
    }

    // Remove a single directed edge from -> to
    private boolean removeEdge(String from, String to) {
        Vertex v = findVertex(from);
        if (v == null) return false;
        Edge current = v.edges;
        Edge previous = null;
        while (current != null) {
            if (current.destination.equalsIgnoreCase(to)) {
                if (previous == null) {
                    v.edges = current.next;
                } else {
                    previous.next = current.next;
                }
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    // ADD CONNECTION: two-way road. Returns a status message.
    public String addConnection(String a, String b) {
        if (a.equalsIgnoreCase(b)) {
            return "A location cannot be connected to itself.";
        }
        Vertex va = findVertex(a);
        Vertex vb = findVertex(b);
        if (va == null || vb == null) {
            return "One or both locations do not exist.";
        }
        if (hasConnection(a, b)) {
            return "This connection already exists.";
        }
        Edge ea = new Edge(vb.name);
        ea.next = va.edges;
        va.edges = ea;

        Edge eb = new Edge(va.name);
        eb.next = vb.edges;
        vb.edges = eb;
        return "OK";
    }

    // REMOVE CONNECTION: removes the road in both directions
    public String removeConnection(String a, String b) {
        if (findVertex(a) == null || findVertex(b) == null) {
            return "One or both locations do not exist.";
        }
        if (!hasConnection(a, b)) {
            return "No such connection exists.";
        }
        removeEdge(a, b);
        removeEdge(b, a);
        return "OK";
    }

    // REMOVE LOCATION: also removes every road connected to it
    public boolean removeLocation(String name) {
        Vertex target = findVertex(name);
        if (target == null) {
            return false;
        }
        // Remove roads pointing to this location from every other vertex
        Vertex current = head;
        while (current != null) {
            if (current != target) {
                removeEdge(current.name, target.name);
            }
            current = current.next;
        }
        // Remove the vertex itself from the vertex list
        if (head == target) {
            head = head.next;
        } else {
            Vertex v = head;
            while (v.next != target) {
                v = v.next;
            }
            v.next = target.next;
        }
        vertexCount--;
        return true;
    }

    // DISPLAY: show every location and its neighbours
    public void displayNetwork() {
        if (head == null) {
            System.out.println("No campus locations added yet.");
            return;
        }
        Vertex current = head;
        while (current != null) {
            System.out.print(current.name + " -> ");
            Edge e = current.edges;
            if (e == null) {
                System.out.print("(no connections)");
            }
            while (e != null) {
                System.out.print(e.destination);
                if (e.next != null) System.out.print(", ");
                e = e.next;
            }
            System.out.println();
            current = current.next;
        }
    }

    // BFS: visit all locations reachable from the start location
    public void bfs(String start) {
        Vertex startVertex = findVertex(start);
        if (startVertex == null) {
            System.out.println("Start location does not exist.");
            return;
        }
        java.util.HashSet<String> visited = new java.util.HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(startVertex.name.toLowerCase());
        queue.add(startVertex.name);

        System.out.print("BFS order: ");
        while (!queue.isEmpty()) {
            String name = queue.poll();
            System.out.print(name);

            Vertex v = findVertex(name);
            Edge e = v.edges;
            while (e != null) {
                if (!visited.contains(e.destination.toLowerCase())) {
                    visited.add(e.destination.toLowerCase());
                    queue.add(e.destination);
                }
                e = e.next;
            }
            if (!queue.isEmpty()) System.out.print(" -> ");
        }
        System.out.println();
    }

    public int getLocationCount() { return vertexCount; }
}