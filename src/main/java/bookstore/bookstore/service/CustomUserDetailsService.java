package bookstore.bookstore.service;

import bookstore.bookstore.domain.AppUser;
import bookstore.bookstore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Yritetään löytää käyttäjä käyttäjänimellä: {}", username);

        AppUser appUser = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    logger.error("Käyttäjää ei löytynyt käyttäjänimellä: {}", username);
                    return new UsernameNotFoundException("Käyttäjää ei löytynyt käyttäjänimellä: " + username);
                });

        logger.info("Käyttäjä löytyi: {}. Rooli: {}", username, appUser.getRole());

        return User.builder()
            .username(appUser.getUsername())
            .password(appUser.getPassword())
            .authorities(AuthorityUtils.createAuthorityList(appUser.getRole().startsWith("ROLE_") ? appUser.getRole() : "ROLE_" + appUser.getRole()))
            .build();

    }
}
