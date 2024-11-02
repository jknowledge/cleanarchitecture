package de.jknowledge.cleanarchitecture.infrastructure.persistence.repository;

import de.jknowledge.cleanarchitecture.domain.aggregate.stock.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStockRepository extends JpaRepository<StockEntity, Long> {
}
