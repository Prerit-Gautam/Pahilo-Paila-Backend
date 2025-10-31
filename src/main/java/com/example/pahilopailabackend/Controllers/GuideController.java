package com.example.pahilopailabackend.Controllers;

import com.example.pahilopailabackend.Models.GuideModel;
import com.example.pahilopailabackend.Services.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GuideController{
    @Autowired
    GuideService guideService;

    @GetMapping("/pahilopaila/getGuides/{lattitude}/{longitude}")
    public List<GuideModel> getGuide(@PathVariable("lattitude") double lattitude, @PathVariable("longitude") double longitude){
        return guideService.getGuideList(lattitude, longitude);
    }

}