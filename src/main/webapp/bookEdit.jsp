<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Edit Book</title>
 <link rel="stylesheet"
href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
crossorigin="anonymous">

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-light">
	<div>
		<a class="navbar-brand"> Book Management Application </a>
	</div>
	<ul class="navbar-nav">
		<li><a href="<%=request.getContextPath()%>/BookServlet1/dashboard" class="nav-link">Back to Dashboard</a></li>
	</ul>
	</nav>
	<div class="container col-md-6">
		<div class="card">
			<div class="card-body">
				<c:if test="${book != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${book == null}">
				  	<form action="insert" method="post">
				</c:if>
				<caption>
					<h2>
						<c:if test="${book != null}"> Edit book </c:if>
						<c:if test="${book == null}"> Add New book </c:if>
					</h2>
				</caption>
				<c:if test="${book != null}">
					<input type="hidden" name="oriName" 
						value="<c:out value='${book.bookName}' />" />
				</c:if>
				<fieldset class="form-group">
					<label>Book Name</label> <input type="text"
						value="<c:out value='${book.bookName}' />" class="form-control" name="bookName" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Book Description</label> <input type="text"
						value="<c:out value='${book.bookDesc}' />" class="form-control" name="bookDesc">
				</fieldset>
				<fieldset class="form-group">
					<label>Book Author</label> <input type="text"
						value="<c:out value='${book.bookAuthor}' />" class="form-control" name="bookAuthor">
				</fieldset>
				<fieldset class="form-group">
					<label> Book Likes</label> <input type="text"
						value="<c:out value='${book.bookLikes}' />" class="form-control" name="bookLikes">
				</fieldset>
					<button type="submit" class="btn btn-success">Save</button>
				  </form>  
		</div>
	</div>

</body>
</html>