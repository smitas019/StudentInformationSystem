/**
 * 
 */
package com.app.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.app.spring.dao.StudentDao;
import com.app.spring.model.Student;

/**
 * @author suhas
 *
 */

@RestController
public class StudentRestController {

	
	@Autowired
	private StudentDao studentDao;
	
	@GetMapping("/students")
	public List<Student> getStudents() {
		return studentDao.listStudent();
	}
	@GetMapping("/app")
	public String displayBasePage() {
		ModelAndView modelAndView = new ModelAndView("Student.jsp");
	    modelAndView.addObject("message", "Baeldung");
	    return "<!DOCTYPE html>\r\n" + 
	    		"<html>\r\n" + 
	    		"    <head>\r\n" + 
	    		"        <title>Hello jQuery</title>\r\n" + 
	    		"        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js\"></script>\r\n" + 
	    		"        <!--  <script src=\"hello.js\"></script> -->\r\n" + 
	    		"    </head>\r\n" + 
	    		"<script>\r\n" + 
	    		"function myFunction() {\r\n" + 
	    		"	$.ajax({\r\n" + 
	    		"	    url: 'http://localhost:8080/StudentInfoSystem/addStudent/',\r\n" + 
	    		"	    type: 'POST',\r\n" + 
	    		"	    data: $('#myform').serializeArray(),\r\n" + 
	    		"	    success: function() { alert('POST completed'); }\r\n" + 
	    		"	});\r\n" + 
	    		"}\r\n" + 
	    		"</script>\r\n" + 
	    		"    <body>\r\n" + 
	    		"    <form Method=\"POST\"  onsubmit=\"myFunction()\" name=\"myForm\">\r\n" + 
	    		"        <div>\r\n" + 
	    		"        Student Name :\r\n" + 
	    		"          <input type=\"text\" name=\"studentName\" class=\"studentName\"><br>\r\n" + 
	    		"         Address :\r\n" + 
	    		"          <input type=\"text\" name=\"address\" class=\"address\"><br>\r\n" + 
	    		"          Courses :\r\n" + 
	    		"          <select name=\"courses\" class =\"courses\">\r\n" + 
	    		"			  <option value=\"java\">Java</option>\r\n" + 
	    		"			  <option value=\"dot net\">Dot Net</option>\r\n" + 
	    		"			  <option value=\"database\">Database</option>\r\n" + 
	    		"			  <option value=\"php\">php</option>\r\n" + 
	    		"		</select>\r\n" + 
	    		"		<input type=\"submit\" value=\"Submit\">\r\n" + 
	    		"        </div>\r\n" + 
	    		"        </form>\r\n" + 
	    		"    </body>\r\n" + 
	    		"</html>";
	}
	@GetMapping("/students/{studentId}")
	public ResponseEntity getStudent(@PathVariable("studentId") Integer studentId) {

		Student student = studentDao.get(studentId);
		if (student == null) {
			return new ResponseEntity("No Student found for ID " + studentId, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(student, HttpStatus.OK);
	}

	//@PostMapping(path="/addStudent")
	@PostMapping("/save")
	/*@RequestMapping(
			  value = "/addStudent", 
			  produces = "application/json", 
			  method = {RequestMethod.PUT})*/
	public ResponseEntity addStudent(@RequestBody Student student) {

		studentDao.create(student);

		return new ResponseEntity(student, HttpStatus.OK);
	}

	@DeleteMapping("/students/{studentId}")
	public ResponseEntity deleteStudent(@PathVariable Integer studentId) {

		if (null == studentDao.delete(studentId)) {
			return new ResponseEntity("No Student found for ID " + studentId, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(studentId, HttpStatus.OK);

	}
	
	@PutMapping("/students/{studentId}")
	public ResponseEntity updateStudent(@PathVariable Integer studentId, @RequestBody Student student) {

		student = studentDao.update(studentId, student);
		
		if (null == student) {
			return new ResponseEntity("No Student found for ID " + studentId, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(student, HttpStatus.OK);
	}

	@GetMapping("/courses/{courseId}")
	public List<Student> getStudents(@PathVariable("courseId") Integer courseId) {
		return studentDao.getStudents(courseId);
	}
}
