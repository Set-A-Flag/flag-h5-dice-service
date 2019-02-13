package com.chasel.h5.dice.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 自定义IOUtils
 * 
 * @author chasel
 *
 */
public class IoUtils {
	/**
	 * 输入流转byte[]
	 * 
	 * @param input
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray(InputStream input) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
		}
		return output.toByteArray();
	}

	/**
	 * 输入流转byte[]
	 * 
	 * @param input
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArrayL(InputStream input) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		copy(input, output);
		return output.toByteArray();
	}

	public static int copy(InputStream input, OutputStream output) throws IOException {
		long count = copyLarge(input, output);
		if (count > 2147483647L) {
			return -1;
		}
		return (int) count;
	}

	public static long copyLarge(InputStream input, OutputStream output) throws IOException {
		byte[] buffer = new byte[4096];
		long count = 0L;
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			count += n;
		}
		return count;
	}

	/**
	 * 导出文件
	 * 
	 * @param bytes
	 * @param fileName
	 * @throws Exception
	 */
	public static void downloadFile(byte[] bytes, String fileName) throws Exception {
		HttpServletResponse response = ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
		// 清空response
		response.reset();
		// 设置response的Header
		response.addHeader("Content-Disposition", "attachment;filename=" + Arrays.toString(fileName.getBytes("ISO-8859-1")));
		response.addHeader("Content-Length", "" + bytes.length);
		OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
		response.setContentType("application/octet-stream");
		toClient.write(bytes);
		toClient.flush();
		toClient.close();
	}

	/**
	 * 导出Excel
	 * 
	 * @param workBook
	 * @param fileName
	 * @throws Exception
	 */
	public static void downloadFile(HSSFWorkbook workBook, String fileName) throws Exception {
		HttpServletResponse response = ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
		// 清空response
		response.reset();
		// 设置response的Header
		response.addHeader("Content-Disposition", "attachment;filename=" + Arrays.toString(fileName.getBytes("ISO-8859-1")));
		OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
		response.setContentType("application/octet-stream");
		workBook.write(toClient);
		toClient.flush();
		toClient.close();
	}
}
