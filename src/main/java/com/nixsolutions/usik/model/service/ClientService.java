package com.nixsolutions.usik.model.service;

import com.nixsolutions.usik.constants.EntityConstants;
import com.nixsolutions.usik.constants.FieldConstants;
import com.nixsolutions.usik.exeptions.ResourceNotFoundException;
import com.nixsolutions.usik.model.entities.Client;
import com.nixsolutions.usik.model.repository.ClientRepository;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
@Transactional
public class ClientService implements IService<Client> {
    @NonNull
    @Getter
    CrudRepository<Client, Long> repository;
    @NonNull
    PasswordEncoder encoder;

    @Override
    public void save(Client client) {
        if (client.getId() == 0 && !repository.existsById(client.getId())) {
            client.setPassword(encoder.encode(client.getPassword()));
        }
        repository.save(client);
    }

    public Client findByEmail(String email) {
        return ((ClientRepository) repository).findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException(
                        EntityConstants.CLIENT, FieldConstants.EMAIL, email));
    }
}
