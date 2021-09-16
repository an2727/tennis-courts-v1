package com.tenniscourts.guests;
 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tenniscourts.config.BaseRestController; 

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/guest")
public class GuestContoller extends BaseRestController {
	
	 
	GuestService guestService;
	
	@PostMapping(path = "/create",consumes = "application/json")
    public ResponseEntity<Void> create(@RequestBody GuestDTO guestDTO) {
    	Long id = guestService.createGuest(guestDTO.getName()).getId();
    	 
        return ResponseEntity.created(locationByEntity(id)).build();
    }
	 
	
	@PutMapping(path = "/update",consumes = "application/json")
    public ResponseEntity<Long> update(@RequestBody GuestDTO guestDTO) {
    	Long id = guestService.updateGuest(guestDTO.getName(),guestDTO.getId()).getId();
    	 
        return ResponseEntity.ok(id);
    }
	
	@GetMapping(path = "/getById/{id}",produces =  "application/json")
    public ResponseEntity<GuestDTO> getById(@PathVariable long id) { 
        return ResponseEntity.ok(guestService.getById(id));
    }
	
	@GetMapping(path = "/getByName",produces = "application/json")
    public ResponseEntity<GuestDTO> getByName(@RequestParam String name) { 
        return ResponseEntity.ok(guestService.getByName(name));
    }
	
	@DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Long> delete(@PathVariable long id) { 
        return ResponseEntity.ok(guestService.deleteGuest(id));
    }
}
