package org.saltframework.core.io;

import org.apache.commons.io.FilenameUtils;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 1. 3.
 */
public class FileSystemSupport extends AbstractFileSystem {
	private String systemName;
	private boolean rename = true;
	private boolean dirDate = true;

	public FileSystemSupport() {
		super();
	}

	public FileSystemSupport(String absolutePath) {
		super(absolutePath);
	}

	public void setSystemCode(SystemCode systemCode) {
		super.setSystemCode(systemCode);
	}

	public void setCategory(Category category) {
		super.setCategory(category);
	}

	@Override
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	/**
	 * 날짜를 이용한 폴더 경로 사용여부
	 *
	 * @param dirDate the dir date
	 */
	public void setDirDate(boolean dirDate) {
		this.dirDate = dirDate;
	}

	/**
	 * 파일명을 임의적으로 변경할지 여부
	 *
	 * @param rename the rename
	 */
	public void setRename(boolean rename) {
		this.rename = rename;
	}

	@Override
	public FileSystem save(String fileName, byte[] bytes) throws IOException {
		Category category = getCategory();
		SystemCode systemCode = getSystemCode();

		Assert.notNull(category);
		Assert.notNull(systemCode);
		Assert.notNull(systemName);

		String absolutePath = getAbsolutePath();
		String relativePath = getRelativePath();

		StringBuffer dirString = new StringBuffer(relativePath);

		String datePath = null;
		if (dirDate) {
			datePath = getDateFormatPath();
			dirString.append(datePath);
		}

		dirString.append(File.separator).append(systemName);

		String dirPath = dirString.toString();
		String fullPath = absolutePath + dirPath;
		createDir(fullPath);

		File file;
		if (rename) {
			file = getFileOnlyOne(fileName, fullPath);
		} else {
			file = new File(fullPath + File.separator + fileName);
		}

		createFile(file);

		wirteFile(file, bytes);

		FileSystem fileSystem = new FileSystem(absolutePath, relativePath);
		fileSystem.setDatePath(datePath);
		fileSystem.setDirPath(dirPath);
		fileSystem.setExtension(FilenameUtils.getExtension(fileName).toLowerCase());
		fileSystem.setFile(file);
		fileSystem.setFilePath(dirPath + File.separator + file.getName());
		fileSystem.setVirtualFileName(file.getName());
		fileSystem.setFileName(fileName);

		return fileSystem;
	}
}
