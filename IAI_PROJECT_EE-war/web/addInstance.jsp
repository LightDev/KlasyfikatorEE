<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>System wspomagania decyzji przyjmowania do żłobków</title>
        <link href="css/StyleSheet1.css" rel="stylesheet" type="text/css">
        <link href="css/admin.css" rel="stylesheet" type="text/css">
        <!--<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>-->
    </head>
    <body>
        <!--<div id="page" style="background: #fff;">-->
        <div id="page" >
            <a href="index.jsp" style="float: right; text-decoration: none;padding-bottom: 7px;">Moduł użytkownika</a>
            <div id="header">
                <h1 class="h2_decoration ">Moduł administratora</h1>
            </div>
            <div id="pageInside" >
                <div id="MENU">
                    <ul>
                        <!--<li><a href="admin_panel.php" style="">PANEL ADMINISTRACYJNY</a></li>-->
                        <li class="set"><a href="adminIndex.jsp" class="set" >Zarządzaj instancjami</a></li>
                        <li><a href=".jsp" >Budowa klasyfikatora</a></li>
                        <li><a href=".jsp" >Ewaluacja klasyfikatora</a></li>
                        <!--        <li><a href=".php" ></a></li>
                                <li><a href=".php" ></a></li>-->
                    </ul>
                </div>
                <div id="TRESC">
                    <h1 class="h2_decoration underline">Dodano nową instancję</h1><br />
                    <div class="wrap">

                        <table id="table-6">
                            <thead>
                            <th>Atrybut</th><th>Wartość</th>
                            </thead>
                            <tbody>        
                                <tr><td>parents</td><td>${parents}</td></tr>
                                <tr><td>has_nurs</td><td>${has_nurs}</td></tr>
                                <tr><td>form</td><td>${form}</td></tr>
                                <tr><td>children</td><td>${children}</td></tr>
                                <tr><td>housing</td><td>${housing}</td></tr>
                                <tr><td>finance</td><td>${finance}</td></tr>
                                <tr><td>social</td><td>${social}</td></tr>
                                <tr><td>health</td><td>${health}</td></tr>
                                <tr><td>class</td><td>${class}</td></tr>
                            </tbody>
                        </table>
                        <br />
                        <a href="adminIndex.jsp">Wróć do zarządania instancjami</a>
                    </div>
                </div>
            </div>
            <div id="footer">Autor: Paweł Parafiniuk</div>
        </div>
    </body>
</html>