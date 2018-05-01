//------------------------------------------------------------------------
//Assignment #4
//Written by: Giselle Martel, ID# 26352936
//COMP 249 Section PP, - Winter 2018
//Due Date: April 13, 2018
//------------------------------------------------------------------------

import java.util.NoSuchElementException;

/**
 * <p>COMP 249 section PP Assignment # 4</p><p> Due Date: April 13, 2018</p>
 *
 * <p>CourseList is a class which implements a linked list data structure. It contains two private attributes - a
 * CourseList object head which will be used as pointer for the nodes of CourseList, and a int size which will store the
 * size of the CourseList (number of nodes contained within it). A inner class CourseNode is implemented within this class
 * which is used to create nodes for the CourseList. </p>
 *
 * @author Giselle Martel 26352936
 */
public class CourseList {

    private CourseNode head;
    private int size = 0;


    /**
     * Default constructor for the CourseList class which sets the head object to null and the size 0 (empty list).
     */
    public CourseList() {
        head = null;
        size = 0;
    }

    /**
     * Copy Constructor which takes a CourseList object and makes a copy of it.
     *
     * @param list is a CourseList object which will be copied.
     */
    public CourseList(CourseList list) {
        //if the list we are copying is empty, then set pointer to null (no copy to be made).
        if (list.head == null) {
            head = null;
            //otherwise is list to be copied is not empty...
        } else {
            CourseNode t1, t2; //declare two pointers
            head = t2 = null; //initialize pointers t2 and head to null
            t1 = list.head; //t1 points to the head of list to be copied
            //as long as t1 is pointing to a node, the program will copy nodes from list
            while (t1 != null) {
                /*if the head of list copy points to no node (this will only happen the first time the while loop executes),
                then point head and t2 to newly created node */
                if (head == null) {
                    //t2 will point to new node containing Course object (last node in list).
                    t2 = new CourseNode(t1.getCourse(), null);
                    head = t2;
                    //If list is not empty, create a new mode linked from t2, then move t2 to node
                } else {
                    t2.next = new CourseNode(t1.course, null); //move t2.next to last node
                    t2 = t2.next; //move t2 to last node
                }
                t1 = t1.next; //move pointer t1 to next node
            }
            t2 = null; //once copying is complete, get rid of unneeded pointers (note that t1 is already null by this point)
            this.size = list.size; //the size of the copied list should equal the size of the passed list object.
        }
    }


    /**
     * addtoStart is a method which adds a new node to the CourseList starting from the head.
     *
     * @param course is an object of the Course class which will be stored in new node added to list.
     */
    public void addToStart(Course course) {
        head = new CourseNode(course, head);
        size++; //increment size of list each time a node is added.
    }

    /**
     * insertAtIndex is a method which will create a new node of the CourseList at an indicated index, and will store
     * a Course object passed in the parameter inside the new node. The node originally located at the index will be
     * shifted
     *
     * @param course is an object of the Course class which will be stored inside the new node
     * @param index  is an int which represents the position in the CourseList where the node to be replaced is.
     * @throws NoSuchElementException when invalid index is given
     */
    public void insertAtIndex(Course course, int index) throws NoSuchElementException {
        if ((index < 0 || index > (size - 1)) && index != 0) {
            throw new NoSuchElementException("Index does not exist in course list!!");
        } else if (head != null) {
            CourseNode currentIndex = head;
            int ctr = 0;
            //while the counter is less than the size of the list, keep moving the pointer to the next index
            while (ctr < index - 1) {
                currentIndex = currentIndex.next;
                ctr++;
            }
            //once at desired index, create new node at indicated index.
            currentIndex.next = new CourseNode(course, currentIndex.next);
            size++; //increment the list size once element is added.
        } else if (size == 0 && index == 0) {
            addToStart(course); //if list is empty and use indicates index 0, call addToStart method.
            size++; //increment the list size once element is added.
        }

    }

