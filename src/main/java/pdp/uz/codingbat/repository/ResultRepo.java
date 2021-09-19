package pdp.uz.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.codingbat.model.Help;
import pdp.uz.codingbat.model.Result;

public interface ResultRepo extends JpaRepository<Result, Long> {

}
