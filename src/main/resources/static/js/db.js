var vm = new Vue({
    el: '#app',
    data: {
        db: {},
        current: 0,
        size: 10,
        total: 0,
        searchForm: {},
        tableData: [],
        dialogVisible: false,
        multipleSelection: [],
        rules: {
            host: [
                {
                    required: true,
                    message: '请输入数据库地址',
                    trigger: 'blur'
                }
            ],
            port: [
                {
                    required: true,
                    message: '请输入端口',
                    trigger: 'blur'
                }
            ],
            username: [
                {
                    required: true,
                    message: '请输入数据库用户名称',
                    trigger: 'blur'
                }
            ],
            password: [
                {
                    required: true,
                    message: '请输入数据库用户密码',
                    trigger: 'blur'
                }
            ],
            databaseName: [
                {
                    required: true,
                    message: '请输入数据库标识',
                    trigger: 'blur'
                }
            ],
            status: [
                {
                    required: true,
                    message: '请选择状态',
                    trigger: 'blur'
                }
            ],
            type: [
                {
                    required: true,
                    message: '请选择数据库类型',
                    trigger: 'blur'
                }
            ]
        },
        statusList:[
            {
                value: 1,
                label: "启用"
            },
            {
                value: 0,
                label: "禁用"
            }
        ],
        typeList:[
            {
                value: 0,
                label: "MySQL"
            },
            {
                value: 1,
                label: "Oracle"
            },
            {
                value: 2,
                label: "SqlServer"
            },
            {
                value: 3,
                label: "Postgres"
            }
        ]
    },
    created() {
        // 获取数据列表
        this.queryList()
    },
    methods: {

        queryList() {
            const url = "/system/database/list"
            const queryParam = {
                current: this.current,
                size: this.size,
                databaseName: this.searchForm.databaseName,
                type: this.searchForm.type
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
        queryDb(id) {
            const url = '/system/database/detail?id=' + id
            axios.get(url, {responseType: 'json'}).then(res => {
                this.db = res.data.data
            }).catch(error => {
                console.log("错误返回数据:" + error.response)
            })
        },
        submit() {
            const url = '/system/database/submit'
            axios.post(url, this.db).then(res => {
                this.dialogVisible = false
                this.queryList()
            })
            this.db = {}
        },
        edit(id) {
            this.dialogVisible = true
            this.queryDb(id)
        },
        remove(id) {
            if (id===null){
                id = this.multipleSelection.join(',')
            }
            const url = '/system/database/remove?ids='+id
            axios.post(url).then(res => {
                // 刷新列表
                this.queryList()
            }).catch(error => {
                console.log("错误返回数据:" + error.response)
            })
        },
        handleSelectionChange(val) {
            this.multipleSelection = val
        }
    }
})
