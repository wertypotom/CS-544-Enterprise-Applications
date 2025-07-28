package repositories;

import domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select st from Student st where st.department.name = :name")
    List<Student> getByDepartmentNameQuery(@Param("name") String name);

    @Query("select st from Student st join st.grades gr where gr.course.name = :courseName")
    List<Student> getByGradesCourseNameQuery(@Param("courseName") String courseName);

    List<Student> getByDepartmentName(String name);
    List<Student> getByGradesCourseName(String courseName);
}
