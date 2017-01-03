package org.saltframework.core.io;

import java.io.File;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 1. 3.
 */
public class FileSystem {
	private final String absolutePath;
	private final String relativePath;

	private File file;
	private String filePath;
	private String dirPath;
	private String datePath;
	private String fileName;
	private String virtualFileName;
	private String extension;

	public FileSystem(String absolutePath, String relativePath) {
		this.absolutePath = absolutePath;
		this.relativePath = relativePath;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public String getRelativePath() {
		return relativePath;
	}

	public File getFile() {
		return file;
	}

	protected void setFile(File file) {
		this.file = file;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getDirPath() {
		return dirPath;
	}

	protected void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}

	public String getDatePath() {
		return datePath;
	}

	protected void setDatePath(String datePath) {
		this.datePath = datePath;
	}

	public String getFileName() {
		return fileName;
	}

	protected void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getVirtualFileName() {
		return virtualFileName;
	}

	protected void setVirtualFileName(String virtualFileName) {
		this.virtualFileName = virtualFileName;
	}

	public String getExtension() {
		return extension;
	}

	protected void setExtension(String extension) {
		this.extension = extension;
	}

	@Override
	public String toString() {
		return "FileSystem{" +
				": absolutePath='" + absolutePath + '\'' +
				", relativePath='" + relativePath + '\'' +
				", file=" + file +
				", dirPath='" + dirPath + '\'' +
				", datePath='" + datePath + '\'' +
				", fileName='" + fileName + '\'' +
				", virtualFileName='" + virtualFileName + '\'' +
				", extension='" + extension + '\'' +
				'}';
	}
}
