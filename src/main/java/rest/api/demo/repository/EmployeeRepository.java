package rest.api.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rest.api.demo.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
