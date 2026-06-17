	
	<div style="display:none;">
			<a data-toggle="modal" id="pptViewModellLink" href="#pptViewModelPopUp">View</a>
			<a data-toggle="modal" id="nicViewModellLink" href="#nicViewModelPopUp">View</a>
			<a data-toggle="modal" id="newNicViewModellLink" href="#newNicViewModelPopUp">View</a>
			<a data-toggle="modal" id="bcViewModellLink" href="#bcViewModelPopUp">View</a>
			<a data-toggle="modal" id="slbfeViewModellLink" href="#slbfeViewModelPopUp">View</a>
			<a data-toggle="modal" id="affidavitViewModellLink" href="#affidavitViewModelPopUp">View</a>
	</div>
	
	<!--  #####################################################	PPT VIEW POPUP   ######################################################## -->
		<div class="modal fade" id="pptViewModelPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		 <div class="modal-dialog">
   			 <div class="modal-content">
				<div class="modal-header">
				     <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				     <span class="modal-title" style="font-size:15px;font-weight:bold;" >Passport Verification</span>
				</div>
				<div class="modal-body">
					<div style="text-align: center;">
						<span style="font-size:18px;font-weight:bold;" >Passport No. <span id="passportNumberAppend"></span></span>
					</div>
					<div>
						<div style="padding: 5px 0px;">
							<span style="font-size:16px;font-weight:bold;" >Scanned Passport Copy from Applicant</span>
						</div>
						<div id="passportLinkDiv" >							
							<div style="padding: 5px 0px;font-size:14px;">
								Please click 
									<a id="passportFileName" target="_blank" href="javascript:void(0)">
										<span style="font-weight:bold;text-decoration:underline; ">here</span>
									</a>
								 to Open/Download Personal detail page file							
							</div>
							<div style="padding: 10px;text-align: center;">
								<a href="images/no_preview_available.png" class="passportImge_link image-link" id="passportImge_link">
  									<img class="image-link"  src="images/preloader_large.gif" id="passportImge" style="width:275px;height:280px;border: 1px solid #000;" /> 
								</a>	
								
							</div>
						</div>
						<div id="passportLinkDivBack" >							
							<div style="padding: 5px 0px;font-size:14px;" id="showHidePptLinkBack">
								Please click 
									<a id="passportFileNameBack" target="_blank" href="javascript:void(0)">
										<span style="font-weight:bold;text-decoration:underline; ">here</span>
									</a>
								 to Open/Download Countries allowed page file								
							</div>
							<div style="padding: 10px;text-align: center;">
								<a href="images/no_preview_available.png" class="passportImge_link image-link" id="passportImge_link_back">
  									<img class="image-link"  src="images/preloader_large.gif" id="passportImgeBack" style="width:275px;height:280px;border: 1px solid #000;" /> 
								</a>	
								
							</div>
						</div>						
					</div>					
				 </div>				 
				 <div class="modal-footer">
				 	<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				 </div>
				</div>
			</div>
		</div>
		
		<!--  #####################################################	NIC VIEW POPUP   ######################################################## -->
		<div class="modal fade" id="nicViewModelPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		 <div class="modal-dialog">
   			 <div class="modal-content">
				<div class="modal-header">
				     <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				     <span class="modal-title" style="font-size:15px;font-weight:bold;" >NIC Verification</span>
				</div>
				<div class="modal-body">
					<div style="text-align: center;  ">
						<span style="font-size:18px;font-weight:bold;" >NIC No. <span id="nicNumberAppend"></span></span>
					</div>
					<div>
						<div style="padding: 5px 0px;">
							<span style="font-size:16px;font-weight:bold;" >Scanned NIC Copy from Applicant</span>
						</div>
						<div id="nicLinkDiv">
							<div style="padding: 5px 0px;font-size:14px;">
								Please click 
								<a id="nicFileName" target="_blank" href="javascript:void(0)">
									<span style="font-weight:bold;text-decoration:underline; ">here</span>
								</a>
								 to Open/Download Nic Front side file							
							</div>
							<div style="padding: 10px;text-align: center;">
								<a href="images/no_preview_available.png" class="nicImge_link image-link" id="nicImge_link">
  									<img class="image-link"  src="images/preloader_large.gif" id="nicImge" style="width:275px;height:280px;border: 1px solid #000;" />
								</a>
							</div>
						</div>
						<br />
						<div id="nicLinkDivBack">
							<div style="padding: 5px 0px;font-size:14px;" id="showHideNicLinkBack">
								Please click 
								<a id="nicFileNameBack" target="_blank" href="javascript:void(0)">
									<span style="font-weight:bold;text-decoration:underline; ">here</span>
								</a>
								 to Open/Download Nic Back side file								
							</div>
							<div style="padding: 10px;text-align: center;">
								<a href="images/no_preview_available.png" class="nicImge_link image-link" id="nicImge_link_back">
  									<img class="image-link"  src="images/preloader_large.gif" id="nicImgeBack" style="width:275px;height:280px;border: 1px solid #000;" />
								</a>
							</div>
						</div>
						
					</div>
					
				 </div>				 
				 <div class="modal-footer">
				 	<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				 </div>
				</div>
			</div>
		</div>


    <!--  ##################################################### New	NIC VIEW POPUP   ######################################################## -->
    <div class="modal fade" id="newNicViewModelPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <span class="modal-title" style="font-size:15px;font-weight:bold;" >New NIC Verification</span>
                </div>
                <div class="modal-body">
                    <div style="text-align: center;  ">
                        <span style="font-size:18px;font-weight:bold;" >New NIC No. <span id="newNicNumberAppend"></span></span>
                    </div>
                    <div>
                        <div style="padding: 5px 0px;">
                            <span style="font-size:16px;font-weight:bold;" >Scanned New NIC Copy from Applicant</span>
                        </div>
                        <div id="newNicLinkDiv">
                            <div style="padding: 5px 0px;font-size:14px;">
                                Please click
                                <a id="newNicFileName" target="_blank" href="javascript:void(0)">
                                    <span style="font-weight:bold;text-decoration:underline; ">here</span>
                                </a>
                                to Open/Download New Nic Front side file
                            </div>
                            <div style="padding: 10px;text-align: center;">
                                <a href="images/no_preview_available.png" class="nicImge_link image-link" id="newNicImge_link">
                                    <img class="image-link"  src="images/preloader_large.gif" id="newNicImge" style="width:275px;height:280px;border: 1px solid #000;" />
                                </a>
                            </div>
                        </div>
                        <br />
                        <div id="newNicLinkDivBack">
                            <div style="padding: 5px 0px;font-size:14px;" id="showHideNewNicLinkBack">
                                Please click
                                <a id="newNicFileNameBack" target="_blank" href="javascript:void(0)">
                                    <span style="font-weight:bold;text-decoration:underline; ">here</span>
                                </a>
                                to Open/Download New Nic Back side file
                            </div>
                            <div style="padding: 10px;text-align: center;">
                                <a href="images/no_preview_available.png" class="nicImge_link image-link" id="newNicImge_link_back">
                                    <img class="image-link"  src="images/preloader_large.gif" id="newNicImgeBack" style="width:275px;height:280px;border: 1px solid #000;" />
                                </a>
                            </div>
                        </div>

                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <!--  #####################################################	BC VIEW POPUP   ######################################################## -->
		<div class="modal fade" id="bcViewModelPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		 <div class="modal-dialog">
   			 <div class="modal-content">
				<div class="modal-header">
				     <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				     <span class="modal-title" style="font-size:15px;font-weight:bold;" >Birth certificate</span>
				</div>
				<div class="modal-body">					
					<div>
						<div style="padding: 5px 0px;">
							<span style="font-size:16px;font-weight:bold;" >Scanned Birth certificate from Applicant</span>
						</div>
						<div id="bcLinkDiv">
							<div style="padding: 5px 0px;font-size:14px;">
								Please click 
								<a id="bcFileName" target="_blank" href="javascript:void(0)">
									<span style="font-weight:bold;text-decoration:underline; ">here</span>
								</a>
								 to Open/Download front side file							
							</div>
							<div style="padding: 10px;text-align: center;">
								<a href="images/no_preview_available.png" class="bcImge_link image-link" id="bcImge_link">
  									<img class="image-link"  src="images/preloader_large.gif" id="bcImge" style="width:275px;height:280px;border: 1px solid #000;" />
								</a>
							</div>
						</div>
						<div id="bcLinkDivBack" >							
							<div style="padding: 5px 0px;font-size:14px;" id="showHideBcLinkBack">
								Please click 
									<a id="bcFileNameBack" target="_blank" href="javascript:void(0)">
										<span style="font-weight:bold;text-decoration:underline; ">here</span>
									</a>
								 to Open/Download back side	file							
							</div>
							<div style="padding: 10px;text-align: center;">
								<a href="images/no_preview_available.png" class="passportImge_link image-link" id="bcImge_link_back">
  									<img class="image-link"  src="images/preloader_large.gif" id="bcImgeBack" style="width:275px;height:280px;border: 1px solid #000;" /> 
								</a>								
							</div>
						</div>
						
					</div>
					
				 </div>				 
				 <div class="modal-footer">
				 	<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				 </div>
				</div>
			</div>
		</div>
		
		
		<!--  #####################################################	SLBFE VIEW POPUP   ######################################################## -->
		<div class="modal fade" id="slbfeViewModelPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		 <div class="modal-dialog">
   			 <div class="modal-content">
				<div class="modal-header">
				     <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				     <span class="modal-title" style="font-size:15px;font-weight:bold;" >SLBFE Letter</span>
				</div>
				<div class="modal-body">					
					<div>
						<div style="padding: 5px 0px;">
							<span style="font-size:16px;font-weight:bold;" >Scanned SLBFE Letter from Applicant</span>
						</div>
						<div id="slbfeLinkDiv">
							<div style="padding: 5px 0px;font-size:14px;">
								Please click 
								<a id="slbfeFileName" target="_blank" href="javascript:void(0)">
									<span style="font-weight:bold;text-decoration:underline; ">here</span>
								</a>
								 to Open/Download file								
							</div>
							<div style="padding: 10px;text-align: center;">
								<a href="images/no_preview_available.png" class="slbfeImge_link image-link" id="slbfeImge_link">
  									<img class="image-link"  src="images/preloader_large.gif" id="slbfeImge" style="width:275px;height:280px;border: 1px solid #000;" />
								</a>
							</div>
						</div>
						
					</div>
					
				 </div>				 
				 <div class="modal-footer">
				 	<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				 </div>
				</div>
			</div>
		</div>
		
