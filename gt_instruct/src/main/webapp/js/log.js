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
        this.projectName = document.getElementById("projectName").value;
        this.getLogFileNameOptions();
    },
    created() {
    },
    methods: {
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
                        _this.$alert(_data.msg, '请求失败', {
                            type: 'warning',
                            center: true
                        });
                    }
                })
                .catch(() => {
                });
        },
        changeLogFileName(selectedValues) {
            let _this = this;
            if (_this.logFileName !== selectedValues[0]) {
                _this.logFileName = selectedValues[0];
                setInterval(function () {
                    _this.getLogFileContent()
                }, 1000);
            }
        },
        getLogFileContent() {
            let _this = this;
            console.log(_this.isLogContentReqDone);
            console.log(_this.logFileContentPosition);
            if (!_this.isLogContentReqDone) {
                return;
            }
            _this.isLogContentReqDone = false;
            let url = "/app/log/content?projectName=" + _this.projectName + "&logFileName=" + _this.logFileName + "&position=" + _this.logFileContentPosition;
            axios.get(url)
                .then(function (res) {
                    let _data = res.data;
                    if (_data.code === 100) {
                        _this.logFileContent += _data.data['content'];
                        _this.logFileContentPosition = _data.data['position'];
                    } else {
                        _this.$alert(_data.msg, '请求失败', {
                            type: 'warning',
                            center: true
                        });
                    }
                    _this.isLogContentReqDone = true;
                })
                .catch(() => {
                    _this.isLogContentReqDone = true;
                });

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