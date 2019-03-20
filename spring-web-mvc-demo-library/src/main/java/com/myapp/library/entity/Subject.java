package com.myapp.library.entity;

import java.io.Serializable;
import java.util.Set;

/**
 * 
 * @author Admin
 *
 */
public class Subject implements Serializable {

	/**
	 * Generated serial version id.
	 */
	private static final long serialVersionUID = 9025558978911065102L;

	private long subjectId;

	private String subtitle;

	private int durationInHrs;

	private Set<Book> references;

	public Subject(long subjectId, String subtitle, int durationInHrs, Set<Book> references) {
		super();
		this.subjectId = subjectId;
		this.subtitle = subtitle;
		this.durationInHrs = durationInHrs;
		this.references = references;
	}

	public Subject() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", subtitle=" + subtitle + ", durationInHrs=" + durationInHrs
				+ ", references=" + references + "]";
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public int getDurationInHrs() {
		return durationInHrs;
	}

	public void setDurationInHrs(int durationInHrs) {
		this.durationInHrs = durationInHrs;
	}

	public Set<Book> getReferences() {
		return references;
	}

	public void setReferences(Set<Book> references) {
		this.references = references;
	}

}
