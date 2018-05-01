package package3; //class is a part of the SportCompetition package.

import package1.*;
//------------------------------------------------------------------------
//Assignment #2
//Written by: Giselle Martel, ID#2652936
//COMP 249 Section PP, - Winter 2018
//Due Date: February 21st 2018
//------------------------------------------------------------------------

/**
 * Class Fair is a subclass of Event, which allows the creation of Fair objects,
 * including 3 inherited attributes and 2 new attributes. The 2 new attributes are made
 * private for Part 2, allowing only the Fair class to access them directly.
 */
public class Fair extends Event {
    //declare enum Type
    public enum Type {Career, Science, Trade, Travel}
    /*create private (restricted access for Part 2, initially default
     package access was used in Part 1) attributes for Fair class */
    private int numExhibitors;
    private Type type;

    /**
     * Default constructor of Fair overrides one from Event class, called when object of Fair is created. Sets attributes of object to default values.
     */
    public Fair() {
        super(); //call superclass default constructor
        numExhibitors = 1;
        type = null;
    }

    /**
     * Parametrized Constructor for SportCompetition class overrides superclass parametrized constructor and takes 7 parameters.
     *
     * @param year          sets attribute year when constructor is called at creation of class object.
     * @param month         sets attribute month when constructor is called at creation of class object.
     * @param numCities     sets attribute numCities when constructor is called at creation of class object.
     * @param numExhibitors sets attribute numExhibitors when constructor is called at creation of class object.
     * @param type          sets attribute type when constructor is called at creation of class object.
     */
    public Fair(int year, int month, int numCities, int numExhibitors, Type type) {
        super(year, month, numCities);
        this.numExhibitors = numExhibitors;
        this.type = type;
    }

    /**
     * Copy Constructor for Fair Class overrides superclass copy constructor and creates copy of Fair object
     *
     * @param f allows the constructor to make a copy of a Fair object
     */
    public Fair(Fair f) {
        super(f);
        this.numExhibitors = f.numExhibitors;
        this.type = f.type;
    }

    /**
     * accessor method for attribute numExhibitors
     *
     * @return numExhibitors returns the number of exhibitors in the Fair.
     */
    public int getNumExhibitors() {
        return numExhibitors;
    }

    /**
     * accessor method for attribute type
     *
     * @return type returns the type of Fair.
     */
    public Type getType() {
        return type;
    }

    /**
     * mutator method for attribute numExhibitors
     *
     * @param numExhibitors sets Fair class attribute numExhibitors to its value
     */
    public void setNumExhibitors(int numExhibitors) {
        this.numExhibitors = numExhibitors;
    }

    /**
     * mutator method for attribute type
     *
     * @param type sets Fair class attribute type to its value
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * toString method for class Fair overrides Event toString method
     */
    public String toString() {
        if (numExhibitors != 1)
            return ("This " + type + super.toString().substring(4) + ", and will have " + numExhibitors + " exhibitors.");
        else
            return ("This " + type + super.toString().substring(4) + ", and will have " + numExhibitors + " exhibitor.");

    }

    /**
     * equals method overrides method from Event class and checks to
     * see if a passed object has the same attributes as an object of
     * Fair class. If the passed object is null or not of the Fair class,
     * then the method will return false. In Part 1, we can directly access the objects
     * of the parent class and can return using this.attribute. In part 2, due to restriction of
     * access rights, we must use the getter method of parent class in return statement using
     * syntax this.getAttribute();
     *
     * @return true when the passed object is of the Fair class and has the same value for every attribute
     */
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            System.out.println("The two objects you are trying to compare are not of the same type");
            return false;
        }
        else {
            Fair f = (Fair) obj;
            System.out.println("The two objects being compared are of the same type.");
            return (this.getYear() == f.getYear() && this.getMonth() == f.getMonth() && this.getNumCities() == f.getNumCities() && this.numExhibitors == f.numExhibitors && this.type == f.type);
        }
    }
}