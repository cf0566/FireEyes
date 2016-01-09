package com.edu.fireeyes.bean;

import java.io.File;

/**
 * 文件实体类
 */
public class FileBean {

	private int icon; // 图标
	private String size; // 文件大小
	private File file; // 文件对象

	public FileBean() {
	}

	public FileBean(int icon, String size, File file) {
		this.icon = icon;
		this.size = size;
		this.file = file;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	/**
	 * 获取文件名
	 */
	public String getName() {
		return file.getName();
	}
	
	/**
	 * 判断是否是文件夹
	 */
	public boolean isDirectory() {
		return file.isDirectory();
	}

	@Override
	public String toString() {
		return "FileBean [icon=" + icon + ", size=" + size + ", file=" + file
				+ "]";
	}

}
