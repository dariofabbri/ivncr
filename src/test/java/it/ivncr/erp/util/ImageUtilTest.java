package it.ivncr.erp.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;

import org.junit.Test;

public class ImageUtilTest {

	@Test
	public void testGetMimeTypeJpeg() throws IOException {

		InputStream is = this.getClass().getResourceAsStream("/images/test.jpg");
		byte[] image = readInputStream(is);

		Assert.assertEquals("image/jpeg", ImageUtil.getMimeType(image));
	}

	@Test
	public void testGetMimeTypeGif() throws IOException {

		InputStream is = this.getClass().getResourceAsStream("/images/test.gif");
		byte[] image = readInputStream(is);

		Assert.assertEquals("image/gif", ImageUtil.getMimeType(image));
	}

	@Test
	public void testGetMimeTypePng() throws IOException {

		InputStream is = this.getClass().getResourceAsStream("/images/test.png");
		byte[] image = readInputStream(is);

		Assert.assertEquals("image/png", ImageUtil.getMimeType(image));
	}


	private byte[] readInputStream(InputStream is) throws IOException {

		int b;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while((b = is.read()) >= 0) {
			baos.write(b);
		}

		return baos.toByteArray();
	}
}
