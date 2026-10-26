package com.SMSApplication.controller;

import com.SMSApplication.entity.User;
import com.SMSApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/home")
    public String home() {
        return "admin-home";
    }
    

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin-dashboard";
    }

    @GetMapping("/students")
    public String manageStudents(Model model) {
        model.addAttribute("students", userRepository.findByRole("STUDENT"));
        return "admin-manage-students";
    }

    @GetMapping("/staffs")
    public String manageStaffs(Model model) {
        model.addAttribute("staffs", userRepository.findByRole("STAFF"));
        return "admin-manage-staffs";
    }
    
    @GetMapping("/edit-student/{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        User student = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + id));

        model.addAttribute("student", student);
        return "admin-edit-student";
    }

    @PostMapping("/update-student/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") User updated) {

        User s = userRepository.findById(id).orElseThrow();

        s.setFirstName(updated.getFirstName());
        s.setLastName(updated.getLastName());
        s.setRollNumber(updated.getRollNumber());
        s.setDepartment(updated.getDepartment());
        s.setYearSemester(updated.getYearSemester());
        s.setPhone(updated.getPhone());
        s.setDob(updated.getDob());
        s.setGender(updated.getGender());
        s.setFatherName(updated.getFatherName());
        s.setAddress(updated.getAddress());

        userRepository.save(s);

        return "redirect:/admin/students";
    }
    
 // OPEN EDIT STAFF PAGE
    @GetMapping("/staff/edit/{id}")
    public String editStaff(@PathVariable Long id, Model model) {
        User staff = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Staff ID: " + id));

        model.addAttribute("staff", staff);
        return "admin-edit-staff";
    }

    // UPDATE STAFF
    @PostMapping("/staff/update/{id}")
    public String updateStaff(@PathVariable Long id, @ModelAttribute("staff") User updated){

    	 User staff = userRepository.findById(id)
    	            .orElseThrow(() -> new IllegalArgumentException("Invalid staff ID"));

        if (staff == null) {
            return "redirect:/admin/staffs?error=StaffNotFound";
        }

        staff.setFirstName(updated.getFirstName());
        staff.setLastName(updated.getLastName());
        staff.setEmail(updated.getEmail());
        staff.setPhone(updated.getPhone());
        staff.setGender(updated.getGender());
        staff.setDepartment(updated.getDepartment());
        staff.setDesignation(updated.getDesignation());
        staff.setStaffId(updated.getStaffId());
        staff.setAddress(updated.getAddress());
        staff.setDob(updated.getDob());

        userRepository.save(staff);

        return "redirect:/admin/staffs?success=updated";
    }

    @GetMapping("/delete-staff/{id}")
    public String deleteStaff(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/admin/staffs?deleted=true";
    }
    
    @GetMapping("/delete-student/{id}")
    public String deleteStudent(@PathVariable Long id) {

        userRepository.deleteById(id);

        return "redirect:/admin/students?deleted=true";
    }
 // OPEN ADD STUDENT FORM
    @GetMapping("/add-student")
    public String addStudentForm(Model model) {
        model.addAttribute("student", new User());
        return "admin-add-student";
    }

    // SAVE NEW STUDENT
    @PostMapping("/save-student")
    public String saveStudent(@ModelAttribute("student") User student) {

        student.setRole("STUDENT");

        userRepository.save(student);

        return "redirect:/admin/students?added=true";
    }
    @GetMapping("/student/add")
    public String showAddStudentPage(Model model) {
        model.addAttribute("student", new User());
        return "admin-add-student";
    }
    
    @PostMapping("/student/add")
    public String addStudent(@ModelAttribute("student") User student) {

        student.setRole("STUDENT"); // important

        userRepository.save(student);

        return "redirect:/admin/students?success=added";
    }

 // OPEN ADD STAFF PAGE
    @GetMapping("/staff/add")
    public String showAddStaffPage(Model model) {
        model.addAttribute("staff", new User());
        return "admin-add-staff";
    }

    // SAVE NEW STAFF
    @PostMapping("/staff/add")
    public String addStaff(@ModelAttribute("staff") User staff) {

        staff.setRole("STAFF"); // Important

        userRepository.save(staff);

        return "redirect:/admin/staffs?success=added";
    }





    
    
}

