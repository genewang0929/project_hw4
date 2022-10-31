package com.example.myproj.sight;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SightRepository extends MongoRepository<Sight, String> {
    List<Sight> findSightByZone(String zone);
    List<Sight> findSightBySightNameLike(String sightName);
}