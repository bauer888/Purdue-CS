public class Equipment {

    private String weapon;
    private String vehicle;

    public Equipment(String weapon, String vehicle) {
        this.weapon = weapon;
        this.vehicle = vehicle;
    }

    public Equipment(String weapon) {
        this.weapon = weapon;
    }

    public String getWeapon() {
        return weapon;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }
}
