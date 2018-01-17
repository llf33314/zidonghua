<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ZH">
<head>
    <meta charset="UTF-8">
    <title>项目部署-日志</title>
    <%@ include file="common/taglib.jsp" %>
    <style>
        .el-cascader-menus {
            width: 99% !important;
        }

        .el-cascader-menu {
            display: block;
            min-width: inherit;
        }
    </style>
</head>
<body>
<div id="vm">
    <el-row>
        <el-alert title="说明" type="success" description="服务器ip：113.106.202.51"></el-alert>
    </el-row>
    <el-row>
        <el-cascader placeholder="请选择日志文件" :options="logFileNameOptions" @change="changeLogFileName" filterable
                     style="width: 91%"></el-cascader>
        <el-button @click="downloadLogFile">下载完整日志文件</el-button>
    </el-row>
    <el-row>
        <el-input type="textarea" :rows="40" placeholder="暂无日志内容" v-model="logFileContent" :disabled="true"></el-input>
    </el-row>
    <br/>
    <input id="projectName" type="hidden" value="${projectName}"/>
</div>
</body>
<script type="text/javascript" src="/js/log.js"></script>
</html>