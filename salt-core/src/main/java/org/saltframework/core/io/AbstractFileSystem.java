package org.saltframework.core.io;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.saltframework.util.io.PathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 파일을 서버에 업로드할 때 지정된 경로에 항상 등록될 수 있게 경로를 표준화하여 제공한다.
 * 해당 경로에 업로드하지 않으면 모듈 혹은 프로그램간의 공유가 어려워 지거나 관리범위에 벗어나므로 관리할 수 없게 된다.
 *
 * /category/system/systemName/date/systemIdx/fileName
 *
 * /category/modules/moduleName/y/m//d/idx/fileName
 * /attachments/modules/moduleName/y/m//d/idx/fileName
 * /temp/modules/moduleName/idx/fileName
 * /cache/plugins/pluginName/idx/fileName
 * /temp/general/targetName/idx/fileName
 *
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 1. 3.
 */
public abstract class AbstractFileSystem {
	private static final Logger logger = LoggerFactory.getLogger(AbstractFileSystem.class);
	private final String absolutePath;

	public AbstractFileSystem() {
		this(PathUtils.getWebRootAbsolutePath());
	}

	public AbstractFileSystem(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	/**
	 * 상대경로를 완성한다.
	 *
	 * @param systemCode 2nd path
	 * @param category   1st path
	 * @return the relative path
	 */
	protected String getRelativePath(Category category, SystemCode systemCode) {
		StringBuffer stringBuffer = new StringBuffer();
		return stringBuffer.append(File.separator)
				.append(category.name())
				.append(File.separator)
				.append(systemCode.name()).toString();
	}

	/**
	 * 현재 년월일을 이용하여 경로를 만듬. /년/월/일
	 *
	 * @return the date format path
	 */
	protected String getDateFormatPath() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(new Date());
		String[] dates = date.split("-");

		String year = dates[0];
		String month = dates[1];
		String day = dates[2];

		StringBuffer stringBuffer = new StringBuffer();
		String result = stringBuffer.append(File.separator)
				.append(year)
				.append(File.separator)
				.append(month)
				.append(File.separator)
				.append(day).toString();

		if (logger.isDebugEnabled()) {
			logger.debug("createDateFormatPath: {}, {}, {} = {}", year, month, day, result);
		}

		return result;
	}

	/**
	 * 폴더를 생성한다 멀티쓰레드 지원을 위해 Files 사용한다.
	 *
	 * @param dir the dir
	 * @throws IOException the io exception
	 */
	protected void createDir(String dir) throws IOException {
		Files.createDirectories(Paths.get(dir));
	}

	protected void createFile(String file) throws IOException {
		Files.createFile(Paths.get(file));
	}

	protected void createFile(File file) throws IOException {
		file.createNewFile();
	}

	protected void wirteFile(File file, byte[] bytes) throws IOException {
		BufferedOutputStream bufferedOutputStream = null;

		try {
			bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
			bufferedOutputStream.write(bytes);
		} finally {
			bufferedOutputStream.close();
		}
	}

	/**
	 * 파일명이 유일하도록 중복인 경우 반복해서 새로운 파일명을 생성한다.
	 *
	 * @param filename the filename
	 * @param path     the path
	 * @return the file only one
	 */
	protected File getFileOnlyOne(String filename, String path) throws IOException {
		String fileRenaming = getFileReNameing(filename);
		return new File(path + File.separator + fileRenaming);
	}

	/**
	 * UUID를 이용한 가상 파일명을 생성한다.
	 *
	 * @param fileName the file name
	 * @return the file re nameing
	 */
	private String getFileReNameing(String fileName) {
		Assert.notNull(fileName);
		StringBuffer stringBuffer = new StringBuffer(UUID.randomUUID().toString().replace("-", ""));
		stringBuffer.append('.').append(FilenameUtils.getExtension(fileName).toLowerCase());
		return stringBuffer.toString();
	}

	public abstract FileSystem save(String fileName, byte[] bytes) throws IOException;
}
