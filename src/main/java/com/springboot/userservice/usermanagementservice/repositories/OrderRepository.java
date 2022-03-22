package com.springboot.userservice.usermanagementservice.repositories;
import com.springboot.userservice.usermanagementservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
