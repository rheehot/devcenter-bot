package com.naver.hackday.devcenterbot.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.service.IssueService;

public class TitleScrapper {

	public static void run() throws IOException {
		//fileBase의 위치가 다를수도 있으므로..
		String baseUrl = "/Users/LeeChnagSup/Desktop/코딩/NaverHackDay2019";
		String fileName = baseUrl + "/devcenter-bot/src/main/resources/Files/log.txt";
		FileReader input = new FileReader(new File(fileName));
		BufferedReader br = new BufferedReader(input);
		int checkNumber = Integer.parseInt(br.readLine());
		IssueService issueService = new IssueService();
//		System.out.println(I.pageIssueEvents("ventulus95", "BaekjoonAnswer",1));
		HashMap<String, String> filter = new HashMap<String, String>();
		filter.put("direction", "asc");
		// 이슈는 무조건 숫자가 클수록 최신버전이 아님. 2. 0~10
		int size = issueService.getIssues("ventulus95", "BaekjoonAnswer", filter).size();
		for (int num = checkNumber; num < size; num++) {
			System.out.print("Issue #" + (num + 1) + " :");
			Issue currentIssue = issueService.getIssues("ventulus95", "BaekjoonAnswer", filter).get(num);
			System.out.println(currentIssue.getTitle());
			String issueTitle = currentIssue.getTitle();
		}
		BufferedWriter out = new BufferedWriter(
			new FileWriter(new File(fileName)));
		System.out.println(size);
		out.write(String.valueOf(size));
		out.flush();
		out.close();
	}

	public static void main(String[] args) throws IOException {
		run();
	}

}
