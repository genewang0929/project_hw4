package com.example.myproj.sight;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class KeelungSightsCrawler {
    private Document document;

    public Sight[] getItems(String zone) {
        ArrayList<Sight> sightArray = new ArrayList<Sight>();

        // Get zone and all the urls related to the zone
        SightHandler handler = new SightHandler();
        String region = handler.getZone(zone);
        ArrayList<String> URLs = handler.getURLs();

        for (String url : URLs)
            sightArray.add(setSight(url, region));

        // Convert the ArrayList to Sight[]
        Sight[] sights = new Sight[sightArray.size()];
        sights = sightArray.toArray(sights);
        return sights;
    }

    // Set the data of Sight

    public Sight setSight(String url, String region) {
        Sight sight = new Sight();
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements sightName = document.select("#point_area > h1 > span");
        sight.setSightName(sightName.text());

        sight.setZone(region);
        Elements category = document.select("#point_area > cite > span.point_type > span:nth-child(2) > strong");
        sight.setCategory(category.text());

        String photoURL = "";
        Elements imageElement = document.select("img");
        for (Element el : imageElement) {
            if (el.hasAttr("src")) {
                photoURL = el.attr("data-src");
                break;
            } else {
                photoURL = "";
            }
        }
        sight.setPhotoURL(photoURL);

        Elements description = document.getElementsByClass("text");
        sight.setDescription(description.text());

        Elements address = document.select("#point_data > div.address > p > a > span");
        sight.setAddress(address.text());

        return sight;
    }
}
