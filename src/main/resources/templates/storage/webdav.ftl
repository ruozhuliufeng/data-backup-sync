<!DOCTYPE html>
<html lang="zh">
<head>
    <title>WebDav 列表</title>
    <#include "../common/common_css.ftl">
</head>

<body>
<div id="app" class="container-fluid">
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <header class="card-header">
                    <div class="card-title">WebDav列表</div>
                </header>
                <div class="card-body">
                    <div class="card-search mb-2-5">
                        <el-form  :inline="true" :model="searchForm" ref="searchForm" class="demo-form-inline">
                            <el-form-item label="存储标识" label-width="120px">
                                <el-input v-model="searchForm.platform" placeholder="请输入存储标识"/>
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
                                prop="server"
                                label="服务器地址"
                                width="240">
                        </el-table-column>
                        <el-table-column
                                prop="username"
                                label="用户名"
                                width="240">
                        </el-table-column>
                        <el-table-column
                                prop="domain"
                                label="访问域名"
                                width="200">
                        </el-table-column>
                        <el-table-column
                                label="是否启用"
                                width="100">
                            <template slot-scope="scope">
                                <el-tag size="small" v-if="scope.row.enableStorage===false" type="danger">禁用
                                </el-tag>
                                <el-tag size="small" v-else-if="scope.row.enableStorage===true" type="success">
                                    启用
                                </el-tag>
                            </template>
                        </el-table-column>
                        <el-table-column
                                prop="platform"
                                label="唯一存储标识"
                                width="100">
                        </el-table-column>
                        <el-table-column
                                prop="basePath"
                                label="基础路径"
                                width="180">
                        </el-table-column>
                        <el-table-column
                                prop="storagePath"
                                label="存储路径"
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
        <el-dialog title="WEBDAV 信息"
                   :visible.sync="dialogVisible"
                   width="50%"
                   center>
            <el-form :model="webdav" :rules="rules" ref="webdav">
                <el-form-item label="服务器地址" label-width="120px">
                    <el-input v-model="webdav.server" placeholder="请输入服务器地址"/>
                </el-form-item>
                <el-form-item label="用户名" label-width="120px">
                    <el-input v-model="webdav.username" autocomplete="off" placeholder="请输入用户名"/>
                </el-form-item>
                <el-form-item label="密码" label-width="120px">
                    <el-input v-model="webdav.password" autocomplete="off" placeholder="请输入密码"/>
                </el-form-item>
                <el-form-item label="访问域名" label-width="120px">
                    <el-input v-model="webdav.domain" autocomplete="off" placeholder="请输入访问域名"/>
                </el-form-item>
                <el-form-item label="启用存储" label-width="120px">
                    <el-select v-model="webdav.enableStorage" placeholder="请选择是否启用">
                        <el-option v-for="item in options"
                                   :key="item.value"
                                   :label="item.label"
                                   :value="item.value"
                                   ></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="存储平台标识" label-width="120px">
                    <el-input v-model="webdav.platform" autocomplete="off" placeholder="请输入存储平台唯一标识"/>
                </el-form-item>
                <el-form-item label="基础路径" label-width="120px">
                    <el-input v-model="webdav.basePath" autocomplete="off" placeholder="请输入基础路径"/>
                </el-form-item>
                <el-form-item label="存储路径" label-width="120px">
                    <el-input v-model="webdav.storagePath" autocomplete="off" placeholder="请输入存储路径，以 / 结尾"/>
                </el-form-item>
                <el-input v-model="webdav.id" type="hidden"/>
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
<script type="text/javascript" src="/js/webdav.js"></script>
</body>
</html>
