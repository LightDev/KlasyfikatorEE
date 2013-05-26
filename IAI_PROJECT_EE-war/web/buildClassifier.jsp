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
                        <li ><a href="adminIndex.jsp" >Zarządzaj instancjami</a></li>
                        <li class="set"><a href="buildClassifier.jsp" class="set">Budowa klasyfikatora</a></li>
                        <li><a href="evaluateClassifier.jsp" >Ewaluacja klasyfikatora</a></li>
                        <!--        <li><a href=".php" ></a></li>
                                <li><a href=".php" ></a></li>-->
                    </ul>
                </div>
                <div id="TRESC">
                    <h1 class="h2_decoration underline">Parametry klasyfikatora PART</h1><br />
                    <div class="wrap">
                        <form action="updateClassifier" method="post">
                            <table id="table-6">
                                <thead>
                                <th>Atrybut</th><th>Wartość</th>
                                </thead>
                                <tbody> 
                                    <tr>
                                        <td>binarySplits</td>
                                        <td>
                                            <select name="binarySplits">
                                                <option value="0" ${class == '0' ? 'selected' : ''}>True</option>
                                                <option value="1" ${class == '1' ? 'selected' : ''}>False</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>confidenceFactor:</td>
                                        <td><input type="text" name="minNumObj" /></td>
                                    </tr>
                                    <tr>
                                        <td>debug:</td>
                                        <td>
                                            <select name="debug">
                                                <option value="0" ${class == '0' ? 'selected' : ''}>True</option>
                                                <option value="1" ${class == '1' ? 'selected' : ''}>False</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>minNumObj:</td>
                                        <td><input type="text" name="minNumObj" /></td>
                                    </tr>
                                    <tr>
                                        <td>numFolds:</td>
                                        <td><input type="text" name="numFolds" /></td>
                                    </tr>
                                    <tr>
                                        <td>reducedErrorPruning:</td>
                                        <td>
                                            <select name="reducedErrorPruning">
                                                <option value="0" ${class == '0' ? 'selected' : ''}>True</option>
                                                <option value="1" ${class == '1' ? 'selected' : ''}>False</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>seed:</td>
                                        <td><input type="text" name="seed" /></td>
                                    </tr>
                                    <tr>
                                        <td>unprunned:</td>
                                        <td>
                                            <select name="unprunned">
                                                <option value="0" ${class == '0' ? 'selected' : ''}>True</option>
                                                <option value="1" ${class == '1' ? 'selected' : ''}>False</option>
                                            </select>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <input type="submit"  class="button" value="Edytuj"/>
                        </form>
                        <br />
                        <!--<a href="adminIndex.jsp">Wróć do zarządania instancjami</a>-->
                    </div>
                </div>
            </div>
            <div id="footer">Autor: Paweł Parafiniuk</div>
        </div>
    </body>
</html>