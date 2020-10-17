import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckingTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    public void test(){
        Profile testProfile = new Profile("Bob", "Vance");
        Date date = new Date( 11, 30, 2015);

        Account testAccount1 = new Checking(testProfile, 500, date, true);
        Account testAccount2 = new Checking(testProfile, 1000, date ,false);

        assertTrue( ((Checking)testAccount1).getIsDirectDeposit() );
        assertFalse( ((Checking)testAccount2).getIsDirectDeposit() );

        System.out.println(testAccount1.toString());
        System.out.println("Monthly Fee: " + testAccount1.monthlyFee());
        System.out.println("Monthly Interest: " + testAccount1.monthlyInterest());
        System.out.println(testAccount2.toString());
        System.out.println("Monthly Fee: " + testAccount2.monthlyFee());
        System.out.println("Monthly Interest: " + testAccount2.monthlyInterest());

    }
}
