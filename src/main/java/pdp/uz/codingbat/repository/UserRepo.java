package pdp.uz.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.codingbat.model.User;

public interface UserRepo extends JpaRepository<User , Long> {

}
