/**
 * 
 */
package com.app.spring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author suhas
 *
 */
@Entity
@Table(name = "Course")
public class Course {

	@Id
	@GeneratedValue
	@Column(name = "courseId")
	private Integer courseId;
	
	private String courseName;
	
	private Integer duration;
	
	private String courseDesc;

	@ManyToMany(mappedBy = "courses",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	//@JoinColumn(name = "studentId")
	private List<Student> student = new ArrayList<Student>();
	/**
	 * @return the student
	 */
	public List<Student> getStudent() {
		return student;
	}

	/*
	 * @param student the student to set
	 */
	public void setStudent(List<Student> student) {
		this.student = student;
	}

	/**
	 * @return the courseId
	 */
	public Integer getCourseId() {
		return courseId;
	}

	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * @return the duration
	 */
	public Integer getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return courseDesc;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.courseDesc = description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseId == null) ? 0 : courseId.hashCode());
		result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
		result = prime * result + ((courseDesc == null) ? 0 : courseDesc.hashCode());
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (courseId == null) {
			if (other.courseId != null)
				return false;
		} else if (!courseId.equals(other.courseId))
			return false;
		if (courseName == null) {
			if (other.courseName != null)
				return false;
		} else if (!courseName.equals(other.courseName))
			return false;
		if (courseDesc == null) {
			if (other.courseDesc != null)
				return false;
		} else if (!courseDesc.equals(other.courseDesc))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		return true;
	}
	
	public Course(){
		
	}

	/**
	 * @param courseId
	 * @param courseName
	 * @param duration
	 * @param courseDesc
	 * @param student
	 */
	public Course(Integer courseId, String courseName, Integer duration, String courseDesc) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.duration = duration;
		this.courseDesc = courseDesc;
	}
	
}
