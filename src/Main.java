import java.util.*;

import java.util.*;

public class Main {
	static void makeTestData() {

		for (int i = 0; i < 100; i++){

			int id = i + 1;
			articles.add(new Article(id, "제목"+ id, "내용"+ id));
		}

	}
	static int articlesLastId = 0;
	static Scanner sc = Container.sc;
	static List<Article> articles = new ArrayList<>();
	public static void main(String[] args) {

		System.out.println("== 게시판 v 0.1 ==");
		System.out.println("== 프로그램 시작 ==");



		makeTestData();

		if (articles.size() > 0) {
			articlesLastId = articles.get(articles.size() - 1).id;
		}

		while (true) {
			System.out.printf("명령) ");
			String cmd = sc.nextLine();

			Rq rq = new Rq(cmd);

			if (rq.getUrlPath().equals("exit")) {
				break;
			} else if (rq.getUrlPath().equals("/usr/article/list")) {

				ActionUserList(rq);

			} else if (rq.getUrlPath().equals("/usr/article/detail")) {
				ActionUserDetail(rq);

			} else if (rq.getUrlPath().equals("/usr/article/write")) {
				ActionUserWrite();
			} else {
				System.out.printf("입력된 명령어 : %s\n", cmd);
			}
		}

		System.out.println("== 프로그램 종료 ==");

		sc.close();
	}

	private static void ActionUserWrite() {
		System.out.println("- 게시물 등록 -");
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		int id = articlesLastId + 1;
		articlesLastId = id;

		Article article = new Article(id, title, body);
		articles.add(article);
		System.out.println("생성된 게시물 객체 : " + article);

		System.out.printf("%d번 게시물이 입력되었습니다.\n", article.id);
	}

	private static void ActionUserDetail(Rq rq) {
		if (rq.getParams().containsKey("id") == false) {
			System.out.println("id를 입력해주세요.");
			return;
		}

		int id = 0;

		try {
			id = Integer.parseInt(rq.getParams().get("id"));
		} catch (NumberFormatException e) {
			System.out.println("id를 정수형태로 입력해주세요.");
			return;
		}

		if (id > articles.size()) {
			System.out.println("게시물이 존재하지 않습니다.");
			return;
		}

		Article article = articles.get(id - 1);

		System.out.println("- 게시물 상세내용 -");
		System.out.printf("번호 : %d\n", article.id);
		System.out.printf("제목 : %s\n", article.title);
		System.out.printf("내용 : %s\n", article.body);
	}

	private static void ActionUserList(Rq rq) {
		System.out.println("- 게시물 리스트 -");
		System.out.println("--------------------");
		System.out.println("번호 / 제목");
		System.out.println("--------------------");

		boolean orderByIdDesc = true;

		List<Article> filteredArticles = articles;

		if (rq.getParams().containsKey("searchKeyword")){
			String searchKeyword = rq.getParams().get("searchKeyword");

			filteredArticles = new ArrayList<>();

			for (Article article : articles){
				boolean matched = article.title.contains(searchKeyword) || article.body.contains(searchKeyword);
				if (matched){
					filteredArticles.add(article);
				}
			}
		}
		List<Article> sortedArticles = filteredArticles;

		if (rq.getParams().containsKey("orderBy") && rq.getParams().get("orderBy").equals("idAsc")) {
			orderByIdDesc = false;
		}

		if (orderByIdDesc) {
			sortedArticles = Util.reverseList(sortedArticles);
		}

		for (Article article : sortedArticles) {
			System.out.printf("%d / %s\n", article.id, article.title);

		}
	}

}


