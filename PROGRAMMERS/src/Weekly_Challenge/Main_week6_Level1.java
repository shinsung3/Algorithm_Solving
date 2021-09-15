package Weekly_Challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main_week6_Level1 {
	public static void main(String[] args) {
		int[] weights = { 60, 70, 60 };
		String[] head2head = { "NWN", "LNN", "NNN" };
//		result = [3,4,1,2]
		int[] arr = solution(weights, head2head);
		System.out.println(Arrays.toString(arr));
	}

	static int[] solution(int[] weights, String[] head2head) {
		int[] answer = new int[weights.length];
		char[][] arr = new char[weights.length][weights.length];
		for (int i = 0; i < head2head.length; i++) {
			arr[i] = head2head[i].toCharArray();
		}
		List<Info> list = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			int cnt = 0;
			int winCnt = 0;
			int total = 0;
			for (int j = 0; j < arr.length; j++) {
				if (arr[i][j] == 'W') {
					if (weights[i] < weights[j]) {
						winCnt++;
					}
					cnt++;
				}
				if (arr[i][j] != 'N') {
					total++;
				}
			}
			double win = 0;
			if (total != 0) {
				win = ((double) cnt / (double) (total));
			}
			list.add(new Info(win, winCnt, weights[i], i + 1));
		}
		Collections.sort(list, new Comparator<Info>() {

			@Override
			public int compare(Info o1, Info o2) {

				if (o1.win == o2.win) {
					if (o1.winCnt == o2.winCnt) {
						if (o1.weight == o2.weight) {
							return o2.idx - o1.idx;
						} else {
							return o1.weight - o2.weight;
						}
					} else {
						return o1.winCnt - o2.winCnt;
					}
				} else {
					return (o1.win - o2.win)>0 ? 1:-1;
				}
			}
		});

		System.out.println(list);

		int idx = 0;
		for (int i = list.size() - 1; i >= 0; i--) {
			answer[idx] = list.get(i).idx;
			idx++;
		}

		return answer;
	}

	static class Info {
		double win;
		int winCnt;
		int weight;
		int idx;

		public Info(double win, int winCnt, int weight, int idx) {
			super();
			this.win = win;
			this.winCnt = winCnt;
			this.weight = weight;
			this.idx = idx;
		}

		@Override
		public String toString() {
			return "Info [win=" + win + ", winCnt=" + winCnt + ", weight=" + weight + ", idx=" + idx + "]";
		}
	}
}
