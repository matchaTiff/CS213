import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MoneyMarketTest {

    @Test
    public void test(){
        Profile testProfile = new Profile("Bob", "Vance");
        Date date = new Date( 11, 30, 2015);

        Account testAccount1 = new MoneyMarket(testProfile, 100, date, 0);
        Account testAccount2 = new MoneyMarket(testProfile, 3000, date, 0);
        Account testAccount3 = new MoneyMarket(testProfile, 3000, date, 7);

        assertTrue(testAccount1.monthlyFee() != 0);
        assertFalse(testAccount2.monthlyFee() != 0);
        assertTrue(testAccount3.monthlyFee() != 0);

        System.out.println(testAccount1.toString());
        System.out.println("Monthly Fee: " + testAccount1.monthlyFee());
        System.out.println("Monthly Interest: " + testAccount1.monthlyInterest());
        System.out.println(testAccount2.toString());
        System.out.println("Monthly Fee: " + testAccount2.monthlyFee());
        System.out.println("Monthly Interest: " + testAccount2.monthlyInterest());
        System.out.println(testAccount3.toString());
        System.out.println("Monthly Fee: " + testAccount3.monthlyFee());
        System.out.println("Monthly Interest: " + testAccount3.monthlyInterest());

    }
}
