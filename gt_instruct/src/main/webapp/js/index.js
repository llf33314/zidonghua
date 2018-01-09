// var basePscmdUrl = '/index/rumPscmd/';
var baseStartcmdUrl = '/app/instruct/rumStartcmd/';
var baseStopcmdUrl = '/app/instruct/rumStopcmd/';
var baseSynchroUrl = '/app/instruct/rumSynchrocmd/';
var baseListServerUrl = '/app/instruct/listServer/';
var vm = new Vue({
    el: '#vm',
    data: {
        tableData: [],
        fullscreenLoading: false
    },
    created() {
        this.listServer();
    },
    methods: {
        startcmd: function (paramData) {
            // TODO 启动服务
            this.fullscreenLoading = true;
            var _url = baseStartcmdUrl + paramData.projectName;
            var _this = this;
            axios.post(_url)
                .then(function (response) {
                    var _data = response.data;
                    _this.fullscreenLoading = false;
                    console.log(_data);
                    if (_data.code == 100) {
                        _this.$alert(_data.message, '运行结果');
                    } else {
                        _this.$alert(_data.message, '请求失败');
                    }
                })
                .catch(function () {
                    _this.fullscreenLoading = false;
                });
        },
        stopcmd: function (paramData) {
            // TODO 关闭服务
            this.fullscreenLoading = true;
            var _url = baseStopcmdUrl + paramData.projectName;
            var _this = this;
            axios.post(_url)
                .then(function (response) {
                    var _data = response.data;
                    _this.fullscreenLoading = false;
                    console.log(_data);
                    if (_data.code == 100) {
                        _this.$alert(_data.message, '运行结果');
                    } else {
                        _this.$alert(_data.message, '请求失败');
                    }
                })
                .catch(function () {
                    _this.fullscreenLoading = false;
                });
        },
        synchro: function (paramData) {
            // TODO 同步代码
            this.fullscreenLoading = true;
            var _url = baseSynchroUrl + paramData.projectName;
            var _this = this;
            axios.post(_url)
                .then(function (response) {
                    var _data = response.data;
                    _this.fullscreenLoading = false;
                    console.log(_data);
                    if (_data.code == 100) {
                        _this.$alert(_data.message, '运行结果');
                    } else {
                        _this.$alert(_data.message, '请求失败');
                    }
                })
                .catch(function () {
                    _this.fullscreenLoading = false;
                });
        },
        listServer: function () {
            // TODO 获取服务列表
            this.fullscreenLoading = true;
            var _url = baseListServerUrl;
            var _this = this;
            axios.post(_url)
                .then(function (response) {
                    var _data = response.data;
                    _this.fullscreenLoading = false;
                    console.log(_data);
                    if (_data.code == 100) {
                        _this.tableData = _data.data;
                        console.log(_this.tableData);
                    } else {
                        _this.$alert(_data.message, '请求失败');
                    }
                })
                .catch(function () {
                    _this.fullscreenLoading = false;
                });
        }
    }
});