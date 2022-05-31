package com.hit.product.applications.repositories;

import com.hit.product.domains.entities.EmailNotification;
import com.hit.product.domains.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmailNotificationRepository extends JpaRepository<EmailNotification, Long> {


}
