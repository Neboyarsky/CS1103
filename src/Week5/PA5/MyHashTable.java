package Week5.PA5;

public class MyHashTable {

    private static class Node {
        String key;
        String value;
        Node next;
    }

    private Node[] hTable;
    private int counter; //the number of elements in the table


    public MyHashTable(int initSize) {
        hTable = new Node[initSize]; // array to store elements of table
    }

    private int hash(Object key) {
        return (Math.abs(key.hashCode())) % hTable.length;
    }

    public int size() {
        return counter;
    }

    public void put(String key, String value) {

        int locat = hash(key);

        Node list = hTable[locat];
        while (list != null) {
            if (list.key.equals(key))
                break;
            list = list.next;
        }

        if (list != null) {
            list.value = value;
        }
        else {
            Node newNode = new Node();
            newNode.key = key;
            newNode.value = value;
            newNode.next = hTable[locat];
            hTable[locat] = newNode;
            counter++;  // Count the added key.
        }
    }

    public String get(String key) {

        int locat = hash(key);

        Node list = hTable[locat];
        while (list != null) {
            if (list.key.equals(key))
                return list.value;
            list = list.next;
        }

        return null;
    }

    public void remove(String key) {

        int locat = hash(key);

        if (hTable[locat] == null) {
            return;
        }

        if (hTable[locat].key.equals(key)) {
            hTable[locat] = hTable[locat].next;
            counter--;
            return;
        }

        Node prev = hTable[locat];
        Node curr = prev.next;
        while (curr != null && ! curr.key.equals(key)) {
            curr = curr.next;
            prev = curr;
        }

        if (curr != null) {
            prev.next = curr.next;
            counter--;  // Record new number of items in the table.
        }
    }

    public boolean containsKey(String key) {

        int locat = hash(key);  // In what location should key be?

        Node list = hTable[locat];  // For traversing the list.
        while (list != null) {
            // If we find the key in this node, return true.
            if (list.key.equals(key))
                return true;
            list = list.next;
        }
        return false;
    }


}