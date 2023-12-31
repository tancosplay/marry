# README

## solidity

### 方法解析&返回值

#### judge_govern 检验调用者和存证

用于检验方法的调用者是否是机构，以及存证是否已使用

##### 参数

| 参数  | 意义                             |
| ----- | -------------------------------- |
| m     | 代码改动时忘记删除的遗留，无意义 |
| proof | byte32 存证                      |

##### 返回值

| 返回值 | 意义           |
| ------ | -------------- |
| 0      | 调用者并非机构 |
| -1     | 存证已经存在   |
| 1      | 检验通过       |

#### judge_sig校验签名

校验签名和输入的地址是否匹配

##### 参数

| 参数      | 意义              |
| --------- | ----------------- |
| man       | 输入的地址        |
| v         | hash签名后2个字节 |
| r         | 前32个字节        |
| s         | 后32个字节        |
| hash_mess | 加密使用的hash    |

##### 返回值

| 返回值 | 意义     |
| ------ | -------- |
| true   | 验证通过 |
| false  | 验证失败 |

#### exist检验是否已婚

检验输入的地址是否已婚（避免重婚）

##### 参数

| 参数  | 意义     |
| ----- | -------- |
| man   | 男方地址 |
| woman | 女方地址 |

##### 返回值

| 返回值 | 意义         |
| ------ | ------------ |
| 3      | 存在一方已婚 |
| 2      | 二者均为未婚 |

#### add添加凭证

在进行以上诸多检验后，机构为其添加凭证

##### 参数

| 参数      | 意义              |
| --------- | ----------------- |
| man       | 输入的男方地址    |
| v         | hash签名后2个字节 |
| r         | 前32个字节        |
| s         | 后32个字节        |
| hash_mess | 加密使用的hash    |
| woman     | 输入的女方地址    |
| v1        | hash签名后2个字节 |
| r1        | 前32个字节        |
| s1        | 后32个字节        |
| proof     | 存证              |

##### 返回值

| 返回值 | 意义                          |
| ------ | ----------------------------- |
| 0      | 调用者并非机构                |
| -1     | 存证已经存在                  |
| 1      | 添加成功                      |
| 7      | 存在一方的签名-地址检验未通过 |

#### del离婚

在进行以上诸多检验后，机构为其删除凭证

##### 参数

| 参数      | 意义              |
| --------- | ----------------- |
| man       | 输入的男方地址    |
| v         | hash签名后2个字节 |
| r         | 前32个字节        |
| s         | 后32个字节        |
| hash_mess | 加密使用的hash    |
| woman     | 输入的女方地址    |
| v1        | hash签名后2个字节 |
| r1        | 前32个字节        |
| s1        | 后32个字节        |

##### 返回值

| 返回值 | 意义                          |
| ------ | ----------------------------- |
| 0      | 调用者并非机构                |
| -1     | 删除成功                      |
| 1      | 存证不存在                    |
| 7      | 存在一方的签名-地址检验未通过 |

#### search查找结婚两方地址

根据存在寻找两方地址，如果存证不存在的话，只是返回空的数组

##### 参数

| 参数  | 意义 |
| ----- | ---- |
| proof | 存证 |

##### 返回值

| 返回值    | 意义       |
| --------- | ---------- |
| address[] | 两方的地址 |

## 后端

### MyUtils功能

**./utils/MyUtils.java**

**id**:自增以来生成proof

#### hexStringToBytes字符串转化为bytes

将前端传入的16进制字符串转化为byte[]并返回

#### bytesToHexString将bytes转化为string

将生成的proof转化为string

#### intToByteArray将int转化为bytes

主要用来生成proof，通过将id转换为16进制数

### 网络请求响应对照表

**./controller/ProofController.java**

统一使用get梭哈，前端的输入作为body参数使用

#### /add添加凭证

##### 参数

| 参数      | 意义              |
| --------- | ----------------- |
| man       | 输入的男方地址    |
| v         | hash签名后2个字节 |
| r         | 前32个字节        |
| s         | 后32个字节        |
| hash_mess | 加密使用的hash    |
| woman     | 输入的女方地址    |
| v1        | hash签名后2个字节 |
| r1        | 前32个字节        |
| s1        | 后32个字节        |

##### 返回值

| 返回值 | 意义                          |
| ------ | ----------------------------- |
| 0      | 调用者并非机构                |
| -1     | 存证已经存在                  |
| 1      | 添加成功                      |
| 7      | 存在一方的签名-地址检验未通过 |

#### /exist检验是否存在一方已婚

检验输入的地址是否已婚（避免重婚）

##### 参数

| 参数  | 意义     |
| ----- | -------- |
| man   | 男方地址 |
| woman | 女方地址 |

##### 返回值

| 返回值 | 意义         |
| ------ | ------------ |
| 3      | 存在一方已婚 |
| 2      | 二者均为未婚 |

#### /del离婚

在进行以上诸多检验后，机构为其删除凭证

##### 参数

| 参数      | 意义              |
| --------- | ----------------- |
| man       | 输入的男方地址    |
| v         | hash签名后2个字节 |
| r         | 前32个字节        |
| s         | 后32个字节        |
| hash_mess | 加密使用的hash    |
| woman     | 输入的女方地址    |
| v1        | hash签名后2个字节 |
| r1        | 前32个字节        |
| s1        | 后32个字节        |

##### 返回值

| 返回值 | 意义                          |
| ------ | ----------------------------- |
| 0      | 调用者并非机构                |
| -1     | 删除成功                      |
| 1      | 存证不存在                    |
| 7      | 存在一方的签名-地址检验未通过 |

#### /search 根据凭证查询信息

根据存在寻找两方地址，如果存证不存在的话，只是返回空的数组

##### 参数

| 参数  | 意义 |
| ----- | ---- |
| proof | 存证 |

##### 返回值

| 返回值    | 意义       |
| --------- | ---------- |
| address[] | 两方的地址 |

## 前端

### 功能函数

#### onBeforeMount登录验证

通过window的prompt获取填入的地址

- 如果地址为空或没填入，则将页面设置为空，

- 地址通过检验，向后端exist发出请求
  - 如果该地址未结婚，则将页面转换到申请结婚一页
  - 如果地址以结婚，则向后端search发出请求
    - 如果返回的地址存在0,说明程序出现问题
    - 如果返回的地址不为0,那么将页面转换到显示婚姻关系一页，并根据返回结果显示婚姻关系中双方的地址

#### marrySend处理结婚add/离婚del数据

通过input框收集数据并进行检验

- 如果存在数据为空，则留在原页面
- 组装ajax参数，并向后端发出请求
  - 如果返回值存在异常,则警告（[返回值参考后端的解释](##后端)）
  - 返回值正常，那么如果是结婚，则切换到已婚界面，如果是离婚，则切换到未婚界面，同时修改address等变量

#### divorce 转换到输入离婚信息的界面

#### marry转换到输入结婚信息的界面

### 工具函数

#### setQuery设置ajax请求地址和参数

#### ajaxSend发出请求并执行回调函数

