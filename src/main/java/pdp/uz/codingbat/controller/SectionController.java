package pdp.uz.codingbat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.codingbat.dto.QuestionDto;
import pdp.uz.codingbat.dto.SectionDto;
import pdp.uz.codingbat.model.Question;
import pdp.uz.codingbat.model.Section;
import pdp.uz.codingbat.service.QuestionService;
import pdp.uz.codingbat.service.SectionService;
import pdp.uz.codingbat.tool.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/section")
@RequiredArgsConstructor
public class SectionController {

    private final SectionService sectionService;

    @GetMapping
    public HttpEntity<List<Section>> getAll(){
        List<Section> all = sectionService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Section> get(@PathVariable Long id){
        Section user = sectionService.get(id);
        return ResponseEntity.status(user != null ? HttpStatus.OK : HttpStatus.CONFLICT).body(user);
    }

    @PostMapping()
    public HttpEntity<ApiResponse> save (@RequestBody SectionDto dto){
        ApiResponse save = sectionService.save(dto);
        return ResponseEntity.status(save.isSuccess() ? 201 : 409).body(save);
    }

    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> edit(@PathVariable Long id , @RequestBody SectionDto dto){
        ApiResponse edit = sectionService.edit(id , dto);
        return ResponseEntity.status(edit.isSuccess() ? 200 : 409).body(edit);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> del (@PathVariable Long id){
        ApiResponse del = sectionService.del(id);
        return ResponseEntity.status(del.isSuccess() ? 204 : 409).body(del);
    }
}
