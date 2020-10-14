<%-- 
    Document   : index
    Created on : 24/05/2011, 21:36:42
    Author     : Admin
--%>

<%@page import="model.vmTela_Compilador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Compilador Dude++</title>
        <script type="text/javascript">
            function Analise() {
            document.Editor.action = 'Handle_Web?action=Analise';
            }
            function Convert2C() {
            document.Editor.action = 'Handle_Web?action=TranslateToC';
            }
        </script>
    </head>

    <body>
        <form name="Editor" action="" method="POST">
            <h3>Editor:</h3>

            <textarea name="txtCodigo" id="txtCodigo" style="width:1024px ;height: 300px"><%
                        if (request.getAttribute("ResultAnalise") != null) {
                            out.print(((vmTela_Compilador) request.getAttribute("ResultAnalise")).getCodigo());
                        }
                %></textarea>
            <br/>
            <br/>
            <br/>
            <br/>
            <input type="radio" name="analise" value="Lexica" checked="<%if (request.getAttribute("ResultAnalise") != null ? ((vmTela_Compilador) request.getAttribute("ResultAnalise")).isLexica() : true);%>"/>Análise Léxica
            <input type="radio" name="analise" value="Sintatica"/>Análise Sintática
            <input type="radio" name="analise" value="Semantica"/>Análise Semantica
            <input type="radio" name="analise" value="Assembly"/>Assembly
            <br/>
            <br/>
            <h3>Debug:</h3>

            <textarea name="txtDebug" style="width: 1024px; height: 100px"><%
                        if (request.getAttribute("ResultAnalise") != null) {
                            out.print(((vmTela_Compilador) request.getAttribute("ResultAnalise")).getDebug());
                        }
                %></textarea>
            <br/>
            <h3>Output:</h3>
            <br/>
            <textarea name="txtOutput" style="width: 1024px; height: 150px"><%
                        if (request.getAttribute("ResultAnalise") != null) {
                            out.print(((vmTela_Compilador) request.getAttribute("ResultAnalise")).getOutput());
                        }
                %></textarea>
            <br/>
            <input  type="submit" value="Converter para C" onclick="Convert2C();return true"/>
            <input  type="submit" value="Compilar" onclick="Analise();return true"/>
        </form>
    </body>
</html>
