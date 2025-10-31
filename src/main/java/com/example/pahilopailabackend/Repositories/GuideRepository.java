package com.example.pahilopailabackend.Repositories;
import com.example.pahilopailabackend.Models.GuideModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuideRepository extends JpaRepository<GuideModel, Long> {
    @Query(value = "SELECT g.*, " +
            "ROUND(SQRT(POWER(69.1 * (g.latitude - :latitude), 2) + " +
            "POWER(69.1 * (:longitude - g.longitude) * COS(g.latitude / 57.3), 2)), 2) AS distance " +
            "FROM guides g " +
            "ORDER BY distance ASC " +
            "LIMIT 20",
            nativeQuery = true)
    List<GuideModel> getGuides(@Param("latitude") Double latitude, @Param("longitude") Double longitude);

}






