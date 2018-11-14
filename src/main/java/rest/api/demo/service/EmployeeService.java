package rest.api.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.api.demo.model.Employee;
import rest.api.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;
	
	public Employee save(Employee employee) {
		return repository.save(employee);
	}
	
	public List<Employee> findAll() {
		return repository.findAll();
	}
	
	public Employee findOne(Long id) {
		return repository.findOne(id);
	}
	
	public void delete(Employee employee) {
		repository.delete(employee);
	}
	
}
