<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="commonUIFiles.jsp"%>
</head>
<body>

  <!-- Modal confirm -->
<div class="modal" id="confirmModal" style="display: none; z-index: 1050;">
  <div class="modal-dialog">
    <div class="modal-content">
    <div class="modal-header">
        <h5 class="modal-title" id="confirmMessage"></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="msgBody">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="confirmOk">Yes</button>
            <button type="button" class="btn btn-secondary" id="confirmCancel">Cancel</button>
          </div>
    </div>
  </div>
</div></body>

</html>
