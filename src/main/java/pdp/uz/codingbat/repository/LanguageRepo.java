package pdp.uz.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.codingbat.model.Language;
import pdp.uz.codingbat.model.User;

public interface LanguageRepo extends JpaRepository<Language, Long> {

}
