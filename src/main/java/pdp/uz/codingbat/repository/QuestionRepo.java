package pdp.uz.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.codingbat.model.Question;
import pdp.uz.codingbat.model.User;

public interface QuestionRepo extends JpaRepository<Question, Long> {

}
