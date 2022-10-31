package com.example.myproj.sight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SightService {
    private final SightRepository sightRepository;

    @Autowired
    public SightService(SightRepository sightRepository) {
        this.sightRepository = sightRepository;
    }

    public List<Sight> getAllSight(String zone) {
        return sightRepository.findSightByZone(zone);
    }

    public List<Sight> getSightBySightName(String zone) {
        return sightRepository.findSightBySightNameLike(zone);
    }
}
