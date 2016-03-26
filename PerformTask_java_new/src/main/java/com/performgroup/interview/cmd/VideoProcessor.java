package com.performgroup.interview.cmd;

import java.io.InputStream;
import java.util.Collection;

import javax.annotation.Resource;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.StringUtils;

import com.performgroup.interview.domain.Video;
import com.performgroup.interview.domain.VideoReportingBean;
import com.performgroup.interview.domain.VideoType;
import com.performgroup.interview.service.VideoService;
import com.performgroup.interview.xml.VideoConverter;

public class VideoProcessor {

	private transient VideoService videoService;

	private transient Jaxb2Marshaller jaxb2Marshaller;

	private transient String videoIngestFolder;

	@Resource
	public void setVideoService(VideoService videoService) {
		this.videoService = videoService;
	}

	@Resource
	public void setJaxb2Marshaller(Jaxb2Marshaller jaxb2Marshaller) {
		this.jaxb2Marshaller = jaxb2Marshaller;
	}

	public void setVideoIngestFolder(String videoIngestFolder) {
		this.videoIngestFolder = videoIngestFolder;
	}

	/**
	 * Outputs video details to the specified logger
	 * 
	 * @param logger
	 */
	public void listVideos(Logger logger) {

		Collection<Video> videos = videoService.listVideos();

		for (Video video : videos) {
			String videoData = String.format("[%d] - %s / %s - %s - [%s] ", video.getId(), video.getTitle(),
					video.getVideoType(), video.getVideoPath(),
					StringUtils.collectionToCommaDelimitedString(video.getCategories()));
			logger.info(videoData);
		}
	}

	/**
	 * 
	 * Count videos by creation date
	 * 
	 * @param logger
	 */
	public void countByDay(Logger logger) {
		Collection<VideoReportingBean> countByVideoType = videoService.countByDay();
		logger.info(String.format("Viedos report by day: %s",
				StringUtils.collectionToCommaDelimitedString(countByVideoType)));

	}

	/**
	 * 
	 * Count videos by type report
	 * 
	 * @param logger
	 */
	public void countByVideoType(Logger logger) {
		Collection<VideoReportingBean> countByVideoType = videoService.countByVideoType();
		logger.info(String.format("Viedos report by type: %s",
				StringUtils.collectionToCommaDelimitedString(countByVideoType)));

	}

	/**
	 * 
	 * Count videos by type
	 * 
	 * @param logger
	 * @param type
	 */
	public void countForVideoType(Logger logger, String type) {
		switch (type) {
		case "FRUIT_MATCH":
		case "VEGETABLE_MATCH":
		case "DRINK_MATCH":
			VideoReportingBean countForVideoType = videoService.countForVideoType(VideoType.valueOf(type));
			logger.info(String.format("%s: %d", countForVideoType.getDescription(), countForVideoType.getCount()));
			break;
		default:
			logger.info("No data!");
		}

	}

	/**
	 * Processes a video by ingesting data from XML
	 * 
	 * @param logger
	 * @param videoFile
	 */
	public void ingestVideo(Logger logger, String videoFile) {

		String path = videoIngestFolder + videoFile;

		InputStream in = this.getClass().getClassLoader().getResourceAsStream(path);
		if (in == null) {
			logger.info("Cannot find file");
		} else {
			com.performgroup.interview.xml.Video videoXml = (com.performgroup.interview.xml.Video) jaxb2Marshaller
					.unmarshal(new StreamSource(in));
			logger.info(String.format("Adding video : %s into library", videoXml));
			videoService.addVideo(new VideoConverter().convert(videoXml));
		}
	}

}
