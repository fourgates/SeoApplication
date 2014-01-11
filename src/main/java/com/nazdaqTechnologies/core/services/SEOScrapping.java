package com.nazdaqTechnologies.core.services;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.nazdaqTechnologies.core.domain.URLProfile;;

public class SEOScrapping {
	public static void main(String[] args) {
		// doc variable stores the content of the url once the crawler reads it.
		// links variable stores all the on page links for the scanned page.

		// Connection.Response response = null;
		Document doc = null;
		Elements links;		
		Elements h1s;
		Elements h2s;
		Elements h3s;
		Elements titles;
		Elements metaDescs;
		Elements images;

		// user input variable
		Scanner input = new Scanner(System.in);

		// temp variable for the url that is scanned
		String scanned_url;

		// array of all the seo resutls. The array is of class type User Profile
		LinkedList<URLProfile> site_seo_results = new LinkedList<URLProfile>();

		System.out.print("Please enter a URL to scan ");

		domain_to_read = input.nextLine();

		urls_to_scan.add(domain_to_read);


		// loops through the urls_to_scan variable until it is empty and crawls
		// all the individual urls.
		// stores the SEO findings for all the urls into the SEO results list.
		for (int i = 0; i < 10; i++) {
			//i++;
			// temp variable of class type Url_Profile
			URLProfile url_seo = new URLProfile();

			scanned_url = urls_to_scan.get(i);
			print("\nScanned URL: %s", scanned_url);

			try {
				doc = Jsoup.connect(scanned_url).get();

				// response = Jsoup.connect(scanned_url).response();

				// print("the response code of the url is %s",
				// response.statusMessage());

				// doc = Jsoup.connect(scanned_url).get();

			} catch (IOException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();

			}
			links = doc.select("a[href]");
			append_urls_to_scan(links);
			
			http://www.amazon.com
				links = doc.select("a[href]");
			h1s = doc.select("h1");
			h2s = doc.select("h2");
			h3s = doc.select("h3");
			titles = doc.select("title");
			metaDescs = doc.select("meta[name=description]");
			images = doc.select("img");

			// append_urls_to_scan function takes all the on page links and adds
			// it to the list of URLs to be scanned
			append_urls_to_scan(links);

			// Storing all the seo findings on the Url_Profile variable and them
			// to the SEO results array
			url_seo.setUrl(scanned_url);
			url_seo.setH1s(h1s);
			url_seo.setH2s(h2s);
			url_seo.setH3s(h3s);
			url_seo.setPageTitles(titles);
			url_seo.setMetaDescs(metaDescs);
			url_seo.setImages(images);
			url_seo.setOnPageLinks(links);
			site_seo_results.add(url_seo);
			print("\nNumber of URL SEOed: %d", i + 1);
			//System.out.println("Number of links: "+urls_to_scan.size());
		}

		try {
			// print all the seo results
			print_seo_result(site_seo_results);
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}
	// Global variables to store all the URL to scan and the main domain to
	// scan. Once a user provides
	// main domain to scan the crawler finds all the links on that page and adds
	// them to the urls to scan list.
	private static LinkedList<String> urls_to_scan = new LinkedList<String>();
	private static String domain_to_read;

	// given a message and list of object arguments it prints the message with
	// the argument variables
	public static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}

	// Given a Elements of on page links the append_urls_to_scan function loops
	// through all the link elements
	// and converts to test with absolute url and appends it to the list of URLs
	// to be scanned
	public static void append_urls_to_scan(Elements links) {

		String temp_url;

		for (Element url : links) {

			temp_url = url.attr("abs:href");

			// if statement checks to make sure the url doesn't already exist on
			// the urls to scan list
			// and it is not empty and it is not an external domain
			if (!urls_to_scan.contains(temp_url) && temp_url != ""
					&& match_pattern(temp_url, domain_to_read)) {

				urls_to_scan.add(temp_url);

			}
		}
	}

	// given an array of type user_profile class the print_seo_result function
	// loops through
	// and prints all the content.
	public static void print_seo_result(LinkedList<URLProfile> site_seo_results) {

		LinkedList<URLProfile>  temp_site_seo_results = site_seo_results;

		Elements links;
		Elements h1s;
		Elements h2s;
		Elements h3s;
		Elements titles;
		Elements metaDescs;
		Elements images;

		print("\nSize of site seo %s", temp_site_seo_results.size());
		for (int i = 0; i < temp_site_seo_results.size(); i++) {
			
			
			h1s = temp_site_seo_results.get(i).getH1s();
			h2s = temp_site_seo_results.get(i).getH2s();
			h3s = temp_site_seo_results.get(i).getH3s();
			titles = temp_site_seo_results.get(i).getPageTitles();
			metaDescs = temp_site_seo_results.get(i).getMetaDescs();
			images = temp_site_seo_results.get(i).getImages();

			print("\nURL: %s | ", temp_site_seo_results.get(i).getUrl());

			for (Element h1 : h1s) {

				print("\nH1: %s | ", h1.text());

			}

			for (Element h2 : h2s) {

				print("\nH2: %s | ", h2.text());
			}

			for (Element h3 : h3s) {

				print("\nH3: %s | ", h3.text());
			}

			for (Element title : titles) {

				print("\nPage Title: %s | ", title.text());
			}

			for (Element meta : metaDescs) {

				print("\nMeta Description: %s | ", meta.attr("content"));
			}

			for (Element image : images) {

				print("\nImage file name: %s. | Image ALT Text: %s | ",
						image.attr("src"), image.attr("atl"));
			}

		}
	}

	// Given a string and a patter to match the match_patter function returns
	// true if the patter exist on the string
	public static boolean match_pattern(String s, String p) {

		Pattern pattern = Pattern.compile(p);

		Matcher match = pattern.matcher(s);

		return match.find();
	}
}
