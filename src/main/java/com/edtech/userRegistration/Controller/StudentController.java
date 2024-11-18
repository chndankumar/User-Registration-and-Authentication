package com.edtech.userRegistration.Controller;

import com.edtech.userRegistration.Dto.ResponseDto;
import com.edtech.userRegistration.Dto.StudentsDto;
import com.edtech.userRegistration.Service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/student")
@Tag(name = "Student Management", description = "APIs for Managing Students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(summary = "Register Student", description = "Register a new student.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Student registered successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed", content = @Content)
    })
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerStudent(@Valid @RequestBody StudentsDto studentDto) {
        studentService.registerStudent(studentDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Student registered successfully"));
    }

    @Operation(summary = "Update Student", description = "Update existing student details by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Student updated successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found", content = @Content)
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentsDto studentDto) {
        studentService.updateStudent(id, studentDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto("200", "Student updated successfully"));
    }

    @Operation(summary = "Delete Student", description = "Delete a student by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Student deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found", content = @Content)
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto("200", "Student deleted successfully"));
    }
}
