package com.tenniscourts.schedules;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tenniscourts.exceptions.EntityNotFoundException;
import com.tenniscourts.tenniscourts.TennisCourtDTO;
import com.tenniscourts.tenniscourts.TennisCourtMapper;
import com.tenniscourts.tenniscourts.TennisCourtRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final ScheduleMapper scheduleMapper;
    
    private final TennisCourtRepository tennisCourtRepository;

     

    private final TennisCourtMapper tennisCourtMapper;
   

    public ScheduleDTO addSchedule(Long tennisCourtId, CreateScheduleRequestDTO createScheduleRequestDTO) {
    	TennisCourtDTO tennisCourtDTO =  tennisCourtRepository.findById(createScheduleRequestDTO.getTennisCourtId()).map(tennisCourtMapper::map).orElseThrow(() -> {
            throw new EntityNotFoundException("Tennis Court not found.");
        });
    	tennisCourtDTO.setTennisCourtSchedules(findSchedulesByTennisCourtId(createScheduleRequestDTO.getTennisCourtId()));
    	 
         ScheduleDTO scheduleDTO = new ScheduleDTO();
         scheduleDTO.setTennisCourt(tennisCourtDTO);
         scheduleDTO.setTennisCourtId(createScheduleRequestDTO.getTennisCourtId());
         scheduleDTO.setStartDateTime(createScheduleRequestDTO.getStartDateTime());
         scheduleDTO.setEndDateTime(createScheduleRequestDTO.getStartDateTime().plusMinutes(30));
         List<ScheduleDTO> scheduleDTOs = tennisCourtDTO.getTennisCourtSchedules();
         scheduleDTOs.add(scheduleDTO);
         tennisCourtDTO.setTennisCourtSchedules(scheduleDTOs);
         return scheduleMapper.map(scheduleRepository.saveAndFlush(scheduleMapper.map(scheduleDTO)));
    }

    public List<ScheduleDTO> findSchedulesByDates(LocalDateTime startDate, LocalDateTime endDate) {
        //TODO: implement
        return null;
    }

    public ScheduleDTO findSchedule(Long scheduleId) {
    	return scheduleMapper.map(scheduleRepository.findById(scheduleId).get());
    }

    public List<ScheduleDTO> findSchedulesByTennisCourtId(Long tennisCourtId) {
        return scheduleMapper.map(scheduleRepository.findByTennisCourt_IdOrderByStartDateTime(tennisCourtId));
    }
}
