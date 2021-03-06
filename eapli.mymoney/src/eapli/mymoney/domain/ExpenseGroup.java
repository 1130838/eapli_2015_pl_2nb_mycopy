/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.mymoney.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Joel
 */
@Entity
public class ExpenseGroup implements Serializable {

	/**
	 * Name of the group.
	 */
	@Id
	private String name;
	/**
	 * Period in witch the group can be used.
	 */
	private Period period;
	/**
	 * This argument is opcional and sets a budget for the current group.
	 */
	private BigDecimal estimation;
	/*
	 * This attribute allows us to control the current status of this group.
	 */
	private boolean status;

	/**
	 * ExpenseTypes associated with this Expense Group (UC-G-006)
	 */
	@OneToMany
	private List<ExpenseType> expenseTypes;

	public ExpenseGroup() {

	}

	/**
	 * Creates Expense Group.
	 *
	 * @param expenseGroupText Expense Group name
	 * @param beginPeriod Beginning of period
	 * @param endPeriod End of period
	 * @param budgetEstimation The value of budget estimation
	 */
	public ExpenseGroup(final String expenseGroupText,
						final Calendar beginPeriod,
						final Calendar endPeriod,
						final BigDecimal budgetEstimation
	) {
		this.name = expenseGroupText;
		this.period = new Period(beginPeriod, endPeriod);
		this.estimation = budgetEstimation;
		this.status = true;
		this.expenseTypes = new ArrayList<>();
	}

	/**
	 * Construtor com pârametro de status
	 *
	 * @param expenseGroupText Expense Group name
	 * @param beginPeriod Beginning of period
	 * @param endPeriod End of period
	 * @param budgetEstimation The value of budget estimation
	 * @param status boolean -> true to enable (default), false to disable
	 */
	public ExpenseGroup(final String expenseGroupText,
						final Calendar beginPeriod,
						final Calendar endPeriod,
						final BigDecimal budgetEstimation,
						final boolean status) {
		this.name = expenseGroupText;
		this.period = new Period(beginPeriod, endPeriod);
		this.estimation = budgetEstimation;
		this.status = status;
		this.expenseTypes = new ArrayList<>();
	}

	/**
	 * Add expense type to the accepted expense type list of this group.
	 *
	 * @param expenseType expense type object
	 */
	public void addExpenseType(ExpenseType expenseType) {
		this.expenseTypes.add(expenseType);
	}

	/**
	 * Remove a specific expense type from the lsit of expense types of this
	 * group.
	 *
	 * @param idExpenseType Expense Type identifier
	 */
	public void removeExpenseType(int idExpenseType) {
		for (int i = this.expenseTypes.size() - 1; i > -1; i--) {
			if (idExpenseType == this.expenseTypes.get(i).getID()) {
				this.expenseTypes.remove(i);
			}
		}
	}

	/**
	 * Get the expense group name
	 *
	 * @return expense group name (string)
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the expense group estimation
	 *
	 * @return expense group estimation (BigDecimal)
	 */
	public BigDecimal getEstimation() {
		return estimation;
	}

	/**
	 * Returns all the expense Types of this group.
	 *
	 * @return List of Expense Type
	 */
	public List<ExpenseType> getExpenseTypes() {
		return expenseTypes;
	}

	/**
	 * Method to return the year of the expenses
	 *
	 * @return year of the expenses
	 */
	public int obtainExpenseYear() {
		int a = period.getPeriodEnd().getTime().getYear();

		return a;
	}
}
