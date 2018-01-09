var baseStartcmdUrl = '/app/instruct/rumStartcmd/';
var baseStopcmdUrl = '/app/instruct/rumStopcmd/';
var baseSynchroUrl = '/app/instruct/rumSynchrocmd/';
var basePscmdUrl = '/app/instruct/rumPscmd/';
var baseListServerUrl = '/app/instruct/listServer/';
var vm = new Vue({
    el: '#vm',
    data: {
        tableData: [],
        fullscreenLoading: false,
    },
    mounted: function () {
        this.forHealth();
        var _this = this;
        setInterval(function () {
            _this.forHealth();
        }, 5000);
    },
    created() {
        this.listServer();
    },
    methods: {
        startcmd: function (paramData) {
            // 启动服务
            this.fullscreenLoading = true;
            var _url = baseStartcmdUrl + paramData.projectName;
            var _this = this;
            axios.post(_url)
                .then(function (response) {
                    var _data = response.data;
                    _this.fullscreenLoading = false;
                    console.log(_data);
                    if (_data.code == 100) {
                        _this.$alert("服务启动", '运行结果');
                    } else {
                        _this.$alert(_data.message, '请求失败');
                    }
                })
                .catch(function () {
                    _this.fullscreenLoading = false;
                });
        },
        stopcmd: function (paramData) {
            // 关闭服务
            this.fullscreenLoading = true;
            var _url = baseStopcmdUrl + paramData.projectName;
            var _this = this;
            axios.post(_url)
                .then(function (response) {
                    var _data = response.data;
                    _this.fullscreenLoading = false;
                    // console.log(_data);
                    if (_data.code == 100) {
                        _this.$alert("服务关闭", '运行结果');
                    } else {
                        _this.$alert(_data.message, '请求失败');
                    }
                })
                .catch(function () {
                    _this.fullscreenLoading = false;
                });
        },
        synchro: function (paramData) {
            // 同步代码
            this.fullscreenLoading = true;
            var _url = baseSynchroUrl + paramData.projectName;
            var _this = this;
            axios.post(_url)
                .then(function (response) {
                    var _data = response.data;
                    _this.fullscreenLoading = false;
                    // console.log(_data);
                    if (_data.code == 100) {
                        _this.$alert("代码同步", '运行结果');
                    } else {
                        _this.$alert(_data.message, '请求失败');
                    }
                })
                .catch(function () {
                    _this.fullscreenLoading = false;
                });
        },
        killps: function (paramData) {
            // 清理PID
            this.fullscreenLoading = true;
            var _url = basePscmdUrl + paramData.projectName;
            var _this = this;
            axios.post(_url)
                .then(function (response) {
                    var _data = response.data;
                    _this.fullscreenLoading = false;
                    // console.log(_data);
                    if (_data.code == 100) {
                        _this.$alert("清理PID", '运行结果');
                    } else {
                        _this.$alert(_data.message, '请求失败');
                    }
                })
                .catch(function () {
                    _this.fullscreenLoading = false;
                });
        },
        listServer: function () {
            // 获取服务列表
            this.fullscreenLoading = true;
            var _url = baseListServerUrl;
            var _this = this;
            axios.post(_url)
                .then(function (response) {
                    var _data = response.data;
                    _this.fullscreenLoading = false;
                    // console.log(_data);
                    if (_data.code == 100) {
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
            var _this = this;
            this.tableData.forEach(function (item, index) {
                _this.isHealth(item, index);
            });
        },
        isHealth: function (paramData, id) {
            // 健康检测
            if (paramData.serverHealthUrl == null || paramData.serverHealthUrl == ''){
                return;
            }
            var _this = this;
            var _url = paramData.serverHealthUrl;
            axios.get(_url).then(res => {
                // console.log(res.status);
                _this.tableData[id].serverStatus = res.status;
            }).catch(error => {
                _this.tableData[id].serverStatus = 0;
            });
        }
    }
});