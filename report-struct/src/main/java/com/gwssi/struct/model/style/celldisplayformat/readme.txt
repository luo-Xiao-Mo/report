/**
 * 数字格式化
 * 
 * version = "20160516";
 * 
 * 实现 显示格式规则 r1。近似参考 Excel
 * 
 *（1）整数部分千分位分隔符（英文逗号）。
 *（2）整数部分最小长度，并支持使用0补齐位数。
 *（3）小数部分精度。
 *（4）小数部分最小长度，并支持使用0补齐位数。
 *（5）小数部分，可以包含*，表示不控制精度。
 *
 *	// t(pattern, number, result)
	t("#", "12123456.55", "12123457");
	t("#.", "12123456.55", "12123457.");
	t("#.*", "12123456.55", "12123456.55");
	t("#.#", "12123456.55", "12123456.6");
	t("#.#00", "12123456.55", "12123456.550");
	t("#,###.", "12123456.55", "12,123,457.");
	t("00#,###.", "12123456.55", "012,123,457.");
	t("000#######.", "12123456.55", "0012123457.");
	
	t("#.#", "12123459.95", "12123460.0");
	t("#.##", "12123459.995", "12123460.00");
	
	t("00#,###.##0", "-12123456.55", "-012,123,456.550");
 * 
 */
 
 