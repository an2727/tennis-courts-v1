package com.tenniscourts.guests;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

	Guest findByName(String name);

}
