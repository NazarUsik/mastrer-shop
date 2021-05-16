package com.nixsolutions.usik.service;

import com.nixsolutions.usik.constants.EntityConstants;
import com.nixsolutions.usik.exeptions.ResourceNotFoundException;
import com.nixsolutions.usik.model.entities.Client;
import com.nixsolutions.usik.model.entities.Role;
import com.nixsolutions.usik.model.repository.ClientRepository;
import com.nixsolutions.usik.model.service.RoleService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Log4j2
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ClientDetailsService implements UserDetailsService {

    @NonNull
    ClientRepository clientRepository;
    @NonNull
    RoleService roleService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        try {
            Client client = clientRepository.findByEmail(email).orElse(null);

            if (client == null) {
                UsernameNotFoundException exception = new UsernameNotFoundException(EntityConstants.CLIENT + " by email: {" + email + "} not found");
                log.error(exception.getMessage());
                throw exception;
            }

            Set<GrantedAuthority> roles = new HashSet<>();
            Role role = roleService.findById(
                    client.getRoleId());
            roles.add(new SimpleGrantedAuthority(
                    "ROLE_" + role.getName().toUpperCase()));
            return new User(client.getEmail(), client.getPassword(), roles);
        } catch (ResourceNotFoundException ex) {
            log.error(EntityConstants.ROLE + " not found");
            throw new UsernameNotFoundException(EntityConstants.ROLE + " not found");
        }
    }
}
