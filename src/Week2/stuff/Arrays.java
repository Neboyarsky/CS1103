package Week2.stuff;

/**

 * Array size = 1000

 Time taken by Selection sort: 0.004 seconds
 Time taken by Arrays.sort(): 0.0 seconds

 */

/**

 * Array size = 10000

 Time taken by Selection sort: 0.0084 seconds
 Time taken by Arrays.sort(): 0.0 seconds

 */

/**

 * Array size = 100000

 Time taken by Selection sort: 0.05238 seconds
 Time taken by Arrays.sort(): 0.0 seconds

 */

import java.util.Random;


public class Arrays {

    public void fillArray(int arr1[],int arr2[],int size)

    {

// create object of Random class

        Random r=new Random();

        for(int i=0;i<size;i++)

            arr2[i]=arr1[i]=r.nextInt();

    }


    public int[] sortFirstArray(int a[],int size)

    {

        for(int i=0;i<size-1;i++)

        {

            int min=i;

            for(int j=i+1;j<size;j++)

                if(a[j]<a[min])

                    min=j;

            int t=a[min];

            a[min]=a[i];

            a[i]=t;

        }

        return a;

    }

    public static void main(String[] args) {

        final int size=100000; // size of arrays

// declaring two arrays

        int arr1[]=new int[size];

        int arr2[]=new int[size];


        Arrays ob=new Arrays();


        ob.fillArray(arr1,arr2,size);

// get starting time for calculating time

        long startTime = System.currentTimeMillis();

// sorting first array using selection sort

        ob.sortFirstArray(arr1,size);


        long runTime = System.currentTimeMillis() - startTime;

        long startTime2 = System.currentTimeMillis();


        Arrays.sort(arr2);


        long runTime2 = System.currentTimeMillis() - startTime2;

// Displays time taken by both arrays to sort after converting into seconds

        System.out.println("Time taken by Selection sort: "+runTime/100000.0+" seconds");

        System.out.println("Time taken by Arrays.sort(): "+runTime2/100000.0+" seconds");

    }

    private static void sort(int[] arr2) {

    }

}