package com.kalanso.event.Repository;

import com.kalanso.event.Model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Notification_repo extends JpaRepository<Notification, Integer> {
}
