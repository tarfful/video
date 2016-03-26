package com.performgroup.interview.cmd;

import java.io.Console;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * The main entry point for the video ingest task
 */
public class VideoTaskRunner {

	private static transient final Logger LOGGER = Logger.getLogger(VideoTaskRunner.class);

	// @formatter:off
	private static transient final String USAGE = 
			"\n====== Usage ====== \n" 
			+ "list = list videos in the system \n"
			+ "add = add a video after prompting for file path \n" 
			+ "report = print video report \n" 
			+ "exit = exits the program \n" + "usage = prints this help";
	// @formatter:on

	public static final void usage() {
		LOGGER.info(USAGE);
	}

	public static final void main(final String[] args) {

		Console c = System.console();

		if (c == null) {
			System.err.println("No console.");
			System.exit(1);
		}

		final GenericApplicationContext ctx = new GenericApplicationContext();
		final XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(ctx);
		String appContext = System.getProperty("appContext");
		xmlReader.loadBeanDefinitions(new ClassPathResource(appContext));
		ctx.refresh();

		VideoProcessor videoProcessor = (VideoProcessor) ctx.getBean("videoProcessor");

		while (true) {

			System.out.print(">");

			String input = c.readLine();
			if (input.equalsIgnoreCase("usage")) {
				usage();
			} else if (input.equalsIgnoreCase("list")) {
				videoProcessor.listVideos(LOGGER);
			} else if (input.equalsIgnoreCase("add")) {
				System.out.println("Video file?");
				String videoDataFile = c.readLine();
				videoProcessor.ingestVideo(LOGGER, videoDataFile);
			} else if (input.equalsIgnoreCase("report")) {
				// @formatter:off
				System.out.println(
						"Select report type:\n "
						+ " 1 - count by video creation date\n "
						+ " 2 - count by video type\n "
						+ " 3 - count for video type\n ");
				// @formatter:on

				String reportType = c.readLine();
				switch (reportType) {
				case "1":
					videoProcessor.countByDay(LOGGER);
					break;
				case "2":
					videoProcessor.countByVideoType(LOGGER);
					break;
				case "3":
					// @formatter:off
					System.out.println("Enter video type:\n"
							+ "  FRUIT_MATCH,\n"
							+ "  VEGETABLE_MATCH,\n"
							+ "  DRINK_MATCH\n");
					// @formatter:on
					String videoType = c.readLine();
					videoProcessor.countForVideoType(LOGGER, videoType);
					break;
				default:
					System.out.println("No report!");
				}
			} else if (input.equalsIgnoreCase("exit")) {
				System.exit(0);
			} else {
				System.out.println("Unrecognised command");
			}

		}
	}
}
