<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="commonUIFiles.jsp"%>

<title>Error Page</title>

<!-- Custom styles for this template -->
<link href="/menu1.css" rel="stylesheet">

</head>
<body>
<h1>Error Page</h1>
<p>Application has encountered an error. Please contact system administrator</p>
<h6> ${message} </h6>
<h6> ${exception} </h6>

	<h6>Failed URL: ${url}</h6>
    <h6>Exception:  ${exception.message}</h6>
       <h6> <c:forEach items="${exception.stackTrace}" var="ex">    ${ex} 
    </c:forEach></h6>
</body>
</html>