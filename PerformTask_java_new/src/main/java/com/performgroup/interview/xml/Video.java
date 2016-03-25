package com.performgroup.interview.xml;

import java.util.ArrayList;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.performgroup.interview.domain.VideoType;

@XmlRootElement(name = "video")
@XmlAccessorType(XmlAccessType.NONE)
public class Video {
	@XmlAttribute(name = "id")
	private Integer id;

	@XmlElement(name = "title")
	private String title;

	@XmlElement(name = "type")
	private VideoType type;

	@XmlElement(name = "path")
	private String path;

	@XmlElementWrapper(name = "keywordSet")
	@XmlElement(name = "keyword", type = String.class)
	private ArrayList<String> keywordSet;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public VideoType getType() {
		return type;
	}

	public void setType(VideoType type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public ArrayList<String> getKeywordSet() {
		return keywordSet;
	}

	public void setKeywordSet(ArrayList<String> keywordSet) {
		this.keywordSet = keywordSet;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "Video [id=" + id + ", title=" + title + ", type=" + type + ", path=" + path + ", keywordSet="
				+ keywordSet.toString() + "]";
	}

}
