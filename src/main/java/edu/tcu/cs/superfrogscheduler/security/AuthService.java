package edu.tcu.cs.superfrogscheduler.security;

import edu.tcu.cs.superfrogscheduler.user.MyUserPrincipal;
import edu.tcu.cs.superfrogscheduler.user.SchedulerUser;
import edu.tcu.cs.superfrogscheduler.user.converter.UserToUserDtoConverter;
import edu.tcu.cs.superfrogscheduler.user.dto.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final JwtProvider jwtProvider;

    private final UserToUserDtoConverter userToUserDtoConverter;

//    public AuthService(JwtProvider jwtProvider, UserToUserDtoConverter userToUserDtoConverter) {
//        this.jwtProvider = jwtProvider;
//        this.userToUserDtoConverter = userToUserDtoConverter;
//    }
        public AuthService(JwtProvider jwtProvider, UserToUserDtoConverter userToUserDtoConverter) {
        this.jwtProvider = jwtProvider;
            this.userToUserDtoConverter = userToUserDtoConverter;
        }

    public Map<String, Object> createLoginInfo(Authentication authentication) {
        // Create user info.
        MyUserPrincipal principal = (MyUserPrincipal)authentication.getPrincipal();
        SchedulerUser schedulerUser = principal.getUser();
        UserDto userDto = this.userToUserDtoConverter.convert(schedulerUser);
        // Create a JWT.
        String token = this.jwtProvider.createToken(authentication);
        //String token = "";

        Map<String, Object> loginResultMap = new HashMap<>();

        loginResultMap.put("userInfo", userDto);
        loginResultMap.put("token", token);

        return loginResultMap;
    }

}
