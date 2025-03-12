package az.security.bookstore.dao.repository;

import az.security.bookstore.dao.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {

}
