package Week2.LinkedDataStructures;

/**
 * An object of type StringList represents a list of integers.  Methods
 * are provided to insert an integer number into the list, to delete a number
 * from the list, and to check whether a given number occurs in the list.
 * (For testing purposes, a method is also provided that will return an
 * array containing all the numbers in the list.)
 *
 * Note that this class is certainly NOT meant to be a full-featured
 * List class.  It is for demonstration only.
 */
public class IntList {


    /**
     * Internally, the list of numbers is represented as a linked list of
     * nodes belonging to the nested class Node.  The strings in the list
     * are stored in increasing order (using the order given by the
     * compareTo() method from the string class, which is the same as
     * alphabetical order if all the strings are made up of lower
     * case letters.)
     */
    private static class Node {
        int item;       // One of the items in the list
        Node next;      // Pointer to the node containing the next item.
        // In the last node of the list, next is null.
    }

    private Node head;  // A pointer to the first node in the linked list.
    // If the list is empty, the value is null.

    /**
     * Searches the list for a specified number. (Note: for demonstration
     * purposes, this method does not use the fact that the items in the
     * list are ordered.)
     * @param searchItem the item that is to be searched for
     * @return true if searchItem is one of the items in the list or false if
     *    searchItem does not occur in the list.
     */
    public boolean isPresent(int searchItem) {
        Node runner;    // A pointer for traversing the list.
        runner = head;  // Start by looking at the head of the list.
        while ( runner != null ) {
            // Go through the list looking at the string in each
            // node.  If the string is the one we are looking for,
            // return true, since the string has been found in the list.
            // (Note:  Since the list is ordered, if we find an item
            // that is greater than searchItem, we could immediately
            // return false.)
            if ( runner.item == searchItem )
                return true;
            runner = runner.next;  // Move on to the next node.
        }

        // At this point, we have looked at all the items in the list
        // without finding searchItem.  Return false to indicate that
        // the item does not exist in the list.

        return false;

    } // end find()


    /**
     * Delete a specified item from the list, if that item is present.
     * If multiple copies of the item are present in the list, only
     * the one that comes first in the list one is deleted.
     * @param deleteItem the item to be deleted
     */
    public void delete(int deleteItem) {

        if ( head == null ) {
            // The list is empty, so it certainly doesn't contain deleteItem.
            // Subroutine under this condition is empty.
            System.out.println("The list is empty, nothing to delete");
        }
        else if ( head.item == deleteItem ) {
            // The string is the first item of the list.  Remove it.
            // Element where pointer in current head becomes the new head.
            head = head.next;
        }
        else {
            // The number, if it occurs at all, is somewhere beyond the
            // first element of the list.  Search the list.
            Node runner;     // A node for traversing the list.
            Node previous;   // Always points to the node preceding runner.
            runner = head.next;   // Start by looking at the SECOND list node.
            previous = head;
            while ( runner != null && (runner.item - (deleteItem)) < 0 ) {
                // Move previous and runner along the list until runner
                // falls off the end or hits a list element that is
                // greater than or equal to deleteItem.  When this
                // loop ends, runner indicates the position where
                // deleteItem must be, if it is in the list.
                previous = runner;
                runner = runner.next;
            }
            if ( runner != null && runner.item == deleteItem ) {
                // Runner points to the node that is to be deleted.
                // Remove it by changing the pointer in the previous node.
                previous.next = runner.next;
            }
            else {
                // The item does not exist in the list.
                System.out.println("Entered number is not present in the list");
            }
        }

    } // end delete()


    /**
     * Insert a specified number to the list, keeping the list in order.
     * @param insertItem the item that is to be inserted.
     */
    public void insert(int insertItem) {

        Node newNode = new Node();  // A Node to contain the new item.
        newNode.item = insertItem;  // (N.B.  newNode.next is null.)

        if ( head == null ) {
            // The new item is the first (and only) one in the list.
            // Set head to point to it.
            head = newNode;
        }
        else {/*if ( (head.item - insertItem) >= 0 ) {
            // The new item is less than the first item in the list,
            // so it has to be inserted at the head of the list.*/
            newNode.next = head;
            head = newNode;
        }
        /*else {
            // The new item belongs somewhere after the first item
            // in the list.  Search for its proper position and insert it.
            Node runner;     // A node for traversing the list.
            Node previous;   // Always points to the node preceding runner.
            runner = head.next;   // Start by looking at the SECOND position.
            previous = head;
            while ( runner != null && ((runner.item - (insertItem)) < 0) ) {
                // Move previous and runner along the list until runner
                // falls off the end or hits a list element that is
                // greater than or equal to insertItem.  When this
                // loop ends, runner indicates the position where
                // insertItem must be inserted.
                previous = runner;
                runner = runner.next;
            }
            newNode.next = runner;     // Insert newNode after previous.
            previous.next = newNode;
        }*/

    }  // end insert()


    /**
     * Returns an array that contains all the elements in the list.
     * If the list is empty, the return value is an array of length zero.
     */
    public int[] getElements() {

        int count;          // For counting elements in the list.
        Node runner;        // For traversing the list.
        int[] elements;  // An array to hold the list elements.

        // First, go through the list and count the number
        // of elements that it contains.

        count = 0;
        runner = head;
        while (runner != null) {
            count++;
            runner = runner.next;
        }

        // Create an array just large enough to hold all the
        // list elements.  Go through the list again and
        // fill the array with elements from the list.

        elements = new int[count];
        runner = head;
        count = 0;
        while (runner != null) {
            elements[count] = runner.item;
            count++;
            runner = runner.next;
        }

        // Return the array that has been filled with the list elements.

        return elements;

    } // end getElements()

    /**
     * Add the value of current object's variable to the sum of variables
     * @return total sum
     */
    public int addToSum() {
        int currentSum = 0;
        Node runner;    // A pointer for traversing the list.
        runner = head;  // Start by looking at the head of the list.
        while (runner != null) {
            // Go through the list looking at the number in each
            // node.  Add
            currentSum = currentSum + head.item;
            runner = runner.next;  // Move on to the next node.
        }

        // At this point, we have looked at all the items in the list
        // without finding searchItem.  Return false to indicate that
        // the item does not exist in the list.

        return currentSum;
    }

} // end class StringList