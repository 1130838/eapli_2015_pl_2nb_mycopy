/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.mymoney.presentation;

import eapli.mymoney.application.EditExpenseGroupController;
import eapli.mymoney.domain.ExpenseGroup;
import eapli.util.Console;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author joaomonteiro
 */
public class EditExpenseGroupUI extends BaseUI {

	/**
	 * Controller of the UC.
	 */
	private final EditExpenseGroupController controller
		= new EditExpenseGroupController();

	/**
	 * Name of the expense group to change data.
	 */
	private String expenseGroup;

	/**
	 * Begin of period.
	 */
	private Calendar beginPeriod;
	/**
	 * End of period.
	 */
	private Calendar endPeriod;
	/**
	 * The value of the budget estimation.
	 */
	private BigDecimal estimation;

	public List<ExpenseGroup> getListExpenseGroup() {

		return controller.getListExpenseGroup();

	}

	/**
	 * Show list of Expense groups and its data to change.
	 *
	 * @return true
	 */
	@Override
	protected boolean doShow() {

		String opcao;
		List<ExpenseGroup> listExpenseGroups = getListExpenseGroup();
		System.out.println("Choose one Expense Group: \n");
		for (int i = 0; i < listExpenseGroups.size(); i++) {
			System.out.println(i + " - " + listExpenseGroups.get(i).getName());
		}

		System.out.println("Choose: \n");
		Console.readLine(expenseGroup);//TODO : CREATE CODE TO DEAL WITH ERRORS
		beginPeriod = controller.getBeginPeriod(expenseGroup);
		System.out.println("Begin Period:\n");
		System.out.println(beginPeriod);
		opcao = Console.readLine("Do You want change? (y/n)");
		if (opcao == "y") {
			beginPeriod = Console.
				readCalendar("Enter expense group begin period » ");
		}
		endPeriod = controller.getEndPeriod(expenseGroup);
		System.out.println("Begin Period:\n");
		System.out.println(endPeriod);
		opcao = Console.readLine("Do You want change? (y/n)");
		if (opcao == "y") {
			endPeriod = Console.
				readCalendar("Enter expense group begin period » ");
		}

		estimation = controller.getEstimation(expenseGroup);
		System.out.println("Begin Period:\n");
		System.out.println(estimation);
		opcao = Console.readLine("Do You want change? (y/n)");
		if (opcao == "y") {
			estimation = BigDecimal.valueOf(Console.
				readDouble("Enter expense group begin period » "));
		}
		controller.
			editExpenseGroup(expenseGroup, beginPeriod, endPeriod, estimation);

		return true;

	}

	/**
	 * TODO Need to create code to support that code.
	 *
	 * @return
	 */
	@Override
	public String headline() {
		return "Editar Grupo de Despesas!";
	}

	private void submit() {
		controller.editExpenseGroup(expenseGroup, beginPeriod,
									endPeriod, estimation);
		System.out.println("\nExpense group recorded!");

	}

}
