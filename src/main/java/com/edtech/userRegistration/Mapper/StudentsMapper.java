package com.edtech.userRegistration.Mapper;

import com.edtech.userRegistration.Dto.StudentsDto;
import com.edtech.userRegistration.Entity.Students;

public class StudentsMapper {

    public static StudentsDto mapToStudentsDto(Students students, StudentsDto studentsDto) {
        //studentsDto.setId(students.getId());
        studentsDto.setName(students.getName());
        studentsDto.setEmail(students.getEmail());
        studentsDto.setPassword(students.getPassword());
        studentsDto.setMobileNumber(students.getMobileNumber());
        studentsDto.setFatherName(students.getFatherName());
        studentsDto.setMotherName(students.getMotherName());
        studentsDto.setGuardianName(students.getGuardianName());
        return studentsDto;
    }

    public static Students mapToStudents(StudentsDto studentsDto, Students students) {
        //students.setId(studentsDto.getId());
        students.setName(studentsDto.getName());
        students.setEmail(studentsDto.getEmail());
        students.setPassword(studentsDto.getPassword());
        students.setMobileNumber(studentsDto.getMobileNumber());
        students.setFatherName(studentsDto.getFatherName());
        students.setMotherName(studentsDto.getMotherName());
        students.setGuardianName(studentsDto.getGuardianName());
        return students;
    }
}


