package com.performgroup.interview.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 * A POJO representing a video in the Perform system.
 */

@Entity
@SequenceGenerator(name = "VID_SEQ_GEN", sequenceName = "VIDSEQ", allocationSize = 1)
public class Video implements Serializable {

	private static final long serialVersionUID = 2284488937952510797L;

	private Long id;
	private String title;
	private String videoPath;
	private VideoType videoType;
	private Date creationDate;
	private Set<VideoCategory> categories = new HashSet<VideoCategory>(0);

	// Default Constructor
	public Video() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VID_SEQ_GEN")
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "title", nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "video_path", nullable = false)
	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}

	@Column(name = "video_type", nullable = false)
	@Enumerated(value = EnumType.STRING)
	public VideoType getVideoType() {
		return videoType;
	}

	public void setVideoType(VideoType videoType) {
		this.videoType = videoType;
	}

	@Column(name = "creation_date", nullable = false)
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "video", cascade = CascadeType.ALL)
	public Set<VideoCategory> getCategories() {
		return categories;
	}

	public void setCategories(Set<VideoCategory> categories) {
		this.categories = categories;
	}

}
