<!DOCTYPE html>
<html lang="zh">
<head>
    <title>数据库 列表</title>
    <#include "../common/common_css.ftl">
</head>

<body>
<div id="app" class="container-fluid">
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <header class="card-header">
                    <div class="card-title">数据库列表</div>
                </header>
                <div class="card-body">
                    <div class="card-search mb-2-5">
                        <el-form :inline="true" :model="searchForm" ref="searchForm" class="demo-form-inline">
                            <el-form-item label="数据库标识" label-width="120px">
                                <el-input v-model="searchForm.databaseName" placeholder="请输入数据库标识"/>
                            </el-form-item>
                            <el-form-item label="数据库类型" label-width="120px">
                                <el-select v-model="searchForm.type" placeholder="请选择数据库类型">
                                    <el-option v-for="item in typeList"
                                               :key="item.value"
                                               :label="item.label"
                                               :value="item.value"
                                    ></el-option>
                                </el-select>
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
                                prop="host"
                                label="数据库地址"
                                width="200">
                        </el-table-column>
                        <el-table-column
                                prop="port"
                                label="端口"
                                width="80">
                        </el-table-column>
                        <el-table-column
                                prop="username"
                                label="数据库用户名称"
                                width="100">
                        </el-table-column>
                        <el-table-column
                                prop="password"
                                label="数据库用户密码"
                                width="100">
                        </el-table-column>
                        <el-table-column
                                prop="databaseName"
                                label="数据库标识"
                                width="200">
                        </el-table-column>
                        <el-table-column
                                prop="updateTime"
                                label="更新时间"
                                width="150">
                        </el-table-column>
                        <el-table-column
                                label="业务状态"
                                width="100">
                            <template slot-scope="scope">
                                <el-tag size="small" v-if="scope.row.status===0" type="danger">异常</el-tag>
                                <el-tag size="small" v-else-if="scope.row.status===1" type="success">正常</el-tag>
                            </template>
                        </el-table-column>
                        <el-table-column
                                label="数据库类型"
                                width="100">
                            <template slot-scope="scope">
                                <el-tag size="small" v-if="scope.row.type===0" type="success">MySQL</el-tag>
                                <el-tag size="small" v-else-if="scope.row.type===1" type="success">Oracle</el-tag>
                                <el-tag size="small" v-else-if="scope.row.type===2" type="success">SqlServer</el-tag>
                                <el-tag size="small" v-else-if="scope.row.type===3" type="success">Postgres</el-tag>
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
        <el-dialog title="数据库 信息"
                   :visible.sync="dialogVisible"
                   width="50%"
                   center>
            <el-form :model="db" :rules="rules" ref="db">
                <el-form-item label="数据库地址" label-width="120px">
                    <el-input v-model="db.host" autocomplete="off" placeholder="请输入密码"/>
                </el-form-item>
                <el-form-item label="端口" label-width="120px">
                    <el-input v-model="db.port" autocomplete="off" placeholder="请输入密码"/>
                </el-form-item>
                <el-form-item label="数据库用户名称" label-width="120px">
                    <el-input v-model="db.username" autocomplete="off" placeholder="请输入密码"/>
                </el-form-item>
                <el-form-item label="数据库用户密码" label-width="120px">
                    <el-input v-model="db.password" autocomplete="off" placeholder="请输入密码"/>
                </el-form-item>
                <el-form-item label="databaseName" label-width="120px">
                    <el-input v-model="db.domain" autocomplete="off" placeholder="请输入访问域名"/>
                </el-form-item>
                <el-form-item label="状态" label-width="120px">
                    <el-select v-model="db.status" placeholder="请选择是否启用">
                        <el-option v-for="item in statusList"
                                   :key="item.value"
                                   :label="item.label"
                                   :value="item.value"
                        ></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="数据库类型" label-width="120px">
                    <el-select v-model="db.type" placeholder="请选择数据库类型">
                        <el-option v-for="item in typeList"
                                   :key="item.value"
                                   :label="item.label"
                                   :value="item.value"
                        ></el-option>
                    </el-select>
                </el-form-item>
                <el-input v-model="db.id" type="hidden"/>
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
<script type="text/javascript" src="/js/db.js"></script>
</body>
</html>
