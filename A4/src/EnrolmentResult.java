//------------------------------------------------------------------------
//Assignment #4
//Written by: Giselle Martel, ID# 26352936
//COMP 249 Section PP, - Winter 2018
//Due Date: April 13, 2018
//------------------------------------------------------------------------

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

/**
 * <p>COMP 249 section PP Assignment # 4</p><p> Due Date: April 13, 2018</p>
 *
 * <p>
 * EnrolmentResult contains the main method and several static methods, used to create objects of the Course class and store
 * them in a linked list of the CourseList class. The purpose of the class is to implement a program which will read a
 * file containing a list of courses and the required prerequisites and co-requisites. These courses will be parsed into
 * Course objects containing attributes for the pertinent information, and each of these objects will be stored into a
 * node of the CourseList class. The CourseList class implements this linked list using an inner class CourseNode.
 * </p>
 * <p>
 * After the information from the file is successfully parsed into Course object and stored in the CourseList nodes, the
 * program will then prompt the user to enter the file they wish to open. This file contains the courses which they have
 * completed and the the courses they wish to register for. The completed courses and the courses requested will each be
 * stored into their own ArrayList. The program will then verify if the registration of the courses may take place, based
 * on the information stored in the CourseList and the courses which have been completed. This is done by iterating
 * through the ArrayList of requested courses, then checking to see if the course exists in one of the nodes of the
 * CourseList, and then finally checking to see if the courses stored in the completed courses ArrayList match the
 * prerequisites and co-requisites stored in the node of the CourseList.
 * completed.
 * </p>
 * <p>
 * The program will then prompt the user to enter a course ID. A query will be made to see if there is a Course object
 * inside one of the CourseList nodes which has the same course ID. The result will be displayed to the user including
 * the course information if match is found. The final part of the program is to test the various methods of the
 * CourseList and Course class and then display the results. The program
 *
 * will allow the user to indicate which methods
 * they wish to test.
 * </p>
 *
 * @author Giselle Martel 26352936
 */
public class EnrolmentResult {

    public static void main(String[] args) {
        System.out.println("===============================================\nWelcome to Giselle's Course Registration System"
                + "\n===============================================\n");


        CourseList l1 = new CourseList(); //instantiate 2 CourseList objects
        CourseList l2 = new CourseList();

        Scanner sc = null;
        //read Syllabus.txt file to populate the CourseList with course info.
        try {
            sc = new Scanner(new FileInputStream("Syllabus.txt"));
        } catch (FileNotFoundException e) {

            System.out.println("File Syllabus.txt not found! Program will terminate");
            System.exit(0);
        }

        readSyllabus(sc, l1); //read contents of Syllabus.txt file, then parse information into CourseList
        l1.displayCourseList();

        boolean verifyCourses = true; //boolean for condition of do-while loop to allow user to open files several times
        Scanner userInput = new Scanner(System.in); //Instantiate Scanner object for user keyboard input.
        do {
            sc = openRequestFiles(sc); //call method to prompt user to enter which file they wish to open

            ArrayList<String> completed = new ArrayList<>(); //create new ArrayList to store requested courses
            ArrayList<String> requested = new ArrayList<>(); //create new ArrayList to store completed courses

            readRequestFiles(sc, requested, completed); //read the request files.

            if (requested.size() != 0) {
                //if a course is requested to enrol in, verify if requirements have been made.
                System.out.println("\n================================================================" +
                        "===================================================\n"
                        + "Verifying if prerequisites and corequistes have been met:\n");
                for (String requestedCourse : requested) {
                    verifyCourseRequirements(requestedCourse, requested, completed, l1); //verify if the course requirements have been met.
                }
            }else{
                System.out.println("\nNo courses selected to enrol in! Please try again with a different file.\n");
            }

            //prompt user if they would like to open a new file after verification is completed
            System.out.println("If you would like to open another file enter 'y', otherwise enter any other key");
            if (!userInput.next().equalsIgnoreCase("y"))
                verifyCourses = false;
        } while (verifyCourses);

        searchCourseList(l1); //call method which allow user to search for courses
        testingMethods(l1, l2); //test methods
        System.out.println("\nThanks for using the Enrolment System. Good Bye!!"); //display goodbye message
        sc.close(); //close Scanner object
        userInput.close(); //close user input Scanner object
        System.exit(0); //terminate program.

    }

    private static void readSyllabus(Scanner sc, CourseList l1) {
        while (sc.hasNextLine()) {
            Course course = new Course();
            String read = sc.next();
            if (read != "") {
                course.setCourseId(read);
                course.setCourseName(sc.next().replaceAll("\\_+", " "));
                course.setCredit(sc.nextDouble());
                sc.nextLine();
                sc.next();
                String prereq = sc.nextLine().replaceAll("\\s+", "");
                course.setPreReqId(prereq);
                sc.next();
                String coreq = sc.nextLine().replaceAll("\\s+", "");
                course.setCoReqId(coreq);
            }
            if (!l1.contains(read)) {
                l1.addToStart(course);
            }
        }
    }

