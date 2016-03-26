package com.performgroup.interview.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import com.performgroup.interview.dao.VideoDAO;
import com.performgroup.interview.dao.VideoReportingJDBCDAO;
import com.performgroup.interview.domain.Video;
import com.performgroup.interview.domain.VideoReportingBean;
import com.performgroup.interview.domain.VideoType;
import com.performgroup.interview.service.VideoService;

public class VideoServiceImpl implements VideoService {

	private VideoDAO videoDAO;

	private VideoReportingJDBCDAO videoReportingJDBCDAO;

	public VideoDAO getVideoDAO() {
		return videoDAO;
	}

	@Resource
	public void setVideoDAO(VideoDAO videoDAO) {
		this.videoDAO = videoDAO;
	}

	public VideoReportingJDBCDAO getVideoReportingJDBCDAO() {
		return videoReportingJDBCDAO;
	}

	@Resource
	public void setVideoReportingJDBCDAO(VideoReportingJDBCDAO videoReportingJDBCDAO) {
		this.videoReportingJDBCDAO = videoReportingJDBCDAO;
	}

	public void addVideo(Video video) {
		getVideoDAO().save(video);
	}

	public void deleteVideo(Video video) {
		getVideoDAO().delete(video);
	}

	public Video getVideo(Integer videoId) {
		return getVideoDAO().findById(videoId);
	}

	public Collection<Video> listVideos() {
		return getVideoDAO().findAll();
	}

	public Collection<VideoReportingBean> countByDay() {
		return videoReportingJDBCDAO.countByDay();
	}

	public Collection<VideoReportingBean> countByVideoType() {
		return videoReportingJDBCDAO.countByVideoType();
	}

	public VideoReportingBean countForVideoType(VideoType videoType) {
		return videoReportingJDBCDAO.countForVideoType(videoType);
	}

}
