package pdp.uz.codingbat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdp.uz.codingbat.dto.HelpDto;
import pdp.uz.codingbat.dto.SectionDto;
import pdp.uz.codingbat.model.Help;
import pdp.uz.codingbat.model.Language;
import pdp.uz.codingbat.model.Section;
import pdp.uz.codingbat.repository.HelpRepo;
import pdp.uz.codingbat.repository.LanguageRepo;
import pdp.uz.codingbat.repository.SectionRepo;
import pdp.uz.codingbat.tool.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class HelpService {

    private final HelpRepo helpRepo;
    private final LanguageRepo languageRepo;


    public List<Help> getAll() {
        return helpRepo.findAll();
    }

    public Help get(Long id) {
        Optional<Help> optionalUser = helpRepo.findById(id);
        return optionalUser.orElse(null);
    }

    public ApiResponse save(HelpDto dto) {

        Optional<Language> optionalLanguage = languageRepo.findById(dto.getLanguagesId());
        if (!optionalLanguage.isPresent()) return new ApiResponse("id not found" , false);

        Help section = new Help();
        section.setName(dto.getName());
        section.setLanguage(optionalLanguage.get());
        section.setText(dto.getText());

        helpRepo.save(section);
        return new ApiResponse("save" , true);
    }

    public ApiResponse edit(Long id, HelpDto dto) {
        Optional<Help> optionalUser = helpRepo.findById(id);
        if (!optionalUser.isPresent()) return new ApiResponse("id not found"  , false);

        Optional<Language> optionalLanguage = languageRepo.findById(dto.getLanguagesId());
        if (!optionalLanguage.isPresent()) return new ApiResponse("id not found" , false);

        Help section = optionalUser.get();
        section.setName(dto.getName());
        section.setLanguage(optionalLanguage.get());
        section.setText(dto.getText());

        helpRepo.save(section);
        return new ApiResponse("edit" , true);
    }

    public ApiResponse del(Long id) {
        try {
            helpRepo.deleteById(id);
            return new ApiResponse("delete" , true);
        }
        catch (Exception ignored){
            return new ApiResponse("error" , false);
        }
    }
}