    /**
     * searchCourseList is a method which will prompt the user to enter a course ID, and then perform a search by
     * iterating through the CourseList nodes to see if a matching Course object is found. If found, the course
     * information will be displayed. The number of times the user performs a search will be displayed at the end of
     * the method.
     *
     * @param l1 is an object of the CourseList class which contains nodes referencing Course objects.
     */
    private static void searchCourseList(CourseList l1) {
        Scanner keyIn = new Scanner(System.in);
        int ctr = 0; //initialize ctr to track # of iterations.
        String response = "y";
        //prompt user to enter course ID and display information pertaining to course. Will allow user to make as many
        // queries as they wish by prompting them after a search has been made.
        while (response.equalsIgnoreCase("y")) {
            System.out.println("==============================================\nPlease enter a course ID to fetch course info:");
            String id = keyIn.next().toUpperCase();
            if (l1.contains(id)) {
                l1.find(id);
                System.out.println("\nCourse has been found in the list. Here is its info:\n");
                l1.displayNode(id);
            } else {
                System.out.println("\nCourse was not found in list\n");
            }
            ctr++;
            System.out.println("\nIf you would you like to enter another course id, please enter 'y'. If not, enter any" +
                    " other key.");
            response = keyIn.next();
            keyIn.nextLine();
        }
        System.out.println("Number of iterations: " + ctr);
    }

    /**
     * openRequestFile is a method which will try to open Request .txt files which will be read later in a different
     * method. If the file cannot be read, the FileNotFoundException will be thrown and catched.
     *
     * @param sc is an object of the Scanner class used to open the input files.
     * @return the Scanner object sc once an attempt at opening the file has been made
     */
    private static Scanner openRequestFiles(Scanner sc) {
        Scanner keyIn = new Scanner(System.in);
        try {
            System.out.println("Please enter the name of the file you would like to open:");
            String file = keyIn.next();
            sc = new Scanner(new FileInputStream(file));
        } catch (FileNotFoundException e1) {
            System.out.println("File does not exist! You have one attempt left.\n");
            try {
                System.out.println("===========================================================\n" +
                        "Please enter the name of the file you would like to open:");
                String file = keyIn.next();
                sc = new Scanner(new FileInputStream(file));
            } catch (FileNotFoundException e2) {
                System.out.println("\nFile does not exist!! Program will terminate.");
                System.exit(0);
            }
        }
        System.out.println("\nFile successfully read.");
        return sc;
    }

    /**
     * readRequestFiles is a method which will be used to read an open Request file, and then parse the information
     * about requested courses into one ArrayList and parse the information about completed courses in another ArrayList
     *
     * @param sc        an an object of the Scanner class used to read info from the .txt Request files
     * @param requested is an ArrayList of Strings which will contain all the requested courses
     * @param completed is an ArrayList of Strings  which will contain all the completed courses
     */
    private static void readRequestFiles(Scanner sc, ArrayList<String> requested, ArrayList<String> completed) {
        //using while loop to read Request files.
        while (sc.hasNextLine()) {
            String read = sc.next(); //read the first String
            if (sc.hasNextLine()) //before going to next line ensure not at end of file
                sc.nextLine(); //go to next line
                 /*if we have read Requested, then store all the requested courses in
                 ArrayList requested, then break out of loop. */
            if (read.contains("Requested")) {
                while (sc.hasNextLine()) {
                    read = sc.next();
                    requested.add(read);
                }
                break;
            }

            if (!read.contains("Finished"))
                completed.add(read);
        }
    }

