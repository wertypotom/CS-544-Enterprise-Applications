package domain;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

@Entity
public class School {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    @MapKey(name = "studentid")
    private Map<Long, Student> students = new HashMap<>();

    public School() {}

    public School(String name, Student student) {
        this.name = name;
        this.students.put(student.getStudentid(), student);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Long, Student> getStudents() {
        return students;
    }

    public void setStudent(Student student) {
        this.students.put(student.getStudentid(), student);
    }
}
