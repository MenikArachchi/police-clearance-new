<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:i18n name="lk.icta.resources.global">
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload Document</title>
    </head>

    <body id="bd">
    <!--main container start -->
    <div id="es-container" class="container">

            <jsp:include page="../common/header.jsp"/>

        <div id="es-content">
            <!-- Including the common page with title bar and help content -->
            <c:set var="pageTitle">Upload Documents</c:set>
            <jsp:include page="../common/commonPage.jsp">
                <jsp:param name="title" value="${pageTitle}"/>
            </jsp:include>

            <s:form theme="simple" class="form-horizontal" id="applicationRegistrationForm" role="form">

            <div class="middle_content">
                <div class="tittle-name">Application Details</div>
                <div style="clear: both;"></div>
                <div style="clear: both;"></div>
                <hr>
                <div class="form-group">
                    <div class="col-lg-12" style="max-width: 100%;">
                        <div class="table-responsive" style="max-width: 100%;">
                            <table class="table table-bordered table-striped" style="max-width: 100%;">
                                <thead>
                                <tr>
                                    <th class="text-center"><strong>Reference</strong></th>
                                    <th class="text-center"><strong>Name</strong></th>
                                    <th class="text-center"><strong>Current NIC No</strong></th>
                                    <th class="text-center"><strong>Passport No</strong></th>
                                    <th class="text-center"><strong>Application Date</strong></th>
                                    <th class="text-center"><strong>Certificate No</strong></th>
                                    <th class="text-center"><strong>Type</strong></th>
                                </tr>
                                </thead>
                                <tbody>

                                <tr>
                                    <td class="text-center-middle" style="vertical-align: middle;">
                                        <a href="viewApplicationStatus.action?aid=${applicationVO.applicationId}"
                                           target="_blank">${applicationVO.referenceNo}</a>
                                    </td>
                                    <td style="vertical-align: middle;text-align: left;">${applicationVO.applicantNameAsNic}</td>
                                    <td class="text-center-middle"
                                        style="vertical-align: middle;">${applicationVO.currentNicNo}</td>
                                    <td class="text-center-middle"
                                        style="vertical-align: middle;">${applicationVO.passport}</td>
                                    <td class="text-center-middle" style="vertical-align: middle;"><fmt:formatDate
                                            value="${applicationVO.submittedDate}" pattern="dd/MM/yyyy hh:mm aa"/></td>
                                    <td class="text-center-middle"
                                        style="vertical-align: middle;">${applicationVO.certificateSerialNo}</td> 
                                    <c:choose>
                                         <c:when test="${applicationVO.applicationType == 'MA'}">
                                             <td class="text-center-middle"
                                    style="vertical-align: middle;">Manual</td>
                                         </c:when>
                                         <c:when test="${applicationVO.applicationType == 'ON'}">
                                             <td class="text-center-middle"
                                    style="vertical-align: middle;">Online</td>
                                         </c:when>
                                     </c:choose>
                                    
                                </tr>

                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>
            </div>

            <div class="middle_content">
                <div class="tittle-name">Upload documents for application</div>
                <div style="clear: both;"></div>
                <div style="clear: both;"></div>
                <hr>

                <div id="uploadFormDiv">
                    <input type="hidden" value="${referenceNo}" id="appRefNo"/>
                    <input type="hidden" value="${applicationId}" id="applicationId"/>

                    <p class="mandatory_field"><b> Note: Once a document is uploaded it cannot be removed.</b></p>
                    <div style="clear: both;"></div>
                    <div style="clear: both;"></div>
                    <div class="col-lg-12" style="margin:1px 0px">

                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="documentName" class="control-label"><span
                                        class="mandatory_field">*</span><b>Document name</b></label>
                            </div>
                            <div class="col-sm-6">
                                <input id="documentName" name="documentName" type="text"
                                       class="form-control"/>
                            </div>

                        </div>
                    </div>
                    <div style="clear: both;"></div>
                    <div style="clear: both;"></div>
                    <div class="col-lg-12" style="margin:1px 0px">

                        <div class="form-group">
                            <div class="col-sm-3">
                                <label for="documentComment" class="control-label"><span
                                        class="mandatory_field">*</span><b>Comment(Maximum 500 Characters)</b></label>
                            </div>
                            <div class="col-sm-6">
                                <textarea id="documentComment" name="documentName"
                                          class="form-control" onkeyup="countChar(this)">
                                </textarea>
                            </div>

                        </div>

                    </div>
                </div>
                <div style="clear: both;"></div>
                <div style="clear: both;"></div>
                <div style="clear: both;"></div>
                <div class="col-lg-12" style="margin:1px 0px">

                    <div class="form-group">
                        <div class="col-sm-3">
                            <label for="documentUploadPath" class="control-label"><b>Document: <i>(Maximum
                                file size is 250 Kb)</i></b></label>
                        </div>
                        <div class="col-sm-3">
                            <input id="documentFileUpload" name="documentUploadPath" type="file" style=""/>
                            <s:hidden name="documentHiddenName" id="documentFileUploadPath"
                                      cssClass="form-control"/>
                        </div>
                        <div class="col-sm-2">
                            <input type="button" onclick="uploadFile('document');"
                                   class="btn btn-primary es-buttton"
                                   value="Upload"/>
                            <img src="images/ajax-loader.gif" id="ajax_loader_document" style="display:none;"/>
                            <img src="images/right_green.jpg" id="upload_complete_document" style="display:none;"/>
                        </div>
                        <div class="col-sm-1">
                            <p id="charNum" style="float: right"></p>
                        </div>
                    </div>
                </div>

            </div>

            <div style="clear: both;"></div>
            <div style="clear: both;"></div>
            <div style="clear: both;"></div>
            <hr>
            <div class="middle_content">
                <c:if test="${isOicUserStr == 'false'}">

                    <div class="tittle-name">View uploaded documents</div>
                    <div style="clear: both;"></div>
                    <div style="clear: both;"></div>
                    <hr>

                    <div id="viewDocumentDiv">
                            <%-------------------------------------Table  Start----------------------------------------------%>
                        <table class="table table-bordered" id="documentTableId">
                            <thead>
                            <tr>
                                <th class="text-center" style="width: 25%;">
                                    <strong>Document Name</strong>
                                </th>
                                <th class="text-center" style="width: 12%">
                                    <strong>Uploaded By</strong>
                                </th>
                                <th class="text-center" style="width: 12%">
                                    <strong>Uploaded Date</strong>
                                </th>
                                <th class="text-center">
                                    <strong>Comment</strong>
                                </th>
                                <th class="text-center" style="width: 5%">
                                    <strong>View</strong>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${documentVOS}" var="documnet">
                                <tr>
                                    <td>
                                        <b>${documnet.documentName}</b>
                                    </td>
                                    <td>
                                        <b>${documnet.createdBy}</b>
                                    </td>
                                    <td class="text-center">
                                        <b>${documnet.createdDate}</b>
                                    </td>
                                    <td>
                                        <b>${documnet.comment}</b>
                                    </td>
                                    <td class="text-center">
                                        <c:choose>
                                            <c:when test="${documnet.fileType == 'IMAGE'}">
                                                <img src="images/open.png" height="25px"
                                                     onclick="viewDocumentPopup('${documnet.filePath}')"/>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="policeFileFinder.htm?fileName=${documnet.filePath}"
                                                   target="_blank"><img src="images/open.png" height="25px"/></a>
                                            </c:otherwise>
                                        </c:choose>

                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                            <%-------------------------------------Table  End  ------------------------------------------------%>

                    </div>
                </c:if>

            </div>

        </div>

        </s:form>

        <div style="display: none;">
            <a data-toggle="modal" id="documentViewModellLink" href="#documentViewModelPopUp">View</a>

        </div>

        <!--  #####################################################	Document VIEW POPUP   ######################################################## -->
        <div class="modal fade" id="documentViewModelPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <span class="modal-title" style="font-size:15px;font-weight:bold;">Uploaded Document</span>
                    </div>
                    <div class="modal-body">
                        <div>
                            <div id="documentLinkDiv">
                                <div style="padding: 5px 0px;font-size:14px;">
                                    Please click
                                    <a id="documentFileName" target="_blank" href="javascript:void(0)">
                                        <span style="font-weight:bold;text-decoration:underline; ">here</span>
                                    </a>
                                    to Open/Download docuemtnt.
                                </div>
                                <div style="padding: 10px;text-align: center;">
                                    <a href="images/no_preview_available.png" class="nicImge_link image-link"
                                       id="documentImge_link">
                                        <img class="image-link" src="images/preloader_large.gif" id="documentImge"
                                             style="width:275px;height:280px;border: 1px solid #000;"/>
                                    </a>
                                </div>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>


        </div>

        <!-- including footer -->
            <jsp:include page="../common/footer.jsp"/>


        <script type="text/javascript" src="js/jquery.numeric.js"></script>
        <script type="text/javascript">
            //function for check the upload is supported for using browser
            function supportAjaxUploadWithProgress() {
                return supportFileAPI() && supportAjaxUploadProgressEvents() && supportFormData();
                function supportFileAPI() {
                    var fi = document.createElement('INPUT');
                    fi.type = 'file';
                    return 'files' in fi;
                };
                function supportAjaxUploadProgressEvents() {
                    var xhr = new XMLHttpRequest();
                    return !!(xhr && ('upload' in xhr) && ('onprogress' in xhr.upload));
                };
                function supportFormData() {
                    return !!window.FormData;
                }
            }

            //to upload the external reports for blanks
            function uploadFile(fileType) {
                //check the browser
                if (!supportAjaxUploadWithProgress) {
                    alert("Please update your browser to upload");
                    return false;
                }

                var documentName = $('#documentName').val();
                var appId = $('#applicationId').val();
                var refNo = $('#appRefNo').val();
                var comment = $('#documentComment').val();

                if (documentName == '') {
                    alert("Please enter document name.");
                    addRedBorder('documentName');
                    return false;
                }

                if (comment == '') {
                    alert("Please enter additional comment for documet.");
                    addRedBorder('documentComment');
                    return false;
                }
                if (comment.length > 500) {
                    alert("Cannot insert more than 500 characters");
                    addRedBorder('documentComment');
                    return false;
                }

                var documentInput = document.getElementById('documentFileUpload');
                var documentFile = "";
                var fileDocumentPath = "";
                var documentExtension = "";

                if (fileType == 'document') {
                    documentFile = documentInput.files[0];
                    fileDocumentPath = document.getElementById('documentFileUpload').value;
                    documentExtension = fileDocumentPath.split(".").pop().trim();
                    if (!validateFileUpload('Document', fileDocumentPath, documentExtension, documentFile)) {
                        $("#ajax_loader_document").hide();
                        return false;
                    }

                }

                var msg = "Are you sure you want to add additional documents?. \n Cannot remove after uploaded.";
                var con = confirm(msg);
                if (con) {
                    var formData = new FormData();
                    formData.append('file', documentFile);
                    formData.append('fileExtension', documentExtension);
                    formData.append('fileType', "ADDI_DOCUMENT");
                    formData.append('uploadType', "UPLOAD");

                    var docFileType = '';
                    var extension = documentExtension.toLocaleLowerCase();
                    if (extension == 'png' || extension == 'jpg' || extension == 'gif') {
                        docFileType = 'IMAGE';
                    } else {
                        docFileType = 'DOC';
                    }

                    var xhr = new XMLHttpRequest();
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState == 4 && xhr.status == 200) {
                            var jsonData = JSON.parse(xhr.responseText);
                            var documentFileName = jsonData.fileName;
                            if (documentFileName != null || documentFileName != '') {
                                var documentVO = {
                                    applicationId: appId,
                                    referenceNo: refNo,
                                    documentName: documentName,
                                    filePath: documentFileName,
                                    documentComment: comment,
                                    fileType: docFileType
                                };
                                saveDocument(documentVO);
                            }
                        }
                    };
                    xhr.open('POST', 'uploadFile.action', true);
                    xhr.send(formData);
                }

            }

            function saveDocument(documentVo) {
                var tableBodyHtml = '';
                $.post('uploadAdditionalApplicationDocuments.action', {
                        applicationId: documentVo.applicationId,
                        referenceNo: documentVo.referenceNo,
                        documentName: documentVo.documentName,
                        filePath: documentVo.filePath,
                        documentComment: documentVo.documentComment,
                        docFileType: documentVo.fileType
                    },
                    function (data) {
                        clearForm();
                        alert('Document uploaded successful.');

                        tableBodyHtml = tableBodyHtml + '<tr>';

                        tableBodyHtml = tableBodyHtml + '<td>';
                        tableBodyHtml = tableBodyHtml + '<b>' + data.savedDocumentVO.documentName + '</b>';
                        tableBodyHtml = tableBodyHtml + '</td>';

                        tableBodyHtml = tableBodyHtml + '<td>';
                        tableBodyHtml = tableBodyHtml + '<b>' + data.savedDocumentVO.createdBy + '</b>';
                        tableBodyHtml = tableBodyHtml + '</td>';

                        tableBodyHtml = tableBodyHtml + '<td class="text-center">';
                        tableBodyHtml = tableBodyHtml + '<b>' + data.savedDocumentVO.createdDate + '</b>';
                        tableBodyHtml = tableBodyHtml + '</td>';

                        tableBodyHtml = tableBodyHtml + '<td>';
                        tableBodyHtml = tableBodyHtml + '<b>' + data.savedDocumentVO.comment + '</b>';
                        tableBodyHtml = tableBodyHtml + '</td>';

                        tableBodyHtml = tableBodyHtml + '<td class="text-center">';

                        if (data.savedDocumentVO.fileType == 'IMAGE') {
                            tableBodyHtml = tableBodyHtml + '<a><img src="images/open.png" height="25px" onclick="viewDocumentPopup(\''+data.savedDocumentVO.filePath+'\');"/></a>';
                        } else {
                            tableBodyHtml = tableBodyHtml + '<a href="policeFileFinder.htm?fileName='+data.savedDocumentVO.filePath+'" target="_blank">';
                            tableBodyHtml = tableBodyHtml + '<img src="images/open.png" height="25px"/></a>';
                        }
                        tableBodyHtml = tableBodyHtml + '</td>';

                        tableBodyHtml = tableBodyHtml + '</tr>';

                        $('#documentTableId tbody').prepend(tableBodyHtml);
                    });
            }

            function clearForm() {
                $('#documentName').val('');
                resetBorder('documentName');
                $('#documentFileUpload').val('');
                $('#documentComment').val('');
                resetBorder('documentComment');
                $('#charNum').text('');
            }

            function validateFileUpload(uploadingFile, path, extension, file) {
                var maximumFileLimit = parseFloat($('#maximumFileLimit').val());
                if (path == '') {
                    alert("please select a file before upload for " + uploadingFile);
                    return false;
                } else if (extension != "pdf" && extension != "PDF" && extension != "doc" && extension != "DOC" && extension != "docx" && extension != "DOCX" && extension != "png" && extension != "PDF" && extension != "jpg" && extension != "jpg") {
                    alert("invalid " + uploadingFile + " file format");
                    return false;
                } else if (file.size > maximumFileLimit) {
                    alert(uploadingFile + " file size is too large to upload, the maximum file size limit is " + maximumFileLimit + " bytes!");
                    return false;
                }
                return true;
            }

            function addRedBorder(id) {
                $("#" + id).css("border", "solid 1px red");
            }

            function resetBorder(id) {
                $("#" + id).css("border", "solid 1px #e0e1fc");
            }

            function setSelectionRange(input, selectionStart, selectionEnd) {
                if (input.setSelectionRange) {
                    input.focus();
                    input.setSelectionRange(selectionStart, selectionEnd);
                } else if (input.createTextRange) {
                    var range = input.createTextRange();
                    range.collapse(true);
                    range.moveEnd('character', selectionEnd);
                    range.moveStart('character', selectionStart);
                    range.select();
                }
            }

            function setCaretToPos(input, pos) {
                setSelectionRange(input, pos, pos);
            }

            $("#documentComment").click(function (e) {

                var thisval = $(this).val();
                thisval = thisval.trim();
                if (thisval == null || thisval == '') {
                    setCaretToPos($("#documentComment")[0], 0);
                } else {
                    setCaretToPos($("#documentComment")[0], thisval.length + 1);
                }

                if (e.which < 0x20) {
                    // e.which < 0x20, then it's not a printable character
                    // e.which === 0 - Not a character
                    return;     // Do nothing
                }
                if (this.value.length == 500) {
                    e.preventDefault();
                } else if (this.value.length > 500) {
                    // Maximum exceeded
                    this.value = this.value.substring(0, 500);
                }

            });

            function countChar(val) {
                var len = val.value.length;
                if (len >= 500) {
                    val.value = val.value.substring(0, 500);
                } else if (len == 500) {
                    val.preventDefault();
                }
                else {
                    $('#charNum').text(500 - len);
                }

            }

            function viewDocumentPopup(filename) {
                blockUI();
                $('#documentImge').attr('src', 'policeFileFinder.htm?fileName=' + filename);
                $('#documentImge_link').attr('href', 'policeFileFinder.htm?fileName=' + filename);

                $('#documentFileName').attr('href', 'policeFileFinder.htm?fileName=' + filename);

                initImageDocument();

                $('#documentViewModellLink').click();
                unBlockUI();
            }

            function initImageDocument() {
                $('#documentImge_link').magnificPopup({
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

    </body>
    </html>
</s:i18n>