//------------------------------------------------------------------------
//Assignment #4
//Written by: Giselle Martel, ID# 26352936
//COMP 249 Section PP, - Winter 2018
//Due Date: April 13, 2018
//------------------------------------------------------------------------

/**
 * <p>COMP 249 section PP Assignment # 4</p><p> Due Date: April 13, 2018</p>
 * <p>
 * Interface Directly Relatable contains one method definition isDirectlyRelatable which will be used in the Course
 * class to check if two Course objects are related (if a course is a co-requisite or prerequisite of a course and
 * vice-versa. This method definition is of return type boolean and takes a Course object as a parameter.
 * </p>
 *
 * @author Giselle Martel 26352936
 */
interface DirectlyRelatable {
    public boolean isDirectlyRelated(Course c);
}
