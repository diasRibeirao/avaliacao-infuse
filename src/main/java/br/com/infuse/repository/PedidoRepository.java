package br.com.infuse.repository;

import br.com.infuse.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Optional<Pedido> findByNumeroControle(String numeroControle);

    List<Pedido> findAllByDataCadastroBetween(LocalDateTime startDate, LocalDateTime endDate);

}