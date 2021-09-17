package pdp.uz.codingbat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.codingbat.dto.UserDto;
import pdp.uz.codingbat.model.User;
import pdp.uz.codingbat.service.UserService;
import pdp.uz.codingbat.tool.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @GetMapping
    public HttpEntity<List<User>> getAll(){
        List<User> all = userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id){
        User user = userService.get(id);
        return ResponseEntity.status(user != null ? HttpStatus.OK : HttpStatus.CONFLICT).body(user);
    }

    @PostMapping()
    public HttpEntity<ApiResponse> save (@RequestBody UserDto dto){
        ApiResponse save = userService.save(dto);
        return ResponseEntity.status(save.isSuccess() ? 201 : 409).body(save);
    }

    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> edit(@PathVariable Long id , @RequestBody UserDto dto){
        ApiResponse edit = userService.edit(id , dto);
        return ResponseEntity.status(edit.isSuccess() ? 200 : 409).body(edit);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> del (@PathVariable Long id){
       ApiResponse del = userService.del(id);
       return ResponseEntity.status(del.isSuccess() ? 204 : 409).body(del);
    }
}