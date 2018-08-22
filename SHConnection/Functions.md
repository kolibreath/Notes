# 功能
-  Auth {
    - 扫一扫进入班级
    - 忘记密码/重置密码
    - 班主任登录
}

- 主页 {
    - 通讯录 -{
        - 分类 ： 小助手 /家长 / 老师
        - 搜索某位家长/老师
        - 查看某位老师的信息
    }
    - 家校圈 查看信息，可以留下评论
    - 孩子评价（?）
    - 发送新的动态： 动态通知(可以添加图片和描述)) 
}

- 我的 入口 左上角头像{
    - 简介查看简介 编辑简介
    - 收藏
    - 评论
    - 设置
}

# 分工
szy:
Auth 扫一扫 进入班级 / 
封装BaseActivity ToolaBarActivity 网络请求 相关依赖,包含通讯录 Fragment HomeFragment CommentFragment(评价)的MainActivity.+
个人中心 + 发送新的通知 + 收集曲线评价的图及其相关功能
xww:
通讯录 Fragment HomeFragment CommentFragment + 其他所有