    /**
     * deleteFromIndex is a method which will remove a node of the CourseList at an indicated index.
     *
     * @param index is an int which represents the position in the CourseList where the node to be replaced is.
     * @throws NoSuchElementException when invalid index is given or list is empty
     */
    public void deleteFromIndex(int index) throws NoSuchElementException {
        //if list is empty, throw exception
        if (size == 0) {
            throw new NoSuchElementException("List is already empty, there is nothing to delete!");
            //if index does not exist, throw new exception
        } else if ((index < 0 || index > (size - 1)) && index != 0) {
            throw new NoSuchElementException("Index does not exist in course list!!");
            //otherwise iterate through node pointers to delete node at index.
        } else if (head != null) {
            CourseNode currentIndex = head;
            int ctr = 0;
            //use counter which will iterate through nodes up until index.
            while (ctr < index - 1) {
                currentIndex = currentIndex.next;
                ctr++;
            }
            //move the pointer to the next node to remove the node at desired index.
            currentIndex.next = currentIndex.next.next;
            size--; //decrement the size of the list after removing node.
        }
    }

    /**
     * deleteFromStart will remove a node from the beginning of the CourseList when called.
     */
    public void deleteFromStart() {
        if (head == null) {
            System.out.println("List is already empty, nothing to delete!");
        } else {
            head = head.next; //move head to next index to remove first node.
            size--; //decrement list each time node is removed.
        }
    }

    /**
     * replaceAtIndex is a method which will replace a node of the CourseList at an indicated index, and will store
     * a Course object passed in the parameter inside the new node.
     *
     * @param course is an object of the Course class which will be stored inside the new node
     * @param index  is an int which represents the position in the CourseList where the node to be replaced is.
     */
    public void replaceAtIndex(Course course, int index) {
        //if invalid index, method returns.
        if ((index < 0 || index > (size - 1)) && index != 0) {
            System.out.println("Index does not exist in list!!");
            return;
        }

        CourseNode currentIndex = head;
        int ctr = 0;
        //if index is 0 and list is empty, add to start of list new node
        if (size == 0 && index == 0) {
            addToStart(course);
        } else if (index == 0) { //replace first node if index is 0
            head = new CourseNode(course, currentIndex.next);
        } else {
            while (ctr < index - 1) { //iterate to find node pointer at index
                currentIndex = currentIndex.next;
                ctr++;
            }
            currentIndex.next = new CourseNode(course, currentIndex.next.next); //replace the node
        }

    }


    /**
     * find is a method which will iterate through the nodes of the CourseList to see if the passed id matches with any
     * course ID of the stored Course objects inside the nodes of the CourseList.
     *
     * @param id is a String value which will be searched for inside the CourseList.
     * @return null if no match to id is found.
     */
    public CourseNode find(String id) {
        CourseNode currentIndex = head; //set pointer to beginning of list
        //as long as the the pointer is pointing to a node, keep searching
        while (currentIndex != null) {
            /*if the courseId found at the current node matches the id from the parameter, a match is found and pointer
            is returned. This pointer can present a security issue however, since the pointer can be used in other
            public methods to modify the list*/
            if (currentIndex.course.getCourseId().equals(id))
                return currentIndex; //potential privacy leak
            currentIndex = currentIndex.next; //if no match is found move pointer to next node.
        }
        //if no match is found in whole list, then return null.
        System.out.println("Sorry, no courses found with the course ID '" + id + "'");
        return null;
    }


    /**
     * contains is a method which will iterate through the nodes of the CourseList to see if the passed id matches with any
     * course ID of the stored Course objects inside the nodes of the CourseList.
     *
     * @param id is a String value which will be searched for inside the CourseList.
     * @return false if the id does not have any match with one of the course IDs in the nodes of the CourseList
     */
    public boolean contains(String id) {
        CourseNode currentIndex = head; //set pointer to beginning of list
        //as long as the pointer points to a node, continue searching
        while (currentIndex != null) {
            //if the id of the Course object stored in the current node matched the id in the parameter, return true.
            if (currentIndex.course.getCourseId().equals(id)) {
                return true;
            }
            currentIndex = currentIndex.next; //move pointer to next node until match is found.
        }
        return false; //if no match is found after iterating through all nodes, then return false
    }