    /**
     * verifyCourseRequirements is a method which will check to see if an enrolment of a course may occur, based on
     * whether or not the prerequisites/co-requisites have been completed and if co-requisites have been requested at
     * the same time. The method will notify the user whether or not enrolment may occur based on the information
     * stored from the Request files in the ArrayLists and comparing it to the Course objects stored in the nodes
     * of the CourseList object l1
     *
     * @param requestedCourse is String containing the id of the requested course to enrol in
     * @param requested is an ArrayList of Strings containing all the requested courses.
     * @param completed is an ArrayList of Strings containing all the completed courses.
     * @param l1        is an object of the CourseList class referencing Course objects in each of its nodes
     */
    private static boolean verifyCourseRequirements(String requestedCourse, ArrayList<String> requested, ArrayList<String> completed, CourseList l1) {

        boolean preReqMet = false;
        boolean coReqMet = false;
        boolean requirementMet =false;

        System.out.println("Checking if requirements have been met for course " + requestedCourse + "...");

        //check to see if course exists in System CourseList
        if (l1.contains(requestedCourse)) {

            //if the course exists, check to see if it has preReqs or coReqs, and if the requirement has been completed.
            Course requestedCourseObj = l1.find(requestedCourse).getCourse();
            String prereq = requestedCourseObj.getPreReqId();
            String coreq = requestedCourseObj.getCoReqId();

            //if there exits a prereq for the course, then verify it has been met.
            if (!prereq.equals("")) {
                if (completed.contains(prereq)) {
                    preReqMet = true;
                }
            } else {
                preReqMet = true; //if there is no prereq for the course, then the requirement is met.
            }
            //check to see if course has a co-requisite
            if (!coreq.equals("")) {
                /*if they have completed the co-requisite, or a request has been made to enrol in co-requisite (and the
                 requirement for this course too have been met) then all requirements have been met.
                  */
                if (completed.contains(coreq)) {
                    coReqMet = true;
                }else if(requested.contains(coreq)){
                    System.out.println("\nYou have registered in the required co-requisite " + coreq +
                            ", now verifying if the requirements for " + coreq + " have been met as well:");
                    if (verifyCourseRequirements(coreq, requested, completed, l1)) {
                        coReqMet = true;
                    }
                }
            } else {
                coReqMet = true;
            }

            if (preReqMet && coReqMet) {
                if (prereq.equals(""))
                    System.out.println("Student has completed the required co-requisite " + coreq +
                            ".\nStudent may enrol in " + requestedCourse);
                else if (coreq.equals(""))
                    System.out.println("Student has completed the required prerequisite " + prereq +
                            ".\nStudent may enrol in " + requestedCourse);
                else
                    System.out.println("Student has completed the required prerequisite " + prereq +
                            " and is also enrolling in co-requisite " + coreq +
                            ".\nStudent may enrol in " + requestedCourse);
                requirementMet = true;
            } else {
                if (!preReqMet && !coReqMet)
                    System.out.println("Student is missing the prerequisite " + prereq +
                            " and has not registered for the co-requisite "
                            + coreq + ".\nStudent cannot enrol in " + requestedCourse);
                else if (!preReqMet && coReqMet)
                    System.out.println("Student is missing the prerequisite " + prereq +
                            ".\nStudent cannot enrol in " + requestedCourse);
                else
                    System.out.println("Student is missing the co-requisite " + coreq +
                            ".\nStudent cannot enrol in " + requestedCourse);
            }
        } else { //if course doesn't exist notify user.
            System.out.println("Sorry, that course does not exist!");
        }
        System.out.println();
        return requirementMet;
    }


