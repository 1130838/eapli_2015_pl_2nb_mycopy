/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.mymoney.presentation;

import eapli.util.Console;

/**
 * The application's main menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends BaseUI {

	private boolean showMainMenu() {
		int option = -1;
		String menuOpt
			= "--- master tables ---\n"
			+ "100. Register an expense type\n"
			+ "101. List expense types\n\n"
			+ "102. Register Expense Group\n"
			+ "103. Disable Expense Group\n"
			+ "104. Show Expense Group Status\n\n"
			+ "105. Register Monthly Budget\n"
			+ "106. Update Monthly Budget\n"
			+ "107. Register Expense\n"
			+ "108. Assign Expense Type to Expense Group\n"
			+ "115. Edit Expense Group\n"
			+ "---------------------\n"
			+ "0. Exit\n\n";

		System.out.println(menuOpt);
		option = Console.readInteger("Please choose an option");
		switch (option) {
			case 0:
				System.out.println("bye bye ...");
				return true;
			case 100:
				final RegisterExpenseTypeUI uc01 = new RegisterExpenseTypeUI();
				uc01.show();
				break;
			case 101:
				final ListExpenseTypesUI uc01_L = new ListExpenseTypesUI();
				uc01_L.show();
				break;
			case 102:
				final RegisterExpenseGroupUI uc01_G = new RegisterExpenseGroupUI();
				uc01_G.show();
				break;
			case 103:
				final RegisterExpenseGroupUI uc03_G = new RegisterExpenseGroupUI();
				uc03_G.doShow();
				break;
			case 104:
				final ShowExpenseGroupStatusUI uc04_G = new ShowExpenseGroupStatusUI();
				uc04_G.doShow();
				break;
			case 105:
				final RegisterMonthlyBudgetUI uc01_O = new RegisterMonthlyBudgetUI();
				uc01_O.show();
				break;
			case 106:
				final UpdateMonthlyBudgetUI uc01_1 = new UpdateMonthlyBudgetUI();
				uc01_1.show();
				break;
			case 107:
				final RegisterExpenseUI uc01_2 = new RegisterExpenseUI();
				uc01_2.doShow();
				break;
			case 108:
				final AssignExpenseTypeToGroupUI UC_G_006 = new AssignExpenseTypeToGroupUI();
				UC_G_006.doShow();
				break;
			case 109:
				final EditExpenseGroupUI ucg002 = new EditExpenseGroupUI();
				ucg002.doShow();
				break;
			default:
				System.out.println("option not recognized.");
				break;
		}
		return false;
	}

	@Override
	protected boolean doShow() {
		return showMainMenu();
	}

	@Override
	public String headline() {
		return "myMoney - EXPENSE MANAGER";
	}

}
