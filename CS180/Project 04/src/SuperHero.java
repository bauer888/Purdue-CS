public class SuperHero {

    private Arsenal arsenal;
    private String name;

    public SuperHero(Arsenal arsenal, String name) {
        this.arsenal = arsenal;
        this.name = name;
    }

    public Arsenal getArsenal() {
        return arsenal;
    }

    public String getName() {
        return name;
    }

    public void setArsenal(Arsenal arsenal) {
        this.arsenal = arsenal;
    }

    public void setName(String name) {
        this.name = name;
    }
}
