<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Project Zero</title>
        <style>
            html, body { width: 100%; height:100%;margin:0;padding:0;}
/*             html {font-family:'yoon','Marian','roboto','돋움','AppleGothic', Dotum,sans-serif;} */
            .mainContainer {position: relative;height: 100%;margin-left:-250px;margin-top:-70px;position: absolute;top: 40%;left:50%;text-align:center;}
            .mainContainer .title {margin-bottom: 40px; font-size: 30pt;}
            .mainContainer input {width: 500px;height: 50px; margin-bottom: 20px; padding:0 15px;border: none;background: #F2F2F2;color: #4f5b66;font-size: 15pt;line-height: 50px;color:#000;}
            .mainContainer .zeroModal {background: #F2F2F2;}
        </style>
    </head>
    <body>
    	<form id="form" name="form" method="POST" action="/loginProcess">
	        <div class="mainContainer" id="main">
	            <div class="title" id="title">
	                Hellow World
	            </div>
	            <div>
	                <input type="text" id="zero_code" name="zero_code" value=""/>
	            </div>
	            <div>
	                <input type="text" id="zero_p_code" name="zero_p_code" value=""/>
	            </div>
	        </div>
        </form>
    </body>
</html>
