package br.com.diegoplaninscheck.n3_seguranca_backend.Repository;

import br.com.diegoplaninscheck.n3_seguranca_backend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Long> {

}
