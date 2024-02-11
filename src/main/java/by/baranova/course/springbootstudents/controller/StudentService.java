package by.baranova.course.springbootstudents.controller;


import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    private final Map<Integer, Student> repository = new HashMap<>();

    public StudentService() {
        Student first = new Student(1, "Alex", "Baranov", "+375(25)5009514", "19.08.1988");
        //  Student second = new Student(2, "Alena");
        //  Student third = new Student(3, "Ilya");

        repository.put(first.getId(), first);
        //  repository.put(second.getId(), second);
        //  repository.put(third.getId(), third);

    }

    public List<Student> findStudents() {
        return List.copyOf(repository.values());
    }

    public Student findStudentById(int id){
        return repository.get(id);
    }

    public void save(Student student) {
        if(student.getId() == null){
            Integer lastId = repository.keySet().stream()
                    .max(Integer::compare)
                    .orElse(0);
            student.setId(++lastId);
        }
        // если id есть, заменить студента
        repository.put(student.getId(), student);
    }
}
