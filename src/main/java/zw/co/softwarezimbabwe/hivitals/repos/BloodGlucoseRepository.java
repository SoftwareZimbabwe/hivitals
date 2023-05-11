package zw.co.softwarezimbabwe.hivitals.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.softwarezimbabwe.hivitals.domain.BloodGlucose;


public interface BloodGlucoseRepository extends JpaRepository<BloodGlucose, Long> {
}
