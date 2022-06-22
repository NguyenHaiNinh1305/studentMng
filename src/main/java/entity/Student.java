package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
@Check(constraints = "gender in ('male', 'female', 'other')")
public class Student implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Gen_ID_Student")
    @SequenceGenerator(name = "Gen_ID_Student", sequenceName = "SEQ_Driver", allocationSize = 1)
    int id;

    @Column(name = "name", nullable = false, length = 50)
    String fullName;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "birthday", nullable = false)
    Date birthday;

    @Column(name = "name_class", nullable = false, length = 10)
    String className;

    @Column(name = "major", nullable = false, length = 50)
    String major;

    @Column(name = "gender", nullable = false, length = 6)
    String gender;

    @Column(name = "average_mark", nullable = false, precision = 2, scale = 1)
    float average_mark;

    public static boolean checkValidStudent(Student student) {
        for(Field f : student.getClass().getDeclaredFields()){
            try {
                if(Objects.isNull(f.get(student))){
                    return false;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
}
