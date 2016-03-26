package com.performgroup.interview.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.performgroup.interview.domain.VideoReportingBean;

public class VideoReportingBeanRowMapper implements RowMapper {

	public VideoReportingBeanRowMapper (String desc) {
		
	}
	
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
