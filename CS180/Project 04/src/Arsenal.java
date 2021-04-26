public class Arsenal {

    private Equipment equipment;
    private SuperPowers superPowers;

    public Arsenal(Equipment equipment, SuperPowers superPowers) {
        this.equipment = equipment;
        this.superPowers = superPowers;
    }

//    public Arsenal(Equipment equipment) {
//        this.equipment = equipment;
//    }
//
//    public Arsenal(SuperPowers superPowers) {
//        this.superPowers = superPowers;
//    }

    public Equipment getEquips() {
        return equipment;
    }

    public SuperPowers getSuperPowers() {
        return superPowers;
    }

    public void setEquips(Equipment equipment) {
        this.equipment = equipment;
    }

    public void setSuperPowers(SuperPowers superPowers) {
        this.superPowers = superPowers;
    }
}
