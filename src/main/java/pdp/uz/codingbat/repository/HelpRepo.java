package pdp.uz.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.codingbat.model.Help;
import pdp.uz.codingbat.model.User;

public interface HelpRepo extends JpaRepository<Help, Long> {

}
