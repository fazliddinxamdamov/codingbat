package pdp.uz.codingbat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdp.uz.codingbat.dto.SectionDto;
import pdp.uz.codingbat.dto.UserDto;
import pdp.uz.codingbat.model.Language;
import pdp.uz.codingbat.model.Section;
import pdp.uz.codingbat.model.User;
import pdp.uz.codingbat.repository.LanguageRepo;
import pdp.uz.codingbat.repository.SectionRepo;
import pdp.uz.codingbat.repository.UserRepo;
import pdp.uz.codingbat.tool.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class SectionService {

    private final SectionRepo sectionRepo;
    private final LanguageRepo languageRepo;


    public List<Section> getAll() {
        return sectionRepo.findAll();
    }

    public Section get(Long id) {
        Optional<Section> optionalUser = sectionRepo.findById(id);
        return optionalUser.orElse(null);
    }

    public ApiResponse save(SectionDto dto) {

        Optional<Language> optionalLanguage = languageRepo.findById(dto.getLanguagesId());
        if (!optionalLanguage.isPresent()) return new ApiResponse("id not found" , false);

        Section section = new Section();
        section.setName(dto.getName());
        section.setLanguages(optionalLanguage.get());
        section.setDescription(dto.getDescription());

        sectionRepo.save(section);
        return new ApiResponse("save" , true);
    }

    public ApiResponse edit(Long id, SectionDto dto) {
        Optional<Section> optionalUser = sectionRepo.findById(id);
        if (!optionalUser.isPresent()) return new ApiResponse("id not found"  , false);

        Optional<Language> optionalLanguage = languageRepo.findById(dto.getLanguagesId());
        if (!optionalLanguage.isPresent()) return new ApiResponse("id not found" , false);

        Section section = optionalUser.get();
        section.setName(dto.getName());
        section.setLanguages(optionalLanguage.get());
        section.setDescription(dto.getDescription());

        sectionRepo.save(section);
        return new ApiResponse("edit" , true);
    }

    public ApiResponse del(Long id) {
        try {
            sectionRepo.deleteById(id);
            return new ApiResponse("delete" , true);
        }
        catch (Exception ignored){
            return new ApiResponse("error" , false);
        }
    }
}