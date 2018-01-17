<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ZH">
<head>
    <meta charset="UTF-8">
    <title>项目部署</title>
    <%@ include file="common/taglib.jsp" %>
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
        <el-table-column label="运行状态">
            <template scope="scope">
                <el-button type="info" v-if="!scope.row.serverStatus" size="small">检测中</el-button>
                <el-button type="success" v-else-if="scope.row.serverStatus == 200" size="small">正常运行</el-button>
                <el-button type="danger" v-else size="small">停止</el-button>
            </template>
        </el-table-column>
        <el-table-column label="操作" fix>
            <template scope="scope">
                <%--TODO 这里的按钮应该根据运行状态进行显示隐藏--%>
                <el-button @click="stopcmd(tableData[scope.$index])" type="text" size="small">关闭服务</el-button>
                <el-button @click="killps(tableData[scope.$index])" type="text" size="small">清理PID</el-button>
                <el-button @click="synchro(tableData[scope.$index])" type="text" size="small">代码同步</el-button>
                <el-button @click="startcmd(tableData[scope.$index])" type="text" size="small">启动服务</el-button>
                <el-button @click="chgcmd(tableData[scope.$index])" type="text" size="small">目录更换</el-button>
                <el-button @click="toLog(tableData[scope.$index])" type="text" size="small">查看日志</el-button>
            </template>
        </el-table-column>
    </el-table>
</div>
</body>
<script type="text/javascript" src="/js/index.js"></script>
</html>
