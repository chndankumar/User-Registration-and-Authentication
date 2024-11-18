package com.edtech.userRegistration.Mapper;

import com.edtech.userRegistration.Dto.AddressDto;
import com.edtech.userRegistration.Entity.Address;

public class AddressMapper {
    public static AddressDto mapToAddressDto(Address address, AddressDto addressDto) {
        //addressDto.setId(address.getId());
        addressDto.setHouseNumber(address.getHouseNumber());
        addressDto.setVillageMohalla(address.getVillageMohalla());
        addressDto.setPostOffice(address.getPostOffice());
        addressDto.setPoliceStation(address.getPoliceStation());
        addressDto.setCity(address.getCity());
        addressDto.setDistrict(address.getDistrict());
        addressDto.setPincode(address.getPincode());
        addressDto.setState(address.getState());
        return addressDto;
    }

    public static Address mapToAddress(AddressDto addressDto, Address address) {
        //address.setId(addressDto.getId());
        address.setHouseNumber(addressDto.getHouseNumber());
        address.setVillageMohalla(addressDto.getVillageMohalla());
        address.setPostOffice(addressDto.getPostOffice());
        address.setPoliceStation(addressDto.getPoliceStation());
        address.setCity(addressDto.getCity());
        address.setDistrict(addressDto.getDistrict());
        address.setPincode(addressDto.getPincode());
        address.setState(addressDto.getState());
        return address;
    }
}




