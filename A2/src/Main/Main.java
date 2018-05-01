package Main;

import package1.*; //import 4 classes from external packages.
import package2.*;
import package3.*;
import package4.*;
//------------------------------------------------------------------------
//Assignment #2
//Written by: Giselle Martel, ID#2652936
//COMP 249 Section PP, - Winter 2018
//Due Date: February 21st 2018
//------------------------------------------------------------------------

/**
 * @author gisellemartel #26352936
 *
 * <h2>Part 1</h2>
 * <p>
 * This Main class includes the Driver method which will create objects of 6 different classes. The purpose of this is
 * to demonstrate the effectiveness of method overriding with inherited classes, including testing equals methods with
 * objects of different classes. The equals method of each class must be able to verify whether or not a passed object
 * is in fact of the same class as the object that called it. Each equals method must also verify if the passed object
 * is null, since in this case the program could crash if a null object is compared to another object. The equals method
 * however cannot prevent a null object from calling the method, so it is important to ensure this does not occur in the
 * driver method. We also have a search algorithm in the Main method which finds the event objects containing the least
 * and most amount of cities. The static method yearSearch is used to find all the pairs of Events that occur in the same year.
 * </p>
 *
 * <h2>Part 2</h2>
 * <p>
 * In part 2, we change the attributes of each class to private, to have restricted access rights. In order for the program
 * to function correctly, it was only necessary to change the syntax in the return statements of the equals method
 * of the child classes. By changing the syntax from this.attribute to this.getAttribute(); the program may now function in the same
 * way as it did previously in Part 1.
 * </p>
 * <p>
 * The Main class includes a Driver method which will create an array of 12 Event objects containing objects of both super and
 * subclasses. A static method will make a copy of this array, and another static method will be used to display the contents of the
 * original array and then the copied array. The method copyFestival which makes a copy of the array, is intended to use
 * the copy constuctor of the class of the object being copied. For example, if the object contained in the Event array
 * to be copied is of the SportCompetition class, then the appropriate copy constructor should be called, using polymorphism.
 * However, the program "misbehaves" and instead calls the copy constructor of the Event class for each object. The reason
 * this happens is because polymorphism is only possible when both inheritance and method overriding occur. Constructors
 * cannot be overridden by subclasses (because constructors are named after the class, and each class has a different name),
 * which is the reason why polymorphism does not occur in this case, even though we are copying objects of inherited classes.
 * A potential solution would be to create a clone() method in each class, which would call the appropriate copy constructor.
 * When the clone method would be called in the copyFestival method, polymorphism would occur since we now would have inheritance
 * and method overriding occurring at the same time.
 * </p>
 */
public class Main {

    /**
     * This static method takes an array of event objects as parameter then makes a copy
     * of it. Each time a new object is copied to the index of the new array, the copy constructor
     * from the correct class should be called (for example is the object is a fair, the fair
     * copy constructor should be called.
     *
     * @param a will be copied
     * @return copyArray
     */
    public static Event[] copyFestival(Event[] a) {
        Event[] copyArray = new Event[a.length];
        for (int i = 0; i < a.length; i++) {
            copyArray[i] = new Event(a[i]);
        }
        return copyArray;
    }

