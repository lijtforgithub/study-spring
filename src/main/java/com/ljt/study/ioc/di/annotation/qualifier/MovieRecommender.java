package com.ljt.study.ioc.di.annotation.qualifier;

import com.ljt.study.ioc.di.annotation.meta.Format;
import com.ljt.study.ioc.di.annotation.meta.MovieQualifier;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author LiJingTang
 * @version 2015年8月28日 下午7:26:07
 */
public class MovieRecommender {

    @Autowired
    @MovieQualifier(format= Format.VHS, genre="Action")
    private MovieCatalog actionVhsCatalog;

    @Autowired
    @MovieQualifier(format=Format.VHS, genre="Comedy")
    private MovieCatalog comedyVhsCatalog;

    @Autowired
    @MovieQualifier(format=Format.DVD, genre="Action")
    private MovieCatalog actionDvdCatalog;

    @Autowired
    @MovieQualifier(format=Format.BLURAY, genre="Comedy")
    private MovieCatalog comedyBluRayCatalog;

    public MovieRecommender() {
		System.out.println(this.getClass().getName() + "无参构造方法实例化");
	}

	public MovieCatalog getActionVhsCatalog() {
		return actionVhsCatalog;
	}
	public MovieCatalog getComedyVhsCatalog() {
		return comedyVhsCatalog;
	}
	public MovieCatalog getActionDvdCatalog() {
		return actionDvdCatalog;
	}
	public MovieCatalog getComedyBluRayCatalog() {
		return comedyBluRayCatalog;
	}
}
