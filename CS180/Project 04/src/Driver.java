import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    Scanner s = new Scanner(System.in);
    private ArrayList<SuperHero> heroList = new ArrayList<>();

    public void addHero() {
        boolean empty = false;
        ArrayList<String> powers = new ArrayList<>(0);
        System.out.println("Enter the hero's world: ");
        String world = s.nextLine();
        if (!world.equalsIgnoreCase("Marvel") && !world.equalsIgnoreCase("DC Universe")) {
            System.out.println("Invalid world");
            return;
        }
        System.out.println("Enter the hero's name: ");
        String name = s.nextLine();
        for (int i = 0; i < heroList.size(); i++) {
            if (heroList.get(i).getName().equalsIgnoreCase(name)) {
                System.out.println("Hero already exists");
                return;
            }
        }
        System.out.println("Enter the hero's weapon: ");
        String weapon = s.nextLine();
        System.out.println("Enter the hero's vehicle: ");
        String vehicle = s.nextLine();
        String power;
        int counter = 0;
        while (!empty) {
            System.out.println("Enter the hero's powers: ");
            power = s.nextLine();
            if (power.equalsIgnoreCase("")) {
                counter++;
                empty = true;
            } else {
                powers.add(power);
                counter++;
            }
        }
        String[] realPowers = new String[powers.size()];
        for (int i = 0; i < realPowers.length; i++) {
            realPowers[i] = powers.get(i);
        }
        //realPowers = powers.toArray(realPowers);


        //String[] realPowers = (String[]) powers.toArray();
        Equipment equipment = new Equipment(weapon, vehicle);
        SuperPowers superPowers = new SuperPowers(realPowers);
        Arsenal arsenal = new Arsenal(equipment, superPowers);
        //SuperHero superHero = null;// = new SuperHero(arsenal,name);
        if (world.equalsIgnoreCase("Marvel")) {
            //superHero = new Marvel(arsenal, name);
            heroList.add(new Marvel(arsenal, name));
            System.out.println(name + " added.");
        } else if (world.equalsIgnoreCase("DC Universe")) {
            //superHero = new DC(arsenal, name);
            heroList.add(new DC(arsenal, name));
            System.out.println(name + " added.");
        }
//        heroList.add(superHero);
//        System.out.println(name + " added.");

    }

    public void modifyHero(String name) {
        System.out.println("1 - Change weapon");
        System.out.println("2 - Change vehicle");
        System.out.println("3 - Change powers");
        String option = s.nextLine();
        while (option.equalsIgnoreCase("")) {
            option = s.nextLine();
        }
        switch (option) {
            case "1":
                for (int i = 0; i < heroList.size(); i++) {
                    if (heroList.get(i) == null) {
                        continue;
                    } else if (heroList.get(i).getName().equalsIgnoreCase(name)) {
                        System.out.println("Enter new weapon: ");
                        String newWeapon = s.nextLine();
                        heroList.get(i).getArsenal().getEquips().setWeapon(newWeapon);
//                        Equipment equipment = new Equipment(newWeapon, heroList.get(i).getArsenal().getEquips().getVehicle());
//                        Arsenal arsenal = new Arsenal(equipment, heroList.get(i).getArsenal().getSuperPowers());
//                        heroList.get(i).setArsenal(arsenal);
                        break;
                    }
                }
                break;
            case "2":
                for (int i = 0; i < heroList.size(); i++) {
                    if (heroList.get(i) == null) {
                        continue;
                    } else if (heroList.get(i).getName().equalsIgnoreCase(name)) {
                        System.out.println("Enter new vehicle: ");
                        String newVehicle = s.nextLine();
                        heroList.get(i).getArsenal().getEquips().setVehicle(newVehicle);
//                        Equipment equipment = new Equipment(heroList.get(i).getArsenal().getEquips().getWeapon(), newVehicle);
//                        Arsenal arsenal = new Arsenal(equipment, heroList.get(i).getArsenal().getSuperPowers());
//                        heroList.get(i).setArsenal(arsenal);
                        break;
                    }
                }
                break;
            case "3":
                for (int i = 0; i < heroList.size(); i++) {
                    if (heroList.get(i) == null) {
                        continue;
                    } else if (heroList.get(i).getName().equalsIgnoreCase(name)) {
                        boolean powers = true;
                        ArrayList<String> temp = new ArrayList<>(0);
                        int counter = 0;
                        while (powers) {
                            System.out.println("Enter new power: ");
                            String newPower = s.nextLine();
                            if (newPower.equalsIgnoreCase("")) {
                                counter++;
                                powers = false;
                            } else {
                                temp.add(newPower);
                                counter++;
                            }
                        }
                        String[] sArray = new String[temp.size()];
                        for (int j = 0; j < sArray.length; j++) {
                            sArray[i] = temp.get(i);
                        }
                        //temp.toArray(sArray);
                        heroList.get(i).getArsenal().setSuperPowers(new SuperPowers(sArray));
//                        SuperPowers superPowers = new SuperPowers(sArray);
//                        Arsenal arsenal = new Arsenal(heroList.get(i).getArsenal().getEquips(), superPowers);
//                        heroList.get(i).setArsenal(arsenal);
                        break;
                    }
                }
                break;
        }
    }

    public void removeHero(String name) {
        if (name == null) {
            return;
        }
        for (int i = 0; i < heroList.size(); i++) {
            if (heroList.get(i) == null) {
                continue;
            } else
                if (name.equalsIgnoreCase(getHeroList().get(i).getName())) {
                getHeroList().remove(i);
                System.out.println("Hero removed!");
                break;
            }
        }
    }

    public void printDetail(String name) {
        if (name == null) {
            return;
        }
        for (int i = 0; i < heroList.size(); i++) {
            if (heroList.get(i) == null) {
                continue;
            } else if (name.equalsIgnoreCase(heroList.get(i).getName())) {
                if (heroList.get(i) instanceof Marvel) {
                    System.out.println("World: Marvel");
                    System.out.println("Name: " + heroList.get(i).getName());
                    System.out.println("====== Equipment ======");
                    System.out.println("Weapon: " + heroList.get(i).getArsenal().getEquips().getWeapon());
                    System.out.println("Vehicle: " + heroList.get(i).getArsenal().getEquips().getVehicle());
                    System.out.println("====== Powers ======");
                    for (int j = 0; j < heroList.get(i).getArsenal().getSuperPowers().getPowers().length; j++) {
//                        if (j == heroList.get(i).getArsenal().getSuperPowers().getPowers().length - 1) {
//
//                        } else {
                            System.out.println((j + 1) + ": " + heroList.get(i).getArsenal().getSuperPowers().getPowers()[j]);
                        //}
                    }
                    System.out.println();
                    break;
                } else if (heroList.get(i) instanceof DC) {
                    System.out.println("World: DC Universe");
                    System.out.println("Name: " + heroList.get(i).getName());
                    System.out.println("====== Equipment ======");
                    System.out.println("Weapon: " + heroList.get(i).getArsenal().getEquips().getWeapon());
                    System.out.println("Vehicle: " + heroList.get(i).getArsenal().getEquips().getVehicle());
                    System.out.println("====== Powers ======");
                    for (int j = 0; j < heroList.get(i).getArsenal().getSuperPowers().getPowers().length; j++) {
//                        if (j == heroList.get(i).getArsenal().getSuperPowers().getPowers().length - 1) {
//
//                        } else {
                            System.out.println((j + 1) + ": " + heroList.get(i).getArsenal().getSuperPowers().getPowers()[j]);
                        //}
                    }
                    System.out.println();
                    break;
                }
            }
        }
    }

    public void printHeroes() {
        for (int i = 0; i <= 1; i++) {
            if (i == 0) {
                System.out.println("====== World: Marvel ======");
                int counter = 0;
                for (int j = 0; j < heroList.size(); j++) {
                    if (heroList.get(j) == null) {
                        continue;
                    } else if (heroList.get(j) instanceof Marvel) {
                        counter++;
                        System.out.println(counter + ": " + heroList.get(j).getName());
                    }
                }
            } else {
                System.out.println("====== World: DC Universe ======");
                int counter = 0;
                for (int j = 0; j < heroList.size(); j++) {
                    if (heroList.get(j) == null) {
                        continue;
                    } else if (heroList.get(j) instanceof DC) {
                        counter++;
                        System.out.println(counter + ": " + heroList.get(j).getName());
                    }
                }
            }
        }
    }

    public ArrayList<SuperHero> getHeroList() {
        return this.heroList;
    }

    public void addFromFile(String name) {
        if (name == null) {
            return;
        }

        File f = new File(name);
        try {
            if (!f.exists()) {
                return;
            }
            FileReader fr = new FileReader(f);
            BufferedReader n = new BufferedReader(fr);
            //Scanner n = new Scanner(f);
            int counter = 0;
            String line;
            while ((line = n.readLine()) != null) {
                //line = n.readLine();
                if (line.isEmpty()) {

                } else {
                    String[] array = line.split(":");
                    //int colon = s.indexOf(":");
                    String world = array[0];
                    //s = s.substring(colon + 2);
                    String[] temp = array[1].split(";");
                    String nombre = temp[0].trim();
                    String weapon = temp[1].trim();
                    String vehicle = temp[2].trim();
                    String[] stuff = temp[3].split(",");
                    ArrayList<String> file = new ArrayList<>(0);
                    for (int i = 0; i < stuff.length; i++) {
                        if (stuff[i].equalsIgnoreCase("none")) {

                        } else {
                            file.add(stuff[i].trim());
                            counter++;
                        }
                    }
                    String[] powers = new String[file.size()];
                    for (int i = 0; i < powers.length; i++) {
                        powers[i] = file.get(i);
                    }
                    //file.toArray(powers);
                    Equipment equipment = new Equipment(weapon, vehicle);
                    SuperPowers superPowers = new SuperPowers(powers);
                    Arsenal arsenal = new Arsenal(equipment, superPowers);
                    if (world.equalsIgnoreCase("Marvel")) {
                        Marvel marvel = new Marvel(arsenal, nombre);
                        heroList.add(marvel);
                    } else if (world.equalsIgnoreCase("DC Universe")) {
                        DC dc = new DC(arsenal, nombre);
                        heroList.add(dc);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(String filename) {
        if (filename == null) {
            return;
        }

        File f = new File(filename);
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileWriter fw = new FileWriter(f, true);
            BufferedWriter pw = new BufferedWriter(fw);
            for (int i = 0; i < heroList.size(); i++) {
                if (heroList.get(i) == null) {
                    continue;
                } else {
                    if (heroList.get(i) instanceof Marvel) {
                        String world = "Marvel: ";
                        String name = heroList.get(i).getName() + "; ";
                        String weapon = heroList.get(i).getArsenal().getEquips().getWeapon() + "; ";
                        String vehicle = heroList.get(i).getArsenal().getEquips().getVehicle() + "; ";
                        String powers = "";
                        for (int j = 0; j < heroList.get(i).getArsenal().getSuperPowers().getPowers().length; j++) {
                            if (j == heroList.get(i).getArsenal().getSuperPowers().getPowers().length - 1) {
                                powers += heroList.get(i).getArsenal().getSuperPowers().getPowers()[j];
                            } else {
                                powers += heroList.get(i).getArsenal().getSuperPowers().getPowers()[j] + ", ";
                            }
                        }
                        String result = world + name + weapon + vehicle + powers + ";\n";
                        pw.write(result);
                        pw.flush();
                    } else if (heroList.get(i) instanceof DC) {
                        String world = "DC Universe: ";
                        String name = heroList.get(i).getName() + "; ";
                        String weapon = heroList.get(i).getArsenal().getEquips().getWeapon() + "; ";
                        String vehicle = heroList.get(i).getArsenal().getEquips().getVehicle() + "; ";
                        String powers = "";
                        for (int j = 0; j < heroList.get(i).getArsenal().getSuperPowers().getPowers().length; j++) {
                            if (j == heroList.get(i).getArsenal().getSuperPowers().getPowers().length - 1) {
                                powers += heroList.get(i).getArsenal().getSuperPowers().getPowers()[j];
                            } else {
                                powers += heroList.get(i).getArsenal().getSuperPowers().getPowers()[j] + ", ";
                            }
                        }
                        String result = world + name + weapon + vehicle + powers + ";\n";
                        pw.write(result);
                        pw.flush();
                    }
                }
            }
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        boolean menu = true;

        while (menu) {
            System.out.println("1 - Add Hero");
            System.out.println("2 - Modify Hero");
            System.out.println("3 - Remove Hero");
            System.out.println("4 - Print Hero Details");
            System.out.println("5 - Print all Heroes");
            System.out.println("6 - Read from file");
            System.out.println("7 - Write to file");
            System.out.println("8 - Exit");
            String option = s.nextLine();
            while (option.equalsIgnoreCase("")) {
               option = s.nextLine();
            }
            switch (option) {
                case "1":
                    addHero();
                    break;
                case "2":
                    System.out.println("Enter hero name: ");
                    String next = s.nextLine();
                    for (int i = 0; i < heroList.size(); i++) {
                        if (next.equalsIgnoreCase(heroList.get(i).getName())) {
                            modifyHero(next);
                            break;
                        }
                    }
                    break;
                case "3":
                    System.out.println("Enter the name of the hero you want to remove: ");
                    String name2 = s.nextLine();
                    removeHero(name2);
                    break;
                case "4":
                    System.out.println("Enter the name of the hero you want to print: ");
                    String name3 = s.nextLine();
                    printDetail(name3);
                    break;
                case "5":
                    printHeroes();
                    break;
                case "6":
                    System.out.println("Enter the name of the file you want to read from: ");
                    String name4 = s.nextLine();
                    addFromFile(name4);
                    break;
                case "7":
                    System.out.println("Enter the name of the file you want to write to: ");
                    String name5 = s.nextLine();
                    writeToFile(name5);
                    break;
                case "8":
                    menu = false;
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid command");
                    continue;
            }

            boolean repeat = true;
            while (repeat) {
                System.out.println("Return to menu? (y/n)");
                String yn = s.nextLine();
                if (yn.equalsIgnoreCase("y")) {
                    repeat = false;
                    menu = true;
                    break;
                } else if (yn.equalsIgnoreCase("n")) {
                    repeat = false;
                    menu = false;
                } else {
                    repeat = true;
                }
            }
        }
    }
    public static void main(String[] args) {
        Driver d = new Driver();
        d.run();
/*        String[] powers = {"laser", "beam"};
        SuperHero hero = new Marvel(new Arsenal(new Equipment("sword")), "cool guy");
        System.out.println(hero.getArsenal());
        System.out.println(hero.getArsenal().getEquips());
        System.out.println(hero.getArsenal().getEquips().getWeapon()); */
    }
}
