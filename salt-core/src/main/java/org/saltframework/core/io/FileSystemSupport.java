package org.saltframework.core.io;

import org.apache.commons.io.FilenameUtils;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 1. 3.
 */
public class FileSystemSupport extends AbstractFileSystem {
	private SystemCode systemCode;
	private Category category;
	private String systemName;

	public FileSystemSupport() {
		super();
	}

	public FileSystemSupport(String absolutePath) {
		super(absolutePath);
	}

	public void setSystemCode(SystemCode systemCode) {
		this.systemCode = systemCode;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	@Override
	public FileSystem save(String fileName, byte[] bytes) throws IOException {
		Assert.notNull(systemCode);
		Assert.notNull(category);
		Assert.notNull(systemName);

		String absolutePath = getAbsolutePath();
		String relativePath = getRelativePath(systemCode, category, systemName);
		String datePath = getDateFormatPath();

		String dirPath = absolutePath + relativePath + datePath;
		createDir(dirPath);

		File file = getFileOnlyOne(fileName, dirPath);
		createFile(file);

		wirteFile(file, bytes);

		FileSystem fileSystem = new FileSystem(absolutePath, relativePath);
		fileSystem.setDatePath(datePath);
		fileSystem.setDirPath(dirPath);
		fileSystem.setExtension(FilenameUtils.getExtension(fileName).toLowerCase());
		fileSystem.setFile(file);
		fileSystem.setVirtualFileName(file.getName());
		fileSystem.setFileName(fileName);

		return fileSystem;
	}
}
