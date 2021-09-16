package com.tenniscourts.guests;

import org.springframework.stereotype.Service;

import com.tenniscourts.exceptions.EntityNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GuestService {
	
	private GuestRepository guestRepository;
	
	private GuestMapper guestMapper;

	public GuestDTO createGuest(String name) {
		Guest guest = new Guest();
		guest.setName(name);
		
		return guestMapper.map(guestRepository.saveAndFlush(guest));
	}

	public GuestDTO updateGuest(String name, Long id) {
		Guest guest = new Guest();
		guest.setName(name);
		guest.setId(id);
		return guestMapper.map(guestRepository.saveAndFlush(guest));
	}

	public GuestDTO getById(long id) {
		return guestRepository.findById(id).map(guestMapper::map).orElseThrow(() -> {
            throw new EntityNotFoundException("Guest not found.");
        }); 
		 
	}

	public Long deleteGuest(long id) {
		guestRepository.deleteById(id);
		return id;
	}

	public GuestDTO getByName(String name) {
		 
		return guestMapper.map(guestRepository.findByName(name));
	}

}
