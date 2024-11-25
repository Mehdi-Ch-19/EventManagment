package net.chiheb.eventmanagment.Repository;

import net.chiheb.eventmanagment.Entity.mail.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
    @Transactional
    @Modifying
    @Query(value = "UPDATE ConfirmationToken c SET c.confirmedAt = ?2 WHERE c.confirmationToken = ?1",nativeQuery = false)
    int updateConfirmedAt(String token, LocalDateTime localDateTime);
}
