package com.edtech.userRegistration.Service;

import com.edtech.userRegistration.Dto.StudentsDto;
import com.edtech.userRegistration.Entity.AcademicDetails;
import com.edtech.userRegistration.Entity.Address;
import com.edtech.userRegistration.Mapper.AcademicDetailsMapper;
import com.edtech.userRegistration.Mapper.AddressMapper;
import com.edtech.userRegistration.Repository.StudentsRepository;
import com.edtech.userRegistration.Entity.Students;
import com.edtech.userRegistration.Exception.ResourceNotFoundException;
import com.edtech.userRegistration.Mapper.StudentsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentsRepository studentsRepository;

    @Transactional
    public void registerStudent(StudentsDto studentDto) {
        Students student = new Students();
        StudentsMapper.mapToStudents(studentDto, student);

        Address address = new Address();
        AddressMapper.mapToAddress(studentDto.getAddress(), address);
        student.setAddress(address);

        AcademicDetails academicDetails = new AcademicDetails();
        AcademicDetailsMapper.mapToAcademicDetails(studentDto.getAcademic(), academicDetails);
        student.setAcademicDetails(academicDetails);

        Students savedStudent = studentsRepository.save(student);
    }

    @Transactional
    public void updateStudent(Long id, StudentsDto studentsDto) {
        boolean isUpdated = false;
        Students existingStudent = studentsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found", "",""));

        StudentsMapper.mapToStudents(studentsDto, existingStudent);

        Address existingAddress = existingStudent.getAddress();
        AddressMapper.mapToAddress(studentsDto.getAddress(), existingAddress);
        existingStudent.setAddress(existingAddress);

        AcademicDetails existingAcademicDetails = existingStudent.getAcademicDetails();
        AcademicDetailsMapper.mapToAcademicDetails(studentsDto.getAcademic(), existingAcademicDetails);
        existingStudent.setAcademicDetails(existingAcademicDetails);

        Students updatedStudent = studentsRepository.save(existingStudent);

    }

    @Transactional
    public void deleteStudent(Long id) {
        if (!studentsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id: ", "", id+"");
        }
        studentsRepository.deleteById(id);
    }
}
