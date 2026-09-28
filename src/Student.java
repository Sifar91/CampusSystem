public class Student {
    private String id;
    private String name;
    private String programme;
    private double marks;

    public Student(String id, String name, String programme, double marks) {
        this.id = id;
        this.name = name;
        this.programme = programme;
        setMarks(marks);
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getProgramme() { return programme; }
    public double getMarks() { return marks; }

    public void setName(String name) { this.name = name; }
    public void setProgramme(String programme) { this.programme = programme; }

    // Marks must be between 0 and 100
    public void setMarks(double marks) {
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100.");
        }
        this.marks = marks;
    }

    @Override
    public String toString() {
        return String.format("ID: %-8s | Name: %-15s | Programme: %-12s | Marks: %.1f",
                id, name, programme, marks);
    }
}