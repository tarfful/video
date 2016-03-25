package com.performgroup.interview.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "VID_CAT_SEQ_GEN", sequenceName = "VIDCATSEQ", allocationSize = 1)
public class VideoCategory {

	private static final long serialVersionUID = 2284488937952510799L;

	private Long id;
	private String categoryName;
	private Video video;

	public VideoCategory() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VID_CAT_SEQ_GEN")
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "category_name", unique = false, nullable = false)
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@ManyToOne
	@JoinColumn(name = "video_id", nullable = false)
	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	@Override
	public String toString() {
		return categoryName;
	}

}
