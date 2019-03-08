/**
 * 将原字符串中所包含的输入字符内容高亮
 * 
 * @param inputStr-输入的需要高亮的字符串
 * @param str-原字符串
 * @param highClass-高亮显示的样式名
 * @return showText-添加上高亮样式后的字符串
 */
function replaceHigh(inputStr, str, highClass) {
	/*- 获取输入字符的不区分大小写的正则字符串 -*/
	var reg = new RegExp(inputStr, "i");
	/*- 以输入字符分割原字符串 -*/
	var arrObj = str.split(reg);
	/*- 直接将arrObj赋值给arrayObj不能实现效果 -*/
	var arrayObj = new Array();
	/*- 对原字符串中包含的输入字符的前后位置插入'|'字符 -*/
	var index = 0;
	for ( var i = 0; i < arrObj.length; i++) {
		arrayObj[i] = arrObj[i];
		arrayObj[i] = "|" + arrayObj[i] + "|";
		//将字符串分为两部分，防止重复替换同样字符，如ICBC
		strBefore=str.substr(0,index);
		strAfter=str.substr(index);
		strAfter = strAfter.replace(arrObj[i], arrayObj[i]);
		str= strBefore+""+strAfter;
		index = str.lastIndexOf('|');
	}
	/*- 以|再次分割字符串，将原字符串中的输入字符与非输入字符分隔开 -*/
	var array = str.split("|");
	var showText = "";
	var before = "<span class='" + highClass + "'>";
	var after = "</span>";
	/*- 对原字符串中包含的输入字符添加样式 -*/
	for ( var i = 0; i < array.length; i++) {
		if (array[i] == "") {
		} else if (array[i].toUpperCase() == inputStr.toUpperCase()) {
			array[i] = before + array[i] + after;
		}
		showText = "" + showText + array[i];
	}
	return showText;
}
