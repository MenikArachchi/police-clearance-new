
// ************** Document Ready  *************
$( document ).ready(function() {
	/**Used to toggle the insurance and VET company names in the index page*/
	$('.toggle-title').click(function(){
		if($(this).next().css('display') =='none'){
			$(this).next().css('display','block');
			$(this).find('img').attr('src','../assets/images/hspminus.gif');
		}else{
			$(this).next().css('display','none');
			$(this).find('img').attr('src','../assets/images/hspplus.gif');

		}
	})
	
});
/*
var message_si = 'මෙම ක්‍රියාව මගින් ඔබගේ දත්ත අහිමි විය හැක.\nභාෂාව වෙනස් කිරීම අවශ්‍ය වේද ?';
var message_ta = 'இந்த அறுவை சிகிச்சை மூலம் தரவு இழக்க கூடும்.\nநீங்கள் தொடர விரும்புகிறீர்களா ?';
var message_en = 'You might loose the data by this operation.\nDo you want to continue ?';

function getNextLangMessage(currentLang) {
	if (currentLang == 'si') {
		return message_si;
	} else if (currentLang == 'ta') {
		return message_ta;
	} else {
		return message_en;
	}
}

function getLangCookie(cookieName) {
	var cookies = document.cookie;
	var index = cookies.indexOf(cookieName);
	if (index != -1) {
		return cookies.substring(index + 8, index + 10);
	} else {
		return "";
	}
}

window.onbeforeunload = function() {
	var langValue = getLangCookie("ch-lang");
		
	if (langValue == 'ys') {
		return getNextLangMessage(currentLang);
	}	
};
*/
