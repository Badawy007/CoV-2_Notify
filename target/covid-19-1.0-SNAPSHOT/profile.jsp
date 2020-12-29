<% String name = (String) session.getAttribute("username");%>
<h2> Welcome <%= name %> </h2>

<h3> Add user </h3>
<form action="<%=request.getContextPath()%>/profile" method="post">
    <input type='text' placeholder='Username' name = 'username' class='input-line full-width'>
    <input type="submit" value="Add" />
    <h3> Delete user </h3>
    <input type='text' placeholder='Username' name = 'username-delete' class='input-line full-width'>
    <input type="submit" value="Delete"/> <br><br>
    <a href="login.jsp"/>Logout</a>
</form>

