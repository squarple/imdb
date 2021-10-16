package by.radzionau.imdb.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * The class ImageInputStreamUtil.
 */
public final class ImageInputStreamUtil {
    private static final Logger logger = LogManager.getLogger();

    private static final class InputStringUtilHolder {
        private static final ImageInputStreamUtil INSTANCE = new ImageInputStreamUtil();
    }

    /**
     * Gets instance.
     *
     * @return the instance of image input stream util
     */
    public static ImageInputStreamUtil getInstance() {
        return InputStringUtilHolder.INSTANCE;
    }

    private ImageInputStreamUtil() {

    }

    /**
     * Gets image input stream as string.
     *
     * @param inputStream the input stream
     * @return the image input stream as string
     */
    public String getImageInputStreamAsString(InputStream inputStream) {
        byte[] byteContent;
        try {
            byteContent = IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            logger.error("Failed to convert InputStream to String", e);
            return "";
        }
        StringBuilder sb = new StringBuilder();
        byte [] encodeImg = Base64.encodeBase64(byteContent, false);
        String imageStr = StringUtils.newStringUtf8(encodeImg);
        sb.append(imageStr);
        return sb.toString();
    }

    /**
     * Gets string as image input stream.
     *
     * @param sourceString the source string
     * @return the string as image input stream
     */
    public InputStream getStringAsImageInputStream(String sourceString) {
        byte[] encodeImg = StringUtils.getBytesUtf8(sourceString);
        byte[] decodeImg = Base64.decodeBase64(encodeImg);

        return new ByteArrayInputStream(decodeImg);
    }
}