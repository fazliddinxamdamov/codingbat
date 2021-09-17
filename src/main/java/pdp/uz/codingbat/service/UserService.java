package pdp.uz.codingbat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdp.uz.codingbat.dto.UserDto;
import pdp.uz.codingbat.model.User;
import pdp.uz.codingbat.repository.UserRepo;
import pdp.uz.codingbat.tool.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepo userRepo;


    public List<User> getAll() {
        return userRepo.findAll();
    }

    public User get(Long id) {
        Optional<User> optionalUser = userRepo.findById(id);
        return optionalUser.orElse(null);
    }


    public ApiResponse save(UserDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        userRepo.save(user);
        return new ApiResponse("save" , true);
    }

    public ApiResponse edit(Long id, UserDto dto) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (!optionalUser.isPresent()) return new ApiResponse("id not found"  , false);

        User user = optionalUser.get();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        userRepo.save(user);
        return new ApiResponse("edit" , true);
    }

    public ApiResponse del(Long id) {
        try {
            userRepo.deleteById(id);
            return new ApiResponse("delete" , true);
        }
        catch (Exception ignored){
            return new ApiResponse("error" , false);
        }
    }
}