package com.interyard.review.util;

public class NameStringUtil {

	public static String nameExchange(String str) {

		// 아이디나 이름이 3보다 작으면 그냥 내보낸다.
		if (str == null || str.length() < 3) {
			// 3보다 작을 경우
			String visiblePart = str.substring(0, 1);
			StringBuilder obfuscatedPart = new StringBuilder();
			for (int i = 1; i < str.length(); i++) {
				obfuscatedPart.append("*");
			}

			return visiblePart + obfuscatedPart.toString();
		} else {

			// 3보다 클 경우 1자리 수 이후부터 ** 으로 표기
			// 가지고 온 아이디나 이름을 2자리 수 이후부터 이름 변경
			String visiblePart = str.substring(0, 2);
			StringBuilder obfuscatedPart = new StringBuilder();
			for (int i = 2; i < str.length(); i++) {
				obfuscatedPart.append("*");
			}

			return visiblePart + obfuscatedPart.toString();
		}
	}

}
