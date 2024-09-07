package org.javamp.module4.repository;

import org.javamp.module4.data.SecretData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretsRepository extends JpaRepository<SecretData, String> {

}
