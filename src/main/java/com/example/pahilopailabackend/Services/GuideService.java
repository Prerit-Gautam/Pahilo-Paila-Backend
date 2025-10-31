package com.example.pahilopailabackend.Services;

import com.example.pahilopailabackend.Models.GuideModel;
import com.example.pahilopailabackend.Repositories.GuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuideService {

    @Autowired
    GuideRepository guideRepo;

    public List<GuideModel> getGuideList(Double lattitude, Double longtitude) {
        return guideRepo.getGuides(lattitude, longtitude);
    }
}