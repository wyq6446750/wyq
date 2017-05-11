package com.my.example.dashboard.utils;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.util.zip.*;

/**
 * Date:17/3/30
 * Time:下午4:10
 *
 * @author yongquan.wen@corp.elong.com
 */
public class GZIPUtils {

    private static final Logger logger = LoggerFactory.getLogger(GZIPUtils.class);

    /**
     * 使用gzip进行压缩
     */
    public static String gzip(String text) {
        if (null == text || text.length() < 1) {
            logger.warn("Illegal empty text string to gzip, and will return null");
            return null;
        }

        String gzipText = null;
        ByteArrayOutputStream bos = null;
        GZIPOutputStream gos = null;
        try {
            bos = new ByteArrayOutputStream();
            gos = new GZIPOutputStream(bos);
            gos.write(text.getBytes("UTF-8"));
            gos.finish();

            gzipText = new String(new Base64().encode(bos.toByteArray()), "UTF-8");
        } catch (Exception e) {
            logger.error("Fail to gzip text: {}", text, e);
        } finally {
            close(gos, bos);
        }

        return gzipText;
    }

    /**
     * 使用gzip进行解压缩
     */
    public static String ungzip(String gzipText) {
        if (null == gzipText || gzipText.length() < 1) {
//            logger.warn("Illegal empty gzipText string, and will return null");
            return null;
        }

        String text = null;
        ByteArrayOutputStream bos = null;
        ByteArrayInputStream bis = null;
        GZIPInputStream gis = null;
        try {
            bis = new ByteArrayInputStream(new Base64().decode(gzipText.getBytes("UTF-8")));
            gis = new GZIPInputStream(bis);

            byte[] buffer = new byte[1024];
            int offset = -1;
            bos = new ByteArrayOutputStream();
            while ((offset = gis.read(buffer)) > 0) {
                bos.write(buffer, 0, offset);
            }

            text = bos.toString();
        } catch (Exception e) {
            logger.error("Fail to ungzip gzipText : {}", gzipText, e);
        } finally {
            close(gis, bis, bos);
        }

        return text;
    }

    /**
     * 使用zip进行压缩
     */
    public static final String zip(String text) {
        if (null == text || text.length() < 1) {
            logger.warn("Illegal empty text string, and will return null");
            return null;
        }

        ByteArrayOutputStream bos = null;
        ZipOutputStream zos = null;
        String zipText = null;
        try {
            bos = new ByteArrayOutputStream();
            zos = new ZipOutputStream(bos);
            zos.putNextEntry(new ZipEntry("0"));
            zos.write(text.getBytes("UTF-8"));
            zos.closeEntry();

            zipText = new String(new Base64().encode(bos.toByteArray()), "UTF-8");
        } catch (Exception e) {
            logger.error("Fail to zip text {}", text, e);
        } finally {
            close(zos, bos);
        }

        return zipText;
    }

    /**
     * 使用zip进行解压缩
     */
    public static final String unzip(String zipText) {
        if (null == zipText || zipText.length() < 1) {
            logger.warn("Illegal empty zipText string, and will return null");
            return null;
        }

        ByteArrayOutputStream bos = null;
        ByteArrayInputStream bis = null;
        ZipInputStream zis = null;
        String text = null;
        try {
            bos = new ByteArrayOutputStream();
            bis = new ByteArrayInputStream(new Base64().decode(zipText.getBytes("UTF-8")));
            zis = new ZipInputStream(bis);
            zis.getNextEntry();
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = zis.read(buffer)) > 0) {
                bos.write(buffer, 0, offset);
            }

            text = bos.toString();
        } catch (IOException e) {
            logger.error("Fail to unzip zipText: {}", zipText, e);
        } finally {
            close(zis, bis, bos);
        }

        return text;
    }

    public static void close(Closeable... closeables) {
        if (null == closeables || closeables.length < 1) {
            return;
        }

        for (Closeable closeable : closeables) {
            try {
                if (null != closeable) {
                    closeable.close();
                }
            } catch (Exception e) {
                logger.error("Fail to close {}", closeable.getClass().getSimpleName(), e);
            }
        }
    }

}
