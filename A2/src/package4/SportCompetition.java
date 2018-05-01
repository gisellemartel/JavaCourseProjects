package package4; //class is a part of the SportCompetition package.

import package1.*;
//------------------------------------------------------------------------
//Assignment #2
//Written by: Giselle Martel, ID#2652936
//COMP 249 Section PP, - Winter 2018
//Due Date: February 21st 2018
//------------------------------------------------------------------------
/**
 * Class SportCompetition is a subclass of Event, which allows the creation of SportCompetition objects,
 * including 3 inherited attributes and 2 new attributes. The 2 new attributes are made
 * private for Part 2, allowing only the SportCompetition class to access them directly.
 */

public class SportCompetition extends Event {
    //declare enum type
    public enum Season{Winter,Spring,Summer,Fall}

    /*create private (restricted access for Part 2, initially default
     package access was used in Part 1) attributes for SportCompetition class */
    private int numActivities;
    private Season season;

    /**
     * Default constructor of SportCompetition overrides one from Event class, called when object of SportCompetition is created. Sets attributes of object to default values.
     */
    public SportCompetition() {
        super(); //call superclass default constructor
        numActivities = 0;
        season = null;
    }

    /**
     * Parametrized Constructor for SportCompetition class overrides superclass parametrized constructor and takes 7 parameters.
     *
     * @param year          sets attribute year when constructor is called at creation of class object.
     * @param month         sets attribute month when constructor is called at creation of class object.
     * @param numCities     sets attribute numCities when constructor is called at creation of class object.
     * @param numActivities sets attribute numActivities when constructor is called at creation of class object.
     * @param season        sets attribute season when constructor is called at creation of class object.
     */
    public SportCompetition(int year, int month, int numCities, int numActivities, Season season) {
        super(year, month, numCities);
        this.numActivities = numActivities;
        this.season = season;
    }

    /**
     * Copy Constructor for SportCompetition Class overrides superclass copy constructor and creates copy of SportCompetition object
     *
     * @param s allows the constructor to make a copy of a SportCompetition object
     */
    public SportCompetition(SportCompetition s) {
        super(s);
        this.numActivities = s.numActivities;
        this.season = s.season;
    }

    /**
     * accessor method for attribute numActivities
     *
     * @return numActivities returns the number of activities in the Sport Competition.
     */
    public int getNumActivities() {
        return numActivities;
    }

    /**
     * accessor method for attribute season
     *
     * @return season returns the season when the Sport Competition occurs.
     */
    public Season getSeason() {
        return season;
    }

    /**
     * mutator method for attribute numActivities
     *
     * @param numActivities sets SportCompetition class attribute numActivities to its value
     */
    public void setNumActivities(int numActivities) {
        this.numActivities = numActivities;
    }

    /**
     * mutator method for attribute season
     *
     * @param season sets SportCompetition class attribute season to its value
     */
    public void setSeason(Season season) {
        this.season = season;
    }

    /**
     * toString method for class SportCompetition overrides Event toString method
     */
    public String toString() {
        if (numActivities != 1)
            return (super.toString() + ", will have " + numActivities + " activities, and will take place in " + season + ".");
        else
            return (super.toString() + ", will have " + numActivities + " activity, and will take place in " + season + ".");


    }

    /**
     * equals method overrides method from Event class and checks to
     * see if a passed object has the same attributes as an object of
     * SportCompetition class. If the passed object is null or not of the SportCompetition class,
     * then the method will return false. In Part 1, we can directly access the objects
     * of the parent class and can return using this.attribute. In part 2, due to restriction of
     * access rights, we must use the getter method of parent class in return statement using
     * syntax this.getAttribute();
     *
     * @return true when the passed object is of the SportCompetition class and has the same value for every attribute
     */
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            System.out.println("The two objects you are trying to compare are not of the same type");
            return false;
        }
        else {
            SportCompetition s = (SportCompetition) obj;
            System.out.println("The two objects being compared are of the same type.");
            return (this.getYear() == s.getYear() && this.getMonth() == s.getMonth() && this.getNumCities() == s.getNumCities() && this.numActivities == s.numActivities && this.season == s.season);
        }
    }
}