    /**
     * equals method for CourseList class which will check is the calling Course List object is identical to passed
     * CourseList object.
     *
     * @param list is a linked list object of the CourseList class
     * @return false if the two lists are not identical
     */
    public boolean equals(CourseList list) {
        //if the two lists are different sizes, then they are not equal.
        if (size != list.size) {
            System.out.println("Lists are different sizes and thus not equivalent!");
            return false;
            //if one of the lists are empty.
        } else if (head == null || list.head == null) {
            //if both lists are empty then they are technically equal.
            if (head == null && list.head == null) {
                System.out.println("Both lists are empty");
                return true;
                //if only one list is empty then they are not equal.
            } else {
                System.out.println("One of the lists is empty!");
                return false;
            }
            //else the two lists are the same size and not empty, then check for equality node by node.
        } else {
            CourseNode t1 = head;
            CourseNode t2 = list.head;
            int ctr = 0; //use a counter to track track if each node is the same.
            //as long as the pointer for each list points to a node, an equals comparison can be made
            while (t1 != null && t2 != null) {
                //calls the equals method of the Course class to check if the two course objects stored in the node are equal.
                if (t1.course.equals(t2.course)) {
                    ctr++; //if the two nodes are equal, then iterate the counter
                }
                //move the pointers to their next respective node
                t1 = t1.next;
                t2 = t2.next;
            }
            //if the counter is the same as the size of list, this means that every node was equivalent so return true.
            if (ctr == size) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * displayCourseList is a method that will display the contents of the CourseList. It will iterate to each pointer and
     * then call the toString method of the Course object stored at that node.
     */
    public void displayCourseList() {

        CourseNode pointer = head; //initialize pointer to head of CourseList
        //while pointer points to a node, print Course object inside node.
        while (pointer != null) {
            System.out.println(pointer.getCourse() + "\n");
            pointer = pointer.next; //move pointer to next node
        }
    }

    /**
     * displayNode is a method that will display the contents of one node in the Course.
     *
     * @param id will be used to see if any of the nodes contain a Course object with a courseId equivalent to it
     */
    public void displayNode(String id) {
        CourseNode pointer = head; //initialize pointer to head of CourseList
        //while pointer points to a node, print Course object inside node.
        while (pointer != null) {
            if (pointer.course.getCourseId().equals(id)) {
                System.out.println(pointer.course);
            }
            pointer = pointer.next;
        }
    }


    /**
     * inner class CourseNode will be used to create nodes for the CourseList. It implements the Cloneable
     * interface allowing the addition of a clone() method. The class contains two private attributes, a Course
     * object which will be stored in each node, and a CourseNode object used to for the CourseList pointer.
     *
     * @author Giselle Martel 26352936
     */
    class CourseNode implements Cloneable {

        private Course course;
        private CourseNode next;

        /**
         * default constructor for CourseNode initializes the two attributes to null
         */
        public CourseNode() {
            course = null;
            next = null;
        }

        /**
         * parametrized constructor for CourseNode which initializes the two attibutes to the parameter values.
         *
         * @param course is object of the Course class which will be used as information in the CourseNode
         * @param next   is an object of the CourseNode class which will be used as a pointer for the CourseNode
         */
        public CourseNode(Course course, CourseNode next) {
            this.course = course;
            this.next = next;
        }

        /**
         * Copy constructor for CourseNode which takes an object of the CourseNode class, and copies the two attributes.
         *
         * @param node is an object of CourseNode, which contains both an object of the Course class (copied using the
         *             clone() method of Course) and an object of the CourseNode class, which will be set to the current
         *             pointer.
         */
        public CourseNode(CourseNode node) {
            this.course = node.course.clone();
            this.next = node.next;
        }

        /**
         * Accessor method for attribute course.
         *
         * @return course which is an object of the Course class and an attribute of CourseNode class.
         */
        public Course getCourse() {
            return course;
        }

        /**
         * Accessor method for attribute next.
         *
         * @return next which is both an object and an attribute of CourseNode class.
         */
        public CourseNode getNext() {
            return next;
        }

        /**
         * Mutator method for attribute course
         *
         * @param course is object of the Course class which will be used to set the Course object attribute of this
         *               class.
         */
        public void setCourse(Course course) {
            this.course = course;
        }

        /**
         * Mutator method for attribute next
         *
         * @param next is object of the CourseNode class which will be used to set the CourseNode object attribute of
         *             this class.
         */
        public void setNext(CourseNode next) {
            this.next = next;
        }

        /**
         * clone method for the CourseNode class which will use the clone method inherited from the Object class, and
         * implemented using the Cloneable interface, in order to create a copy of the calling CourseNode object
         *
         * @return null when CloneNotSupportedException is thrown.
         */
        public CourseNode clone() {
            CourseNode node;
            try {
                node = (CourseNode) super.clone();
            } catch (CloneNotSupportedException e) {
                return null;
            }
            return node;
        }


    }
}