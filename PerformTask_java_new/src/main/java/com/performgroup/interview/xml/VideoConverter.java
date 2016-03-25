package com.performgroup.interview.xml;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.performgroup.interview.domain.Video;
import com.performgroup.interview.domain.VideoCategory;

public final class VideoConverter {

	public final Video convert(com.performgroup.interview.xml.Video videoXml) {
		Video video = null;
		if (videoXml != null) {
			video = new Video();
			video.setTitle(videoXml.getTitle());
			video.setVideoPath(videoXml.getPath());
			video.setVideoType(videoXml.getType());
			video.setCreationDate(new Date());

			Set<VideoCategory> categories = null;
			if (videoXml.getKeywordSet() != null) {
				categories = new HashSet<VideoCategory>();

				for (String string : videoXml.getKeywordSet()) {
					VideoCategory videoCategory = new VideoCategory();
					videoCategory.setCategoryName(string);
					videoCategory.setVideo(video);
					categories.add(videoCategory);
				}
				video.setCategories(categories);
			}

		}
		return video;
	}

}
