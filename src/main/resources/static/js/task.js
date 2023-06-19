var vm = new Vue({
    el: '#app',
    data() {
        return {
            task: {},
            current: 0,
            size: 10,
            total: 0,
            searchForm: {},
            tableData: [],
            dialogVisible: false,
            multipleSelection: [],
            rules: {
                taskName: [
                    {
                        required: true,
                        message: '请输入任务名称',
                        trigger: 'blur'
                    }
                ],
                taskCron: [
                    {
                        required: true,
                        message: '请输入定时任务表达式',
                        trigger: 'blur'
                    }
                ],
                taskStatus: [
                    {
                        required: true,
                        message: '请选择任务状态',
                        trigger: 'blur'
                    }
                ],
                taskType: [
                    {
                        required: true,
                        message: '请选择任务类型',
                        trigger: 'blur'
                    }
                ],
            },
            statusList: [
                {
                    value: 0,
                    label: "未开始"
                },
                {
                    value: 1,
                    label: "已启用"
                },
                {
                    value: 2,
                    label: "异常终止"
                }
            ],
            typeList: [
                {
                    value: 0,
                    label: "指定文件同步"
                },
                {
                    value: 1,
                    label: "指定目录同步"
                },
                {
                    value: 2,
                    label: "数据库表同步"
                }
            ],
            storageOptions: [],
            databaseOptions: []
        }
    },
    created() {
        // 获取数据列表
        this.queryList()
        // 获取存储下拉选项
        this.queryStorageOption()
        // 获取数据库下拉选项
        this.queryDatabaseOption()
    },
    methods: {

        queryList() {
            const url = "/system/task/list"
            const queryParam = {
                current: this.current,
                size: this.size,
                taskName: this.searchForm.taskName
            }
            axios.get(url, {
                params: queryParam
            }, {responseType: 'json'}).then(res => {
                this.tableData = res.data.data.records
                this.total = res.data.data.total
                this.current = res.data.data.current
                this.size = res.data.data.size
            })
        },
        queryTask(id) {
            const url = '/system/task/detail?id=' + id
            axios.get(url, {responseType: 'json'}).then(res => {
                this.task = res.data.data
                let storage = []
                storage[0] = this.task.storageType
                storage[1] = this.task.storageId
                this.task.storage = storage
            }).catch(error => {
                console.log("错误返回数据:" + error.response)
            })
        },
        submit() {
            const url = '/system/task/submit'
            axios.post(url, this.task).then(res => {
                this.dialogVisible = false
                this.queryList()
            })
            this.task = {}
        },
        edit(id) {
            this.dialogVisible = true
            this.queryTask(id)
        },
        remove(id) {
            if (id === null) {
                id = this.multipleSelection.join(',')
                console.log("数据ID列表：" + id)
            }
            const url = '/system/task/remove?ids=' + id
            axios.post(url).then(res => {
                // 刷新列表
                this.queryList()
            }).catch(error => {
                console.log("错误返回数据:" + error.response)
            })
        },
        handleSelectionChange(val) {
            this.multipleSelection = val
        },
        queryStorageOption() {
            const url = "/system/task/query/storage/option"
            let data = []
            axios.get(url).then(res => {
                data = res.data.data
                data.forEach(item => {
                    if (item.children) {
                        item.children.forEach(child => {
                            delete child.children
                        })
                    }
                })
                this.storageOptions = data
            }).catch(error => {
                console.log("错误返回数据:" + error.response)
            })

        },
        queryDatabaseOption() {
            const url = "/system/database/query/database/option"
            axios.get(url).then(res => {
                this.databaseOptions = res.data.data
            }).catch(error => {
                console.log("错误返回数据:" + error.response)
            })
        },
        handleChange(value) {
            if (value) {
                this.task.storageType = value[0]
                this.task.storageId = value[1]
            }
        }
    }
})
