package com.edtech.userRegistration.Service;

import com.edtech.userRegistration.Entity.Users;
import com.edtech.userRegistration.Model.UserPrincipal;
import com.edtech.userRegistration.Repository.UserRegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRegistrationRepo userRegistrationRepo;

    /**
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRegistrationRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found"));
        return new UserPrincipal(user);
    }
}
