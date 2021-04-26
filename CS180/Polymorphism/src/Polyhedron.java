public abstract class Polyhedron {

    private double sideLength;

    public Polyhedron(double sideLength) throws IllegalArgumentException {
        if (sideLength < 0) {
            throw new IllegalArgumentException("Side length can't be negative");
        } else {
            this.sideLength = sideLength;
        }
    }

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) throws IllegalArgumentException {
        if (sideLength < 0) {
            throw new IllegalArgumentException("Side length can't be negative");
        } else {
            this.sideLength = sideLength;
        }
    }

    public boolean equals(Object anObject) {
        if (anObject instanceof  Polyhedron) {
            if (getSideLength() == ((Polyhedron) anObject).getSideLength()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public String toString() {
        return String.format("Polyhedron[%f]", getSideLength());
    }

    public abstract double getSurfaceArea();

    public abstract double getVolume();
}
