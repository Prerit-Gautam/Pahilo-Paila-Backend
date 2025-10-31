package com.example.pahilopailabackend.Controllers;

import com.example.pahilopailabackend.Models.GuideModel;
import com.example.pahilopailabackend.Services.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pahilopaila")
public class GuideController {

    @Autowired
    GuideService guideService;

    @GetMapping("/getGuides/{lattitude}/{longitude}")
    public List<GuideModel> getGuide(@PathVariable("lattitude") double lattitude, @PathVariable("longitude") double longitude) {
        return guideService.getGuideList(lattitude, longitude);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerGuide(@RequestBody GuideModel guide) {
        try {
            GuideModel registeredGuide = guideService.registerGuide(guide);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of(
                            "success", true,
                            "message", "Guide registered successfully",
                            "guide", registeredGuide
                    ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "success", false,
                            "message", e.getMessage()
                    ));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginGuide(@RequestBody Map<String, String> loginRequest) {
        try {
            String email = loginRequest.get("email");
            String password = loginRequest.get("password");

            GuideModel guide = guideService.loginGuide(email, password);
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Login successful",
                    "guide", guide
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                            "success", false,
                            "message", e.getMessage()
                    ));
        }
    }
}