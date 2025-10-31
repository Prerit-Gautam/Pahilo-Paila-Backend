package com.example.pahilopailabackend.Repositories;
import com.example.pahilopailabackend.Models.GuideModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuideRepository extends JpaRepository<GuideModel, Long> {
    @Query(value = "SELECT g.id, g.name, g.email, g.password, g.latitude, g.longitude, g.rate " +
            "FROM guides g " +
            "ORDER BY SQRT(POWER(69.1 * (g.latitude - :latitude), 2) + " +
            "POWER(69.1 * (:longitude - g.longitude) * COS(RADIANS(g.latitude)), 2)) ASC " +
            "LIMIT 20",
            nativeQuery = true)
    List<GuideModel> getGuides(@Param("latitude") Double latitude, @Param("longitude") Double longitude);

    Optional<GuideModel> findByEmail(String email);
}