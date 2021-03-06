package com.techelevator;

import com.techelevator.view.Menu;

import java.util.List;
import java.util.Map;

public class VendingMachineCLI extends Import {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT= "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
		MAIN_MENU_OPTION_EXIT};

	private Menu menu;
	private Purchase purchase = new Purchase();
	private Log log = new Log();

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		stockVendingMachine();


		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				Map<String, List<String>> vendingItems = getVendingCategories();
				for(Map.Entry<String, List<String>> item:vendingItems.entrySet()) {
					if (item.getKey().contains("*")) {
						System.out.println("SOLD OUT: " + item.getValue().get(0));
					} else {
						System.out.println(item.getKey() + ": " + item.getValue().get(0) + " " + item.getValue().get(1));
					}
				}

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				purchase.purchaseMenu(getVendingCategories());
			}
			else if(choice.equals(MAIN_MENU_OPTION_EXIT)) {
				log.logMessage("Exit Program");
				break;
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
