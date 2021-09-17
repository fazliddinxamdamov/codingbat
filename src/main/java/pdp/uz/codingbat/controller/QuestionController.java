package pdp.uz.codingbat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.codingbat.dto.QuestionDto;
import pdp.uz.codingbat.dto.UserDto;
import pdp.uz.codingbat.model.Question;
import pdp.uz.codingbat.model.User;
import pdp.uz.codingbat.service.QuestionService;
import pdp.uz.codingbat.tool.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor

public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    public HttpEntity<List<Question>> getAll(){
        List<Question> all = questionService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> get(@PathVariable Long id){
        Question user = questionService.get(id);
        return ResponseEntity.status(user != null ? HttpStatus.OK : HttpStatus.CONFLICT).body(user);
    }

    @PostMapping()
    public HttpEntity<ApiResponse> save (@RequestBody QuestionDto dto){
        ApiResponse save = questionService.save(dto);
        return ResponseEntity.status(save.isSuccess() ? 201 : 409).body(save);
    }

    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> edit(@PathVariable Long id , @RequestBody QuestionDto dto){
        ApiResponse edit = questionService.edit(id , dto);
        return ResponseEntity.status(edit.isSuccess() ? 200 : 409).body(edit);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> del (@PathVariable Long id){
        ApiResponse del = questionService.del(id);
        return ResponseEntity.status(del.isSuccess() ? 204 : 409).body(del);
    }
}
