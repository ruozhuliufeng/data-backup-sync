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
                            <el-form-item label="任务名称" label-width="120px">
                                <el-input v-model="searchForm.taskName" placeholder="请输入任务名称"/>
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
                                prop="storageName"
                                label="云端存储标识"
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
                                prop="taskTypeName"
                                label="任务类型"
                                width="100">
                        </el-table-column>
                        <el-table-column
                                prop="syncPath"
                                label="同步文件夹"
                                width="100">
                        </el-table-column>
                        <el-table-column
                                prop="databaseName"
                                label="数据库标识"
                                width="200">
                        </el-table-column>
                        <el-table-column
                                prop="dbName"
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
        <el-dialog title="任务信息"
                   :visible.sync="dialogVisible"
                   width="50%"
                   center>
            <el-form :model="task" :rules="rules" ref="task">
                <el-form-item label="任务名称" label-width="120px">
                    <el-input v-model="task.taskName" autocomplete="off" placeholder="请输入任务名称"/>
                </el-form-item>
                <el-form-item label="任务状态" label-width="120px">
                    <el-select v-model="task.taskStatus" placeholder="请选择任务状态">
                        <el-option v-for="item in statusList"
                                   :key="item.value"
                                   :label="item.label"
                                   :value="item.value"
                        ></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="定时任务表达式" label-width="120px">
                    <el-input v-model="task.taskCron" autocomplete="off" placeholder="请输入定时任务表达式"/>
                </el-form-item>
                <el-form-item label="云端存储" label-width="120px">
                    <el-cascader
                            v-model="task.storage"
                            :options="storageOptions"
                            :props="{value:'id',label:'name'}"
                            @change="handleChange"></el-cascader>
                </el-form-item>
                <el-form-item label="任务类型" label-width="120px">
                    <el-select v-model="task.taskType" placeholder="请选择任务类型">
                        <el-option v-for="item in typeList"
                                   :key="item.value"
                                   :label="item.label"
                                   :value="item.value"
                        ></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="同步文件(夹)" label-width="120px">
                    <el-input v-model="task.syncPath" autocomplete="off" placeholder="请输入同步文件,文件夹以/结尾"/>
                </el-form-item>
                <el-form-item label="数据库" label-width="120px">
                    <el-select v-model="task.databaseId" placeholder="请选择数据库">
                        <el-option v-for="item in databaseOptions"
                                   :key="item.id"
                                   :label="item.name"
                                   :value="item.id"
                        ></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="数据库表" label-width="120px">
                    <el-input v-model="task.dbName" autocomplete="off" placeholder="请输入需要同步的数据库表"/>
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
