package zw.co.softwarezimbabwe.hivitals.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.softwarezimbabwe.hivitals.domain.Weight;


public interface WeightRepository extends JpaRepository<Weight, Long> {
}
