package com.nazdaqTechnologies.core.domain;

import org.jsoup.select.Elements;

// The Url_Profile class stores the SEO findings for each of the crawled URL. 
// It has methods to set the variables and read the variables
public class URLProfile {

	private String url;
	private Elements h1s;
	private Elements h2s;
	private Elements h3s;
	private Elements links;
	private Elements titles;
	private Elements metaDescs;
	private Elements images;
	
	public void setUrl(String scanned_url) {

		url = scanned_url;
	}

	public void setH1s(Elements scanned_h1s) {

		h1s = scanned_h1s;
	}

	public void setH2s(Elements scanned_h2s) {

		h2s = scanned_h2s;
	}

	public void setH3s(Elements scanned_h3s) {

		h3s = scanned_h3s;
	}

	public void setOnPageLinks(Elements scanned_links) {

		links = scanned_links;
	}

	public void setPageTitles(Elements scanned_titles) {

		titles = scanned_titles;
	}

	public void setMetaDescs(Elements scanned_metaDescs) {

		metaDescs = scanned_metaDescs;
	}

	public void setImages(Elements scanned_images) {

		images = scanned_images;
	}

	public String getUrl() {

		return url;
	}

	public Elements getH1s() {

		return h1s;
	}

	public Elements getH2s() {

		return h2s;
	}

	public Elements getH3s() {

		return h3s;
	}

	public Elements getonPageLinks() {

		return links;
	}

	public Elements getPageTitles() {

		return titles;
	}

	public Elements getMetaDescs() {

		return metaDescs;
	}

	public Elements getImages() {

		return images;
	}
}