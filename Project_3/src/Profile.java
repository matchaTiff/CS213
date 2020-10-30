/**
 * This class defines the profile of an account holder.
 *
 * @author Seth Santos, Tiffany Chen
 *
 */
public class Profile {
    private String fname;
    private String lname;

    /**
     * Default constructor for Profile
     */
    public Profile() {
        fname = "N/A";
        lname = "N/A";
    }

    /**
     * Constructor with parameters
     * @param fname Account holder's first name
     * @param lname Account holder's last name
     */
    public Profile(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
    }

    /**
     * Combines first and last names to make full name
     * @return Full name of account holder
     */
    @Override
    public String toString() {
        return (fname + " " + lname);
    }

    /**
     * Getter method for First name
     * @return first name of profile holder
     */
    public String getFirstName() {
        return fname;
    }

    /**
     * Getter method for Last name
     * @return last name of profile holder
     */
    public String getLastName() {
        return lname;
    }

    /**
     * Compares the first and last names of two different profiles
     * @param obj The Profile being compared to
     * @return True if the same
     *         False if different
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            Profile holder = (Profile) obj;
            if (this.fname.equals(holder.fname) &&
                    this.lname.equals(holder.lname)) {
                return true;
            }
        }
        return false;
    }
}
