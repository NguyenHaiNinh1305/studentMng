package service;

import entity.Student;
import org.apache.log4j.Logger;
import repository.StudentRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentService {
    Logger logger = Logger.getLogger(StudentService.class);
    StudentRepository studentRepository = new StudentRepository();

    public List<Student> getListStudent() {
        return studentRepository.getAll();
    }

    public Student findID(int id) {
        return studentRepository.findById(id);
    }

    public boolean insert(Student student) {
        List<Student> students = studentRepository.getAll();
        students.sort((o1, o2) -> o1.getId() < o2.getId() ? 1 : -1);
        int id = students.get(0).getId() + 1;

        student.setId(id);
        if (checkValidStudent(student) == true) {
            return studentRepository.insert(student);
        } else {
            return false;
        }
    }

    public boolean checkValidStudent(Student student) {


        if (Student.checkValidStudent(student) == true) {
            if (0 <= student.getAverage_mark() && 10 >= student.getAverage_mark()) {
                int age = new Date().getYear() - student.getBirthday().getYear();
                if (age > 0 && age <= 100) {
                    return true;
                } else {
                    logger.error("invalid age");
                    return false;
                }
            } else {
                logger.error("invalid mark");
                return false;
            }
        }
        logger.error("null attribute");
        return Student.checkValidStudent(student);
    }

    public boolean removeStudent(int id) {
        return studentRepository.removeStudent(id);
    }

    public List<Student> getAllStudentsHappybrithday() {

        List<Student> students = new ArrayList<>();
        for (Student s : getListStudent()) {
            if (s.getBirthday().getMonth() == new Date().getMonth()
                    && s.getBirthday().getDay() == new Date().getDay()) {
                students.add(s);
            }
        }
        return students;
    }

    public List<String> sendHappyBirthdayCongr() {
        List<String> messages = new ArrayList<>();
        if(getAllStudentsHappybrithday() != null) {
            for (Student s : getAllStudentsHappybrithday()) {
                String message = "Happy birthday " + s.getFullName();
                messages.add(message);
            }
            return messages;
        }
        logger.info("Having no student's birthday");
        return null;
    }

}
