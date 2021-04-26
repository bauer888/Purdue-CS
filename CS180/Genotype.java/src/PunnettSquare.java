import java.util.Objects;

/**
 * A diagram used to predict genotypes.
 *
 * <p>Purdue -- CS18000 -- Spring 2019 -- Class and Methods -- Homework</p>
 *
 * @author Jack Bauer, bauer88@purdue.edu
 *
 * @version February 25, 2019
 */
public final class PunnettSquare  {
    private Genotype mother;
    private Genotype father;

    public PunnettSquare() {
    }

    public PunnettSquare(Genotype mother, Genotype father) {
        this.mother = mother;
        this.father = father;
    }

    public Genotype getMother() {
        return mother;
    }

    public Genotype getFather() {
        return father;
    }

    public Genotype[][] getDiagram() {
        Genotype[][] geno = {{this.mother}, {this.father}};
        return geno;

    } // fix getDiagram

    public void setMother(Genotype mother) {
        this.mother = mother;
    }

    public void setFather(Genotype father) {
        this.father = father;
    }

    public String toString() {
        String punnettSquare = "PunnettSquare[mother = " + mother + ", father = " + father + "]";
        return punnettSquare;
    } // fix toString
    /*
     * Please DO NOT remove this method declaration. It ensures that the test cases will run correctly.
     */

    /**
     * Returns the hash code of this punnett square.
     *
     * @return the hash code of this punnett square
     */
    @Override
    public int hashCode() {
        int result = 23;

        result = 19 * result + Objects.hashCode(this.mother);

        result = 19 * result + Objects.hashCode(this.father);

        return result;
    } //hashCode

    /*
     * Please DO NOT remove this method declaration. It ensures that the test cases will run correctly.
     */

    /**
     * Determines whether or not the specified object is equal to this punnett square. {@code true} is returned if and
     * only if the specified object is an instance of {@code PunnettSquare} and its mother and father are equal to this
     * punnett square's.
     *
     * @param object the object to be compared
     * @return {@code true}, if the specified object is equal to this punnett square and {@code false} otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof PunnettSquare) {
            boolean equals;

            equals = Objects.equals(this.mother, ((PunnettSquare) object).mother);

            equals &= Objects.equals(this.father, ((PunnettSquare) object).father);

            return equals;
        } else {
            return false;
        } //end if
    } //equals
}
