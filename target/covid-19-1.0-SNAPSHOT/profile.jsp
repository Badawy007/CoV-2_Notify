<h1> Add user </h1>
<form action="<%=request.getContextPath()%>/profile" method="post">
    <input type='text' placeholder='Username' name = 'username' class='input-line full-width'>
    <input type="submit" value="Add" />
</form>

<h1> Delete user </h1>
<form action="<%=request.getContextPath()%>/profile" method="post">
    <input type='text' placeholder='Username' name = 'username-delete' class='input-line full-width'>
    <input type="submit" value="Delete" />
</form>