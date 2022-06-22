import controller.StudentController;
import entity.Student;
import service.StudentService;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
       StudentService studentService = new StudentService();
//        System.out.println(studentService.getListStudent());
       Student student = new Student(3, "ninh", new Date(), "1", "1" ,"male", (float) 22.2);


        studentService.insert(student);
        System.out.println(studentService.sendHappyBirthdayCongr());
//        System.out.println(student);
//        System.out.println(studentService.checkValidStudent(student));
    }
}
