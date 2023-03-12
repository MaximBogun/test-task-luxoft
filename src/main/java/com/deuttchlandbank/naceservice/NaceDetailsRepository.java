package com.deuttchlandbank.naceservice;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NaceDetailsRepository extends JpaRepository<NaceDetails, Long> {

    Optional<NaceDetails> findByCode(String code);

}
