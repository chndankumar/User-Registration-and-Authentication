package com.edtech.userRegistration.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "academicDetails")
public class AcademicDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotBlank(message = "School board for 10th is required")
    private String schoolBoard10;

//    @Positive(message = "10th marks should be a positive number")
    private Double schoolMarks10;

//    @NotNull(message = "Passing year for 10th is required")
    private Integer passingYear10;

//    @NotBlank(message = "School board for 12th is required")
    private String schoolBoard12;

//    @Positive(message = "12th marks should be a positive number")
    private Double schoolMarks12;

//    @NotNull(message = "Passing year for 12th is required")
    private Integer passingYear12;

//    @NotBlank(message = "College name is required")
    private String collegeName;

//    @NotBlank(message = "Degree is required")
    private String degree;

//    @NotBlank(message = "Core subject is required")
    private String coreSubject;

//    @Positive(message = "College marks should be a positive number")
    private Double collegeMarks;

//    @NotNull(message = "Passing year for college is required")
    private Integer passingYear;

}

