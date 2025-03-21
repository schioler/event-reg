<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
         <div class="col-content">
         <div>       
            <div class="col-container">
            <div>&nbsp;</div>
            <div>
               <form action="user-authenticate.do" method="post">
               <div class="grid-container">
               <div class="item1">
                        <h2>
                           <fmt:message key="login" />
                        </h2>
               </div>
               <div class="item2">
                        <div class="row-container">
                           <div><fmt:message key="username" />:</div>
                           <div><fmt:message key="password" />:</div>
                        </div>
               </div>
               <div class="item3">
                        <div class="row-container">
                           <div><input type="text" name="username" autofocus="true"></div>
                           <div><input type="password" name="password"></div>
                        </div>
               </div>
                  <div class="item5">
                        <input type="submit" name="<fmt:message key='login'/>"
                           value="<fmt:message key='login'/>">
               </div>
               </div>
               </form>
            </div>
            <div>&nbsp;</div>
            </div> <!-- EO col-container -->
         </div>
         </div>

</body>
</html>