    /**
     * This static method takes an array of Event objects as a parameter and then displays each object
     *
     * @param a will have the contents of each of its indexes displayed.
     */
    public static void displayArray(Event[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i] + "\n");
        }
    }
    /**
     * This static method searches through an array of objects and identifies which objects occur
     * in the same year, as well as the location of their index within the array.
     *
     * @param a will be searched through using for-loops to located objects with same year attribute.
     */
    public static void search(Event[] a) {
        int[] yearArray = new int[a.length];
        //use for nested for loop to find all pairs of events with same year.
        for (int i = 0; i < a.length; i++) {
            for (int j = 1; j < a.length; j++) {
                if (i != j && a[i].getYear() == a[j].getYear()) {
                    //store each year that has more than one event in yearArray
                    yearArray[i] = a[i].getYear();
                }
            }
        }
        //use nested for-loop to find any duplicates within the year array, and set one to 0.
        for (int i = 0; i < yearArray.length; i++) {
            for (int j = i + 1; j < yearArray.length; j++) {
                if (yearArray[i] == yearArray[j]) {
                    yearArray[j] = 0;
                }
            }
        }
        //use nested for loop to search through entire array to see which indexes occur
        //in one of the years stored in yearArray, then display info and index.
        for (int i = 0; i < a.length; i++) {
            if (yearArray[i] != 0) {
                System.out.println("\nFollowing Events will occur in " + yearArray[i] + ":");
                for (int j = 0; j < a.length; j++) {
                    if (yearArray[i] == a[j].getYear()) {
                        System.out.println("Located at index " + j + ":\t\t" + a[j].toString().substring(5));
                    }
                }
            }
        }
    }


    public static void main(String[] args) {

        //* * * * * * * * * * * * * PART 1 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
        System.out.println("* * * * * * * * * *WELCOME* * * * * * * * * *\n\n");

        //create objects of each class
        Event e = new Event(2018, 5, 4);
        Festival f = new Festival(2018, 9, 8, "International Wine", 22.99, 4);
        Musicfiesta m = new Musicfiesta(2020, 7, 3, "Latin Food", 22.99, 4, 1);
        Culturalfiesta c = new Culturalfiesta(2020, 7, 3, "Latin Food", 22.99, 4, 1);
        SportCompetition s = new SportCompetition(2018, 3, 2, 10, SportCompetition.Season.Winter);
        SportCompetition s2 = new SportCompetition(2020, 6, 9, 23, SportCompetition.Season.Spring);
        Fair fr = new Fair(2019, 10, 1, 25, Fair.Type.Career);
        Fair fr2 = new Fair(2019, 6, 6, 23, Fair.Type.Science);
        Musicfiesta m2 = new Musicfiesta(2021, 1, 12, "Rock'n'Roll", 33.95, 12, 100);
        Festival f2 = new Festival(2017, 12, 2, "Pizza", 12.00, 3);

        //print objects of different classes
        System.out.println("* * * Testing toString on objects...");
        System.out.println(e);
        System.out.println(f);
        System.out.println(m);
        System.out.println(c);
        System.out.println(s);
        System.out.println(fr);

        //test equals methods
        System.out.println("\n\n* * *Testing equals methods of different classes...");
        System.out.println(m.equals(c));
        System.out.println(c.equals(m));
        System.out.println(s2.equals(fr2));
        System.out.println(fr2.equals(s2));
        System.out.println(e.equals(e));
        System.out.println(fr2.equals(fr));
        System.out.println(fr.equals(m));

        //create array of event objects.
        Event[] array = new Event[10];
        //store each object in array
        array[0] = e;
        array[1] = m;
        array[2] = c;
        array[3] = s;
        array[4] = s2;
        array[5] = fr;
        array[6] = fr2;
        array[7] = m2;
        array[8] = f2;
        array[9] = f;

        int minIndex = 0, maxIndex = 0;
        //search through array to find Event with least/most number of cities.
        System.out.println("\n\n* * *Searching for the Events happening in the least/most number of cities...");
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1].getNumCities() > array[maxIndex].getNumCities()) {
                maxIndex = (i - 1);
            }
        }
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1].getNumCities() < array[minIndex].getNumCities()) {
                minIndex = (i - 1);
            }
        }
        System.out.println("\n* Event with the least number of cities found at index: " + minIndex + ". \nPrinting Event Information:\n" + array[minIndex].toString().substring(5));
        System.out.println("\n* Event with the most number of cities found at index: " + maxIndex + ". \nPrinting Event Information:\n" + array[maxIndex].toString().substring(5));

        //search through array to find events occuring in the same year by calling yearSearch static method.
        System.out.println("\n\n* * *Searching for events that are happening in the same year...");
        search(array);


        //* * * * * * * * * * * * * PART 2 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
        //Create new array of 12 objects to be copied.
        Event[] array2 = new Event[12];
        array2[0] = new Event(2019, 4, 3);
        array2[1] = new Event(2020, 7, 2);
        array2[2] = new Festival(2020, 5, 4, "Hair", 20, 4);
        array2[3] = new Festival(2021, 10, 6, "Poutine", 12.50, 5);
        array2[4] = new Culturalfiesta(2020, 2, 4, "Skiing", 100, 6, 4);
        array2[5] = new Musicfiesta(2019, 6, 3, "metal", 75.99, 3, 54);
        array2[6] = new SportCompetition(2018, 4, 22, 3, SportCompetition.Season.Spring);
        array2[7] = new Fair(2023, 5, 13, 4, Fair.Type.Trade);
        array2[8] = new Event(2017, 6, 2);
        array2[9] = new Event(2020, 7, 4);
        array2[10] = new Fair(2021, 5, 23, 12, Fair.Type.Travel);
        array2[11] = new Festival(2019, 5, 1, "Jazz", 68, 14);

        //Display both array of Event objects and copied array
        System.out.println("\n\n* * *Displaying contents of array of objects...\n");
        displayArray(array2);
        //Program will display each copied object as an Event, since only the Event copy constructor is called (polymorphism doesn't occur)
        System.out.println("\n* * *Displaying contents of copied array...\n");
        displayArray(copyFestival(array2));

        //Display goodbye message and Terminate program
        System.out.println("\n\n* * * * * * * * * THANKS FOR USING THE PROGRAM. GOODBYE!! * * * * * * * * * * ");
        System.exit(0);

    }
}


