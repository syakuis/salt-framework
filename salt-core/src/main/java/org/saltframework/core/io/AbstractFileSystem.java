package org.saltframework.core.io;

import org.apache.commons.io.FilenameUtils;
import org.saltframework.core.io.enums.Category;
import org.saltframework.core.io.enums.SystemCode;
import org.saltframework.util.io.PathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 일반화된 경로를 이용하여 파일을 저장하거나 경로를 얻을 수 있다.
 * 해당 경로에 업로드하지 않으면 모듈 혹은 프로그램간의 공유가 어려워 지거나 관리범위에 벗어나므로 관리할 수 없게 된다.
 *
 * -------------------------> getAbsolutePath
 * @see Category 1st path
 * @see SystemCode 2nd path
 * -------------------------> getRelativePath
 * 3rd systemName moduleName or pluginName or otherName
 * 4rd user custom naming
 * -------------------------> FileSystem.setDirPath 해당 경로를 백업해둬야 다시 읽을 수 있다.
 *
 * /category/system/systemName/date/systemIdx/fileName
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
	private Category category;
	private SystemCode systemCode;

	public AbstractFileSystem() {
		this(PathUtils.getWebRootAbsolutePath());
	}

	public AbstractFileSystem(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public SystemCode getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(SystemCode systemCode) {
		this.systemCode = systemCode;
	}

	/**
	 * 파일시스템 절대 경로
	 *
	 * @return the absolute path
	 */
	public String getAbsolutePath() {
		return absolutePath;
	}

	/**
	 * 상대경로를 완성한다.
	 * /category/systemCode
	 *
	 * @return the relative path
	 */
	public String getRelativePath() {
		Assert.notNull(this.category);
		Assert.notNull(this.systemCode);

		return new StringBuffer().append(File.separator)
				.append(category.name())
				.append(File.separator)
				.append(systemCode.name()).toString();
	}

	/**
	 * 상대경로를 완성한다.
	 *
	 * @param systemCode 2nd path
	 * @param category   1st path
	 * @return the relative path
	 */
	public String getRelativePath(Category category, SystemCode systemCode) {
		Assert.notNull(category);
		Assert.notNull(systemCode);
		return new StringBuffer().append(File.separator)
				.append(category.name())
				.append(File.separator)
				.append(systemCode.name()).toString();
	}

	/**
	 * 현재 년월일을 이용하여 경로를 만듬. /년/월/일
	 *
	 * @return the date format path
	 */
	public String getDateFormatPath() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(new Date());
		String[] dates = date.split("-");

		String year = dates[0];
		String month = dates[1];
		String day = dates[2];

		String result = new StringBuffer().append(File.separator)
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
	public void createDir(String dir) throws IOException {
		Files.createDirectories(Paths.get(dir));
	}

	public void createFile(String file) throws IOException {
		Files.createFile(Paths.get(file));
	}

	public void createFile(File file) throws IOException {
		file.createNewFile();
	}

	public void writeFile(File file, byte[] bytes) throws IOException {
		BufferedOutputStream bufferedOutputStream = null;

		try {
			bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
			bufferedOutputStream.write(bytes);
		} finally {
			if (bufferedOutputStream != null) {
				bufferedOutputStream.close();
			}
		}
	}

	/**
	 * 파일명이 유일하도록 중복인 경우 반복해서 새로운 파일명을 생성한다.
	 *
	 * @param filename the filename
	 * @param path     the path
	 * @return the file only one
	 */
	public File getFileOnlyOne(String filename, String path) throws IOException {
		String fileRenaming = getFileReNameing(filename);
		return new File(path + File.separator + fileRenaming);
	}

	/**
	 * UUID를 이용한 가상 파일명을 생성한다.
	 *
	 * @param fileName the file name
	 * @return the file re nameing
	 */
	public String getFileReNameing(String fileName) {
		Assert.notNull(fileName);
		StringBuffer stringBuffer = new StringBuffer(UUID.randomUUID().toString().replace("-", ""));
		stringBuffer.append('.').append(FilenameUtils.getExtension(fileName).toLowerCase());
		return stringBuffer.toString();
	}

	public abstract void setSystemName(String systemName);
	public abstract FileSystem initiation(String fileName) throws IOException;
	public abstract FileSystem save(String fileName, byte[] bytes) throws IOException;
}
