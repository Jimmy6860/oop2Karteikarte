package model;

import java.io.Serializable;
import java.util.Date;

public class FileInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date date;
	private String fileName;
	private String dirPath;
	
	public FileInfo() {
	}
	
	public FileInfo(Date date, String fileName, String dirPath) {
		this.date = date;
		this.fileName = fileName;
		this.dirPath = dirPath;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDirPath() {
		return dirPath;
	}

	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}
}