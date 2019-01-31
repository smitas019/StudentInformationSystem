<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Hello jQuery</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <!--  <script src="hello.js"></script> -->
    </head>
<script>
function myFunction() {
	 $.ajax({
	        url: "http://localhost:8080/StudentInfoSystem/addStudent/"
	    }).then(function(data) {
	       $('.studentName').append(data.studentName);
	       $('.address').append(data.address);
	       $('.courses').append(data.courses);
	    });
	 });
}
</script>
    <body>
    <form action="" onsubmit="myFunction()" Method="POST">
        <div>
        Student Name :
          <input type="text" name="studentName" class="studentName"><br>
         Address :
          <input type="text" name="address" class="address"><br>
          Courses :
          <select name="courses" class ="courses">
			  <option value="java">Java</option>
			  <option value="dot net">Dot Net</option>
			  <option value="database">Database</option>
			  <option value="php">php</option>
		</select>
		<input type="submit" value="Submit">
        </div>
        </form>
    </body>
</html>