    /**
     * testingMethods is a method which will allow the user to test multiple methods that have been created for
     * the CourseList and Course class. The user will be allowed to enter the methods they wish to test until
     * they request to exit the program.
     *
     * @param l1 is a CourseList object used to test methods of the CourseList class
     * @param l2 is a CourseList object used to test methods of the CourseList class
     */
    private static void testingMethods(CourseList l1, CourseList l2) {
        Scanner sc = new Scanner(System.in);
        String response;
        do {
            System.out.println("\nWhich method would you like to test? For copy constructors enter 'copy' and for other methods enter the name i.e. 'addToStart' (case-sensitive)");
            String choice = sc.next();

            switch (choice) {

                //Test Copy Constructors
                case "copy":
                    System.out.println("\n==================================================================\n" +
                            "Course Copy Constructor\n==================================================================");
                    Course course = l1.find("COMP249").getCourse();
                    System.out.println("Copying course:\n\n" + course);
                    Course copyCourse = new Course(course, "MUSIC101");
                    System.out.println("\nDisplay contents copied copied course with new id...\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
                    System.out.println(copyCourse);

                    System.out.println("\n==================================================================\n" +
                            "CourseList Copy Constructor\n==================================================================");
                    CourseList copy = new CourseList(l1);
                    System.out.println("Display contents copied list...\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
                    copy.displayCourseList();


                    System.out.println("Display contents copied list...\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
                    copy.displayCourseList();
                    break;

                //Test addToStart method
                case "addToStart":
                    System.out.println("\n==================================================================\n" +
                            "addToStart() method\n==================================================================");
                    System.out.println("Display contents of course list first...\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
                    l1.displayCourseList();
                    Course add = new Course("MUSIC123", "Music Theory 101", 2, "ART101", "");
                    l1.addToStart(add);
                    System.out.println("Display contents of list with node inserted at start...\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
                    l1.displayCourseList();
                    break;


                //Test insertAtIndex method
                case "insertAtIndex":
                    try {
                        System.out.println("\n==================================================================\n" +
                                "insertAtIndex() method\n==================================================================");
                        System.out.println("Display contents of course list first...\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
                        l1.displayCourseList();
                        Course c = new Course("MUSIC123", "Music Theory 101", 2, "ART101", "");
                        System.out.println("Display contents of list with node inserted at index 2...\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
                        l1.insertAtIndex(c, 2);
                        l1.displayCourseList();
                        System.out.println("Calling method to empty list (index 0)...\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
                        l2.insertAtIndex(c, 0);
                        l2.displayCourseList();
                        System.out.println("Calling method to empty list (invalid index)...\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
                        l2.insertAtIndex(c, 3);
                        l2.displayCourseList();
                    } catch (NoSuchElementException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                //Testing deleteFromIndex method
                case "deleteFromIndex":
                    try {
                        System.out.println("\n==================================================================\n" +
                                "deleteFromIndex() method\n==================================================================");
                        System.out.println("Display contents of course list first...\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
                        l1.displayCourseList();
                        System.out.println("Display contents of list with removed node at index 1...\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
                        l1.deleteFromIndex(1);
                        l1.displayCourseList();
                        System.out.println("Calling method to empty list...\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
                        l2.deleteFromIndex(3);
                    } catch (NoSuchElementException e) {
                        System.out.println(e.getMessage());
                    }
                    break;


                //test replaceAtIndex method
                case "replaceAtIndex":
                    System.out.println("\n==================================================================\n" +
                            "replaceAtIndex() method\n==================================================================");
                    System.out.println("Display contents of course list first...\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
                    l1.displayCourseList();
                    Course c1 = new Course("MUSIC123", "Music Theory 101", 2, "ART101", "");
                    l1.replaceAtIndex(c1, 2);
                    System.out.println("Display contents of list with node modified at index 2...\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
                    l1.displayCourseList();
                    System.out.println("Calling method to empty list...\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
                    l2.replaceAtIndex(c1, 3);
                    break;

                // Test deleteFromStart method
                case "deleteFromStart":
                    System.out.println("\n==================================================================\n" +
                            "deleteFromStart() method\n==================================================================");
                    System.out.println("Display contents of course list first...\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
                    l1.displayCourseList();
                    System.out.println("Display contents of list with removed node...\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
                    l1.deleteFromStart();
                    l1.displayCourseList();
                    System.out.println("Display result calling method to empty list...\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
                    l2.deleteFromStart();
                    break;

                //test find method
                case "find":
                    System.out.println("\n==================================================================\n" +
                            "find() method\n==================================================================");
                    System.out.println("Testing with an invalid courseID...");
                    l1.find("this is not an id");
                    System.out.println("\nTesting with a valid courseID...");
                    Course c2 = l1.find("COMP201").getCourse();
                    System.out.println(c2);
                    System.out.println("\nCalling method with empty list...");
                    System.out.println(l2.find("COMP249"));
                    break;


                //test contains method
                case "contains":
                    System.out.println("\n==================================================================\n"
                            + "contains() method\n==================================================================");
                    System.out.println("Testing with invalid course ID 'COMP500'");
                    System.out.println(l1.contains("COMP500"));
                    System.out.println("\nTesting with a valid course ID 'COMP249'");
                    System.out.println(l1.contains("COMP249"));
                    System.out.println("\nCalling method with empty list...");
                    System.out.println(l2.contains("COMP249"));
                    break;

                //test equals method
                case "equals":
                    System.out.println("\n==================================================================\n" +
                            "equals() method\n==================================================================");
                    CourseList c4 = new CourseList(l1); //make a copy to test
                    System.out.println("Testing two identical lists by making a copy of one...");
                    System.out.println(c4.equals(l1));
                    Course c5 = new Course("MUSIC123", "Music Theory 101", 2, "ART101", "");
                    c4.replaceAtIndex(c5, 4); //modify the copied list
                    System.out.println("Modified entry from one list. Now retesting to see if the lists are still equal...");
                    System.out.println(l1.equals(c4));
                    break;

                //test isDirectlyRelated method
                case "isDirectlyRelated":
                    System.out.println("\n==================================================================\n" +
                            "isDirectlyRelated() method\n==================================================================");
                    Course course1 = l1.find("COMP249").getCourse();
                    Course course2 = l1.find("COMP248").getCourse();
                    System.out.println("Testing with COMP 248 and COMP 249...");
                    System.out.println(course2.isDirectlyRelated(course1));
                    Course course3 = l1.find("COMP249").getCourse();
                    Course course4 = l1.find("COMP228").getCourse();
                    System.out.println("Testing with COMP 249 and COMP 228...");
                    System.out.println(course4.isDirectlyRelated(course3));
                    break;
                default:
                    System.out.println("Sorry, you have chosen an invalid method.");
            }
            System.out.println("\nTry another method? Enter 'y' for yes otherwise press any other key to exit");
            response = sc.next().toLowerCase();
        } while (response.equals("y"));
    }
}
