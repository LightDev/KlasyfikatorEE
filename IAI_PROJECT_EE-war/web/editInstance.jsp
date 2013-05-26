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
                    <h1 class="h2_decoration underline">Edytujesz instancję nr ${id}</h1><br />
                    <div class="wrap">
                        <form action="editInstanceSummary" method="post">
                            <!--                    parents        usual, pretentious, great_pret
                                                has_nurs       proper, less_proper, improper, critical, very_crit
                                                form           complete, completed, incomplete, foster
                                                children       1, 2, 3, more
                                                housing        convenient, less_conv, critical
                                                finance        convenient, inconv
                                                social         non-prob, slightly_prob, problematic
                                                health         recommended, priority, not_recom-->

                            <table>
                                <tr>
                                    <td>parents:</td><td><select name="parents" >
                                            <option value="0" ${parents == '0' ? 'selected' : ''}>usual</option>
                                            <option value="1" ${parents == '1' ? 'selected' : ''}>pretentious</option>
                                            <option value="2" ${parents == '2' ? 'selected' : ''}>great_pret</option>
                                        </select></td>
                                </tr>
                                <!--<br />-->
                                <tr>
                                    <td>has_nurs:</td>
                                    <td><select name="has_nurs">
                                            <option value="0" ${has_nurs == '0' ? 'selected' : ''}>proper</option>
                                            <option value="1" ${has_nurs == '1' ? 'selected' : ''}>less_proper</option>
                                            <option value="2" ${has_nurs == '2' ? 'selected' : ''}>improper</option>
                                            <option value="3" ${has_nurs == '3' ? 'selected' : ''}>critical</option>
                                            <option value="4" ${has_nurs == '4' ? 'selected' : ''}>very_crit</option>
                                        </select></td>
                                </tr>
                                <tr>
                                    <td>form:</td>
                                    <td><select name="form">
                                            <option value="0" ${form == '0' ? 'selected' : ''}>complete</option>
                                            <option value="1" ${form == '1' ? 'selected' : ''}>completed</option>
                                            <option value="2" ${form == '2' ? 'selected' : ''}>incomplete</option>
                                            <option value="3" ${form == '3' ? 'selected' : ''}>foster</option>
                                        </select>
                                    </td>
                                </tr><!--<br />-->
                                <tr>
                                    <td>children:</td>
                                    <td><select name="children">
                                            <option value="0" ${children == '0' ? 'selected' : ''}>1</option>
                                            <option value="1" ${children == '1' ? 'selected' : ''}>2</option>
                                            <option value="2" ${children == '2' ? 'selected' : ''}>3</option>
                                            <option value="3" ${children == '3' ? 'selected' : ''}>more</option>
                                        </select>
                                    </td>
                                </tr>
                                <!--<br />-->
                                <tr>
                                    <td>housing:</td>
                                    <td><select name="housing">
                                            <option value="0" ${housing == '0' ? 'selected' : ''}>convenient</option>
                                            <option value="1" ${housing == '1' ? 'selected' : ''}>less_conv</option>
                                            <option value="2" ${housing == '2' ? 'selected' : ''}>critical</option>
                                        </select></td>
                                    <!--<br />-->
                                </tr>
                                <tr>                       <td>finance:</td>
                                    <td><select name="finance">
                                            <option value="0" ${finance == '0' ? 'selected' : ''}>convenient</option>
                                            <option value="1" ${finance == '1' ? 'selected' : ''}>inconv</option>
                                        </select>
                                    </td>
                                </tr>                      <!--<br />-->
                                <tr>                      <td>social:</td><td><select name="social">
                                            <option value="0" ${social == '0' ? 'selected' : ''}>non-prob</option>
                                            <option value="1" ${social == '1' ? 'selected' : ''}>slightly_prob</option>
                                            <option value="2" ${social == '2' ? 'selected' : ''}>problematic</option>
                                        </select></td>
                                </tr>
                                <tr>                        <!--<br />-->
                                    <td>health:</td><td><select name="health">
                                            <option value="0" ${health == '0' ? 'selected' : ''}>recommended</option>
                                            <option value="1" ${health == '1' ? 'selected' : ''}>priority</option>
                                            <option value="2" ${health == '2' ? 'selected' : ''}>not_recom</option>
                                        </select></td>
                                </tr>
                                <!--<br />-->
                                <tr>                        <!--<br />-->
                                    <td>class:</td><td><select name="class">
                                            <option value="0" ${class == '0' ? 'selected' : ''}>not_recom</option>
                                            <option value="1" ${class == '0' ? 'selected' : ''}>recommend</option>
                                            <option value="2" ${class == '0' ? 'selected' : ''}>very_recom</option>
                                            <option value="3" ${class == '0' ? 'selected' : ''}>priority</option>
                                            <option value="4" ${class == '0' ? 'selected' : ''}>spec_prior</option>
                                        </select></td>
                                </tr>
                                <!--<br />-->
                                <tr>                        <!--<br />-->
                                    <td></td>
                                    <td></td>
                                </tr>                        <!--<br />-->

                                <tr>                        <!--<br />-->
                                    <td></td>
                                    <td><input type="submit"  class="button" value="Edytuj"/></td>
                                </tr>                        <!--<br />-->

                            </table>
                        </form>
                        <br />
                    </div>
                </div>
            </div>
            <div id="footer">Autor: Paweł Parafiniuk</div>
        </div>
    </body>
</html>