package pdp.uz.codingbat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.codingbat.dto.HelpDto;
import pdp.uz.codingbat.dto.SectionDto;
import pdp.uz.codingbat.model.Help;
import pdp.uz.codingbat.model.Section;
import pdp.uz.codingbat.service.HelpService;
import pdp.uz.codingbat.service.SectionService;
import pdp.uz.codingbat.tool.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/help")
@RequiredArgsConstructor
public class HelpController {

    private final HelpService helpService;

    @GetMapping
    public HttpEntity<List<Help>> getAll(){
        List<Help> all = helpService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Help> get(@PathVariable Long id){
        Help user = helpService.get(id);
        return ResponseEntity.status(user != null ? HttpStatus.OK : HttpStatus.CONFLICT).body(user);
    }

    @PostMapping()
    public HttpEntity<ApiResponse> save (@RequestBody HelpDto dto){
        ApiResponse save = helpService.save(dto);
        return ResponseEntity.status(save.isSuccess() ? 201 : 409).body(save);
    }

    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> edit(@PathVariable Long id , @RequestBody HelpDto dto){
        ApiResponse edit = helpService.edit(id , dto);
        return ResponseEntity.status(edit.isSuccess() ? 200 : 409).body(edit);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> del (@PathVariable Long id){
        ApiResponse del = helpService.del(id);
        return ResponseEntity.status(del.isSuccess() ? 204 : 409).body(del);
    }
}
