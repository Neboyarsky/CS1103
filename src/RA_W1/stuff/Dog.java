package RA_W1.stuff;

public class Dog {
    //variable area
    private int size;
    private String name;
    private String breed;

    //methods area

    /**
     * Just defining a normal dog that has a size, a breed, and a name
     * @param size - dog weight in pounds, must be positive integer
     * @param name - dog name, must be a String
     * @param breed - dog breed, must be a String
     */
    Dog(int size, String name, String breed){
    }

    /**
     * This subroutine makes a dog bark n times; n must be a positive integer.
     * @param n - how many times the dog barks
     * @throws IllegalArgumentException if user makes the dog bark less than 0 times
     */
    void bark_n(int n){
        if (n < 0){
            throw new IllegalArgumentException("Dog cannot bark negative amount of times!.");
        }
        for (int i = 0; i < n; i++){
            System.out.print("Baw!");
        }
        System.out.println(" ");
    }

    //getter area
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    //setter area
    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
