package 캘린더;

import java.text.ParseException;
import java.util.Scanner;

public class Prompt {

	public void printMenu() {
		System.out.println("+----------------------+");
		System.out.println("| 1. 일정 등록  ");
		System.out.println("| 2. 일정 검색");
		System.out.println("| 3. 달력 보기");
		System.out.println("| h. 도움말 q. 종료");
		System.out.println("+----------------------+");
//		System.out.println("명령 (1, 2, 3, h, q)");
	}

	/**
	 * 
	 * @param week 요일명
	 * @return 0~6 (0 == Sunday, 6 == Saturday)
	 */

	public int weekDay(String week) {
		switch (week) {

		case "su":
			return 0;
		case "mo":
			return 1;
		case "tu":
			return 2;
		case "we":
			return 3;
		case "th":
			return 4;
		case "fr":
			return 5;
		case "sa":
			return 6;
		default:
			return 0;

		}
	}

	private final static String PROMPT = "cal> ";

	public void runPrompt() throws ParseException {
		printMenu();

		Scanner sc = new Scanner(System.in);
		Calendar cal = new Calendar();

		boolean isLoop = true;
		while (isLoop) {

			System.out.println("명령 (1, 2, 3, h, q)");
			String cmd = sc.next();

			switch (cmd) {
			case "1":
				cmdRegister(sc, cal);
				break;
			case "2":
				cmdSearch(sc, cal);
				break;
			case "3":
				cmdCal(sc, cal);
				break;
			case "h":
				printMenu();
				break;
			case "q":
				isLoop = false;
				System.out.println("Bye~");
				break;

			}
		}
	}

	private void cmdCal(Scanner sc, Calendar c) {
		int month = 0;
		int year = 0;
		System.out.println("년도를 입력하세요.");
		System.out.print(PROMPT);
		year = sc.nextInt();

		System.out.println("월을 입력하세요.");
		System.out.print(PROMPT);
		month = sc.nextInt();

		if (month > 12 || month < 1) {
			System.out.println("잘못된 입력입니다.");
			return;
		}

		c.printSampleCalendar(year, month);
		System.out.println();

	}

	private void cmdSearch(Scanner sc, Calendar c) {
		System.out.println("일정 검색");
		System.out.println("날짜를 입력해주세요 (yyyy-mm-dd)");
		String date = sc.next();
		PlanItem plan;
		plan = c.searchPlan(date);

		if (plan != null) {
			System.out.println(plan.detail);
		} else {
			System.out.println("일정이 없습니다.");
		}

	}

	private void cmdRegister(Scanner sc, Calendar c) throws ParseException {
		System.out.println("새 일정 등록");
		System.out.println("날짜를 입력해주세요 (yyyy-mm-dd)");
		String date = sc.next();
		String text = "";
		System.out.println("일정을 입력해주세요.(문장의 끝에 ;을 입력해주세요.");
		while (true) {
			String word = sc.next();
			text += word + " ";
			if (word.endsWith(";")) {
				break;
			}
		}
		
		c.registerPlan(date, text);

	}

	public static void main(String[] args) throws ParseException {
		// 셀 실행
		Prompt p = new Prompt();
		p.runPrompt();

	}

}
