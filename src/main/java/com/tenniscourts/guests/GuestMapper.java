package com.tenniscourts.guests;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GuestMapper {
	
	Guest map(GuestDTO guestDto);
	GuestDTO map(Guest guest);

}