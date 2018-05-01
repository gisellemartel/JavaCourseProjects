package package2; //class is a part of the Festival package.

import package1.*;

//------------------------------------------------------------------------
//Assignment #2
//Written by: Giselle Martel, ID#2652936
//COMP 249 Section PP, - Winter 2018
//Due Date: February 21st 2018
//------------------------------------------------------------------------

/**
 * class Festival is a subclass of Event, which adds allows the creation of Festival objects,
 * including 3 new attributes and 3 inherited attributes. The 3 new attributes are made private
 * for Part 2 (most restrictive access), meaning that the child classes Musicfiesta and Culturalfiesta
 * can only access the attributes by calling methods of the Festival class. The trade-off is these
 * child classes cannot access these attributes directly, but having such a restriction
 * is more secure than protected attributes since we cannot modify the object attributes
 * from the parent class without using the methods of the parent class. Removing protected access
 * from Part 1 also removes access rights to these attributes from classes in the same package.
 * This new restriction does not change the functionality, since the methods of the parent class
 * Event are sufficient within the context of this program. It may pose an inconvenience in
 * larger, more complex programs.
 */
public class Festival extends Event {
    /*create additional private attributes for subclass Festival (restricted access for Part 2).
     * Initially, in part 1 attributes were protected to allow child classes Culturalfiesta
     * and Musicfiesta direct access these attributes without the use of methods from Festival class.
     */
    private String name;
    private double ticketPrice;
    private int duration;

    /**
     * Default constructor of Festival overrides one from Event class, called when object of Festival is created. Sets attributes of object to default values.
     */
    public Festival() {
        super(); //call superclass default constructor.
        name = "";
        ticketPrice = 0.0;
        duration = 0;
    }

    /**
     * Parametrized Constructor for Festival Class overrides superclass parametrized constructor and takes 6 parameters.
     *
     * @param year        sets attribute year when constructor is called at creation of class object.
     * @param month       sets attribute month when constructor is called at creation of class object.
     * @param numCities   sets attribute numCities when constructor is called at creation of class object.
     * @param name        sets attribute name when constructor is called at creation of class object.
     * @param ticketPrice sets attribute ticketPrice when constructor is called at creation of class object.
     * @param duration    sets attribute duration when constructor is called at creation of class object.
     */
    public Festival(int year, int month, int numCities, String name, double ticketPrice, int duration) {
        //call superclass parametrized constructor and set attributes to initial values.
        super(year, month, numCities);
        this.name = name;
        this.ticketPrice = ticketPrice;
        this.duration = duration;
    }

    /**
     * Copy Constructor for Festival Class overrides superclass copy constructor and  creates copy of Festival object
     *
     * @param f allows the constructor to make a copy of a festival object
     */
    public Festival(Festival f) {
        super(f); //call superclass copy constructor.
        this.name = f.name;
        this.ticketPrice = f.ticketPrice;
        this.duration = f.duration;
    }

    /**
     * accessor method for attribute name
     *
     * @return name returns the name of the Festival.
     */
    public String getName() {
        return name;
    }

    /**
     * accessor method for attribute ticketPrice
     *
     * @return ticketPrice returns the price of a ticket for the Festival.
     */
    public double getTicketPrice() {
        return ticketPrice;
    }

    /**
     * accessor method for attribute duration
     *
     * @return duration returns the duration of the Festival.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * mutator method for attribute name
     *
     * @param name sets Festival class attribute name to its value
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * mutator method for attribute ticketPrice
     *
     * @param ticketPrice sets Festival class attribute ticketPrice to its value
     */
    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    /**
     * mutator method for attribute duration
     *
     * @param duration sets Festival class attribute duration to its value
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * toString method for class Festival overrides Event toString method
     */
    public String toString() {
        return ("This " + name + super.toString().substring(4) + ", for " + duration + " days, the ticket will cost " + ticketPrice + "$");

    }

    /**
     * equals method overrides method from Event class and checks to see
     * if a passed object has the same attributes as an object of Festival
     * class. If the passed object is null or not of the Festival class,
     * then the method will return false. In Part 1, we can directly access the objects
     * of the parent class and can return using this.attribute. In part 2, due to restriction of
     * access rights, we must use the getter method of parent class in return statement using
     * syntax this.getAttribute();
     *
     * @return true when the passed object is of the Festival class and has the same value for every attribute
     */
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            System.out.println("The two objects you are trying to compare are not of the same type");
            return false;
        } else {
            Festival f = (Festival) obj;
            System.out.println("The two objects being compared are of the same type.");
            return (this.getYear() == f.getYear() && this.getMonth() == f.getMonth() && this.getNumCities() == f.getNumCities() && this.name == f.name && this.ticketPrice == f.ticketPrice && this.duration == f.duration);
        }
    }
}
