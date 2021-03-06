package com.naver.hackday.devcenterbot;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.naver.hackday.devcenterbot.model.BotRequest;
import com.naver.hackday.devcenterbot.model.KeywordChecker;
import com.naver.hackday.devcenterbot.model.TitleScrapper;

@SpringBootTest
class DevcenterBotApplicationTests {

	@Autowired
	private TitleScrapper titleScrapper;
	@Autowired
	private KeywordChecker keywordChecker;

	@Test
	void contextLoads() {
	}

	@Test
	public void testNewBotRequest() {
		BotRequest botRequest = BotRequest.SMART_EDITOR_REQUEST.issueNumber(String.valueOf(2)).build();
		String keyword = botRequest.getKeyword().getWord();
		int issueNumber = Integer.parseInt(botRequest.getIssueNumber());

		assertThat(keyword).isEqualTo("smart-editor");
		assertThat(issueNumber).isEqualTo(2);
	}

	@Test
	public void testQueue() throws IOException {
		keywordChecker.checkToTitle(titleScrapper.run());
	}
}
