package rest.api.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rest.api.demo.model.Employee;
import rest.api.demo.service.EmployeeService;

@RestController
@RequestMapping("/company")
public class EmployeeController {
	
	@Autowired
	private EmployeeService eService;
	
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		return eService.save(employee);
	}
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return eService.findAll();
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") Long id) {
		
		Employee emp = eService.findOne(id);
		
		if (emp == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(emp);
		
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") Long id,@Valid @RequestBody Employee employee) {
		
		Employee emp = eService.findOne(id);
		
		if (emp == null) {
			return ResponseEntity.notFound().build();
		}
		
		emp.setName(employee.getName());
		emp.setDesignation(employee.getDesignation());
		emp.setExpertise(employee.getExpertise());

		Employee updatedEmployee = eService.save(emp);
		
		return ResponseEntity.ok().body(updatedEmployee);
		
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable(value="id") Long id) {
		
		Employee employee = eService.findOne(id);
		
		if (employee == null) {
			return ResponseEntity.notFound().build();
		}

		eService.delete(employee);
		
		return ResponseEntity.ok().build();
		
	}
	
}
