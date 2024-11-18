package com.edtech.userRegistration.Mapper;

import com.edtech.userRegistration.Dto.AcademicDetailsDto;
import com.edtech.userRegistration.Entity.AcademicDetails;

public class AcademicDetailsMapper {
    public static AcademicDetailsDto mapToAcademicDetailsDto(AcademicDetails academicDetails, AcademicDetailsDto academicDetailsDto) {
        //academicDetailsDto.setId(academicDetails.getId());
        academicDetailsDto.setSchoolBoard10(academicDetails.getSchoolBoard10());
        academicDetailsDto.setSchoolMarks10(academicDetails.getSchoolMarks10());
        academicDetailsDto.setPassingYear10(academicDetails.getPassingYear10());
        academicDetailsDto.setSchoolBoard12(academicDetails.getSchoolBoard12());
        academicDetailsDto.setSchoolMarks12(academicDetails.getSchoolMarks12());
        academicDetailsDto.setPassingYear12(academicDetails.getPassingYear12());
        academicDetailsDto.setCollegeName(academicDetails.getCollegeName());
        academicDetailsDto.setDegree(academicDetails.getDegree());
        academicDetailsDto.setCoreSubject(academicDetails.getCoreSubject());
        academicDetailsDto.setCollegeMarks(academicDetails.getCollegeMarks());
        academicDetailsDto.setPassingYear(academicDetails.getPassingYear());
        return academicDetailsDto;
    }

    public static AcademicDetails mapToAcademicDetails(AcademicDetailsDto academicDetailsDto, AcademicDetails academicDetails) {
        //academicDetails.setId(academicDetailsDto.getId());
        academicDetails.setSchoolBoard10(academicDetailsDto.getSchoolBoard10());
        academicDetails.setSchoolMarks10(academicDetailsDto.getSchoolMarks10());
        academicDetails.setPassingYear10(academicDetailsDto.getPassingYear10());
        academicDetails.setSchoolBoard12(academicDetailsDto.getSchoolBoard12());
        academicDetails.setSchoolMarks12(academicDetailsDto.getSchoolMarks12());
        academicDetails.setPassingYear12(academicDetailsDto.getPassingYear12());
        academicDetails.setCollegeName(academicDetailsDto.getCollegeName());
        academicDetails.setDegree(academicDetailsDto.getDegree());
        academicDetails.setCoreSubject(academicDetailsDto.getCoreSubject());
        academicDetails.setCollegeMarks(academicDetailsDto.getCollegeMarks());
        academicDetails.setPassingYear(academicDetailsDto.getPassingYear());
        return academicDetails;
    }
}




