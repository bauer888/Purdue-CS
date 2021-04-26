public class Icosahedron extends Polyhedron {
    public Icosahedron(double sideLength) throws IllegalArgumentException {
        super(sideLength);
    }

    @Override
    public double getSurfaceArea() {
        return 5.0 * Math.sqrt(3.0) * Math.pow(getSideLength(), 2.0);
    }

    @Override
    public double getVolume() {
        return ((15.0 + (5.0 * Math.sqrt(5.0))) / 12.0) * Math.pow(getSideLength(), 3.0);
    }

    public boolean equals(Object anObject) {
        if (anObject instanceof Icosahedron) {
            if (getSideLength() == ((Polyhedron) anObject).getSideLength()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public String toString() {
        return String.format("Icosahedron[%f]", getSideLength());
    }
}
