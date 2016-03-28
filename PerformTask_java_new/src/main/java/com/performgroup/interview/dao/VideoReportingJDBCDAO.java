package com.performgroup.interview.dao;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.performgroup.interview.domain.VideoReportingBean;
import com.performgroup.interview.domain.VideoType;

/**
 * The JDBC implementation of the reporting DAO. Needs to remain JDBC driven for
 * the purpose of this task (as opposed to Hibernate).
 */
public class VideoReportingJDBCDAO implements VideoReportingDAO {

	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings("unchecked")
	public Collection<VideoReportingBean> countByDay() {
		List<VideoReportingBean> report = jdbcTemplate.query(
				"SELECT count(creation_date) as \"count\", "
						+ "EXTRACT (DAY  FROM creation_date)+ '/' + EXTRACT (MONTH  FROM creation_date) + '/' + EXTRACT (YEAR  FROM creation_date)  as description "
						+ "FROM video GROUP BY EXTRACT ( DAY FROM creation_date), EXTRACT (MONTH FROM creation_date), EXTRACT (YEAR FROM creation_date)",
				new BeanPropertyRowMapper(VideoReportingBean.class));
		return report;
	}

	@SuppressWarnings("unchecked")
	public List<VideoReportingBean> countByVideoType() {
		List<VideoReportingBean> report = jdbcTemplate.query(
				"SELECT count(video_type) as \"count\", video_type as description FROM video GROUP BY video_type",
				new BeanPropertyRowMapper(VideoReportingBean.class));
		return report;
	}

	public VideoReportingBean countForVideoType(VideoType videoType) {
		int queryForInt = jdbcTemplate.queryForInt("SELECT count(*) FROM video WHERE video_type = ?",
				new Object[] { videoType.toString() });
		return new VideoReportingBean(queryForInt, String.format("Video count for type: %s", videoType));
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Resource
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

}
