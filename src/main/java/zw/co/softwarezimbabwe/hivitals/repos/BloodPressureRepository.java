package zw.co.softwarezimbabwe.hivitals.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.softwarezimbabwe.hivitals.domain.BloodPressure;


public interface BloodPressureRepository extends JpaRepository<BloodPressure, Long> {
}
