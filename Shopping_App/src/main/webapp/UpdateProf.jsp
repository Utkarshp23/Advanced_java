<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="entities.User"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>Insert title here</title>
  </head>
  <body>
    <form action="handleUpdate">
      <input type="text" name="uid" id="" value=${user.getUid()} disabled /><br/>
       <input type="text" name="pwd" id="" value=${user.getPwd()} disabled /><br/>
      <input type="text" name="fname" id="" value=${user.getFname()} disabled /><br/>
      <input type="text" name="mname" id="" value=${user.getMname()} disabled /><br/>
      <input type="text" name="lname" id="" value=${user.getLname()} disabled /><br/>
      <input type="text" name="email" id="" value=${user.getEmail()}  /><br/>
      <input type="text" name="mono" id="" value=${user.getMono()}  /><br/><br/>
      <input type="submit" value="Update"/>
    </form>
  </body>
</html>
