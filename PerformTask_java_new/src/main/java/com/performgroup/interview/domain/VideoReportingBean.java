package com.performgroup.interview.domain;

/**
 * A bean to contain reporting information. Only contains a number and a string
 * description.
 */
public class VideoReportingBean {

	private Integer count;
	private String description;

	public VideoReportingBean() {
		super();
	}

	public VideoReportingBean(Integer count, String description) {
		super();
		this.count = count;
		this.description = description;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "[count=" + count + ", description=" + description + "]";
	}

}
