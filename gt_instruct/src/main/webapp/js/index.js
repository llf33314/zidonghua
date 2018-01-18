var baseStartcmdUrl = '/app/instruct/rumStartcmd/';
var baseStopcmdUrl = '/app/instruct/rumStopcmd/';
var baseSynchroUrl = '/app/instruct/rumSynchrocmd/';
var basePscmdUrl = '/app/instruct/rumPscmd/';
var baseChgcmdUrl = '/app/instruct/rumChgcmd/';
var baseListServerUrl = '/app/instruct/listServer/';
var vm = new Vue({
    el: '#vm',
    data: {
        heartBeatReqDone: {},
        tableData: [],
        fullscreenLoading: false,
    },
    mounted: function () {
        let _this = this;
        setInterval(function () {
            _this.checkStatus();
        }, 1000);
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
        checkStatus() {
            let _this = this;
            _this.tableData.forEach(function (item, index) {
                _this.heartBeat(item, index);
            });
        },
        heartBeat(paramData, id) {
            if (!paramData.serverHealthUrl) {
                return;
            }
            let _this = this;
            if (_this.heartBeatReqDone[paramData.projectName] === undefined || _this.heartBeatReqDone[paramData.projectName] === null) {
                _this.heartBeatReqDone[paramData.projectName] = true;
            }
            if (!_this.heartBeatReqDone[paramData.projectName]) {
                return;
            }
            _this.heartBeatReqDone[paramData.projectName] = false;
            let _url = "/app/heartBeat?projectName=" + paramData.projectName;
            axios.get(_url).then(res => {
                let _data = res.data;
                if (_data.code === 100) {
                    _this.tableData[id].serverStatus = _data.data;
                } else {
                    _this.$alert(_data.msg, '请求失败', {
                        type: 'warning',
                        center: true
                    });
                }
                _this.heartBeatReqDone[paramData.projectName] = true;
            }).catch(error => {
                _this.tableData[id].serverStatus = -1;
                _this.heartBeatReqDone[paramData.projectName] = true;
            });
        },
        toLog: function (paramData) {
            let url = '/app/route/log?projectName=' + paramData.projectName;
            window.open(url);
        }
    }
});