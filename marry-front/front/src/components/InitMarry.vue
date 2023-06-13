<script setup lang="ts">
import { ref, reactive,type Ref ,onBeforeMount} from 'vue';

let exist:Ref<boolean> = ref(false)
let address:any = reactive([])
let marryInfo = ref(false)
let info = ref(false)

/**
 * 记录mary或divorce的输入信息
 */
let man = ref("")
let v = ref("")
let r = ref("")
let s = ref("")
let woman = ref("")
let v1 = ref("")
let r1 = ref("")
let s1 = ref("")
let hash_info = ref("")

const ajax = new XMLHttpRequest()

const existNet = "http://127.0.0.1:8080/exist?"
const addNet = "http://127.0.0.1:8080/add?"
const delNet = "http://127.0.0.1:8080/del?"
const searchNet = "http://127.0.0.1:8080/search?"

/**
 * 加载页面前确认访问者是否已婚，如果填入的地址不符合要求则关闭网页
*/
onBeforeMount(() => {
    let man = window.prompt("请输入地址");
    if(!judgeBlank(man)) {
        alert("输入地址不能为空")
        window.location.href="about:blank";
		window.close();
    }
    let query = setQuery(existNet, {man, woman:man})
    ajaxSend("get", query, (res) => {
        res = JSON.parse(res)
        console.log(res)
        if (res == 2) {
            exist.value = false
        } else {
            exist.value = true
            let proofIn = prompt("您已婚，请输入凭证号以查看婚姻情况") 
            if (!judgeBlank(proofIn)) {
                alert("请输入正确的凭证")
                return 
            }
            query = setQuery(searchNet, {proof:proofIn})
            ajaxSend("get", query, (res) => {
                res = eval(res)
                if (res[0] === "0x0000000000000000000000000000000000000000") {
                    alert("地址有错误")
                } else {
                    address.push(res[0])
                    address.push(res[1])
                }
            })
        }
    })
})

/** 
*  发送get请求并执行回调函数
*   @param method 请求方式
*   @param query 请求参数
*   @param func 回调函数
*/
function ajaxSend(method, query, func) {
    ajax.open(method, query);
    ajax.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    ajax.send();
    ajax.onreadystatechange = function () {
        if (ajax.readyState == 4 && ajax.status == 200) {
            func(this.responseText)
    }
  };
}

/** 
 *  根据参数设置网络get请求的网址
 * @param  net 网络地址
 * @param query 请求参数
 * @returns String
*/
function setQuery(net, query) {
    const keys = Object.keys(query)
    const length = keys.length

    for(let i=0; i<length; i++) {
        net = net + keys[i] + "=" + query[keys[i]]

        if (i == length-1) {
            break
        }

        net += "&"
    }
    return net
}

/**
 * 判断字符串是否为空
 * @param str String
 * @returns bool
 */
function judgeBlank(str) {
    console.log(typeof str)
    if (str == null || str === undefined ||str.trim() === "") {
        return false
    } 
    return true
}

/*
*   发送并检验结婚/离婚信息
*/
function marrySend() { 
    let flag = judgeBlank(man.value) || judgeBlank(v.value)|| judgeBlank(r.value)|| judgeBlank(s.value)|| 
    judgeBlank(woman.value)|| judgeBlank(v1.value)||judgeBlank(r1.value)||judgeBlank(s1.value) || judgeBlank(hash_info.value)

    if(!flag) {
        alert("填写的信息不能够为空")
        return
    } 

    const addInfo = {man:man.value, v:v.value, r:r.value, s:s.value, woman:woman.value, v1:v1.value, r1:r1.value, s1:s1.value, hash_info:hash_info.value}
    const query = marryInfo.value? setQuery(addNet, addInfo): setQuery(delNet, addInfo)
    ajaxSend("get", query, (res) => {
        if(marryInfo.value) {
            if (res == 1) {
                alert(`凭证通过, 凭证为${man.value}`)
                address.push(man.value)
                address.push(woman.value)
                info.value = false
                marryInfo.value = false
                exist.value = true
            } else if(res == 7) {
                alert("存在一方的签名-地址检验未通过")
            } else if (res == -1) {
                alert("存在一方已婚")
            }
        } else {
            if (res == 1) {
                alert("二者并未结婚")
            } else if (res == 7) {
                alert("存在一方的签名-地址检验未通过")
            } else if (res == -1) {
                alert("解除婚姻成功")
                address.length = 0
                info.value = false
                marryInfo.value = true
                exist.value = false
            }
        }
    })
}

/**
 * 切换到离婚登记页面
 */
function divorce() {
    info.value = true
    marryInfo.value = false
}

/**
 * 切换到结婚页面
 */
function marry() {
    info.value = true
    marryInfo.value = true
}
</script>

<template>
    <div id="marry" v-if="info">
        <div id="form">
            <h1>信息登记</h1>
            男方地址：<input type="text" placeholder="请输入地址" style="width: 400px;height: 30px;" v-model="man">
            v：<input type="password" placeholder="请输入签名信息" style="width: 400px;height: 30px;" v-model="v">
            r：<input type="password" placeholder="请输入签名信息" style="width: 400px;height: 30px;" v-model="r">
            s：<input type="password" placeholder="请输入签名信息" style="width: 400px;height: 30px;" v-model="s">
            女方地址：<input type="text" placeholder="请输入地址" style="width: 400px;height: 30px;" v-model="woman">
            v：<input type="password" placeholder="请输入签名信息" style="width: 400px;height: 30px;" v-model="v1">
            r：<input type="password" placeholder="请输入签名信息" style="width: 400px;height: 30px;" v-model="r1">
            s：<input type="password" placeholder="请输入签名信息" style="width: 400px;height: 30px;" v-model="s1">
            hash:<input type="text" placeholder="请输入hash" style="width: 400px; height: 30px;" v-model="hash_info">
            <span id="apply">
                <button id="submitMarry" style="width: 100px; height: 50px;" @click="marrySend">申请</button>
            </span>
        </div>
    </div>
    <div id="container" v-else>
        <button id="marryApply" v-if="!exist" @click="marry">
            申请结婚
        </button>
        <div id="marriageDisplay" v-else>
            <p>
                男：<span class="displayAddress">{{ address[0] }}</span>
            </p>
            <p>
                女：<span class="displayAddress">{{ address[1] }}</span>
            </p>
            <button id="divorce" @click="divorce">离婚</button>
        </div>
    </div>
</template>

<style scoped>
    #marry,#container, #container > div {
        display: flex;
        align-items: center;
        justify-content: center;
        flex-direction: column;;
    }

    #container {
        width: 100%;
        height: 100%;
    }

    #marry {
        height: 100%;
        width:100%;
        background-color: rgba(185, 184, 184, 0.6);
        z-index: 10;
    }

    #form {
        display: flex;
        justify-content: center;
        flex-direction: column;
        border: 1px solid grey;
        padding: 2%;
        background-color: rgb(235, 234, 234, 1);
    }

    #apply {
        margin-top: 4%;
        text-align: center;
    }

    button {
        background-color: #87CEEB; /* Green */
        border: 1px solid rgba(60, 105, 226, 0.384);
        border-radius: 5%;
        color: white;
        padding: 15px 32px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;;
    }

    #marriageDisplay {
        width: 40%;
        height: 40%;
    }


    #marriageDisplay > p {
        width: 100%;
        height: 20%;
        display: flex;
        align-items: center;
        font-size: 1.4em;
    }

    .displayAddress {
        border:1px solid rgba(100, 99, 99, 0.2);
        border-radius: 5%;
        font-size: 2 em;
        width: 80%;
        height: 40%;
    }
</style>