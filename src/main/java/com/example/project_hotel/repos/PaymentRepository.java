package com.example.project_hotel.repos;

import com.example.project_hotel.models.Payment;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository  extends JpaRepository<Payment,Long> {
}
