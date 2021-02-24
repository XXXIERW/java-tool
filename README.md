### plus-framework用来做一系列通用类的框架

#### 具体使用在比如：
    1.string字符串的转化问题
    2.时间戳的使用问题
    3.切面类的通用问题
    4.返回JSON格式的统一问题
    5.http请求的格式统一问题
    6.rpc,soa调用等问题
    7.时间格式处理问题
    8.uuid生成问题
    9.md5的通用问题
    10.base64的解码编码问题
    11.增加mybatis分页加强组件实现mybatis快速分页
    12.封装swagger,kaptch 基本配置 实现打入注解即可使用 详细使用看demo
    13.RequiresPermissionsDesc引入权限校验按钮 校验用户是否有该按钮的权限 详细看shiro中RequiresPermissions
    14.封装logger，slf4j错误日志打印详细使用看demo
    15.写入安全性组件：其中包括xss攻击，sql注入攻击，html注入攻击等进行过滤exception
    
### 文件夹说明
    1.plus-core 里面用来做基本的通用工具类操作，比如：字符串处理，时间格式处理等等
    2.plus-web 里面存放这用来给web前端交互的工具，方便web的编写 🔧
    3.plus-web-core web的工具类编写
    4.plus-demo 进行工具类测试的编写