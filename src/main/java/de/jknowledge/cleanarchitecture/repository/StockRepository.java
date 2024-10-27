package de.jknowledge.cleanarchitecture.repository;

import de.jknowledge.cleanarchitecture.domain.aggregate.stock.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {
}
