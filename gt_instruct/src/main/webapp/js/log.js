var vm = new Vue({
    el: '#vm',
    data: {
        isLogContentReqDone: true,
        projectName: '',
        logFileName: '',
        logFileNameOptions: [],
        logFileContent: '',
        logFileContentPosition: 0
    },
    mounted: function () {
        let _this = this;
        _this.projectName = document.getElementById("projectName").value;
        _this.getLogFileNameOptions();
        setInterval(function () {
            _this.getLogFileContent()
        }, 2000);
        _this.$notify({
            title: '关于catalina日志',
            message: '默认加载倒数30行，若有更新，将自动加载',
            position: 'bottom-right'
        });
    },
    created() {
    },
    methods: {
        getLogFileContent() {
            let _this = this;
            if (!_this.isLogContentReqDone) {
                return;
            }
            _this.isLogContentReqDone = false;
            let url = "/app/log/content?projectName=" + _this.projectName + "&position=" + _this.logFileContentPosition;
            axios.get(url)
                .then(function (res) {
                    let _data = res.data;
                    if (_data.code === 100) {
                        _this.logFileContent += _data.data['content'];
                        _this.logFileContentPosition = _data.data['position'];
                    } else {
                        console.log(_data);
                    }
                    _this.isLogContentReqDone = true;
                })
                .catch(() => {
                    _this.isLogContentReqDone = true;
                });
        },
        getLogFileNameOptions() {
            let _this = this;
            let url = "/app/log/list?projectName=" + _this.projectName;
            axios.get(url)
                .then(function (res) {
                    let _data = res.data;
                    if (_data.code === 100) {
                        let logFileNameOptions = [];
                        _data.data.forEach(function (item, index) {
                            let logFileNameOption = {
                                value: item,
                                label: item
                            };
                            logFileNameOptions.push(logFileNameOption);
                        });
                        _this.logFileNameOptions = logFileNameOptions;
                    } else {
                        console.log(_data);
                    }
                })
                .catch(() => {
                });
        },
        changeLogFileName(selectedValues) {
            let _this = this;
            if (_this.logFileName !== selectedValues[0]) {
                _this.logFileName = selectedValues[0];

            }
        },
        downloadLogFile() {
            let _this = this;
            let logFileName = _this.logFileName;
            if (!logFileName) {
                _this.$alert('请先选择日志文件', '操作错误', {
                    type: 'warning',
                    center: true
                });
                return;
            }
            let url = "/app/log/download?projectName=" + _this.projectName + "&logFileName=" + _this.logFileName;
            window.open(url);
        }
    }
});