package com.app.spring.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.app.spring.model.Course;
import com.app.spring.model.Student;

@Component
public class StudentDao {

	SessionFactory sessionFactory = null;
	// Dummy database. Initialize with some dummy values.
		private static List<Student> students;
		{
			//Prep work
			sessionFactory = HibernateAnnotationUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			
			//HQL example - Get All Employees
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Student");
			List<Student> studList = query.list();
			students = new ArrayList<Student>();
			for(Student stud : studList){
				Student s=new Student(stud.getStudentId(),stud.getStudentName(),stud.getAddress());
				students.add(s);
				List<Course> courseList=new ArrayList<Course>();
				for(Course c : stud.getCourses()) {
					courseList.add(new Course(c.getCourseId(), c.getCourseName(), c.getDuration(), c.getDescription()));
				}
				s.setCourses(courseList);
			}
			tx.commit();
			session.close();
		}

		/**
		 * Returns list of customers from dummy database.
		 * 
		 * @return list of customers
		 */
		public List<Student> listStudent() {
			return students;
		}

		/**
		 * Return customer object for given id from dummy database. If customer is
		 * not found for id, returns null.
		 * 
		 * @param id
		 *            customer id
		 * @return customer object for given id
		 */
		public Student get(Integer id) {

			/*for (Student s : students) {
				if (s.getStudentId().equals(id)) {
					return s;
				}
			}*/
			Session session = sessionFactory.openSession();
			//Transaction tx = session.beginTransaction();
			Student student=(Student) session.get(Student.class, id);
			
			for (Course course : student.getCourses()) {
				course.setStudent(null);
			}
			return student;
		}

		/**
		 * Create new customer in dummy database. Updates the id and insert new
		 * customer in list.
		 * 
		 * @param customer
		 *            Customer object
		 * @return customer object with updated id
		 */
		public Student create(Student student) {
			
			Session session = sessionFactory.openSession();
			
			//HQL example - Get All Employees
			Transaction tx = session.beginTransaction();
			session.persist(student);
			tx.commit();
			session.close();
			students.add(student);
			return student;
		}

		/**
		 * Delete the customer object from dummy database. If customer not found for
		 * given id, returns null.
		 * 
		 * @param id
		 *            the customer id
		 * @return id of deleted customer object
		 */
		public Integer delete(Integer id) {

			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Student student=(Student) session.get(Student.class, id);
			session.delete(student);
			tx.commit();
			session.close();
			
			for (Student c : students) {
				if (c.getStudentId().equals(id)) {
					students.remove(c);
					//return id;
				}
			}

			return id;
		}

		/**
		 * Update the customer object for given id in dummy database. If customer
		 * not exists, returns null
		 * 
		 * @param id
		 * @param student
		 * @return customer object with id
		 */
		public Student update(Integer id, Student student) {

			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Student student1=(Student) session.get(Student.class, id);
			student1 = student;
			for (Student c : students) {
				if (c.getStudentId().equals(id)) {
					student.setStudentId(c.getStudentId());
					students.remove(c);
					students.add(student);
					//return student;
				}
			}
			tx.commit();
			session.close();
			return student1;
		}
		
		public List<Student> getStudents(Integer courseId){
			
			//Prep work
			sessionFactory = HibernateAnnotationUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			
			//HQL example - Get All Employees
			Transaction tx = session.beginTransaction();
			Course courseList = (Course) session.get(Course.class, courseId);
			List<Student> studentList = new ArrayList<Student>();
			for(Student stud : courseList.getStudent()) {
					Student st=new Student(stud.getStudentId(),stud.getStudentName(),stud.getAddress());
					studentList.add(st);
			}
			tx.commit();
			session.close();
			
			return studentList;
		}
}
