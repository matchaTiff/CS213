import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateTest {

    @Test
    public void test(){
        Date testDate1 = new Date(10, 30, 2010);
        Date testDate2 = new Date(2, 29, 2005 );
        Date testDate3 = new Date( 2, 29, 2016);

        assertTrue(testDate1.isValid());
        assertFalse(testDate2.isValid());
        assertTrue(testDate3.isValid());

        Date Date1 = new Date(04, 05, 2010);
        Date Date2 = new Date(11, 22,2010);
        Date Date3 = new Date(11, 22, 2010);

        System.out.println(Date1.compareTo(Date2));
        System.out.println(Date2.compareTo(Date3));
        System.out.println(Date3.compareTo(Date1));

    }
}
