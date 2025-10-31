package com.example.pahilopailabackend.Services;

import com.example.pahilopailabackend.Models.GuideModel;
import com.example.pahilopailabackend.Repositories.GuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuideService {

    @Autowired
    GuideRepository guideRepo;

    public List<GuideModel> getGuideList(Double lattitude, Double longtitude) {
        return guideRepo.getGuides(lattitude, longtitude);
    }

    public GuideModel registerGuide(GuideModel guide) {
        // Check if email already exists
        Optional<GuideModel> existingGuide = guideRepo.findByEmail(guide.getEmail());
        if (existingGuide.isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        // Validate required fields
        if (guide.getName() == null || guide.getName().isEmpty()) {
            throw new RuntimeException("Name is required");
        }
        if (guide.getEmail() == null || guide.getEmail().isEmpty()) {
            throw new RuntimeException("Email is required");
        }
        if (guide.getPassword() == null || guide.getPassword().isEmpty()) {
            throw new RuntimeException("Password is required");
        }
        if (guide.getLatitude() == null || guide.getLongitude() == null) {
            throw new RuntimeException("Latitude and longitude are required");
        }
        if (guide.getRate() == null) {
            throw new RuntimeException("Rate is required");
        }

        // Save and return the guide
        return guideRepo.save(guide);
    }

    public GuideModel loginGuide(String email, String password) {
        // Find guide by email
        Optional<GuideModel> guide = guideRepo.findByEmail(email);

        if (guide.isEmpty()) {
            throw new RuntimeException("Guide not found");
        }

        GuideModel guideModel = guide.get();

        // Compare passwords (plain text for MVP - no security)
        if (!guideModel.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return guideModel;
    }
}