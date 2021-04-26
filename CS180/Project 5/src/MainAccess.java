import java.util.Random;
import java.util.Scanner;

public class MainAccess {
	public static void main(String[] args) {
		Random rand; //change rand's seed at your grade's peril

		if (args.length == 1) {
			try {
				rand = new Random(Integer.parseInt(args[0]));
			} catch (NumberFormatException e) {
				rand = new Random();
			}
		} else {
			rand = new Random(7777);
		}

		double[] defaultNum = {200, 800 + (rand.nextInt(7) - 3) * 100, 10 + rand.nextInt(2) - 1,
                               800 + (rand.nextInt(7) - 2) * 100, 250 + (rand.nextInt(6) * 10 - 25),
                               800 + (rand.nextInt(7) - 1) * 100, 500 + (rand.nextInt(11) * 10),
                               800 + (rand.nextInt(6)) * 100, 1000 + rand.nextInt(101) * 10};
		String input = "";
		String output = "";
		Scanner s = new Scanner(System.in);
		RobotOwner ro = new RobotOwner();
		ro.receiveMoney(defaultNum[0]);

		System.out.println("Welcome to the game! You are given " + defaultNum[0]
                           + " to assemble your robot, which one will you choose?");
		System.out.println("1. Iron\n2. Silver\n3. Gold");
		input = s.next();
		output = ro.getRobot(input);
		System.out.println(output);

		while (output.equals(ConstantNumber.GET_ROBOT_ERROR)) {
			System.out.println("Please write a correct command");
			System.out.println("Welcome to the game, you are given " + defaultNum[0]
                                + " to assemble your robot, which one will you choose?");
			System.out.println("1. Iron\n2. Silver\n3. Gold");
			input = s.next();
			output = ro.getRobot(input);
			System.out.println(output);
		}

		int i = 0;

		while (i <= 3) {
			i++;
			ro.receiveMoney(defaultNum[2 * i - 1]);
			System.out.println("You are given " + defaultNum[2 * i - 1] + " to buy weapons or upgrade your robot, your"
                                + " next enemy has an attack of " + defaultNum[2 * i]);
			System.out.println("Your robot's status: " + ro.printRobot());
			System.out.println("Your money is: " + ro.getMoney());
			System.out.println("1. BuyWeapon:[number of weapons]");
			System.out.println("2. SellWeapon:[number of weapons]");
			System.out.println("3. Upgrade:[Silver/Gold]");
			input = s.next();
			output = ro.operateCommand(input);
			System.out.println(output);

			while (output.contains(ConstantNumber.NUMBER_ERROR) 
				|| output.contains(ConstantNumber.UPGRADE_ERROR)
                || output.contains(ConstantNumber.COMMAND_ERROR)) {
			    System.out.println("Please write a correct command");
			    System.out.println("You are given "
			    		+ defaultNum[2 * i - 1]
			    	 	+ " to buy weapons or upgrade your robot, your"
                        + " next enemy has an attack of "
                        + defaultNum[2 * i]);
                System.out.println("Your money is: " +  ro.getMoney());
                System.out.println("1. BuyWeapon:[number of weapons]");
                System.out.println("2. SellWeapon:[number of weapons]");
                System.out.println("3. Upgrade:[Silver/Gold]");
                input = s.next();
				output = ro.operateCommand(input);
				System.out.println(output);
			}

			if (ro.getAttack() > defaultNum[2 * i]) {
				System.out.println("You win!!!!!");
			} else {
				System.out.println("You lose!");
				return;
			}
		}
	}
}
