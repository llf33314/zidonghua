var baseStartcmdUrl = '/app/instruct/rumStartcmd/';
var baseStopcmdUrl = '/app/instruct/rumStopcmd/';
var baseSynchroUrl = '/app/instruct/rumSynchrocmd/';
var basePscmdUrl = '/app/instruct/rumPscmd/';
var baseChgcmdUrl = '/app/instruct/rumChgcmd/';
var baseListServerUrl = '/app/instruct/listServer/';
var vm = new Vue({
    el: '#vm',
    data: {
        tableData: [],
        fullscreenLoading: false,
    },
    mounted: function () {
        this.forHealth();
        let _this = this;
        setInterval(function () {
            _this.forHealth();
        }, 5000);
    },
    created() {
        this.listServer();
    },
    methods: {
        startcmd: function (paramData) {
            this.instructCmd('启动服务', baseStartcmdUrl, paramData);
        },
        stopcmd: function (paramData) {
            this.instructCmd('关闭服务', baseStopcmdUrl, paramData);
        },
        synchro: function (paramData) {
            this.instructCmd('同步代码', baseSynchroUrl, paramData);
        },
        killps: function (paramData) {
            this.instructCmd('清理PID', basePscmdUrl, paramData);
        },
        chgcmd: function (paramData) {
            this.instructCmd('更换目录', baseChgcmdUrl, paramData);
        },
        instructCmd: function (instructName, baseInstructUrl, paramData) {
            this.fullscreenLoading = true;
            this.$confirm('此操作将直接' + instructName + ', 是否继续?', instructName, {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'info'
            }).then(() => {
                let _url = baseInstructUrl + paramData.projectName;
                let _this = this;
                axios.post(_url)
                    .then(function (response) {
                        _this.fullscreenLoading = false;
                        let _data = response.data;
                        _this.$alert(_data.data, _data.code === 100 ? '请求成功' : '请求失败');
                    }).catch(function () {
                    _this.fullscreenLoading = false;
                });
            }).catch(() => {
                this.fullscreenLoading = false;
            });
        },
        listServer: function () {
            // 获取服务列表
            this.fullscreenLoading = true;
            let _url = baseListServerUrl;
            let _this = this;
            axios.post(baseListServerUrl)
                .then(function (response) {
                    let _data = response.data;
                    _this.fullscreenLoading = false;
                    // console.log(_data);
                    if (_data.code === 100) {
                        _this.tableData = _data.data;
                        // console.log(_this.tableData);
                    } else {
                        _this.$alert(_data.message, '请求失败');
                    }
                })
                .catch(function () {
                    _this.fullscreenLoading = false;
                });
        },
        forHealth: function () {
            // 健康检测循环
            let _this = this;
            this.tableData.forEach(function (item, index) {
                _this.isHealth(item, index);
            });
        },
        isHealth: function (paramData, id) {
            // 健康检测
            if (!paramData.serverHealthUrl) {
                return;
            }
            let _this = this;
            let _url = paramData.serverHealthUrl;
            axios.get(_url).then(res => {
                // console.log(res.status);
                _this.tableData[id].serverStatus = res.status;
            }).catch(error => {
                _this.tableData[id].serverStatus = -1;
            });
        },
        toLog: function (paramData) {
            let url = '/app/route/log?projectName=' + paramData.projectName;
            window.open(url);
        }
    }
});