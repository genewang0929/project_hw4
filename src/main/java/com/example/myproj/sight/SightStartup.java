package com.example.myproj.sight;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SightStartup{

    @Bean
    CommandLineRunner commandLineRunner(SightRepository sightRepository) {
        return args -> {
            sightRepository.deleteAll();

            String[] keelungSights = {"七堵區", "中山區", "中正區", "仁愛區", "安樂區", "信義區", "暖暖區"};
            KeelungSightsCrawler crawler = new KeelungSightsCrawler();
            for (String keelungSight : keelungSights) {
                Sight[] sights = crawler.getItems(keelungSight);
                for (Sight sight : sights)
                    sightRepository.insert(sight);
            }
        };
    }
}
