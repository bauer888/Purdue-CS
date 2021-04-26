import java.util.Objects;
import java.util.Arrays;

/**
 * An organism's genetic makeup.
 *
 * <p>Purdue -- CS18000 -- Spring 2019 -- Class and Methods -- Homework</p>
 *
 * @author Jack Bauer, bauer88@purdue.edu
 * @version February 25, 2019
 */
public final class Genotype {
    private String traitName;
    private char[] alleles;

    public Genotype() {
        traitName = null;
        alleles = null;
    }

    public Genotype(String traitName, char[] alleles) {
        this.traitName = traitName;
        this.alleles = alleles;
    }

    public String getTraitName() {
        return traitName;
    }

    public char[] getAlleles() {
        return alleles;
    }

    public void setTraitName(String traitName) {
        this.traitName = traitName;
    }

    public void setAlleles(char[] alleles) {
        this.alleles = alleles;
    }

    public String toString() {
        String genotype = "Genotype[traitName = " + traitName + ", alleles = " + Arrays.toString(alleles) + "]";
        return genotype;
    } // fix toString

    /*
     * Please DO NOT remove this method declaration. It ensures that the test cases will run correctly.
     */
    /**
     * Returns the hash code of this genotype.
     *
     * @return the hash code of this genotype
     */
    @Override
    public int hashCode() {
        int result = 23;

        result = 19 * result + Objects.hashCode(this.traitName);

        for (char allele : this.alleles) {
            result = 19 * result + allele;
        } //end for

        return result;
    } //hashCode

    /*
     * Please DO NOT remove this method declaration. It ensures that the test cases will run correctly.
     */
    /**
     * Determines whether or not the specified object is equal to this genotype. {@code true} is returned if and only
     * if the specified object is an instance of {@code Genotype} and its trait name and alleles are equal to this
     * genotype's.
     *
     * @param object the object to be compared
     * @return {@code true}, if the specified object is equal to this genotype and {@code false} otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof Genotype) {
            boolean equals;

            equals = Objects.equals(this.traitName, ((Genotype) object).traitName);

            equals &= Arrays.equals(this.alleles, ((Genotype) object).alleles);

            return equals;
        } else {
            return false;
        } //end if
    } //equals
}