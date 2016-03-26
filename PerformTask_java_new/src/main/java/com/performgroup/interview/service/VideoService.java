package com.performgroup.interview.service;

import java.util.Collection;

import com.performgroup.interview.domain.Video;
import com.performgroup.interview.domain.VideoReportingBean;
import com.performgroup.interview.domain.VideoType;

/**
 * A service layer to retrieve video information
 */
public interface VideoService {

	Video getVideo(Integer videoId);

	Collection<Video> listVideos();

	void addVideo(Video video);

	void deleteVideo(Video video);

	public Collection<VideoReportingBean> countByDay();

	public Collection<VideoReportingBean> countByVideoType();

	VideoReportingBean countForVideoType(VideoType videoType);

}
