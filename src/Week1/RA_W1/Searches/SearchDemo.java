package Week1.RA_W1.Searches;

public class SearchDemo {
    public static void main(String[] args) {
        int[] arrayDemo = new int[10];
        for (int i = 0; i < arrayDemo.length; i++) {
            arrayDemo[i] = (int)(7 * Math.random());
            System.out.println((i + 1) + " " + arrayDemo[i] + ";");
        }

        System.out.print("Element found in position " + Searches.find(arrayDemo, 5));
    }
}
