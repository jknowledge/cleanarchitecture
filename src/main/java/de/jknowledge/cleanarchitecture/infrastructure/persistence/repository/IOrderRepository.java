package de.jknowledge.cleanarchitecture.infrastructure.persistence.repository;

import de.jknowledge.cleanarchitecture.domain.aggregate.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
}
