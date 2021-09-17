package pdp.uz.codingbat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdp.uz.codingbat.dto.QuestionDto;
import pdp.uz.codingbat.dto.UserDto;
import pdp.uz.codingbat.model.Question;
import pdp.uz.codingbat.model.Section;
import pdp.uz.codingbat.model.User;
import pdp.uz.codingbat.repository.QuestionRepo;
import pdp.uz.codingbat.repository.SectionRepo;
import pdp.uz.codingbat.repository.UserRepo;
import pdp.uz.codingbat.tool.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class QuestionService {

    private final QuestionRepo questionRepo;
    private final SectionRepo sectionRepo;


    public List<Question> getAll() {
        return questionRepo.findAll();
    }

    public Question get(Long id) {
        Optional<Question> optionalUser = questionRepo.findById(id);
        return optionalUser.orElse(null);
    }


    public ApiResponse save(QuestionDto dto) {

        Question question = new Question();
        question.setName(dto.getName());

        Optional<Section> optionalSection = sectionRepo.findById(dto.getSectionId());
        if (!optionalSection.isPresent()) return new ApiResponse("id not found" , false);

        question.setSection(optionalSection.get());

        questionRepo.save(question);
        return new ApiResponse("save" , true);
    }

    public ApiResponse edit(Long id, QuestionDto dto) {
        Optional<Question> optionalUser = questionRepo.findById(id);
        if (!optionalUser.isPresent()) return new ApiResponse("id not found"  , false);

        Question question = optionalUser.get();
        question.setName(dto.getName());

        Optional<Section> optionalSection = sectionRepo.findById(dto.getSectionId());
        if (!optionalSection.isPresent()) return new ApiResponse("id not found" , false);

        question.setSection(optionalSection.get());

        questionRepo.save(question);
        return new ApiResponse("edit" , true);
    }

    public ApiResponse del(Long id) {
        try {
            questionRepo.deleteById(id);
            return new ApiResponse("delete" , true);
        }
        catch (Exception ignored){
            return new ApiResponse("error" , false);
        }
    }
}
