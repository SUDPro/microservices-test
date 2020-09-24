package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
