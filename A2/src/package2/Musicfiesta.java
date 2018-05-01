package package2; //class is a part of the Festival package.
//------------------------------------------------------------------------
//Assignment #2
//Written by: Giselle Martel, ID#2652936
//COMP 249 Section PP, - Winter 2018
//Due Date: February 21st 2018
//------------------------------------------------------------------------


/**
 * class Musicfiesta is a subclass of Festival, which allows the creation of
 * Musicfiesta objects, including 6 inherited attributes and 1 new attribute.
 * The new attribute is made private for Part 2, allowing only the Musicfiesta
 * class to access it directly.
 */

public class Musicfiesta extends Festival {

    /*create private (restricted access for Part 2, initially default
    package access was used in Part 1) attribute for Musicfiesta class */
    private int numBands;

    /**
     * Default constructor of Musicfiesta overrides one from Festival class, called when object of Musicfiesta is created. Sets attributes of object to default values.
     */
    public Musicfiesta() {
        super();
        numBands = 0;
    }

    /**
     * Parametrized Constructor for Musicfiesta class overrides superclass parametrized constructor and takes 7 parameters.
     *
     * @param year        sets attribute year when constructor is called at creation of class object.
     * @param month       sets attribute month when constructor is called at creation of class object.
     * @param numCities   sets attribute numCities when constructor is called at creation of class object.
     * @param name        sets attribute name when constructor is called at creation of class object.
     * @param ticketPrice sets attribute ticketPrice when constructor is called at creation of class object.
     * @param duration    sets attribute duration when constructor is called at creation of class object.
     * @param numBands    sets attribute numBands when constructor is called at creation of class object.
     */
    public Musicfiesta(int year, int month, int numCities, String name, double ticketPrice, int duration, int numBands) {
        super(year, month, numCities, name, ticketPrice, duration);
        this.numBands = numBands;
    }

    /**
     * Copy Constructor for Musicfiesta Class overrides superclass copy constructor and creates copy of Musicfiesta object
     *
     * @param m allows the constructor to make a copy of a Musicfiesta object
     */
    public Musicfiesta(Musicfiesta m) {
        super(m);
        this.numBands = m.numBands;
    }

    /**
     * accessor method for attribute numBands
     *
     * @return numBands returns the numBands
     */
    public int getNumBands() {
        return numBands;
    }

    /**
     * mutator method for attribute numBands
     *
     * @param numBands sets Musicfiesta class attribute numBands to its value
     */
    public void setNumBands(int numBands) {
        this.numBands = numBands;
    }

    /**
     * toString method for class Musicfiesta overrides Festival toString method
     */
    public String toString() {
        if (numBands != 1)
            return (super.toString() + " and " + numBands + " bands will be performing.");
        else
            return (super.toString() + " and " + numBands + " band will be performing.");

    }

    /**
     * equals method overrides method from Festival class and checks to
     * see if a passed object has the same attributes as an object of
     * Musicfiesta class. If the passed object is null or not of the Musicfiesta class,
     * then the method will return false. In Part 1, we can directly access the objects
     * of the parent class and can return using this.attribute. In part 2, due to restriction of
     * access rights, we must use the getter method of parent class in return statement using
     * syntax this.getAttribute();
     * @return true when the passed object is of the Musicfiesta class and has the same value for every attribute
     */
    public boolean equals(Object obj) {
        if(obj==null || obj.getClass() != this.getClass()) {
            System.out.println("The two objects you are trying to compare are not of the same type");
            return false;
        }
        else {
            Musicfiesta m = (Musicfiesta)obj;
            System.out.println("The two objects being compared are of the same type.");
            return (this.getYear() == m.getYear() && this.getMonth() == m.getMonth() && this.getNumCities() == m.getNumCities() && this.getName() == m.getName() && this.getTicketPrice() == m.getTicketPrice() && this.getDuration() == m.getDuration() && this.numBands == m.numBands);
        }
    }
}