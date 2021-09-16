package com.tenniscourts.reservations;

import com.tenniscourts.config.BaseRestController;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("reservation")
public class ReservationController extends BaseRestController {

    private final ReservationService reservationService;

    @PostMapping(path = "/create",consumes = "application/json")
    public ResponseEntity<Void> bookReservation(@RequestBody CreateReservationRequestDTO createReservationRequestDTO) {
    	Long id = reservationService.bookReservation(createReservationRequestDTO).getId();
    	 
        return ResponseEntity.created(locationByEntity(id)).build();
    }
    
    @RequestMapping(value="/find/{reservationId}",method = RequestMethod.GET, produces =   "application/json" )
    public ResponseEntity<ReservationDTO> findReservation(@PathVariable Long reservationId) {
        return ResponseEntity.ok(reservationService.findReservation(reservationId));
    }
    
    @RequestMapping(value="/cancel/{reservationId}",method = RequestMethod.GET, produces =   "application/json" )
    public ResponseEntity<ReservationDTO> cancelReservation(@PathVariable Long reservationId) {
        return ResponseEntity.ok(reservationService.cancelReservation(reservationId));
    }
    @RequestMapping(value="/reschedule/{reservationId}/{scheduledId}",method = RequestMethod.GET, produces =   "application/json" )
    public ResponseEntity<ReservationDTO> rescheduleReservation(@PathVariable Long reservationId, @PathVariable Long scheduledId) {
        return ResponseEntity.ok(reservationService.rescheduleReservation(reservationId, scheduledId));
    }
}
