package Week3.Exercises;

/**
 * This program includes a subroutine that makes a reversed copy of a
 * list of ints.  The main program simply tests that routine on a few lists.
 */
public class ReverseListDemo {


    /**
     * Objects of type ListNode are linked together into linked lists.
     */
    static class ListNode {
        int item;        // An item in the list.
        ListNode next;   // Pointer to the next node in the list.
    }


    /**
     * Return a new list containing the same items as the list,
     * but in the reverse order.
     */
    static ListNode reverse( ListNode list ) {
        ListNode rev = null;     // rev points to the first element of the new list.
        ListNode runner = list;  // For traversing through the nodes of the original list.
        while (runner != null) {
            // "Push" the next node of list onto the front of rev.
            ListNode newNode = new ListNode();  // create a new blank node;
            newNode.item = runner.item;         // copy item from original list.item to newNode.item
            newNode.next = rev;                 // make newNode point to what is currently pointer to
                                                // the first node of the reversed copy
            rev = newNode;                      // now reverse list points to the newly created node
            // Move on to the next node in the list.
            runner = runner.next;               // process next item in the original list
        }
        return rev;
    } // end reverse()


    /**
     * Prints the items in the list to which the parameter, start, points.
     * They are printed on one line, separated by spaces and enclosed in
     * parentheses.
     */
    static void printList(ListNode start) {
        ListNode runner;  // For running along the list.
        runner = start;
        System.out.print("(");
        while (runner != null) {
            System.out.print(" " + runner.item);
            runner = runner.next;
        }
        System.out.print(" )");
    } // end printList()


    public static void main(String[] args) {

        System.out.println("I will print out a list and its reverse, for");
        System.out.println("several different lists.  The first list is empty.\n");

        ListNode list = null;   // A list, initially empty.
        ListNode reversedList;  // The reversed list.

        int ct = 0;  // How many lists have we processed?

        while (true) {
            // Print the current list and its reverse.  Then
            // add a new node onto the head of the list before
            // repeating.
            System.out.print("The list:          ");
            printList(list);
            System.out.println();
            reversedList = reverse(list);
            System.out.print("The reversed list: ");
            printList(reversedList);
            System.out.println();
            System.out.println();
            ct++;
            if (ct == 16)
                break;
            ListNode head = new ListNode();  // A new node to add to the list.
            head.item = (int)(Math.random()*100);  // A random item.
            head.next = list;
            list = head;
        }

    } // end main()


} // end class ReverseListDemo