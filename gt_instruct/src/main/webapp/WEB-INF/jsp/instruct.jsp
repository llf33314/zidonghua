<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ZH">
<head>
    <meta charset="UTF-8">
    <title>项目部署</title>
    <%@ include file="common/taglib.jsp"%>
</head>
<body>
<div id="vm">
    <el-row>
        <el-alert title="说明" type="success" description="服务器ip：113.106.202.51"></el-alert>
    </el-row>
    <br/>
    <el-table :data="tableData" stripe highlight-current-row style="width: 100%">
        <el-table-column prop="serverEnv" label="对应环境"></el-table-column>
        <el-table-column prop="serverName" label="服务名"></el-table-column>
        <el-table-column prop="projectName" label="项目名"></el-table-column>
        <el-table-column prop="projectDb" label="对应数据库"></el-table-column>
        <el-table-column label="操作" fix>
            <template scope="scope">
                <el-button @click="stopcmd(tableData[scope.$index])" type="text" size="small">关闭服务</el-button>
                <el-button @click="killps(tableData[scope.$index])" type="text" size="small">清理PID</el-button>
                <el-button @click="synchro(tableData[scope.$index])" type="text" size="small">代码同步</el-button>
                <el-button @click="startcmd(tableData[scope.$index])" type="text" size="small">启动服务</el-button>
            </template>
        </el-table-column>
    </el-table>
</div>
</body>
<script type="text/javascript" src="/js/index.js"></script>
</html>