<!--  #####################################################	Affidavit VIEW POPUP   ######################################################## -->
		<div class="modal fade" id="affidavitViewModelPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		 <div class="modal-dialog">
   			 <div class="modal-content">
				<div class="modal-header">
				     <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				     <span class="modal-title" style="font-size:15px;font-weight:bold;" >Affidavit</span>
				</div>
				<div class="modal-body">
					<div>
						<div style="padding: 5px 0px;">
							<span style="font-size:16px;font-weight:bold;" >Scanned affidavit from Applicant</span>
						</div>
						<div id="affidavitLinkDiv">
							<div style="padding: 5px 0px;font-size:14px;">
								Please click
								<a id="affidavitFileName" target="_blank" href="javascript:void(0)">
									<span style="font-weight:bold;text-decoration:underline; ">here</span>
								</a>
								 to Open/Download file
							</div>
							<div style="padding: 10px;text-align: center;">
								<a href="images/no_preview_available.png" class="slbfeImge_link image-link" id="affidavirImge_link">
  									<img class="image-link"  src="images/preloader_large.gif" id="affidavitImge" style="width:275px;height:280px;border: 1px solid #000;" />
								</a>
							</div>
						</div>

					</div>

				 </div>
				 <div class="modal-footer">
				 	<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				 </div>
				</div>
			</div>
		</div>

