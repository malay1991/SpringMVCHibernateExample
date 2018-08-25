package io.springmvc.controller;

import io.springmvc.model.StudentOne;
import io.springmvc.service.ServiceStudent;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MyCompanyOne {

	@Autowired
	ServiceStudent service;
	
	@Autowired
	MessageSource messageSource;

	/*
	 * List all existing Students.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listStudents(ModelMap model) {

		List<StudentOne> studentOnes = service.findAllStudents();
		model.addAttribute("students", studentOnes);
		return "allstudents";
	}

	/*
	 * Add a new StudentOne.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newStudent(ModelMap model) {
		StudentOne studentOne = new StudentOne();
		model.addAttribute("student", studentOne);
		model.addAttribute("edit", false);
		return "registration";
	}

	/*
	 * Handling POST request for validating the user input and saving StudentOne in database.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveStudent(@Valid StudentOne studentOne, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}
		
		if(!service.isStudentCodeUnique(studentOne.getId(), studentOne.getCode())){
			FieldError codeError =new FieldError("StudentOne","code",messageSource.getMessage("non.unique.code", new String[]{studentOne.getCode()}, Locale.getDefault()));
		    result.addError(codeError);
			return "registration";
		}
		
		service.saveStudent(studentOne);

		model.addAttribute("success", "StudentOne " + studentOne.getName() + " registered successfully");
		return "success";
	}


	/*
	 * Provide the existing StudentOne for updating.
	 */
	@RequestMapping(value = { "/edit-{code}-student" }, method = RequestMethod.GET)
	public String editStudent(@PathVariable String code, ModelMap model) {
		StudentOne studentOne = service.findStudentByCode(code);
		model.addAttribute("student", studentOne);
		model.addAttribute("edit", true);
		return "registration";
	}
	
	/*
	 * Handling POST request for validating the user input and updating StudentOne in database.
	 */
	@RequestMapping(value = { "/edit-{code}-student" }, method = RequestMethod.POST)
	public String updateStudent(@Valid StudentOne studentOne, BindingResult result,
			ModelMap model, @PathVariable String code) {

		if (result.hasErrors()) {
			return "registration";
		}

		if(!service.isStudentCodeUnique(studentOne.getId(), studentOne.getCode())){
			FieldError codeError =new FieldError("StudentOne","code",messageSource.getMessage("non.unique.code", new String[]{studentOne.getCode()}, Locale.getDefault()));
		    result.addError(codeError);
			return "registration";
		}

		service.updateStudent(studentOne);

		model.addAttribute("success", "StudentOne " + studentOne.getName()	+ " updated successfully");
		return "success";
	}

	
	/*
	 * Delete an StudentOne by it's CODE value.
	 */
	@RequestMapping(value = { "/delete-{code}-student" }, method = RequestMethod.GET)
	public String deleteStudent(@PathVariable String code) {
		service.deleteStudentByCode(code);
		return "redirect:/list";
	}

}
