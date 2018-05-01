package package2; //class is a part of the Festival package.
//------------------------------------------------------------------------
//Assignment #2
//Written by: Giselle Martel, ID#2652936
//COMP 249 Section PP, - Winter 2018
//Due Date: February 21st 2018
//------------------------------------------------------------------------
/**
 * class Culturalfiesta is a subclass of Festival, which allows the creation of
 * Culturalfiesta objects, including 6 inherited attributes and 1 new attribute.
 * The new attribute is made private for Part 2, allowing only the Musicfiesta
 * class to access it directly.
 */

public class Culturalfiesta extends Festival {
    /*create private (restricted access for Part 2, initially default
     package access was used in Part 1) attribute for Culturalfiesta class */
    private int numSpokenLang;

    /**
     * Default constructor of Culturalfiesta overrides one from Festival class, called when object of Culturalfiesta is created. Sets attributes of object to default values.
     */
    public Culturalfiesta() {
        super(); //call superclass default constructor
        numSpokenLang = 1;
    }

    /**
     * Parametrized Constructor for Culturalfiesta class overrides superclass parametrized constructor and takes 7 parameters.
     *
     * @param year          sets attribute year when constructor is called at creation of class object.
     * @param month         sets attribute month when constructor is called at creation of class object.
     * @param numCities     sets attribute numCities when constructor is called at creation of class object.
     * @param name          sets attribute name when constructor is called at creation of class object.
     * @param ticketPrice   sets attribute ticketPrice when constructor is called at creation of class object.
     * @param duration      sets attribute duration when constructor is called at creation of class object.
     * @param numSpokenLang sets attribute numSpokenLang when constructor is called at creation of class object.
     */
    public Culturalfiesta(int year, int month, int numCities, String name, double ticketPrice, int duration, int numSpokenLang) {
        super(year, month, numCities, name, ticketPrice, duration);
        this.numSpokenLang = numSpokenLang;
    }

    /**
     * Copy Constructor for Culturalfiesta Class overrides superclass copy constructor and creates copy of Culturalfiesta object
     *
     * @param cf allows the constructor to make a copy of a culturalfiesta object
     */
    public Culturalfiesta(Culturalfiesta cf) {
        super(cf);
        this.numSpokenLang = cf.numSpokenLang;
    }

    /**
     * accessor method for attribute numSpokenLang
     *
     * @return numSpokenLang returns the numSpokenLang
     */
    public int getNumSpokenLang() {
        return numSpokenLang;
    }

    /**
     * mutator method for attribute numSpokenLang
     *
     * @param numSpokenLang sets Culturalfiesta class attribute numSpokenLang to its value
     */
    public void setNumSpokenLang(int numSpokenLang) {

        this.numSpokenLang = numSpokenLang;
    }

    /**
     * toString method for class Culturalfiesta overrides Festival toString method
     */
    public String toString() {
        if (numSpokenLang != 1)
            return (super.toString() + " and will have " + numSpokenLang + " spoken languages.");
        else
            return (super.toString() + " and will have " + numSpokenLang + " spoken language.");
    }

    /**
     * equals method overrides method from Festival class and checks to
     * see if a passed object has the same attributes as an object of
     * Culturalfiesta class. If the passed object is null or not of the Culturalfiesta class,
     * then the method will return false. In Part 1, we can directly access the objects
     * of the parent class and can return using this.attribute. In part 2, due to restriction of
     * access rights, we must use the getter method of parent class in return statement using
     * syntax this.getAttribute();
     *
     * @return true when the passed object is of the Culturalfiesta class and has the same value for every attribute
     */
    public boolean equals(Object obj) {
        if(obj==null || obj.getClass() != this.getClass()) {
            System.out.println("The two objects you are trying to compare are not of the same type");
            return false;
        }
        else {
            Culturalfiesta c = (Culturalfiesta)obj;
            System.out.println("The two objects being compared are of the same type.");
            return (this.getYear() == c.getYear() && this.getMonth() == c.getMonth() && this.getNumCities() == c.getNumCities() && this.getName()== c.getName() && this.getTicketPrice() == c.getTicketPrice() && this.getDuration() == c.getDuration() && this.numSpokenLang == c.numSpokenLang);
        }
    }
}