<script type="text/javascript">

	function viewNicPopup(applicationId){
		  blockUI();			
		  $('#nicImge').attr('src','images/preloader_large.gif');
		 $('#nicImgeBack').attr('src','images/preloader_large.gif');
		  //blockUI();		
		 //alert(applicationId);	
		 var fileName=$('#hiddenNicFileName_' + applicationId).val();
		 var fileType=$('#hiddenNicFileType_' + applicationId).val();
		 
		 var fileNameBack=$('#hiddenNicFileNameBack_' + applicationId).val();
		 var fileTypeBack=$('#hiddenNicFileTypeBack_' + applicationId).val();
		 
		 var pptNo=$('#hiddenNicNo_' + applicationId).val();

		 if(fileType=='IMAGE'){
			 $('#nicImge').attr('src','policeFileFinder.htm?fileName=' + fileName);		
			 $('#nicImge_link').attr('href','policeFileFinder.htm?fileName=' + fileName);
		 }else{
			 $('#nicImge').attr('src','images/no_preview_available.png');
			 $('#nicImge_link').attr('href','images/no_preview_available.png');
		 }
		 
		 if(fileTypeBack=='IMAGE'){
			 $('#nicImgeBack').attr('src','policeFileFinder.htm?fileName=' + fileNameBack);		
			 $('#nicImge_link_back').attr('href','policeFileFinder.htm?fileName=' + fileNameBack);
		 }else{
			 $('#nicImgeBack').attr('src','images/no_preview_available.png');
			 $('#nicImge_link_back').attr('href','images/no_preview_available.png');
		 }
		 
		 if(fileName != ""){
			$('#showHideNicLink').show();
		 } else {
			$('#showHideNicLink').hide();
		 }
		 
		 if(fileNameBack != ""){
			$('#showHideNicLinkBack').show();
		 } else {
			$('#showHideNicLinkBack').hide();
		 }
		 
		 //$('#nicFileName').html(fileName);
		 $('#nicFileName').attr('href','policeFileFinder.htm?fileName=' + fileName);
		 $('#nicFileNameBack').attr('href','policeFileFinder.htm?fileName=' + fileNameBack);
		 
		 $('#nicNumberAppend').html(pptNo);
		 
		 initImageNic();
		 
		 $('#nicViewModellLink').click();	
		 unBlockUI();
	}

    function viewNewNicPopup(applicationId){
        blockUI();
        $('#newNicImge').attr('src','images/preloader_large.gif');
        $('#newNicImgeBack').attr('src','images/preloader_large.gif');
        //blockUI();
        //alert(applicationId);
        var fileName=$('#hiddenNewNicFileName_' + applicationId).val();
        var fileType=$('#hiddenNewNicFileType_' + applicationId).val();

        var fileNameBack=$('#hiddenNewNicFileNameBack_' + applicationId).val();
        var fileTypeBack=$('#hiddenNewNicFileTypeBack_' + applicationId).val();

        var pptNo=$('#hiddenNewNicNo_' + applicationId).val();

        if(fileType=='IMAGE'){
            $('#newNicImge').attr('src','policeFileFinder.htm?fileName=' + fileName);
            $('#newNicImge_link').attr('href','policeFileFinder.htm?fileName=' + fileName);
        }else{
            $('#newNicImge').attr('src','images/no_preview_available.png');
            $('#newNicImge_link').attr('href','images/no_preview_available.png');
        }

        if(fileTypeBack=='IMAGE'){
            $('#newNicImgeBack').attr('src','policeFileFinder.htm?fileName=' + fileNameBack);
            $('#newNicImge_link_back').attr('href','policeFileFinder.htm?fileName=' + fileNameBack);
        }else{
            $('#newNicImgeBack').attr('src','images/no_preview_available.png');
            $('#newNicImge_link_back').attr('href','images/no_preview_available.png');
        }

        if(fileName != ""){
            $('#showHideNewNicLink').show();
        } else {
            $('#showHideNewNicLink').hide();
        }

        if(fileNameBack != ""){
            $('#showHideNewNicLinkBack').show();
        } else {
            $('#showHideNewNicLinkBack').hide();
        }

        //$('#nicFileName').html(fileName);
        $('#newNicFileName').attr('href','policeFileFinder.htm?fileName=' + fileName);
        $('#newNicFileNameBack').attr('href','policeFileFinder.htm?fileName=' + fileNameBack);

        $('#newNicNumberAppend').html(pptNo);

        initImageNewNic();

        $('#newNicViewModellLink').click();
        unBlockUI();
    }

    function initImageNewNic(){
        $('#newNicImge_link').magnificPopup({
            type: 'image',
            closeOnContentClick: true,
            closeBtnInside: false,
            mainClass: 'mfp-no-margins mfp-with-zoom', // class to remove default margin from left and right side
            image: {
                verticalFit: true
            },
            zoom: {
                enabled: true,
                duration: 300 // don't foget to change the duration also in CSS
            }
        });

        $('#newNicImge_link_back').magnificPopup({
            type: 'image',
            closeOnContentClick: true,
            closeBtnInside: false,
            mainClass: 'mfp-no-margins mfp-with-zoom', // class to remove default margin from left and right side
            image: {
                verticalFit: true
            },
            zoom: {
                enabled: true,
                duration: 300 // don't foget to change the duration also in CSS
            }
        });
    }
	
	function viewPptPopup(applicationId){
			 blockUI();
			
			 $('#passportImge').attr('src','images/preloader_large.gif');
			$('#passportImgeBack').attr('src','images/preloader_large.gif');
			// blockUI();
			//alert(applicationId);
			 var fileName=$('#hiddenPptFileName_' + applicationId).val();
			 var fileType=$('#hiddenPptFileType_' + applicationId).val();
			 
			 var fileNameBack=$('#hiddenPptFileNameBack_' + applicationId).val();
			 var fileTypeBack=$('#hiddenPptFileTypeBack_' + applicationId).val();
			 
			 var pptNo=$('#hiddenPptNo_' + applicationId).val();

			 if(fileType=='IMAGE'){
				 $('#passportImge').attr('src','policeFileFinder.htm?fileName=' + fileName);	
				 $('#passportImge_link').attr('href','policeFileFinder.htm?fileName=' + fileName);	
			 }else{
				 $('#passportImge').attr('src','images/no_preview_available.png');
				 $('#passportImge_link').attr('href','images/no_preview_available.png');
			 }
			 
			 if(fileTypeBack=='IMAGE'){
				 $('#passportImgeBack').attr('src','policeFileFinder.htm?fileName=' + fileNameBack);	
				 $('#passportImge_link_back').attr('href','policeFileFinder.htm?fileName=' + fileNameBack);	
			 }else{
				 $('#passportImgeBack').attr('src','images/no_preview_available.png');
				 $('#passportImge_link_back').attr('href','images/no_preview_available.png');
			 }
			 
			 if(fileName != ""){
				$('#showHidePptLink').show();
			 } else {
				$('#showHidePptLink').hide();
			 }
			 
			 if(fileNameBack != ""){
				$('#showHidePptLinkBack').show();
			 } else {
				$('#showHidePptLinkBack').hide();
			 }
			 
			 //$('#passportFileName').html(fileName);
			 $('#passportFileName').attr('href','policeFileFinder.htm?fileName=' + fileName);
			 $('#passportFileNameBack').attr('href','policeFileFinder.htm?fileName=' + fileNameBack);
			 
			 $('#passportNumberAppend').html(pptNo);
			 
			 initImagePassport();
			 
			 $('#pptViewModellLink').click();	
			 //unBlockUI();	
			 unBlockUI();
		}
	
	function viewBcPopup(applicationId){
			 blockUI();
			
			 $('#bcImge').attr('src','images/preloader_large.gif');
		    $('#bcImgeBack').attr('src','images/preloader_large.gif');
			// blockUI();
			//alert(applicationId);
			 var fileName=$('#hiddenBcFileName_' + applicationId).val();
			 var fileType=$('#hiddenBcFileType_' + applicationId).val();
			 
			 var fileNameBack=$('#hiddenBcFileName_back_' + applicationId).val();
			 var fileTypeBack=$('#hiddenBcFileType_back_' + applicationId).val();
			 
			 var pptNo=$('#hiddenBcNo_' + applicationId).val();
			 
			 if(fileType=='IMAGE'){
				 $('#bcImge').attr('src','policeFileFinder.htm?fileName=' + fileName);	
				 $('#bcImge_link').attr('href','policeFileFinder.htm?fileName=' + fileName);	
			 }else{
				 $('#bcImge').attr('src','images/no_preview_available.png');
				 $('#bcImge_link').attr('href','images/no_preview_available.png');
			 }
			 
			 if(fileTypeBack=='IMAGE'){
				 $('#bcImgeBack').attr('src','policeFileFinder.htm?fileName=' + fileNameBack);	
				 $('#bcImge_link_back').attr('href','policeFileFinder.htm?fileName=' + fileNameBack);	
			 }else{
				 $('#bcImgeBack').attr('src','images/no_preview_available.png');
				 $('#bcImge_link_back').attr('href','images/no_preview_available.png');
			 }
			 
			 if(fileName != ""){
				$('#showHideBcLink').show();
			 } else {
				$('#showHideBcLink').hide();
			 }
			 
			 if(fileNameBack != ""){
				$('#showHideBcLinkBack').show();
			 } else {
				$('#showHideBcLinkBack').hide();
			 }
			 
			 //$('#passportFileName').html(fileName);
			 $('#bcFileName').attr('href','policeFileFinder.htm?fileName=' + fileName);
			 $('#bcFileNameBack').attr('href','policeFileFinder.htm?fileName=' + fileNameBack);
			 
			 $('#bcNumberAppend').html(pptNo);
			 
			 initImageBc();
			 
			 $('#bcViewModellLink').click();	
			 unBlockUI();
		}

    function initImageBc(){
        $('#bcImge_link').magnificPopup({
            type: 'image',
            closeOnContentClick: true,
            closeBtnInside: false,
            mainClass: 'mfp-no-margins mfp-with-zoom', // class to remove default margin from left and right side
            image: {
                verticalFit: true
            },
            zoom: {
                enabled: true,
                duration: 300 // don't foget to change the duration also in CSS
            }
        });

        $('#bcImge_link_back').magnificPopup({
            type: 'image',
            closeOnContentClick: true,
            closeBtnInside: false,
            mainClass: 'mfp-no-margins mfp-with-zoom', // class to remove default margin from left and right side
            image: {
                verticalFit: true
            },
            zoom: {
                enabled: true,
                duration: 300 // don't foget to change the duration also in CSS
            }
        });
    }
	
	function viewSlbfePopup(applicationId){
			 blockUI();
			
			 var fileName=$('#hiddenSlbfeFileName_' + applicationId).val();
			 var fileType=$('#hiddenSlbfeFileType_' + applicationId).val();
	
			 if(fileType=='IMAGE'){
				 $('#slbfeImge').attr('src','policeFileFinder.htm?fileName=' + fileName);	
				 $('#slbfeImge_link').attr('href','policeFileFinder.htm?fileName=' + fileName);	
			 }else{
				 $('#slbfeImge').attr('src','images/no_preview_available.png');
				 $('#slbfeImge_link').attr('href','images/no_preview_available.png');
			 }
			 
			 //$('#passportFileName').html(fileName);
			 $('#slbfeFileName').attr('href','policeFileFinder.htm?fileName=' + fileName);
			 
			 initImageSlbfe();
			 
			 $('#slbfeViewModellLink').click();	
			 unBlockUI();
		}

    function initImageSlbfe(){
        $('#slbfeImge_link').magnificPopup({
            type: 'image',
            closeOnContentClick: true,
            closeBtnInside: false,
            mainClass: 'mfp-no-margins mfp-with-zoom', // class to remove default margin from left and right side
            image: {
                verticalFit: true
            },
            zoom: {
                enabled: true,
                duration: 300 // don't foget to change the duration also in CSS
            }
        });
    }

    function viewAffidavitPopup(applicationId){
        blockUI();

        var fileName=$('#hiddenAffidavitFileName_' + applicationId).val();
        var fileType=$('#hiddenAffidavitFileType_' + applicationId).val();

        if(fileType=='IMAGE'){
            $('#affidavitImge').attr('src','policeFileFinder.htm?fileName=' + fileName);
            $('#affidavitImge_link').attr('href','policeFileFinder.htm?fileName=' + fileName);
        }else{
            $('#affidavitImge').attr('src','images/no_preview_available.png');
            $('#affidavitImge_link').attr('href','images/no_preview_available.png');
        }

        $('#affidavitFileName').attr('href','policeFileFinder.htm?fileName=' + fileName);

        initImageAffidavit();

        $('#affidavitViewModellLink').click();
        unBlockUI();
    }

    function initImageAffidavit(){
        $('#affidavitImge_link').magnificPopup({
            type: 'image',
            closeOnContentClick: true,
            closeBtnInside: false,
            mainClass: 'mfp-no-margins mfp-with-zoom', // class to remove default margin from left and right side
            image: {
                verticalFit: true
            },
            zoom: {
                enabled: true,
                duration: 300 // don't foget to change the duration also in CSS
            }
        });
    }

	function initImagePassport(){
	  $('#passportImge_link').magnificPopup({
			type: 'image',
			closeOnContentClick: true,
			closeBtnInside: false,
			mainClass: 'mfp-no-margins mfp-with-zoom', // class to remove default margin from left and right side
			image: {
				verticalFit: true
			},
			zoom: {
				enabled: true,
				duration: 300 // don't foget to change the duration also in CSS
			}
		});

	  $('#passportImge_link_back').magnificPopup({
			type: 'image',
			closeOnContentClick: true,
			closeBtnInside: false,
			mainClass: 'mfp-no-margins mfp-with-zoom', // class to remove default margin from left and right side
			image: {
				verticalFit: true
			},
			zoom: {
				enabled: true,
				duration: 300 // don't foget to change the duration also in CSS
			}
		});
	}

	function initImageNic(){
	  $('#nicImge_link').magnificPopup({
			type: 'image',
			closeOnContentClick: true,
			closeBtnInside: false,
			mainClass: 'mfp-no-margins mfp-with-zoom', // class to remove default margin from left and right side
			image: {
				verticalFit: true
			},
			zoom: {
				enabled: true,
				duration: 300 // don't foget to change the duration also in CSS
			}
		});

	  $('#nicImge_link_back').magnificPopup({
			type: 'image',
			closeOnContentClick: true,
			closeBtnInside: false,
			mainClass: 'mfp-no-margins mfp-with-zoom', // class to remove default margin from left and right side
			image: {
				verticalFit: true
			},
			zoom: {
				enabled: true,
				duration: 300 // don't foget to change the duration also in CSS
			}
		});
	}



</script>