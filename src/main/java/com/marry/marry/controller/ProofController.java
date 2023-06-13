package com.marry.marry.controller;

import com.alibaba.fastjson2.JSON;
import com.marry.marry.model.bo.ProofAddInputBO;
import com.marry.marry.model.bo.ProofDelInputBO;
import com.marry.marry.model.bo.ProofExistInputBO;
import com.marry.marry.model.bo.ProofSearchInputBO;
import com.marry.marry.service.ProofService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

import static com.marry.marry.utils.MyUtils.*;

@RestController
@RequestMapping("/")
public class ProofController {
    @Autowired
    private ProofService service;

    /**
     * *
     * @param man 男方地址
     * @param v 签名后两个字节
     * @param r 签名前32个字节
     * @param s 签名后32个字节
     * @param woman 女方地址
     * @param v1 签名后两个字节
     * @param r1 签名前32个字节
     * @param s1 签名后32个字节
     * @param hash_info 共同的hash前缀
     * @return String 返回数据添加情况，详情见文档
     * @throws Exception
     */
    @CrossOrigin
    @GetMapping("/add")
    public String add(@RequestParam("man") String man, @RequestParam("v") String v, @RequestParam("r") String r, @RequestParam("s") String s,
                     @RequestParam("woman") String woman, @RequestParam("v1") String v1, @RequestParam("r1") String r1, @RequestParam("s1") String s1,
                     @RequestParam("hash_info") String hash_info) throws Exception {

        // 根据字符串转义为int8
        BigInteger vb = new BigInteger(v);
        BigInteger v_1b = new BigInteger(v1);

        // 根据字符串转义为byte32
        byte[] rb = hexStringToBytes(r.substring(2));
        byte[] sb = hexStringToBytes(s.substring(2));
        byte[] hash = hexStringToBytes(hash_info.substring(2));
        byte[] r_1b = hexStringToBytes(r1.substring(2));
        byte[] s_1b = hexStringToBytes(s1.substring(2));

        // 取proof为男方地址——唯一
        byte[] proof = hexStringToBytes(man.substring(2));

        ProofAddInputBO addInputBO = new ProofAddInputBO(man,vb, rb, sb, hash,woman,v_1b, r_1b, s_1b, proof);

        BigInteger result = (BigInteger)service.add(addInputBO).getReturnObject().get(0);

        return result.toString();
    }

    /**
     * @param man 男方地址
     * @param woman 女方地址
     * @return String 放回数据查询状态，详情见文档
     * @throws Exception
     */
    @CrossOrigin
    @GetMapping("/exist")
    public String exist(@RequestParam("man") String man, @RequestParam("woman") String woman) throws Exception {
        ProofExistInputBO existInputBO = new ProofExistInputBO(man, woman);
        return service.exist(existInputBO).getReturnObject().get(0).toString();
    }

    /**
     * @param man 男方地址
     * @param v 签名后两个字节
     * @param r 签名前32个字节
     * @param s 签名后32个字节
     * @param woman 女方地址
     * @param v1 签名后两个字节
     * @param r1 签名前32个字节
     * @param s1 签名后32个字节
     * @param hash_info 共同的hash前缀
     * @return String 返回删除状态，详情见文档
     * @throws Exception
     */
    @CrossOrigin
    @GetMapping("/del")
    public String del(@RequestParam("man") String man, @RequestParam("v") String v, @RequestParam("r") String r, @RequestParam("s") String s,
                      @RequestParam("woman") String woman, @RequestParam("v1") String v1, @RequestParam("r1") String r1, @RequestParam("s1") String s1,
                      @RequestParam("hash_info") String hash_info) throws Exception {
        // 根据字符串转义为int8
        BigInteger vb = new BigInteger(v);
        BigInteger v_1b = new BigInteger(v1);

        // 根据字符串转义为byte32
        byte[] rb = hexStringToBytes(r.substring(2));
        byte[] sb = hexStringToBytes(s.substring(2));
        byte[] hash = hexStringToBytes(hash_info.substring(2));
        byte[] r_1b = hexStringToBytes(r1.substring(2));
        byte[] s_1b = hexStringToBytes(s1.substring(2));

        byte[] proofByte = hexStringToBytes((man.substring(2)));
        ProofDelInputBO delInputBO = new ProofDelInputBO(man, vb, rb, sb, hash, woman, v_1b, r_1b, s_1b, proofByte);

        return service.del(delInputBO).getReturnObject().get(0).toString();
    }

    /**
     * @param proof 存证
     * @return String 返回信息
     * @throws Exception
     */
    @GetMapping("/search")
    @CrossOrigin
    public String search(@RequestParam("proof") String proof) throws Exception{
        byte[] proofByte = hexStringToBytes(proof.substring(2));

        ProofSearchInputBO searchInputBO = new ProofSearchInputBO(proofByte);
        return JSON.toJSONString(service.search(searchInputBO).getReturnObject().get(0));
    }
}
