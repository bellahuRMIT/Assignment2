import java.util.ArrayList;
import java.util.Scanner;

//This is my OBJECT-ORIENTED solution for Part B:
public class MyTiManager {
	static double balance = 0.0;
	static Scanner scanner = new Scanner(System.in);

	public static void printPurchases() {
		System.out.println("Purchases:");
		for (TravelPass ticket : purchaseHistory) {
			System.out.println("Purchased " + ticket.getDescription());
		}
		System.out.println("");
	}

	static ArrayList<TravelPass> purchaseHistory = new ArrayList<>();

	public static void processTicket(TravelPass ticket) {
		balance = ticket.buyTicket(balance);
		if (ticket.getIsSuccess()) {
			purchaseHistory.add(ticket);
		}
	}

	public static void buyTravelPass() {
		System.out.println("What time period:\r\n" + "a) 2 Hours\r\n" + "b) All Day\r\n" + "c) cancel");
		String timeChoice = scanner.next().toLowerCase();// nextLine() won't work
		while (!"a".equals(timeChoice) && !"b".equals(timeChoice) && !"c".equals(timeChoice)) {
			System.out.println("Invalid input. a:2 Hours, b:All Day, c: Cancel");
			timeChoice = scanner.next().toLowerCase();
		}
		System.out.println("Your selection:" + timeChoice + "\r\n");
		if (!"c".equals(timeChoice)) {
			System.out.println("Which zone:\r\n" + "a) Zone 1\r\n" + "b) Zones 1 and 2\r\n" + "c) cancel");
			String zoneChoice = scanner.next().toLowerCase(); // nextLine() won't work
			while (!"a".equals(zoneChoice) && !"b".equals(zoneChoice) && !"c".equals(zoneChoice)) {
				System.out.println("Invalid input. a:Zone 1, b:Zone 1+2, c: Cancel");
				zoneChoice = scanner.next().toLowerCase();
			}
			System.out.println("Your selection:" + zoneChoice + "\r\n");
			if (!"c".equals(zoneChoice)) {
				String choice = timeChoice + zoneChoice;
				switch (choice) {
				case "aa":
					Type1Ticket myTi1 = new Type1Ticket();
					processTicket(myTi1);
					break;
				case "ab":
					Type2Ticket myTi2 = new Type2Ticket();
					processTicket(myTi2);
					break;
				case "ba":
					Type3Ticket myTi3 = new Type3Ticket();
					processTicket(myTi3);
					break;
				case "bb":
					Type4Ticket myTi4 = new Type4Ticket();
					processTicket(myTi4);
					break;
				default:
					break;
				}
				showCredit();
			}
		}
	}

	public static void showCredit() {
		System.out.println("Your credit = $" + Math.round(balance * 100.0) / 100.0 + "\r\n");

	}

	// Keep it DRY...
	public static int getInt(String message) {
		System.out.println(message);
		while (!scanner.hasNextInt()) {
			System.out.println("Invalid input:" + scanner.next());
			System.out.println("You must enter a number:");
		}
		return scanner.nextInt();
	}

	public static int getAmount() {
		int validAmount = 0;
		int amount = getInt("How much do you want to add:");
		while (amount <= 0) {
			amount = getInt("You must enter a positive number:");
		}
		if (amount + balance > 100) {
			System.out.println("Sorry, the max amount of credit allowed is $100.00");
		} else {
			if (amount % 5 != 0) {
				System.out.println("Sorry, you can only add multiples of $5.00");
			} else {
				validAmount = amount;
			}
		}
		return validAmount;
	}

	public static void chargeMyTi() {
		int amount = getAmount();
		balance += amount;
		System.out.println("Your credit = $" + balance);
	}

	public static int getMenuChoice() {
		boolean invalidInput = true;
		int option = 0;
		System.out.println("Menu Options:\r\n" + "1. Buy a travel pass\r\n" + "2. Charge my MyTi\r\n"
				+ "3. Show remaining credit\r\n" + "4. Print purchases\r\n" + "0. Quit");
		do {
			System.out.println("Enter a menu option (integer only): ");
			try {
				option = Integer.parseInt(scanner.next()); // skip the incorrect value
				invalidInput = false;
			} catch (Exception e) {
				System.out.println("Invalid option. Try again. ");
			}
		} while (invalidInput);
		return option;
	}

	public static void main(String[] args) {
		int option = getMenuChoice();
		while (option != 0) {
			switch (option) {
			case 1:
				buyTravelPass();
				break;
			case 2:
				chargeMyTi();
				break;
			case 3:
				showCredit();
				break;
			case 4:
				printPurchases();
				break;
			case 0:
				System.out.println("Goodbye!");
				System.exit(0);
				break;
			default:
				System.out.println("Please enter a valid choice.");
				break;
			}
			option = getMenuChoice();
		}
	}
}
