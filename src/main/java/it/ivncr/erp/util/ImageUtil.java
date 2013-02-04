package it.ivncr.erp.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageUtil {

	private static final Logger logger = LoggerFactory.getLogger(ImageUtil.class);

	public static String getMimeType(byte[] image) {

		ByteArrayInputStream bais = new ByteArrayInputStream(image);
		ImageInputStream iis = null;
		try {
			iis = ImageIO.createImageInputStream(bais);
		} catch (IOException e) {
			String msg = "Exception caught while creating image input stream.";
			logger.error(msg, e);
			throw new RuntimeException(msg, e);
		}

		String format = null;

		Iterator<ImageReader> i = ImageIO.getImageReaders(iis);
		if(i.hasNext()) {
			ImageReader reader = i.next();
			try {
				format = reader.getFormatName();

				if(format.equalsIgnoreCase("gif")) {
					return "image/gif";
				} else if(format.equalsIgnoreCase("png")) {
					return "image/png";
				} else if(format.equalsIgnoreCase("jpeg")) {
					return "image/jpeg";
				} else {
					logger.warn(String.format("Unexpected format detected: %s", format));
				}

			} catch (IOException e) {
				String msg = "Exception caught while retrieving format name.";
				logger.error(msg, e);
				throw new RuntimeException(msg, e);
			}
		}

		return format;
	}
}
