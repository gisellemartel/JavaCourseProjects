package package1;
//------------------------------------------------------------------------
//Assignment #2
//Written by: Giselle Martel, ID#2652936
//COMP 249 Section PP, - Winter 2018
//Due Date: February 21st 2018
//------------------------------------------------------------------------

/**
 * Create Event class whose attributes will be inherited by all the other classes.
 * The attributes are  made private for Part 2 (most restrictive access), meaning that the child classes
 * and classes in the same package can only access the attributes by calling methods of the Event class.
 * The trade-off is child classes and classes in same package cannot access these attributes
 * directly, but having such a restriction is more secure since we cannot modify the object attributes
 * from the parent class without using the methods of the parent class.
 * This new restriction does not change the functionality, since the methods of the parent class
 * Event are sufficient within the context of this program. It may pose an inconvenience in
 * larger, more complex programs.
 */
public class Event {

    /*create private (restricted access for Part 2, initially default
     package access was used in Part 1) attributes for Event class */
    private int year;
    private int month;
    private int numCities;

    /**
     * Default constructor for superclass
     */
    public Event() {
        year = 2000;
        month = 1;
        numCities = 1;
    }

    /**
     * Parametrized Constructor for Festival Class which takes 3 parameters
     *
     * @param year      sets attribute year when constructor is called at creation of class object.
     * @param month     sets attribute month when constructor is called at creation of class object.
     * @param numCities sets attribute numCities when constructor is called at creation of class object.
     */
    public Event(int year, int month, int numCities) {
        this.year = year;
        this.month = month;
        this.numCities = numCities;
    }

    /**
     * Copy Constructor for Event Class creates copy of Event object
     *
     * @param e allows the constructor to make a copy of an Event object
     */
    public Event(Event e) {
        this.year = e.year;
        this.month = e.month;
        this.numCities = e.numCities;
    }

    /**
     * accessor method for attribute year
     *
     * @return year returns the year the Event takes place.
     */
    public int getYear() {
        return year;
    }

    /**
     * mutator method for attribute year
     *
     * @param year sets Event class attribute year to its value
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * accessor method for attribute month
     *
     * @return month returns the month the Event takes place.
     */
    public int getMonth() {
        return month;
    }

    /**
     * mutator method for attribute month
     *
     * @param month sets Event class attribute month to its value
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * accessor method for attribute numCities
     *
     * @return numCities returns the number of cities participating in the event.
     */
    public int getNumCities() {
        return numCities;
    }

    /**
     * mutator method for attribute numCities
     *
     * @param numCities sets Event class attribute numCities to its value
     */
    public void setNumCities(int numCities) {
        this.numCities = numCities;
    }

    /**
     * toString method for superclass Event
     */
    public String toString() {
        String monthName = "January";
        String className = getClass().getSimpleName();

        //use switch allocated name of month to String monthName based on the corresponding int value of the month.
        switch (month) {
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;
            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "December";
                break;
        }
        if (numCities != 1)
            return "This " + className + " will be held in " + monthName + ", " + year + " in " + numCities + " cities";
        else
            return "This " + className + " will be held in " + monthName + ", " + year + " in " + numCities + " city";
    }

    /**
     * equals method checks to see if a passed object has the same attributes as an object of Event class.
     * If the passed object is null or the object if from the Event class, then the method will return false.
     * However, in the case the method is called from the Driver with a null object, then the program would crash
     * since we cannot verify this within the equals method, and it hence the limitation of this method.
     *
     * @return true when the passed object is of the Event class and has the same value for year, month, and numCities
     */
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            System.out.println("The two objects you are trying to compare are not of the same type");
            return false;
        } else {
            Event e = (Event) obj;
            System.out.println("The two objects being compared are of the same type.");
            return (e.year == this.year && e.month == this.month && e.numCities == this.numCities);
        }
    }
}
