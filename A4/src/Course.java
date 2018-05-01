//------------------------------------------------------------------------
//Assignment #4
//Written by: Giselle Martel, ID# 26352936
//COMP 249 Section PP, - Winter 2018
//Due Date: April 13, 2018
//------------------------------------------------------------------------

import java.util.Scanner;

/**
 * <p>COMP 249 section PP Assignment # 4</p><p> Due Date: April 13, 2018</p>
 *
 * <p>The Course class contains 5 private attributes, one for the course ID, course Name, credit, prerequesite ID, and
 * co-requesite ID. This class implements two interfaces - DirectlyRelatable which contains a method heading which
 * will be used to see if a course if related to another (if one course is a prereq or coreq of another and vice-versa),
 * and Cloneable which will be used to implement a clone method calling the clone method from the Object class.
 * </p>
 *
 * @author Giselle Martel 26352936
 */
public class Course implements DirectlyRelatable, Cloneable {

    private static Scanner userInput = new Scanner(System.in);

    //class attributes
    private String courseId;
    private String courseName;
    private double credit;
    private String preReqId;
    private String coReqId;

    /**
     * Default constructor of the Course class which will set all parameters to initial values.
     */
    public Course() {
        courseId = "";
        courseName = "";
        credit = 0;
        preReqId = "";
        coReqId = "";
    }

    /**
     * Parametrized constructor of the Course class which will set all class attributes to values of passed parameters
     *
     * @param courseId   is the id of the course and will be used to set the courseId attribute.
     * @param courseName is the name of the course and will be used to set the courseName attribute.
     * @param credit     is the number of credits the class is worth and will be used to set the credit attribute.
     * @param preReqId   is the id of the prerequisite and will be used to set the preReqId attribute.
     * @param coReqId    is the id of the co-requisite and will be used to set the coReqId attribute.
     */
    public Course(String courseId, String courseName, double credit, String preReqId, String coReqId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credit = credit;
        this.preReqId = preReqId;
        this.coReqId = coReqId;
    }

    /**
     * Copy constructor for the Course class which will take a Course object set all its attributes (except the courseId
     * to the values of the attributes of this.
     *
     * @param c        is an object of the Course class which will store the copy of this
     * @param courseId is the courseId which will be used to set the courseId parameter of the copied object.
     */
    public Course(Course c, String courseId) {
        this.courseId = courseId;
        this.courseName = c.courseName;
        this.credit = c.credit;
        this.preReqId = c.preReqId;
        this.coReqId = c.coReqId;
    }

    /**
     * This clone method calls the super.clone() method of the object class using the Cloneable interface. The cloned
     * object will then be returned. If the clone fails, a CloneNotSupportedException will be thrown and the method will
     * return null.
     *
     * @return Course object which is a copy of a Course object, with courseId set by the user
     */
    public Course clone() {
        System.out.println("Please indicate a course ID");
        String newId = userInput.next();
        try {
            Course clone = (Course) super.clone(); //call clone method to clone object
            clone.courseId = newId; //set courseId of copied object to the new ID chose by user.
            return clone;

        } catch (CloneNotSupportedException e) {
            System.out.println("Failed to clone Course object");
            return null;
        }
    }


    /**
     * Accessor method for attribute courseId
     *
     * @return the attribute courseId of type String
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * Mutator method for attribute courseId
     *
     * @param courseId which is a String used to set the value of the attribute courseId this class.
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }


    /**
     * Accessor method for attribute courseName
     *
     * @return the attribute courseName of type String
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Mutator method for attribute courseName
     *
     * @param courseName which is a String used to set the value of the attribute courseName this class.
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    /**
     * Accessor method for attribute credit
     *
     * @return the attribute credit of type double
     */
    public double getCredit() {
        return credit;
    }

    /**
     * Mutator method for attribute credit
     *
     * @param credit which is a double used to set the value of the attribute credit this class.
     */
    public void setCredit(double credit) {
        this.credit = credit;
    }

    /**
     * Accessor method for attribute preReqId
     *
     * @return the attribute preReqId of type String
     */
    public String getPreReqId() {
        return preReqId;
    }

    /**
     * Mutator method for attribute preReqId
     *
     * @param preReqId which is a String used to set the value of the attribute preReqId this class.
     */
    public void setPreReqId(String preReqId) {
        this.preReqId = preReqId;
    }

    /**
     * Accessor method for attribute coReqId
     *
     * @return the attribute coReqId of type String
     */
    public String getCoReqId() {
        return coReqId;
    }

    /**
     * Mutator method for attribute coReqId
     *
     * @param coReqId which is a String used to set the value of the attribute coReqId this class.
     */
    public void setCoReqId(String coReqId) {
        this.coReqId = coReqId;
    }

    /**
     * equals method for the Course class, which will check to see if the calling Course object is identical to the
     * passed object
     *
     * @param o is an object of any class, which will be check to see if it is of the same class and contains the same
     *          values for each attribute.
     * @return false if the two objects are not of the same class or contain different attribute values.
     */
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        } else {
            Course c = (Course) o;
            return (this.courseName.equals(c.courseName) && this.credit == c.credit && this.preReqId.equals(c.preReqId)
                    && this.coReqId.equals(c.coReqId));
        }
    }

    /**
     * toString method for the Course class
     *
     * @return all the attributes and their values to be printed to the screen
     */
    public String toString() {
        return ("Course ID: " + courseId + "\nCourse name: " + courseName + "\nCredit: " + credit + "\nPrerequisite ID: "
                + preReqId + "\nCorequisite ID: " + coReqId);
    }


    /**
     * isDirectlyRelatable is a method of the interface DirectlyRelatable. It will verify whether two course objects
     * are related to each other. If one Course is a a prerequisite or co-requisite of another course, and vice versa,
     * then they are related.
     *
     * @param c is an object of the Course class which will be compared to the calling object of the Course class
     * @return false if the two objects are not related.
     */
    public boolean isDirectlyRelated(Course c) {
        return (c.courseId.equals(this.coReqId) || c.courseId.equals(this.preReqId) || this.courseId.equals(c.coReqId)
                || this.courseId.equals(c.preReqId));
    }

}
