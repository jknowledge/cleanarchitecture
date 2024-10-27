package de.jknowledge.cleanarchitecture.repository;

import de.jknowledge.cleanarchitecture.domain.aggregate.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
