<!DOCTYPE html>
<html lang="zh">
<head>
    <title>任务 列表</title>
    <#include "../common/common_css.ftl">
</head>

<body>
<div id="app" class="container-fluid">
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <header class="card-header">
                    <div class="card-title">任务列表</div>
                </header>
                <div class="card-body">
                    <div class="card-search mb-2-5">
                        <el-form :inline="true" :model="searchForm" ref="searchForm" class="demo-form-inline">
                            <el-form-item label="存储标识" label-width="120px">
                                <el-input v-model="searchForm.taskName" placeholder="请输入存储标识"/>
                            </el-form-item>
                            <el-form-item>
                                <el-button type="primary" @click="queryList">查询</el-button>
                            </el-form-item>
                        </el-form>
                    </div>
                </div>
                <div class="card-btns mb-2-5">
                    <el-button type="primary" round @click="dialogVisible=true">新增</el-button>
                    <el-button type="danger" round @click="remove()">删除</el-button>
                </div>
                <div class="table-responsive">
                    <el-table
                            :data="tableData"
                            stripe
                            border
                            style="width:100%"
                            @selection-change="handleSelectionChange"
                    >
                        <el-table-column
                                type="selection"
                                width="55">
                        </el-table-column>
                        <el-table-column
                                prop="taskName"
                                label="任务名称"
                                width="240">
                        </el-table-column>
                        <el-table-column
                                prop="taskCron"
                                label="定时任务表达式"
                                width="240">
                        </el-table-column>
                        <el-table-column
                                prop="storageId"
                                label="云端存储ID"
                                width="200">
                        </el-table-column>
                        <el-table-column
                                prop="bucketName"
                                label="存储桶名称"
                                width="200">
                        </el-table-column>
                        <el-table-column
                                prop="domain"
                                label="访问域名"
                                width="200">
                        </el-table-column>
                        <el-table-column
                                label="任务状态"
                                width="100">
                            <template slot-scope="scope">
                                <el-tag size="small" v-if="scope.row.taskStatus===0" type="primary">未开始</el-tag>
                                <el-tag size="small" v-else-if="scope.row.taskStatus===1" type="success">进行中</el-tag>
                                <el-tag size="small" v-else-if="scope.row.taskStatus===2" type="danger">异常终止</el-tag>
                            </template>
                        </el-table-column>
                        <el-table-column
                                label="任务类型"
                                width="100">
                            <template slot-scope="scope">
                                <el-tag size="small" v-if="scope.row.taskType===0" type="success">指定文件同步</el-tag>
                                <el-tag size="small" v-else-if="scope.row.taskType===1" type="success">指定文件夹同步</el-tag>
                                <el-tag size="small" v-else-if="scope.row.taskType===2" type="success">数据库备份并同步</el-tag>
                            </template>
                        </el-table-column>
                        <el-table-column
                                prop="syncPath"
                                label="同步文件夹"
                                width="100">
                        </el-table-column>
                        <el-table-column
                                prop="databaseId"
                                label="数据库ID"
                                width="180">
                        </el-table-column>
                        <el-table-column
                                prop="databaseName"
                                label="需要同步的数据库表"
                                width="100">
                        </el-table-column>
                        <el-table-column
                                prop="updateTime"
                                label="更新时间"
                                width="150">
                        </el-table-column>
                        <el-table-column
                                prop="status"
                                label="业务状态"
                                width="100">
                            <template slot-scope="scope">
                                <el-tag size="small" v-if="scope.row.status===0" type="danger">异常</el-tag>
                                <el-tag size="small" v-else-if="scope.row.status===1" type="success">正常</el-tag>
                            </template>
                        </el-table-column>
                        <el-table-column
                                label="操作"
                                fixed="right"
                                width="180">
                            <template slot-scope="scope">
                                <el-button size="small" type="text" @click="edit(scope.row.id)">编辑</el-button>
                                <el-button size="small" type="text" @click="remove(scope.row.id)">删除</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
                <el-pagination
                        background
                        layout="prev, pager, next"
                        :total="total">
                </el-pagination>
            </div>
        </div>
        <el-dialog title="任务 信息"
                   :visible.sync="dialogVisible"
                   width="50%"
                   center>
            <el-form :model="task" :rules="rules" ref="task">
                <el-form-item label="访问密钥" label-width="120px">
                    <el-input v-model="task.accessKey" autocomplete="off" placeholder="请输入密码"/>
                </el-form-item>
                <el-form-item label="密钥" label-width="120px">
                    <el-input v-model="task.secretKey" autocomplete="off" placeholder="请输入密码"/>
                </el-form-item>
                <el-form-item label="端点" label-width="120px">
                    <el-input v-model="task.endPoint" autocomplete="off" placeholder="请输入密码"/>
                </el-form-item>
                <el-form-item label="存储桶名称" label-width="120px">
                    <el-input v-model="task.bucketName" autocomplete="off" placeholder="请输入密码"/>
                </el-form-item>
                <el-form-item label="访问域名" label-width="120px">
                    <el-input v-model="task.domain" autocomplete="off" placeholder="请输入访问域名"/>
                </el-form-item>
                <el-form-item label="启用存储" label-width="120px">
                    <el-select v-model="task.enableStorage" placeholder="请选择是否启用">
                        <el-option v-for="item in options"
                                   :key="item.value"
                                   :label="item.label"
                                   :value="item.value"
                        ></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="存储平台标识" label-width="120px">
                    <el-input v-model="task.platform" autocomplete="off" placeholder="请输入存储平台唯一标识"/>
                </el-form-item>
                <el-form-item label="基础路径" label-width="120px">
                    <el-input v-model="task.basePath" autocomplete="off" placeholder="请输入基础路径"/>
                </el-form-item>
                <el-input v-model="task.id" type="hidden"/>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="submit">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</div>

</div>
<#include "../common/common_js.ftl">
<script type="text/javascript" src="/js/main.min.js"></script>
<script type="text/javascript" src="/js/task.js"></script>
</body>
</html>
