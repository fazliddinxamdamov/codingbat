package pdp.uz.codingbat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.codingbat.dto.ResultDto;
import pdp.uz.codingbat.dto.UserDto;
import pdp.uz.codingbat.model.Result;
import pdp.uz.codingbat.model.User;
import pdp.uz.codingbat.service.ResultService;
import pdp.uz.codingbat.service.UserService;
import pdp.uz.codingbat.tool.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/result")
@RequiredArgsConstructor

public class ResultController {

    private final ResultService resultService;

    @GetMapping
    public HttpEntity<List<Result>> getAll(){
        List<Result> all = resultService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> get(@PathVariable Long id){
        Result user = resultService.get(id);
        return ResponseEntity.status(user != null ? HttpStatus.OK : HttpStatus.CONFLICT).body(user);
    }

    @PostMapping()
    public HttpEntity<ApiResponse> save (@RequestBody ResultDto dto){
        ApiResponse save = resultService.save(dto);
        return ResponseEntity.status(save.isSuccess() ? 201 : 409).body(save);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> del (@PathVariable Long id){
       ApiResponse del = resultService.del(id);
       return ResponseEntity.status(del.isSuccess() ? 204 : 409).body(del);
    }
}