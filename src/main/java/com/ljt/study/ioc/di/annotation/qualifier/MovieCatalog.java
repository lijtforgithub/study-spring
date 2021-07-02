package com.ljt.study.ioc.di.annotation.qualifier;

/**
 * @author LiJingTang
 * @version 2015年9月23日上午10:19:59
 */
public class MovieCatalog {
	
	private String format;
	private String genre;
	
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	@Override
	public String toString() {
		return "MovieCatalog [format=" + format + ", genre=" + genre + "]";
	}
	
}