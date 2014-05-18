package org.yangchigi.support;

public class MyString {


	public static String replace(String value) {
		if (value != null) {
			if (value.contains("<"))
				value = value.replace("<", "&lt;");

			if (value.contains(">"))
				value = value.replace(">", "&gt;");
		}
		return value;
	}

}
