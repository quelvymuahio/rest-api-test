package rest.api.demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import rest.api.demo.dao.TestDAO;
import rest.api.demo.model.Employee;

@Controller
@RequestMapping("/teste")
public class TestController {

	public final String HOME_VIEW = "home";
	
	@Autowired
	private TestDAO dao;
	
	@RequestMapping
	public ModelAndView home() throws Exception {
		
		HttpURLConnection connection = dao.save();
		List<Employee> employees = readFileAndConvertObject(connection);
		
		ModelAndView model = new ModelAndView(HOME_VIEW);
		
		model.addObject("employees", employees);
		return model;
	}
	
	@RequestMapping("/findAll")
	public ModelAndView findAll() throws Exception {
		
		HttpURLConnection connection = dao.findAll();
		List<Employee> employees = readFileAndConvertObject(connection);
		
		ModelAndView model = new ModelAndView(HOME_VIEW);
		
		model.addObject("employees", employees);
		return model;
	}
	
	
	public List<Employee> readFileAndConvertObject(HttpURLConnection connection) throws Exception {
		
		/*
		 * Lendo a resposta da requisicao feita
		 * */
		
		InputStream inputStream = connection.getInputStream();
		
		/* Conversao de Json para Lista de Objectos */
		ObjectMapper mapper = new ObjectMapper();
		CollectionType constructCollectionType = mapper.getTypeFactory().constructCollectionType(List.class, Employee.class);
		List<Employee> employees = mapper.readValue(inputStream, constructCollectionType);

		for (Employee employee : employees) {
			System.out.println(employee.getId());
		}
		
		/*
		 * Caso queira fazer a verificacao da resposta
		 * 
		 *	int responseCode = connection.getResponseCode();
		 *	InputStreamReader responseBody = null;
		 *	if(responseCode == HttpURLConnection.HTTP_OK) {
		 *		responseBody = new InputStreamReader(inputStream);
		 *	} else 
		 *	responseBody = new InputStreamReader(inputStream);
		 **/
		
		return employees;
		
	}
	
}
