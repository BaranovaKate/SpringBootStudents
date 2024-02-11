package by.baranova.course.springbootstudents.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
//@RequestMapping("/students")
//public class StudentController {
//
//    @GetMapping
//    public String findStudents(Model model){
//        model.addAttribute("message", "kzkzkzkzkzk");
//        return "students/list";
//    }
//}

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    //students
    @GetMapping
    public String findStudents(Model model){

        final List<Student> students = studentService.findStudents();
        model.addAttribute("students", students);

        return "students/list";
    }

    @GetMapping("/new")
    public String createStudents(@ModelAttribute Student student){
        return "students/new";
    }

    @PostMapping
    public String handleStudentCreation(@ModelAttribute Student student){
        studentService.save(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}")
    public String findStudent(Model model, @PathVariable("id") Integer id){

        final Student student = studentService.findStudentById(id);

        model.addAttribute("id", student.getId());
        model.addAttribute("name", student.getName());
        model.addAttribute("lastName", student.getLastName());
        model.addAttribute("phoneNumber", student.getPhoneNumber());
        model.addAttribute("dob", student.getDob());

        return "students/page";
    }
}
