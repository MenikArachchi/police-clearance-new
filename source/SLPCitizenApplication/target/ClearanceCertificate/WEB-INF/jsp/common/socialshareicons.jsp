<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="col-1 clear-float">
<div style="padding-top:10px;">&nbsp;</div>
	<div class="col-1">
		<table style="width: 100%">
			<tr style="width: 50%">
				<td>
					<div class="button-center padding-top">
						<div class="display-change login-images">
							<img src="images/1919web banner-small.jpg" alt="">
						</div>
					</div>
				</td> 
				<td style="width: 50%">
					<div class="social-row ">
						<span class="social-buttons"> <a
							onclick="viewPopup('https://www.facebook.com/sharer/sharer.php?u=<s:text name="global.social.share.url"/>','Facebook')">
								<img title="Facebook" src="images/facebookIcon.png"
								class="social-image-round" alt="">
						</a>
						</span> <span class="social-buttons"> <a
							onclick="viewPopup('https://twitter.com/intent/tweet?url=<s:text name="global.social.share.url"/>&amp;text=<s:text name="global.social.share.title"/>&amp;hashtags=Government,VehicleInformation,DMT','Twitter')">
								<img title="Twitter" src="images/twitterIcon.png"
								class="social-image-round" alt="">
						</a>
						</span> <span class="social-buttons"> <a
							onclick="viewPopup('https://plus.google.com/share?url=<s:text name="global.social.share.url"/>&amp;gpsrc=frameless&amp;partnerid=frameless','Google+')">
								<img title="Google+" src="images/googlePlusIcon.png"
								class="social-image-round" alt="">
						</a>
						</span> <span class="social-buttons"> <a
							onclick="viewPopup('http://www.linkedin.com/shareArticle?mini=true&amp;url=<s:text name="global.social.share.url"/>&amp;title=<s:text name="global.social.share.title"/>','LinkedIn')">
								<img title="LinkedIn" src="images/linkedInIcon.png"
								class="social-image-round" alt="">
						</a>
						</span>
					</div>
				</td>
			</tr>
		</table>
	</div>
</div>

<script language="javascript" type="text/javascript">
function viewPopup(url, title) {
 	window.open(url, title, "width=500, height=450");
 	return false;
 }
</script>
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  //ga('set', 'userId', {{USER_ID}}); // Set the user ID using signed-in user_id.
  ga('create', 'UA-49623813-2', 'auto');
  ga('send', 'pageview');

</script>