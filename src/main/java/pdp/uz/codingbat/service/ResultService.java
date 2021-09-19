package pdp.uz.codingbat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdp.uz.codingbat.dto.ResultDto;
import pdp.uz.codingbat.dto.UserDto;
import pdp.uz.codingbat.model.Question;
import pdp.uz.codingbat.model.Result;
import pdp.uz.codingbat.model.User;
import pdp.uz.codingbat.repository.QuestionRepo;
import pdp.uz.codingbat.repository.ResultRepo;
import pdp.uz.codingbat.repository.UserRepo;
import pdp.uz.codingbat.tool.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ResultService {

    private final ResultRepo resultRepo;
    private final QuestionRepo questionRepo;
    private final UserRepo userRepo;


    public List<Result> getAll() {
        return resultRepo.findAll();
    }

    public Result get(Long id) {
        Optional<Result> optionalUser = resultRepo.findById(id);
        return optionalUser.orElse(null);
    }


    public ApiResponse save(ResultDto dto) {
        Optional<User> optionalUser = userRepo.findById(dto.getUserId());
        if (!optionalUser.isPresent()) return new ApiResponse("id not found" , false);

        Optional<Question> optionalQuestion = questionRepo.findById(dto.getQuestionId());
        if (!optionalQuestion.isPresent()) return new ApiResponse("id not found" , false);

        Result result = new Result();
        result.setUser(optionalUser.get());
        result.setQuestion(optionalQuestion.get());
        result.setResponse(dto.getResponse());
        result.setCorrect(dto.isCorrect());

        resultRepo.save(result);
        return new ApiResponse("save" , true);
    }

    public ApiResponse del(Long id) {
        try {
            resultRepo.deleteById(id);
            return new ApiResponse("delete" , true);
        }
        catch (Exception ignored){
            return new ApiResponse("error" , false);
        }
    }
}