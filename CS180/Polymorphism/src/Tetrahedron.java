public class Tetrahedron extends Polyhedron {
    public Tetrahedron(double sideLength) throws IllegalArgumentException {
        super(sideLength);
    }

    @Override
    public double getSurfaceArea() {
        return Math.sqrt(3.0) * Math.pow(getSideLength(), 2.0);
    }

    @Override
    public double getVolume() {
        return Math.pow(getSideLength(), 3) / (6.0 * Math.sqrt(2.0));
    }

    public boolean equals(Object anObject) {
        if (anObject instanceof Tetrahedron) {
            if (getSideLength() == ((Polyhedron) anObject).getSideLength()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public String toString() {
        return String.format("Tetrahedron[%f]", getSideLength());
    }
}
