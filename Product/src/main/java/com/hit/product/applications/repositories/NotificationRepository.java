package com.hit.product.applications.repositories;

import com.hit.product.domains.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
