public class Professor extends Person {

    private double salary;
    private String course;
    private String rank;

    public Professor(String name, String address, String course, String rank) {
        super(name, address);
        this.course = course;
        this.rank = rank;
        if (this.rank.equals("Assistant")) {
            this.salary = 70000;
        }
        if (this.rank.equals("Professor")) {
            this.salary = 100000;
        }
    }

    public double getSalary() {
        return salary;
    }

    public String getCourse() {
        return course;
    }

    public String getRank() {
        return rank;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setRank(String rank) {
        if (rank == null) {
            return;
        }
        if (rank.equals("Assistant")) {
            this.rank = rank;
            this.salary = 70000;
        }
        if (rank.equals("Professor")) {
            this.rank = rank;
            this.salary = 100000;
        }
    }
}
