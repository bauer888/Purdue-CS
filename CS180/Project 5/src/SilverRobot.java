public class SilverRobot implements Robot {
    private String name;
    private int attack;
    private double price;
    private int weapons;

    public SilverRobot() {
        this.name = "Silver Robot";
        this.attack = ConstantNumber.SILVER_ROBOT_ATTACK;
        this.price = ConstantNumber.SILVER_ROBOT_SALE_PRICE / 2;
        this.weapons = 0;
    }

    public SilverRobot(Robot r) {
        SilverRobot silverRobot = new SilverRobot(r);
    }

    public void addWeapon(int number) {
        return;  //TODO: delete this statement and write your code
    }

    public String getName() {
        return this.name; //TODO: delete this statement and write your code
    }

    public int getAttack() {
        return this.attack; //TODO: delete this statement and write your code
    }

    public double getPrice() {
        return this.price; //TODO: delete this statement and write your code
    }

    public int getWeapon() {
        return this.weapons; //TODO: delete this statement and write your code
    }
}
