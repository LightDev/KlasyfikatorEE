
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
                        <li><a href="adminIndex.jsp"  >Zarządzaj instancjami</a></li>
                        <li><a href="buildClassifier" >Budowa klasyfikatora</a></li>
                        <li><a href="evaluateClassifier.jsp" >Ewaluacja klasyfikatora</a></li>
                        <li class="set"><a href="Stats" class="set" >Statystyki</a></li>

                        <!--        <li><a href=".php" ></a></li>
                                <li><a href=".php" ></a></li>-->
                    </ul>
                </div>
                <div id="TRESC">
                    <!--                    <h1 class="h2_decoration underline">Dodaj nową instancję</h1><br />-->
                    <div class="wrap">

                        <h1 class="h2_decoration underline">Statystyka bazy</h1><br />
                        <table id="table-6">
                            <thead>
                            <th>Atrybut</th><th>Wartość</th>
                            </thead>
                            <tbody> 
                                <tr>
                                    <td>Liczba instancji</td><td>${instNum}</td>
                                </tr>
                                <tr>
                                    <td>Liczba atrybutów</td><td>${attrNum}</td>
                                </tr>
                            </tbody>
                        </table> 
                    </div>
                </div>
            </div>
            <div id="footer">Autor: Paweł Parafiniuk</div>
        </div>
    </body>
</html>