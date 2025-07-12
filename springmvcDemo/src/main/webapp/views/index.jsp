<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>View </title>

    </head>
    <body>
    <form action="addAlien" method="post">
    Enter your Id :<input type="text" name="id"><br>

    Enter your name :<input type="text" name="name"><br>
    <input type="submit">
    </form>
    </form>
     	<hr>
     	<form action="getAlien" method="get">
     		Enter your id : <input type="text" name="id"><br>
     		<input type="submit">
     		</form>
     		<hr>
                 	<form action="getAlienByName" method="get">
                 		Enter your name: <input type="text" name="name"><br>
                 		<input type="submit">
                 		</form>
    </body>
</html>