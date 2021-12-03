package com.asj.exam.board;

import java.util.*;

public class Main {

	public static void main(String[] args) {

		Scanner sc = Container.sc;

		System.out.println("== 게시판 v 0.1 ==");
		System.out.println("== 프로그램 시작 ==");


		while (true) {
			System.out.printf("명령) ");
			String cmd = sc.nextLine();

			Rq rq = new Rq(cmd);

			if (rq.getUrlPath().equals("exit")) {
				break;
			} else if (rq.getUrlPath().equals("/usr/article/list")) {
				Container.usrArticlesController.ActionUsrList(rq);
			} else if (rq.getUrlPath().equals("/usr/article/detail")) {
				Container.usrArticlesController.ActionUsrDetail(rq);
			} else if (rq.getUrlPath().equals("/usr/article/write")) {
				Container.usrArticlesController.ActionUsrWrite();
			} else {
				System.out.printf("입력된 명령어 : %s\n", cmd);
			}
		}

		System.out.println("== 프로그램 종료 ==");

		sc.close();
	}

}

