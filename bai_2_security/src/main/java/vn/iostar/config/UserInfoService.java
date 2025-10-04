package vn.iostar.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.iostar.entity.UserInfo;
import vn.iostar.repository.UserInfoRepository;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = repository.findByUsername(username);

        return userInfo
                .map(UserInfoDetails::new)  // UserInfoDetails implements UserDetails
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
