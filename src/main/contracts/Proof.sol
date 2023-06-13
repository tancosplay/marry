pragma solidity ^0.6.10;

contract Proof {
    // 记录机构的地址
    address private govern;
    // 凭证结构体
    struct Proof{
        address man;
        address woman;
    }
    
    // 凭证映射
    mapping (bytes32=> Proof) public proofs;
    
    bytes32[] proofArr;
    
    constructor () public{
        govern = msg.sender;
    }
    
    /*
        如果调用者不是政府，那么返回0；
        如果凭证已存在，那么返回-1；
        其他情况下返回1
    */
    function judge_govern(address m, bytes32 proof) public returns (int8) {
        if (msg.sender != govern) {
            return 0;
        } else if (proofs[proof].man != address(0)) {
            return -1;
        }
        
        return 1;
    }
    
    
    /*
        校验签名;
        如果签名为真，则返回true;
        否则返回false；
    
    */
    function judge_sig(address man, uint8 v, bytes32 r, bytes32 s, bytes32 hash_mess) public returns (bool) {
        address spe = ecrecover(hash_mess, v, r, s);
        
        if(spe == man) {
            return true;
        } 
        
        return false;
    }
    
    
    /*
        检测是否已结婚，有一方已结婚则返回3,否则返回2
    */
    function exist(address m, address wm) public returns (int8) {
        uint256 length = proofArr.length;
        for (uint256 i=0; i<length; i++) {
            Proof memory p = proofs[proofArr[i]];
            if (p.man == m || p.woman == wm) {
                return 3;
            }
        }
        return 2;
    }
    
    /*
        添加凭证，返回7说明签名不正确
    */

    function add(address man, uint8 v, bytes32 r, bytes32 s, bytes32 hash_mess, address  woman, uint8 v1, bytes32 r1, bytes32 s1, bytes32 proof) public returns (int8) {
        int8 result = judge_govern(msg.sender, proof);
        if (result != 1) {
            return result;
        }
        
        bool sig = judge_sig(man, v, r, s, hash_mess) || judge_sig(woman, v1, r1, s1, hash_mess);
        
        if (sig) {
            proofs[proof] = Proof(man, woman);
            proofArr.push(proof);
            
            return result;
        }
        return 7;
    }
    
    /*
        删除凭证, 返回7说明签名不正确
    */
    function del(address man, uint8 v, bytes32 r, bytes32 s, bytes32 hash_mess, address  woman, uint8 v1, bytes32 r1, bytes32 s1, bytes32 proof) public returns(int8) {
        int8 result = judge_govern(msg.sender, proof);
        if (result != -1) {
            return result;
        }
        bool sig = judge_sig(man, v, r, s, hash_mess) || judge_sig(woman, v1, r1, s1, hash_mess);

        if (sig) {
            delete proofs[proof];
            return result;
        }
        
        return 7;
    }
    
    
    /*
        查询凭证信息
    */
    function search(bytes32 proof) public returns( address[] memory) {
        address[] memory addr = new address[](2);
        addr[0] = proofs[proof].man;
        addr[1] = proofs[proof].woman;
        return addr;
    }
}