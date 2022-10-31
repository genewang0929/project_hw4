package com.example.myproj.sight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class SightController {

    private final SightService sightService;

    @Autowired
    public SightController(SightService sightService) {
        this.sightService = sightService;
    }

    @GetMapping("/SightAPI")
    public List<Sight> getSight(
            @RequestParam(value = "zone", defaultValue = "") String name
    ) {
        return sightService.getAllSight(name);
    }

    @GetMapping("/SightAPI/search")
    public List<Sight> getSightBySearch(
            @RequestParam(value = "sightName", defaultValue = "") String name
    ) {
        return sightService.getSightBySightName(name);
    }
}
