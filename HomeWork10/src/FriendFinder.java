
import java.util.List;
import java.util.HashSet;
import java.util.Set;

/*
 * SD2x Homework #10
 * Modify the method below so that it uses defensive programming.
 * Please be sure not to change the method signature!
 */

public class FriendFinder {
	
	protected ClassesDataSource classesDataSource;
	protected StudentsDataSource studentsDataSource;

	public FriendFinder(ClassesDataSource cds, StudentsDataSource sds) {
		classesDataSource = cds;
		studentsDataSource = sds;
	}
	
	public Set<String> findClassmates(Student theStudent) {
		if (theStudent == null) {
			throw new IllegalArgumentException("Input Student is null");
		}
		if (theStudent.getName() == null || theStudent.getName().isEmpty()) {
			throw new IllegalArgumentException("Student's name is invalid");
		}
		if (classesDataSource == null || studentsDataSource == null) {
			throw new IllegalStateException("Input Student is null");
		}
		
		String name = theStudent.getName();
		
		// find the classes that this student is taking
		List<String> myClasses = classesDataSource.getClasses(name);
		if (myClasses == null || myClasses.isEmpty()) {
			return null;
		}
		
		Set<String> classmates = new HashSet<>();
		
		// use the classes to find the names of the students
		for (String myClass : myClasses) {
			// list all the students in the class
			List<Student> students = studentsDataSource.getStudents(myClass);
			if (students == null || students.isEmpty()) {
				return null;
			}
			
			for (Student student : students) {
				if (student == null || student.getName() == null || student.getName().isEmpty()) {
					continue;
				}
				
				// find the other classes that they're taking
				List<String> theirClasses = classesDataSource.getClasses(student.getName());
				if (theirClasses == null || theirClasses.isEmpty()) {
					return null;
				}
			
				// see if all of the classes that they're taking are the same as the ones this student is taking
				boolean same = true;
				for (String c : myClasses) {
					
					if (!theirClasses.contains(c)) {
						same = false;
						break;
					}
				}
				if (same) {
					if (!student.getName().equals(name))
						classmates.add(student.getName());
				}
			}

		}
				
		if (classmates.isEmpty()) { 
			return null;
		}
		else return classmates;
	}
	

}
