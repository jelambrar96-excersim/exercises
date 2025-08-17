import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

class School {

    private Map<String, Student> mapSchool = new HashMap<>();

    boolean add(String student, int grade) {
        if (this.mapSchool.containsKey(student)) { return false; }
        this.mapSchool.put(student, new Student(student, grade));
        return true;
    }

    List<String> roster() {
        List<Student> studentList = new ArrayList<>(this.mapSchool.size());
        studentList.addAll(this.mapSchool.values());
        studentList.sort(Student::compareTo);
        return studentList.stream().map(x-> x.getName()).toList();
    }

    List<String> grade(int grade) {
        List<Student> studentCollection = this.mapSchool.values().stream()
            .filter(x -> x.getGrade() == grade).toList();
        List<Student> studentList = new ArrayList<>(studentCollection);
        studentList.sort(Student::compareTo);
        return studentList.stream().map(x -> x.getName()).toList();    
    }

    private static class Student implements Comparable{
        private String name; 
        private Integer grade;
        
        Student(String name, Integer grade) {
            this.name = name;
            this.grade = grade;
        }

        @Override
        public int compareTo(Object arg0) {
            Student s = (Student)arg0;
            int gradeCompartion = this.grade.compareTo(s.getGrade());
            if (gradeCompartion != 0) return gradeCompartion;
            return this.name.compareTo(s.getName());
        }

        public String getName() { return this.name; }
        public Integer getGrade() { return this.grade; }
    }

}
