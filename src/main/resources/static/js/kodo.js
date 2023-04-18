var vm = new Vue({
    el: '#app',
    data: {
        kodo: {},
        current: 0,
        size: 10,
        total: 0,
        searchForm: {},
        tableData: [],
        dialogVisible: false,
        multipleSelection: [],
        rules: {
            accessKey: [
                {
                    required: true,
                    message: '请输入访问密钥',
                    trigger: 'blur'
                }
            ],
            secretKey: [
                {
                    required: true,
                    message: '请输入密钥',
                    trigger: 'blur'
                }
            ],
            endPoint: [
                {
                    required: true,
                    message: '请输入端点',
                    trigger: 'blur'
                }
            ],
            bucketName: [
                {
                    required: true,
                    message: '请输入存储桶名称',
                    trigger: 'blur'
                }
            ],
            domain: [
                {
                    required: true,
                    message: '请输入访问域名',
                    trigger: 'blur'
                }
            ],
            enableStorage: [
                {
                    required: true,
                    message: '请选择是否启用',
                    trigger: 'blur'
                }
            ],
            platform: [
                {
                    required: true,
                    message: '请输入存储平台标识',
                    trigger: 'blur'
                }
            ]
        },
        options:[
            {
                value: true,
                label: "启用"
            },
            {
                value: false,
                label: "禁用"
            }
        ]
    },
    created() {
        // 获取数据列表
        this.queryList()
    },
    methods: {

        queryList() {
            const url = "/storage/kodo/list"
            const queryParam = {
                current: this.current,
                size: this.size,
                platform: this.searchForm.platform
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
        queryKodo(id) {
            const url = '/storage/kodo/detail?id=' + id
            axios.get(url, {responseType: 'json'}).then(res => {
                this.kodo = res.data.data
            }).catch(error => {
                console.log("错误返回数据:" + error.response)
            })
        },
        submit() {
            const url = '/storage/kodo/submit'
            axios.post(url, this.kodo).then(res => {
                this.dialogVisible = false
                this.queryList()
            })
            this.kodo = {}
        },
        edit(id) {
            this.dialogVisible = true
            this.queryKodo(id)
        },
        remove(id) {
            if (id===null){
                id = this.multipleSelection.join(',')
            }
            const url = '/storage/kodo/remove?ids='+id
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
