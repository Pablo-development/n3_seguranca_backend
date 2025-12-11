package br.com.diegoplaninscheck.n3_seguranca_backend.Repository;

import br.com.diegoplaninscheck.n3_seguranca_backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
