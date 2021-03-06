package eapli.framework.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Currency;
import javax.persistence.Embeddable;

/**
 * represents money values.
 *
 * based on http://martinfowler.com/eaaDev/quantity.html
 *
 */
@Embeddable
public class Money implements Comparable<Money>, Serializable {

    private final static transient int HUNDRED = 100;

    protected Money() {
        // for ORM tool only
    }

    /*
     * If you use one currency a lot, you may want a special constructor for
     * that currency.
     */
    public static Money dollars(final double amount) {
        return new Money(amount, Currency.getInstance("USD"));
    }

    public static Money euros(final double amount) {
        return new Money(amount, Currency.getInstance("EUR"));
    }

    private BigInteger amount;
    private Currency currency;

    /*
     * You'll notice there are no setters. Money is a Value Object and is thus
     * immutable. It helps to have a variety of constructors to make it easy to
     * make monies. Constructors that convert from basic numeric types are
     * always useful.
     */
    public Money(double amount, Currency currency) {
        this.amount = BigInteger.valueOf(Math.round(amount * HUNDRED));
        this.currency = currency;
    }

    public Money(long amount, Currency currency) {
        this.amount = BigInteger.valueOf(amount * HUNDRED);
        this.currency = currency;
    }

    private Money(BigInteger amountInPennies, Currency currency,
            boolean privacyMarker) {
        // Assert.notNull(amountInPennies);
        // Assert.notNull(currency);
        // FIX do validations
        this.amount = amountInPennies;
        this.currency = currency;
    }

    /*
     * Notice that I use a BigInteger. In Java I could equally well use a
     * BigDecimal, but in many languages an integer is the only decent option,
     * so using an integer seems the best for explanation. Don't be afraid to
     * choose your representation of the amount part of a quantity based on
     * performance factors. The beauty of objects is that you can choose any
     * data structure you like on the inside, providing you hide it on the
     * outside.
     */
    public double amount() {
        return amount.doubleValue() / HUNDRED;
    }

    public Currency currency() {
        return currency;
    }

    /*
     * For addition and subtraction I'm not trying to do any fancy conversion.
     * Notice that I'm using a special constructor with a marker argument.
     */
    public Money add(Money arg) {
        assertSameCurrencyAs(arg);
        return new Money(amount.add(arg.amount), currency, true);
    }

    public Money subtract(Money arg) {
        return this.add(arg.negate());
    }

    void assertSameCurrencyAs(Money arg) {
        // Assert.equals("money math mismatch", currency, arg.currency);
        // FIX do validations
    }

    public Money negate() {
        return new Money(amount.negate(), currency, true);
    }

    /*
     * Multiplication is very straightforward.
     */
    public Money multiply(double arg) {
        return new Money(amount() * arg, currency);
    }

    /*
     * But division is not, as we have to take care of errant pennies. We'll do
     * that by returning an array of monies, such that the sum of the array is
     * equal to the original amount, and the original amount is distributed
     * fairly between the elements of the array. Fairly in this sense means
     * those at the begriming get the extra pennies.
     */
    public Money[] divide(int denominator) {
        BigInteger bigDenominator = BigInteger.valueOf(denominator);
        Money[] result = new Money[denominator];
        BigInteger simpleResult = amount.divide(bigDenominator);
        for (int i = 0; i < denominator; i++) {
            result[i] = new Money(simpleResult, currency, true);
        }
        int remainder = amount.subtract(simpleResult.multiply(bigDenominator))
                .intValue();
        for (int i = 0; i < remainder; i++) {
            result[i] = result[i].add(new Money(BigInteger.valueOf(1),
                    currency, true));
        }
        return result;
    }

    /*
     * Next we'll look at comparing monies, in Java the approach is to implement
     * comparable.
     */
    @Override
    public int compareTo(Money moneyArg) {
        assertSameCurrencyAs(moneyArg);
        return amount.compareTo(moneyArg.amount);
    }

    /*
     * It's also useful to provide some better named operations such as: That
     * makes methods that need the comparison much easier to read.
     */
    public boolean greaterThan(Money arg) {
        return (this.compareTo(arg) == 1);
    }

    public boolean lessThan(Money arg) {
        return (this.compareTo(arg) == -1);
    }

    public boolean greaterThanOrEqual(Money arg) {
        return (this.compareTo(arg) >= 0);
    }

    public boolean lessThanOrEqual(Money arg) {
        return (this.compareTo(arg) <= 0);
    }

    /*
     * Since money is a value, it should override equals.
     */
    @Override
    public boolean equals(Object arg) {
        if (!(arg instanceof Money)) {
            return false;
        }
        Money other = (Money) arg;
        return (currency.equals(other.currency) && (amount.equals(other.amount)));
    }

    /*
     * Since you override equals, don't forget to also override hash (here's a
     * simple suggestion for that).
     */
    @Override
    public int hashCode() {
        return amount.hashCode();
    }
}
