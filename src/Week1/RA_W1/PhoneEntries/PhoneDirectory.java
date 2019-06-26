package Week1.RA_W1.PhoneEntries;

/**
 * A PhoneDirectory holds a list of names with a phone number for
 * each name. It is possible to find the number associated with
 * a given name, and to specify the phone number for a given name.
 */
public class PhoneDirectory {
    /**
     * An object of type PhoneEntry holds one name/number pair.
     */
    private static class PhoneEntry {
        String name; // The name.
        String number; // The associated phone number.
    }
    private PhoneEntry[] data; // Array that holds the name/number pairs.
    private int dataCount; // The number of pairs stored in the array.
    /**
     * Constructor creates an initially empty directory.
     */
    public PhoneDirectory() {
        data = new PhoneEntry[1];
        dataCount = 0;
    }
    /**
     * Looks for a name/number pair with a given name. If found, the index
     * of the pair in the data array is returned. If no pair contains the
     * given name, then the return value is -1.
     */
    private int find( String name ) {
        for (int i = 0; i < dataCount; i++) {
            if (data[i].name.equals(name))
                return i; // The name has been found in position i.
        }
        return -1; // The name does not exist in the array.
    }
    /**
     * Finds the phone number, if any, for a given name.
     * @return The phone number associated with the name; if the name does
     * not occur in the phone directory, then the return value is null.
     */
    public String getNumber( String name ) {
        int position = find(name);
        if (position == -1)
            return null; // There is no phone entry for the given name.
        else
            return data[position].number;
    }
    /**
     * Associates a given name with a given phone number. If the name
     * already exists in the phone directory, then the new number replaces
     * the old one. Otherwise, a new name/number pair is added. The
     * name and number should both be non-null. An IllegalArgumentException
     * is thrown if this is not the case.
     */
    public void putNumber( String name, String number ) {
        if (name == null || number == null)
            throw new IllegalArgumentException("name and number cannot be null");
        int i = find(name);
        if (i >= 0) {
// The name already exists, in position i in the array.
// Just replace the old number at that position with the new.
            data[i].number = number;
        }
        else {
// Add a new name/number pair to the array. If the array is
// already full, first create a new, larger array.
            if (dataCount == data.length) {
                PhoneEntry[] newData = new PhoneEntry[ 2*data.length ];
                System.arraycopy(newData,0,data,0,dataCount);
                data = newData;
            }
            PhoneEntry newEntry = new PhoneEntry(); // Create a new pair.
            newEntry.name = name;
            newEntry.number = number;
            data[dataCount] = newEntry; // Add the new pair to the array.
            dataCount++;
        }
    }
} // end class PhoneDirectory